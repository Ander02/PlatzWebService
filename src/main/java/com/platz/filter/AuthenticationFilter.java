package com.platz.filter;

import com.platz.controller.ContaController;
import com.platz.dao.ContaDao;
import com.platz.model.ContaModel;
import com.platz.model.Perfil;
import com.platz.util.DataUtil;
import com.platz.util.PerfilAuth;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.security.DenyAll;
import javax.annotation.security.PermitAll;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

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
            List<String> authorizations = headers.get(HttpHeaders.AUTHORIZATION);
            //Se o Authorization Header for nulo ou vazio
            if (authorizations == null || authorizations.isEmpty()) {
                //Abortar a requisição com um Unauthorized
                requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).entity("Acesso negado").build());
                return;
            }

            //Pegar o token
            String token = authorizations.get(0).replaceFirst("Bearer ", "");

            System.out.println("Token " + token);

            System.out.println("");

            //Se o método apresentar a anotação @RolesAllowed 
            if (method.isAnnotationPresent(PerfilAuth.class)) {

                //Pegar as Roles do método
                PerfilAuth perfilAnnotation = method.getAnnotation(PerfilAuth.class);
                Set<Perfil> perfilSet = new HashSet<>(Arrays.asList(perfilAnnotation.value()));

                //Verificar se a conta existe
                if (!verificarPermissao(token, perfilSet)) {
                    requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).entity("Acesso negado").build());
                }
            }
        }
    }

    private boolean verificarPermissao(String token, Set<Perfil> perfilSet) {

        try {
            //Buscar Conta pelo token
            ContaModel conta = new ContaDao().getConta(token);

            //Se a conta não for nula
            if (conta != null) {
                //Verificar se o perfil existe

                if (new Date().before(new DataUtil().adicionaDias(1, new DataUtil().converterData(conta.getUltimoAcesso())))) {
                    return perfilSet.contains(conta.getPerfil());
                } else {
                    new ContaController().logoff(conta);
                }
            } else {
                ContaModel contaAndroid = new ContaDao().getContaPorTokenAndroid(token);

                //Se a conta não for nula
                if (contaAndroid != null) {
                    //Verificar se o perfil existe

                    if (new Date().before(new DataUtil().adicionaDias(1, new DataUtil().converterData(contaAndroid.getUltimoAcesso())))) {
                        return perfilSet.contains(contaAndroid.getPerfil());
                    } else {
                        new ContaController().logoff(contaAndroid);
                    }

                }
                return false;
            }
            return false;
        } catch (Exception e) {
            System.out.println("Erro de autenticação: " + e.getMessage());
            return false;
        }
    }
}
