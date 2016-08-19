package com.platz.service;

import com.platz.controller.EmpresaController;
import com.platz.http.cadastro.EmpresaCadastro;
import com.platz.http.leitura.EmpresaLeitura;
import com.platz.model.ContaModel;
import com.platz.model.EmpresaModel;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author Anderson
 */
@Path("")
public class EmpresaService {

    private final EmpresaController empresaController = new EmpresaController();

    @POST
    @Path(value = "/empresa")
    @Consumes(value = MediaType.APPLICATION_JSON)
    @Produces(value = MediaType.APPLICATION_JSON + ";charset=UTF-8")
    public Response cadastrar(EmpresaCadastro empresa) {
        //Instanciar uma nova model
        EmpresaModel model = new EmpresaModel();

       // try {
            //Settar informações na model baseado na Empresa de Cadastro passada
            model.setCnpj(empresa.getCnpj());
            model.setNomeFantasia(empresa.getNomeFantasia());
            model.setRazaoSocial(empresa.getRazaoSocial());
            model.setTelefone(empresa.getTelefone());
            model.setTelefone2(empresa.getTelefone2());
            model.setImagemPerfil(empresa.getImagemPerfil());
            
            model.setConta(new ContaModel(empresa.getConta()));
            
//            model.getConta().setEmail(empresa.getConta().getEmail());
//            model.getConta().setSenha(empresa.getConta().getSenha());
//            model.getConta().setPerfil(empresa.getConta().getPerfil());

            empresaController.cadastrar(model);

            // Retorna a resposta para o cliente com o Status Code CREATED e a Mensagem de Leitura
            return Response.status(Response.Status.CREATED).entity(new EmpresaLeitura(model)).build();

//        } catch (Exception e) {
//
//            //Envia erro pelo console
//            System.out.println("Erro: " + e.getMessage());
//            //Retorna uma BadRequest ao usuário
//            return Response.status(Response.Status.BAD_REQUEST).entity("Erro ao cadastrar mensagem").build();
//        }
    }

}
