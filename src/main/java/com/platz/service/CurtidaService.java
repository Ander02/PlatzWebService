package com.platz.service;

import com.platz.controller.CurtidaController;
import com.platz.controller.EventoController;
import com.platz.controller.UsuarioController;
import com.platz.http.cadastro.CurtidaCadastro;
import com.platz.http.leitura.CurtidaLeitura;
import com.platz.model.CurtidaModel;
import com.platz.model.Perfil;
import com.platz.util.PerfilAuth;
import java.util.List;
import javax.annotation.security.PermitAll;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
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
public class CurtidaService {

    private final CurtidaController curtidaController = new CurtidaController();

    @POST
    @Path(value = "/curtir")
    @PerfilAuth(Perfil.USUARIO)
    @Consumes(value = MediaType.APPLICATION_JSON)
    @Produces(value = MediaType.APPLICATION_JSON + ";charset=UTF-8")
    public Response curtir(CurtidaCadastro curtida) {

        CurtidaModel model = new CurtidaModel(curtida);
        try {
            // Cadastrar assunto
            curtidaController.curtir(model);

            // Retorna a resposta para o cliente com o Status Code CREATED e o Assunto de Leitura
            return Response.status(Response.Status.CREATED).entity(new CurtidaLeitura(model)).build();

        } catch (Exception e) {
            // Envia erro pelo console
            System.out.println("Erro: " + e.getMessage());
            //Retorna uma BadRequest ao usuário
            return Response.status(Response.Status.BAD_REQUEST).entity("Erro ao curtir evento").build();
        }
    }

    @GET
    @Path(value = "/curtidas")
    @PermitAll
    @Produces(value = MediaType.APPLICATION_JSON + ";charset=UTF-8")
    public Response listarTodos() {
        try {
            //Lista com todas as AssuntoEntity cadastradas
            List<CurtidaModel> models = curtidaController.listarTodos();

            //Lista de Leitura baseado na lista de models
            List<CurtidaLeitura> listaDeLeitura = new CurtidaLeitura().converterLista(models);

            //Retorna a lista com um Status Code OK
            return Response.ok(listaDeLeitura).build();

        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
            //Retorna uma BadRequest ao usuário
            return Response.status(Response.Status.BAD_REQUEST).entity("Erro ao listar curtidas").build();
        }
    }

    @GET
    @Path(value = "/curtida/{id}")
    @PermitAll
    @Produces(value = MediaType.APPLICATION_JSON + ";charset=UTF-8")
    public Response buscarPeloId(@PathParam("id") String id) {
        CurtidaModel model = curtidaController.buscarPorId(id);

        //Verifica se a entidade retornada não é nula
        if (model != null) {

            //Retorna um Status Code OK com o Assunto de leitura
            return Response.ok(new CurtidaLeitura(model)).build();

        }

        //Se a entity for nula retorna um Status Code Not Found
        return Response.status(Response.Status.NOT_FOUND).entity("curtida não encontrada").build();
    }

    @GET
    @Path(value = "/curtidas/evento/{id}")
    @PermitAll
    @Produces(value = MediaType.APPLICATION_JSON + ";charset=UTF-8")
    public Response buscarPeloEvento(@PathParam("id") String id) {
        try {
            List<CurtidaModel> models = curtidaController.buscarPeloEvento(new EventoController().buscarPorId(id));

            //Lista de Leitura baseado na lista de models
            List<CurtidaLeitura> listaDeLeitura = new CurtidaLeitura().converterLista(models);

            //Retorna a lista com um Status Code OK
            return Response.ok(listaDeLeitura).build();
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
            //Retorna uma BadRequest ao usuário
            return Response.status(Response.Status.BAD_REQUEST).entity("Erro ao listar avaliações").build();
        }

    }

    @GET
    @Path(value = "/curtidas/usuario/{id}")
    @PermitAll
    @Produces(value = MediaType.APPLICATION_JSON + ";charset=UTF-8")
    public Response buscarPeloUsuario(@PathParam("id") String id) {
        try {
            List<CurtidaModel> models = curtidaController.buscarPeloUsuario(new UsuarioController().buscarPorId(id));

            //Lista de Leitura baseado na lista de models
            List<CurtidaLeitura> listaDeLeitura = new CurtidaLeitura().converterLista(models);

            //Retorna a lista com um Status Code OK
            return Response.ok(listaDeLeitura).build();
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
            //Retorna uma BadRequest ao usuário
            return Response.status(Response.Status.BAD_REQUEST).entity("Erro ao listar avaliações").build();
        }
    }

    @DELETE
    @Path("/descurtir/{id}")
    @PerfilAuth(Perfil.USUARIO)
    @Produces(value = MediaType.APPLICATION_JSON + ";charset=UTF-8")
    public Response descurtir(@PathParam("id") String id) {

        try {

            CurtidaModel model = curtidaController.buscarPorId(id);

            curtidaController.descurtir(model);

            return Response.status(Response.Status.NO_CONTENT).build();
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
            //Retorna uma BadRequest ao usuário
            return Response.status(Response.Status.BAD_REQUEST).entity("Erro ao listar avaliações").build();
        }

    }

}
