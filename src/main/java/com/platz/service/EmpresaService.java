package com.platz.service;

import com.platz.controller.ContaController;
import com.platz.controller.EmpresaController;
import com.platz.http.cadastro.EmpresaCadastro;
import com.platz.http.edicao.EmpresaEdicao;
import com.platz.http.leitura.EmpresaLeitura;
import com.platz.model.ContaModel;
import com.platz.model.EmpresaModel;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
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

        try {
            //Instanciar uma nova model, passando o http de cadastro
           EmpresaModel model = new EmpresaModel(empresa);

            empresaController.cadastrar(model);

            // Retorna a resposta para o cliente com o Status Code CREATED e a Mensagem de Leitura
            return Response.status(Response.Status.CREATED).entity(new EmpresaLeitura(model)).build();

        } catch (Exception e) {

            //Envia erro pelo console
            System.out.println("Erro: " + e.getMessage());
            //Retorna uma BadRequest ao usuário
            return Response.status(Response.Status.BAD_REQUEST).entity("Erro ao cadastrar empresa").build();
        }
    }

    @GET
    @Path(value = "/empresas")
    @Produces(value = MediaType.APPLICATION_JSON + ";charset=UTF-8")
    public Response listarTodos() {

       // try {
            //Lista com todas as Models cadastradas
            List<EmpresaModel> models = empresaController.listarTodos();
            //Converter a lista de models para uma lista de leitura
            List<EmpresaLeitura> listaDeEmpresas = new EmpresaLeitura().converterLista(models);
            //Retorna a lista com um Status Code OK
            return Response.ok(listaDeEmpresas).build();

    /*    } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
            //Retorna uma BadRequest ao usuário
            return Response.status(Response.Status.BAD_REQUEST).entity("Erro ao listar empresas").build();
        }*/
    }

    @GET
    @Path(value = "/empresa/{id}")
    @Produces(value = MediaType.APPLICATION_JSON + ";charset=UTF-8")
    public Response buscarPeloId(@PathParam("id") String id) {
        EmpresaModel model = empresaController.buscarPorId(id);

        //Verifica se a model retornada não é nula
        if (model != null) {

            //Retorna um Status Code OK com a conta de leitura
            return Response.ok(new EmpresaLeitura(model)).build();

        }

        //Se a model for nula retorna um Status Code Not Found
        return Response.status(Response.Status.NOT_FOUND).entity("Conta não encontrada").build();
    }

    @GET
    @Path(value = "/empresa/cnpj/{cnpj}")
    @Produces(value = MediaType.APPLICATION_JSON + ";charset=UTF-8")
    public Response buscarPeloCNPJ(@PathParam("cnpj") String cnpj) {
        EmpresaModel model = empresaController.buscarPeloCNPJ(cnpj);

        //Verifica se a model retornada não é nula
        if (model != null) {

            //Retorna um Status Code OK com a conta de leitura
            return Response.ok(new EmpresaLeitura(model)).build();

        }

        //Se a model for nula retorna um Status Code Not Found
        return Response.status(Response.Status.NOT_FOUND).entity("Conta não encontrada").build();
    }

    @GET
    @Path(value = "/empresa/conta/{id}")
    @Produces(value = MediaType.APPLICATION_JSON + ";charset=UTF-8")
    public Response buscarPelaConta(@PathParam("id") String id) {
        
        ContaModel conta = new ContaController().buscarPorId(id);
        
        EmpresaModel model = empresaController.buscarPelaConta(conta);

        //Verifica se a model retornada não é nula
        if (model != null) {

            //Retorna um Status Code OK com a conta de leitura
            return Response.ok(new EmpresaLeitura(model)).build();

        }

        //Se a model for nula retorna um Status Code Not Found
        return Response.status(Response.Status.NOT_FOUND).entity("Conta não encontrada").build();
    }

    @GET
    @Path(value = "/empresas/{nome}")
    @Produces(value = MediaType.APPLICATION_JSON + ";charset=UTF-8")
    public Response buscarPeloNome(@PathParam("nome") String nome) {

        try {
            //Lista com todas as Models cadastradas
            List<EmpresaModel> models = empresaController.buscarPeloNome(nome);
            //Converter a lista de models para uma lista de leitura
            List<EmpresaLeitura> listaDeEmpresas = new EmpresaLeitura().converterLista(models);
            //Retorna a lista com um Status Code OK
            return Response.ok(listaDeEmpresas).build();

        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
            //Retorna uma BadRequest ao usuário
            return Response.status(Response.Status.BAD_REQUEST).entity("Erro ao listar empresas").build();
        }
    }
    
    @PUT
    @Path(value = "/empresa/{id}")
    @Consumes(value = MediaType.APPLICATION_JSON)
    @Produces(value = MediaType.APPLICATION_JSON + ";charset=UTF-8")
    public Response alterar(@PathParam("id") String id, EmpresaEdicao empresa) {

       // try {

            //Settar informações na model
            EmpresaModel model = empresaController.buscarPorId(id);
            
            //Alterar registro
            empresaController.alterar(model, empresa);

            //Retorna Status Code OK com a entity de leitura com a modificação
            return Response.status(Response.Status.OK).entity(new EmpresaLeitura(model)).build();

      /*  } catch (Exception e) {
            System.out.println("Erro" + e.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).entity("Erro ao alterar empresa").build();
        }*/
    }

}