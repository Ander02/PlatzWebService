package com.platz.service;

import com.platz.controller.AssuntoController;
import com.platz.controller.MensagemController;
import com.platz.http.cadastro.MensagemCadastro;
import com.platz.http.leitura.MensagemLeitura;
import com.platz.model.AssuntoModel;
import com.platz.model.MensagemModel;
import com.platz.model.Perfil;
import com.platz.util.EmailUtil;
import com.platz.util.PerfilAuth;
import java.util.List;
import javax.annotation.security.DenyAll;
import javax.annotation.security.PermitAll;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
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
public class MensagemService {

    private final MensagemController mensagemController = new MensagemController();

    @POST
    @Path(value = "/mensagem")
    @PermitAll
    @Consumes(value = MediaType.APPLICATION_JSON)
    @Produces(value = MediaType.APPLICATION_JSON + ";charset=UTF-8")
    public Response cadastrar(MensagemCadastro mensagem) {
        try {
            MensagemModel model = new MensagemModel(mensagem);

            mensagemController.cadastrar(model);

            // Retorna a resposta para o cliente com o Status Code CREATED e a Mensagem de Leitura
            return Response.status(Response.Status.CREATED).entity(new MensagemLeitura(model)).build();

        } catch (Exception e) {

            //Envia erro pelo console
            System.out.println("Erro: " + e.getMessage());
            //Retorna uma BadRequest ao usuário
            return Response.status(Response.Status.BAD_REQUEST).entity("Erro ao cadastrar mensagem").build();
        }
    }

    @POST
    @Path(value = "/mensagem/{id}")
    @PerfilAuth(Perfil.ADMINISTRADOR)
    @Produces(value = MediaType.APPLICATION_JSON + ";charset=UTF-8")
    public Response responderMensagem(String resposta, @PathParam("id") String id) {

        try {

            MensagemModel model = mensagemController.buscarPorId(id);

            new EmailUtil().enviarEmailComHtml(model.getEmail(), "Resposta: " + model.getAssunto().getNome(), model.getConteudo(), resposta);

            return Response.ok().build();

        } catch (Exception e) {
            //Envia erro pelo console
            System.out.println("Erro: " + e.getMessage());
            //Retorna uma BadRequest ao usuário
            return Response.status(Response.Status.BAD_REQUEST).entity("Erro ao cadastrar mensagem").build();
        }
    }

    @GET
    @Path(value = "/mensagens")
    @PerfilAuth(Perfil.ADMINISTRADOR)
    @Produces(value = MediaType.APPLICATION_JSON + ";charset=UTF-8")
    public Response listarTodos() {

        try {
            //Lista com todas as Models cadastradas
            List<MensagemModel> models = mensagemController.listarTodos();
            //Converter a lista de models para uma lista de leitura
            List<MensagemLeitura> listaDeMensagens = new MensagemLeitura().converterLista(models);
            //Retorna a lista com um Status Code OK
            return Response.ok(listaDeMensagens).build();

        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
            //Retorna uma BadRequest ao usuário
            return Response.status(Response.Status.BAD_REQUEST).entity("Erro ao listar contas").build();
        }
    }

    @GET
    @Path(value = "/mensagem/{id}")
    @PerfilAuth(Perfil.ADMINISTRADOR)
    @Produces(value = MediaType.APPLICATION_JSON + ";charset=UTF-8")
    public Response buscarPeloId(@PathParam("id") String id) {
        MensagemModel model = mensagemController.buscarPorId(id);

        //Verifica se a model retornada não é nula
        if (model != null) {

            //Retorna um Status Code OK com a conta de leitura
            return Response.ok(new MensagemLeitura(model)).build();

        }

        //Se a model for nula retorna um Status Code Not Found
        return Response.status(Response.Status.NOT_FOUND).entity("Conta não encontrada").build();
    }

    @GET
    @Path(value = "/mensagens/{email}")
    @DenyAll
    @Produces(value = MediaType.APPLICATION_JSON + ";charset=UTF-8")
    public Response buscarPeloEmail(@PathParam("email") String email) {

        try {
            //Lista com todas as Models cadastradas
            List<MensagemModel> models = mensagemController.buscarPeloEmail(email);
            //Converter a lista de models para uma lista de leitura
            List<MensagemLeitura> listaDeMensagens = new MensagemLeitura().converterLista(models);
            //Retorna a lista com um Status Code OK
            return Response.ok(listaDeMensagens).build();

        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
            //Retorna uma BadRequest ao usuário
            return Response.status(Response.Status.BAD_REQUEST).entity("Erro ao listar mensagens").build();
        }
    }

    @GET
    @Path(value = "/mensagens/marcadas")
    @PerfilAuth(Perfil.ADMINISTRADOR)
    @Produces(value = MediaType.APPLICATION_JSON + ";charset=UTF-8")
    public Response buscarMarcadas() {

        try {
            //Lista com todas as Models cadastradas
            List<MensagemModel> models = mensagemController.buscarMarcadas();
            //Converter a lista de models para uma lista de leitura
            List<MensagemLeitura> listaDeMensagens = new MensagemLeitura().converterLista(models);
            //Retorna a lista com um Status Code OK
            return Response.ok(listaDeMensagens).build();

        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
            //Retorna uma BadRequest ao usuário
            return Response.status(Response.Status.BAD_REQUEST).entity("Erro ao listar mensagens").build();
        }
    }

    @GET
    @Path(value = "/mensagens/marcadasNaoExcluidas")
    @PerfilAuth(Perfil.ADMINISTRADOR)
    @Produces(value = MediaType.APPLICATION_JSON + ";charset=UTF-8")
    public Response buscarMarcadasNaoExcluidas() {

        try {
            //Lista com todas as Models cadastradas
            List<MensagemModel> models = mensagemController.buscarMarcadasNaoExcluidas();
            //Converter a lista de models para uma lista de leitura
            List<MensagemLeitura> listaDeMensagens = new MensagemLeitura().converterLista(models);
            //Retorna a lista com um Status Code OK
            return Response.ok(listaDeMensagens).build();

        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
            //Retorna uma BadRequest ao usuário
            return Response.status(Response.Status.BAD_REQUEST).entity("Erro ao listar mensagens").build();
        }
    }

    @GET
    @Path(value = "/mensagens/excluidas")
    @PerfilAuth(Perfil.ADMINISTRADOR)
    @Produces(value = MediaType.APPLICATION_JSON + ";charset=UTF-8")
    public Response buscarExcluidas() {

        try {
            //Lista com todas as Models cadastradas
            List<MensagemModel> models = mensagemController.buscarExluidas();
            //Converter a lista de models para uma lista de leitura
            List<MensagemLeitura> listaDeMensagens = new MensagemLeitura().converterLista(models);
            //Retorna a lista com um Status Code OK
            return Response.ok(listaDeMensagens).build();

        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
            //Retorna uma BadRequest ao usuário
            return Response.status(Response.Status.BAD_REQUEST).entity("Erro ao listar mensagens").build();
        }
    }

    @GET
    @Path(value = "/mensagens/naoExcluidas")
    @PerfilAuth(Perfil.ADMINISTRADOR)
    @Produces(value = MediaType.APPLICATION_JSON + ";charset=UTF-8")
    public Response buscarNaoExcluidas() {

        try {
            //Lista com todas as Models cadastradas
            List<MensagemModel> models = mensagemController.buscarNaoExluidas();
            //Converter a lista de models para uma lista de leitura
            List<MensagemLeitura> listaDeMensagens = new MensagemLeitura().converterLista(models);
            //Retorna a lista com um Status Code OK
            return Response.ok(listaDeMensagens).build();

        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
            //Retorna uma BadRequest ao usuário
            return Response.status(Response.Status.BAD_REQUEST).entity("Erro ao listar mensagens").build();
        }
    }

    @GET
    @Path(value = "/mensagens/lidas")
    @PerfilAuth(Perfil.ADMINISTRADOR)
    @Produces(value = MediaType.APPLICATION_JSON + ";charset=UTF-8")
    public Response buscarLidas() {

        try {
            //Lista com todas as Models cadastradas
            List<MensagemModel> models = mensagemController.buscarLidas();
            //Converter a lista de models para uma lista de leitura
            List<MensagemLeitura> listaDeMensagens = new MensagemLeitura().converterLista(models);
            //Retorna a lista com um Status Code OK
            return Response.ok(listaDeMensagens).build();

        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
            //Retorna uma BadRequest ao usuário
            return Response.status(Response.Status.BAD_REQUEST).entity("Erro ao listar mensagens").build();
        }
    }

    @GET
    @Path(value = "/mensagens/naoLidas")
    @PerfilAuth(Perfil.ADMINISTRADOR)
    @Produces(value = MediaType.APPLICATION_JSON + ";charset=UTF-8")
    public Response buscarNaoLidas() {

        try {
            //Lista com todas as Models cadastradas
            List<MensagemModel> models = mensagemController.buscarNaoLidas();
            //Converter a lista de models para uma lista de leitura
            List<MensagemLeitura> listaDeMensagens = new MensagemLeitura().converterLista(models);
            //Retorna a lista com um Status Code OK
            return Response.ok(listaDeMensagens).build();

        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
            //Retorna uma BadRequest ao usuário
            return Response.status(Response.Status.BAD_REQUEST).entity("Erro ao listar mensagens").build();
        }
    }

    @GET
    @Path(value = "/mensagens/lidasNaoExcluidas")
    @PerfilAuth(Perfil.ADMINISTRADOR)
    @Produces(value = MediaType.APPLICATION_JSON + ";charset=UTF-8")
    public Response buscarLidasNaoExcluidas() {

        try {
            //Lista com todas as Models cadastradas
            List<MensagemModel> models = mensagemController.buscarLidasNaoExcluidas();
            //Converter a lista de models para uma lista de leitura
            List<MensagemLeitura> listaDeMensagens = new MensagemLeitura().converterLista(models);
            //Retorna a lista com um Status Code OK
            return Response.ok(listaDeMensagens).build();

        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
            //Retorna uma BadRequest ao usuário
            return Response.status(Response.Status.BAD_REQUEST).entity("Erro ao listar mensagens").build();
        }
    }

    @GET
    @Path(value = "/mensagens/naoLidasNaoExcluidas")
    @PerfilAuth(Perfil.ADMINISTRADOR)
    @Produces(value = MediaType.APPLICATION_JSON + ";charset=UTF-8")
    public Response buscarNaoLidasNaoExcluidas() {

        try {
            //Lista com todas as Models cadastradas
            List<MensagemModel> models = mensagemController.buscarNaoLidasNaoExcluidas();
            //Converter a lista de models para uma lista de leitura
            List<MensagemLeitura> listaDeMensagens = new MensagemLeitura().converterLista(models);
            //Retorna a lista com um Status Code OK
            return Response.ok(listaDeMensagens).build();

        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
            //Retorna uma BadRequest ao usuário
            return Response.status(Response.Status.BAD_REQUEST).entity("Erro ao listar mensagens").build();
        }
    }

    @GET
    @Path(value = "/mensagens/assunto/{id}")
    @DenyAll
    @Produces(value = MediaType.APPLICATION_JSON + ";charset=UTF-8")
    public Response buscarPeloAssunto(@PathParam("id") String id) {

        try {
            AssuntoModel assunto = new AssuntoController().buscarPorId(id);

            //Lista com todas as Models cadastradas
            List<MensagemModel> models = mensagemController.buscarPeloAssunto(assunto);
            //Converter a lista de models para uma lista de leitura
            List<MensagemLeitura> listaDeMensagens = new MensagemLeitura().converterLista(models);
            //Retorna a lista com um Status Code OK
            return Response.ok(listaDeMensagens).build();

        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
            //Retorna uma BadRequest ao usuário
            return Response.status(Response.Status.BAD_REQUEST).entity("Erro ao listar mensagens").build();
        }
    }

    @PUT
    @Path(value = "/mensagem/visualizar/{id}")
    @PerfilAuth(Perfil.ADMINISTRADOR)
    @Produces(value = MediaType.APPLICATION_JSON + ";charset=UTF-8")
    public Response visualizar(@PathParam("id") String id) {

        try {
            MensagemModel model = mensagemController.buscarPorId(id);

            mensagemController.visualizar(model);

            return Response.ok(new MensagemLeitura(model)).build();

        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
            //Retorna uma BadRequest ao usuário
            return Response.status(Response.Status.BAD_REQUEST).entity("Erro ao alterar mensagem").build();
        }
    }

    @PUT
    @Path(value = "/mensagem/cancelarVisualizacao/{id}")
    @PerfilAuth(Perfil.ADMINISTRADOR)
    @Produces(value = MediaType.APPLICATION_JSON + ";charset=UTF-8")
    public Response cancelarVisualizacao(@PathParam("id") String id) {

        try {
            MensagemModel model = mensagemController.buscarPorId(id);

            mensagemController.cancelarVisualizacao(model);

            return Response.ok(new MensagemLeitura(model)).build();

        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
            //Retorna uma BadRequest ao usuário
            return Response.status(Response.Status.BAD_REQUEST).entity("Erro ao alterar mensagem").build();
        }
    }

    @PUT
    @Path(value = "/mensagem/marcar/{id}")
    @PerfilAuth(Perfil.ADMINISTRADOR)
    @Produces(value = MediaType.APPLICATION_JSON + ";charset=UTF-8")
    public Response marcar(@PathParam("id") String id) {

        try {
            MensagemModel model = mensagemController.buscarPorId(id);

            mensagemController.marcar(model);

            return Response.ok(new MensagemLeitura(model)).build();

        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
            //Retorna uma BadRequest ao usuário
            return Response.status(Response.Status.BAD_REQUEST).entity("Erro ao listar mensagens").build();
        }
    }

    @PUT
    @Path(value = "/mensagem/desmarcar/{id}")
    @PerfilAuth(Perfil.ADMINISTRADOR)
    @Produces(value = MediaType.APPLICATION_JSON + ";charset=UTF-8")
    public Response desmarcar(@PathParam("id") String id) {

        try {
            MensagemModel model = mensagemController.buscarPorId(id);

            mensagemController.desmarcar(model);

            return Response.ok(new MensagemLeitura(model)).build();

        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
            //Retorna uma BadRequest ao usuário
            return Response.status(Response.Status.BAD_REQUEST).entity("Erro ao listar mensagens").build();
        }
    }

    @PUT
    @Path(value = "/mensagem/excluir/{id}")
    @PerfilAuth(Perfil.ADMINISTRADOR)
    @Produces(value = MediaType.APPLICATION_JSON + ";charset=UTF-8")
    public Response marcarExcluida(@PathParam("id") String id) {

        try {
            MensagemModel model = mensagemController.buscarPorId(id);

            mensagemController.marcarExcluida(model);

            return Response.ok(new MensagemLeitura(model)).build();

        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
            //Retorna uma BadRequest ao usuário
            return Response.status(Response.Status.BAD_REQUEST).entity("Erro ao listar mensagens").build();
        }
    }

    @PUT
    @Path(value = "/mensagem/restaurar/{id}")
    @PerfilAuth(Perfil.ADMINISTRADOR)
    @Produces(value = MediaType.APPLICATION_JSON + ";charset=UTF-8")
    public Response restaurar(@PathParam("id") String id) {

        try {
            MensagemModel model = mensagemController.buscarPorId(id);

            mensagemController.restaurar(model);

            return Response.ok(new MensagemLeitura(model)).build();

        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
            //Retorna uma BadRequest ao usuário
            return Response.status(Response.Status.BAD_REQUEST).entity("Erro ao listar mensagens").build();
        }
    }

    @DELETE
    @Path(value = "/mensagem/{id}")
    @PerfilAuth(Perfil.ADMINISTRADOR)
    @Produces(value = MediaType.APPLICATION_JSON + ";charset=UTF-8")
    public Response deletar(@PathParam("id") String id) {
        try {

            MensagemModel model = mensagemController.buscarPorId(id);

            mensagemController.excluir(model);

            return Response.status(Response.Status.NO_CONTENT).build();

        } catch (Exception e) {

            System.out.println("Erro: " + e.getMessage());
            //Retorna uma BadRequest ao usuário
            return Response.status(Response.Status.BAD_REQUEST).entity("Erro ao excluir mensagens").build();
        }
    }

}
