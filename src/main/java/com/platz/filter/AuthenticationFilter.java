package com.platz.filter;

import com.platz.dao.ContaDao;
import com.platz.model.ContaModel;
import com.platz.util.EncriptAES;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;
import javax.annotation.security.DenyAll;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import org.glassfish.jersey.internal.util.Base64;

/**
 *
 * @author Anderson
 */
@Provider
public class AuthenticationFilter implements ContainerRequestFilter {

    @Context
    private ResourceInfo resourceInfo;

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {

        //Pegar o método chamado pelo Service
        Method method = resourceInfo.getResourceMethod();

        //Se o método não apresenta a anotação @PermitAll
        if (!method.isAnnotationPresent(PermitAll.class)) {

            //Se o método apresentar a anotação @DenyAll
            if (method.isAnnotationPresent(DenyAll.class)) {
                //Abortar a requisição com um Forbidden
                requestContext.abortWith(Response.status(Response.Status.FORBIDDEN).entity("Acesso Bloqueado").build());
                return;
            }

            //Pegar os headers da request
            MultivaluedMap<String, String> headers = requestContext.getHeaders();

            //Pegar o Authorization Header
            List<String> authorization = headers.get("Authorization");

            //Se o Authorization Header for nulo ou vazio
            if (authorization == null || authorization.isEmpty()) {
                //Abortar a requisição com um Unauthorized
                requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).entity("Acesso negado").build());
                return;
            }

            //Pegar encoded email e senha
            String encodedUsuarioSenha = authorization.get(0).replaceFirst("Basic" + " ", "");

            System.out.println("encoded Email e Senha " + encodedUsuarioSenha);

            //Decodificar o email e senha
            String emailESenha = new String(Base64.decode(encodedUsuarioSenha.getBytes()));

            System.out.println("Decoded Email e Senha " + emailESenha);

            //Pegar o email e senha
            StringTokenizer tokenizer = new StringTokenizer(emailESenha, ":");
            String email = tokenizer.nextToken();
            String senha = tokenizer.nextToken();

            System.out.println("Email " + email);
            System.out.println("Senha " + senha);

            System.out.println("");

            //Se o método apresentar a anotação @RolesAllowed 
            if (method.isAnnotationPresent(RolesAllowed.class)) {
                //Pegar as Roles do método
                RolesAllowed rolesAnnotation = method.getAnnotation(RolesAllowed.class);
                Set<String> rolesSet = new HashSet<>(Arrays.asList(rolesAnnotation.value()));

                //Verificar se a conta existe
                if (!verificarPermissao(email, senha, rolesSet)) {
                    requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).entity("Acesso negado").build());
                }
            }
        }
    }

    private boolean verificarPermissao(String email, String senha, Set<String> rolesSet) {

        try {
            //Encriptar Senha recebida
            String senhaEncriptada = new EncriptAES().byteParaString(new EncriptAES().encrypt(senha, EncriptAES.getChaveEncriptacao()));

            //Buscar Conta
            ContaModel conta = new ContaDao().getConta(email, senhaEncriptada);

            //Se a conta não for nula
            if (conta != null) {
                //Verificar se o perfil existe            
                return rolesSet.contains(conta.getPerfil().getLabel());
            }
            return false;

        } catch (Exception e) {
            System.out.println("Erro de autenticação: " + e.getMessage());
            return false;
        }
    }
}
