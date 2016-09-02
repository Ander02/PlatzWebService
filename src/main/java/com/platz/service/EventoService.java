package com.platz.service;

import com.platz.controller.CategoriaController;
import com.platz.controller.EmpresaController;
import com.platz.controller.EventoController;
import com.platz.http.cadastro.EventoCadastro;
import com.platz.http.edicao.EventoEdicao;
import com.platz.http.leitura.EventoLeitura;
import com.platz.model.CategoriaModel;
import com.platz.model.EmpresaModel;
import com.platz.model.EventoModel;
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
public class EventoService {

    private final EventoController eventoController = new EventoController();

    @POST
    @Path(value = "/evento")
    @Consumes(value = MediaType.APPLICATION_JSON)
    @Produces(value = MediaType.APPLICATION_JSON + ";charset=UTF-8")
    public Response cadastrar(EventoCadastro evento) {

        try {
            //Settar informações na model baseado na Conta de Cadastro passada
            EventoModel model = new EventoModel(evento);

            eventoController.cadastrar(model);

            // Retorna a resposta para o cliente com o Status Code CREATED e a Conta de Leitura
            return Response.status(Response.Status.CREATED).entity(new EventoLeitura(model)).build();

        } catch (Exception e) {

            // Envia erro pelo console
            System.out.println("Erro: " + e.getMessage());
            //Retorna uma BadRequest ao usuário
            return Response.status(Response.Status.BAD_REQUEST).entity("Erro ao cadastrar evento").build();
        }
    }

    @GET
    @Path(value = "/eventos")
    @Produces(value = MediaType.APPLICATION_JSON + ";charset=UTF-8")
    public Response listarTodos() {
        try {
            //Lista com todas as AssuntoEntity cadastradas
            List<EventoModel> models = eventoController.listarTodos();

            //Lista de Assuntos de Leitura baseado na lista de models
            List<EventoLeitura> listaDeLeitura = new EventoLeitura().converterLista(models);

            //Retorna a lista com um Status Code OK
            return Response.ok(listaDeLeitura).build();

        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
            //Retorna uma BadRequest ao usuário
            return Response.status(Response.Status.BAD_REQUEST).entity("Erro ao listar eventos").build();
        }
    }

    @GET
    @Path(value = "/evento/{id}")
    @Produces(value = MediaType.APPLICATION_JSON + ";charset=UTF-8")
    public Response buscarPeloId(@PathParam("id") String id) {
        EventoModel model = eventoController.buscarPorId(id);

        //Verifica se a model retornada não é nula
        if (model != null) {

            //Retorna um Status Code OK com a conta de leitura
            return Response.ok(new EventoLeitura(model)).build();

        }

        //Se a model for nula retorna um Status Code Not Found
        return Response.status(Response.Status.NOT_FOUND).entity("Evento não encontrada").build();
    }

    @GET
    @Path(value = "/eventos/{nome}")
    @Produces(value = MediaType.APPLICATION_JSON + ";charset=UTF-8")
    public Response buscarPeloNome(@PathParam("nome") String nome) {
        try {

            //Buscar Models pelo nome
            List<EventoModel> models = eventoController.buscarPeloNome(nome);

            //Lista de Assuntos de Leitura baseado na lista de entidades
            List<EventoLeitura> listaDeLeitura = new EventoLeitura().converterLista(models);

            //Retorna a lista com um Status Code OK
            return Response.ok(listaDeLeitura).build();

        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
            //Retorna uma BadRequest ao usuário
            return Response.status(Response.Status.BAD_REQUEST).entity("Erro ao listar eventos").build();
        }
    }

    @GET
    @Path(value = "/eventos/empresa/{id}")
    @Produces(value = MediaType.APPLICATION_JSON + ";charset=UTF-8")
    public Response buscarPelaEmpresa(@PathParam("id") String id) {
        try {
            EmpresaModel empresa = new EmpresaController().buscarPorId(id);

            List<EventoModel> models = eventoController.buscarPelaEmpresa(empresa);

            List<EventoLeitura> listaDeLeitura = new EventoLeitura().converterLista(models);

            return Response.ok(listaDeLeitura).build();

        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
            //Retorna uma BadRequest ao usuário
            return Response.status(Response.Status.BAD_REQUEST).entity("Erro ao listar eventos").build();
        }
    }

    @GET
    @Path(value = "/eventos/categoria/{id}")
    @Produces(value = MediaType.APPLICATION_JSON + ";charset=UTF-8")
    public Response buscarPelaCategoria(@PathParam("id") String id) {
        try {
            CategoriaModel categoria = new CategoriaController().buscarPorId(id);

            List<EventoModel> models = eventoController.buscarPelaCategoria(categoria);

            List<EventoLeitura> listaDeLeitura = new EventoLeitura().converterLista(models);

            return Response.ok(listaDeLeitura).build();

        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
            //Retorna uma BadRequest ao usuário
            return Response.status(Response.Status.BAD_REQUEST).entity("Erro ao listar eventos").build();
        }
    }

    @GET
    @Path(value = "/eventos/cancelados")
    @Produces(value = MediaType.APPLICATION_JSON + ";charset=UTF-8")
    public Response buscarCancelados() {
        try {
            List<EventoModel> models = eventoController.buscarCancelados();

            List<EventoLeitura> listaDeLeitura = new EventoLeitura().converterLista(models);

            return Response.ok(listaDeLeitura).build();

        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
            //Retorna uma BadRequest ao usuário
            return Response.status(Response.Status.BAD_REQUEST).entity("Erro ao listar eventos").build();
        }
    }

    @GET
    @Path(value = "/eventos/censurados")
    @Produces(value = MediaType.APPLICATION_JSON + ";charset=UTF-8")
    public Response buscarCensurados() {
        try {
            List<EventoModel> models = eventoController.buscarCensurados();

            List<EventoLeitura> listaDeLeitura = new EventoLeitura().converterLista(models);

            return Response.ok(listaDeLeitura).build();

        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
            //Retorna uma BadRequest ao usuário
            return Response.status(Response.Status.BAD_REQUEST).entity("Erro ao listar eventos").build();
        }
    }

    @GET
    @Path(value = "/eventos/destacados")
    @Produces(value = MediaType.APPLICATION_JSON + ";charset=UTF-8")
    public Response buscarDestacados() {
        try {
            List<EventoModel> models = eventoController.buscarDestaques();

            List<EventoLeitura> listaDeLeitura = new EventoLeitura().converterLista(models);

            return Response.ok(listaDeLeitura).build();

        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
            //Retorna uma BadRequest ao usuário
            return Response.status(Response.Status.BAD_REQUEST).entity("Erro ao listar eventos").build();
        }
    }

    @GET
    @Path(value = "/eventos/naoCancelados")
    @Produces(value = MediaType.APPLICATION_JSON + ";charset=UTF-8")
    public Response buscarNaoCancelados() {
        try {
            List<EventoModel> models = eventoController.buscarNaoCancelados();

            List<EventoLeitura> listaDeLeitura = new EventoLeitura().converterLista(models);

            return Response.ok(listaDeLeitura).build();

        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
            //Retorna uma BadRequest ao usuário
            return Response.status(Response.Status.BAD_REQUEST).entity("Erro ao listar eventos").build();
        }
    }

    @GET
    @Path(value = "/eventos/naoCensurados")
    @Produces(value = MediaType.APPLICATION_JSON + ";charset=UTF-8")
    public Response buscarNaoensurados() {
        try {
            List<EventoModel> models = eventoController.buscarNaoCensurados();

            List<EventoLeitura> listaDeLeitura = new EventoLeitura().converterLista(models);

            return Response.ok(listaDeLeitura).build();

        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
            //Retorna uma BadRequest ao usuário
            return Response.status(Response.Status.BAD_REQUEST).entity("Erro ao listar eventos").build();
        }
    }

    @GET
    @Path(value = "/eventos/semDestaque")
    @Produces(value = MediaType.APPLICATION_JSON + ";charset=UTF-8")
    public Response buscarSemDestacados() {
        try {
            List<EventoModel> models = eventoController.buscarSemDestaques();

            List<EventoLeitura> listaDeLeitura = new EventoLeitura().converterLista(models);

            return Response.ok(listaDeLeitura).build();

        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
            //Retorna uma BadRequest ao usuário
            return Response.status(Response.Status.BAD_REQUEST).entity("Erro ao listar eventos").build();
        }
    }

    @GET
    @Path(value = "/eventos/canceladosECensurados")
    @Produces(value = MediaType.APPLICATION_JSON + ";charset=UTF-8")
    public Response buscarCanceladosECensurado() {
        try {
            List<EventoModel> models = eventoController.buscarCanceladosECensurados();

            List<EventoLeitura> listaDeLeitura = new EventoLeitura().converterLista(models);

            return Response.ok(listaDeLeitura).build();

        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
            //Retorna uma BadRequest ao usuário
            return Response.status(Response.Status.BAD_REQUEST).entity("Erro ao listar eventos").build();
        }
    }

    @GET
    @Path(value = "/eventos/naoCanceladosESemCensura")
    @Produces(value = MediaType.APPLICATION_JSON + ";charset=UTF-8")
    public Response buscarNaoCanceladosESemCensura() {
        try {
            List<EventoModel> models = eventoController.buscarNaoCanceladosENaoCensurados();

            List<EventoLeitura> listaDeLeitura = new EventoLeitura().converterLista(models);

            return Response.ok(listaDeLeitura).build();

        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
            //Retorna uma BadRequest ao usuário
            return Response.status(Response.Status.BAD_REQUEST).entity("Erro ao listar eventos").build();
        }
    }

    @GET
    @Path(value = "/eventos/idade/{idade}")
    @Produces(value = MediaType.APPLICATION_JSON + ";charset=UTF-8")
    public Response buscarPelaIdade(@PathParam("idade") int idade) {
        try {
            List<EventoModel> models = eventoController.buscarPelaIdade(idade);

            List<EventoLeitura> listaDeLeitura = new EventoLeitura().converterLista(models);

            return Response.ok(listaDeLeitura).build();

        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
            //Retorna uma BadRequest ao usuário
            return Response.status(Response.Status.BAD_REQUEST).entity("Erro ao listar eventos").build();
        }
    }

    @GET
    @Path(value = "/eventos/gratuitos")
    @Produces(value = MediaType.APPLICATION_JSON + ";charset=UTF-8")
    public Response buscarGratuitos() {
        try {
            List<EventoModel> models = eventoController.buscarGratuitos();

            List<EventoLeitura> listaDeLeitura = new EventoLeitura().converterLista(models);

            return Response.ok(listaDeLeitura).build();

        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
            //Retorna uma BadRequest ao usuário
            return Response.status(Response.Status.BAD_REQUEST).entity("Erro ao listar eventos").build();
        }
    }

    @GET
    @Path(value = "/eventos/preco/{preco}")
    @Produces(value = MediaType.APPLICATION_JSON + ";charset=UTF-8")
    public Response buscarPeloValorMaximo(@PathParam("preco") double preco) {
        try {
            List<EventoModel> models = eventoController.buscarPeloValorMaximo(preco);

            List<EventoLeitura> listaDeLeitura = new EventoLeitura().converterLista(models);

            return Response.ok(listaDeLeitura).build();

        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
            //Retorna uma BadRequest ao usuário
            return Response.status(Response.Status.BAD_REQUEST).entity("Erro ao listar eventos").build();
        }
    }

    @GET
    @Path(value = "/eventos/passados")
    @Produces(value = MediaType.APPLICATION_JSON + ";charset=UTF-8")
    public Response buscarEventosPassados() {
        try {
            List<EventoModel> models = eventoController.buscarEventosPassados();

            List<EventoLeitura> listaDeLeitura = new EventoLeitura().converterLista(models);

            return Response.ok(listaDeLeitura).build();

        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
            //Retorna uma BadRequest ao usuário
            return Response.status(Response.Status.BAD_REQUEST).entity("Erro ao listar eventos").build();
        }
    }

    @GET
    @Path(value = "/eventos/futuros")
    @Produces(value = MediaType.APPLICATION_JSON + ";charset=UTF-8")
    public Response buscarEventosFuturos() {
        try {
            List<EventoModel> models = eventoController.buscarEventosFuturos();

            List<EventoLeitura> listaDeLeitura = new EventoLeitura().converterLista(models);

            return Response.ok(listaDeLeitura).build();

        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
            //Retorna uma BadRequest ao usuário
            return Response.status(Response.Status.BAD_REQUEST).entity("Erro ao listar eventos").build();
        }
    }

    @GET
    @Path(value = "/eventos/semana")
    @Produces(value = MediaType.APPLICATION_JSON + ";charset=UTF-8")
    public Response buscarEventosDaSemana() {
        try {
            List<EventoModel> models = eventoController.buscarEventosDaSemana();

            List<EventoLeitura> listaDeLeitura = new EventoLeitura().converterLista(models);

            return Response.ok(listaDeLeitura).build();

        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
            //Retorna uma BadRequest ao usuário
            return Response.status(Response.Status.BAD_REQUEST).entity("Erro ao listar eventos").build();
        }
    }

    @GET
    @Path(value = "/eventos/dia/{dia}")
    @Produces(value = MediaType.APPLICATION_JSON + ";charset=UTF-8")
    public Response buscarEventosPorDiaLimite(@PathParam("dia") int dia) {
        try {
            List<EventoModel> models = eventoController.buscarEventosPorDiaLimite(dia);

            List<EventoLeitura> listaDeLeitura = new EventoLeitura().converterLista(models);

            return Response.ok(listaDeLeitura).build();

        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
            //Retorna uma BadRequest ao usuário
            return Response.status(Response.Status.BAD_REQUEST).entity("Erro ao listar eventos").build();
        }
    }

    @PUT
    @Path(value = "/evento/{id}")
    @Produces(value = MediaType.APPLICATION_JSON + ";charset=UTF-8")
    public Response alterar(@PathParam("id") String id, EventoEdicao evento) {
        try {
            EventoModel model = eventoController.buscarPorId(id);

            //Alterar registro
            eventoController.alterar(model, evento);

            //Retorna Status Code OK com a model de leitura com a modificação
            return Response.status(Response.Status.OK).entity(new EventoLeitura(model)).build();

        } catch (Exception e) {
            System.out.println("Erro" + e.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).entity("Erro ao alterar evento").build();
        }
    }

    @PUT
    @Path(value = "/evento/cancelar/{id}")
    @Produces(value = MediaType.APPLICATION_JSON + ";charset=UTF-8")
    public Response cancelar(@PathParam("id") String id) {

        try {
            EventoModel model = eventoController.buscarPorId(id);

            //Alterar registro
            eventoController.cancelar(model);

            //Retorna Status Code OK com a model de leitura com a modificação
            return Response.status(Response.Status.OK).entity(new EventoLeitura(model)).build();

        } catch (Exception e) {
            System.out.println("Erro" + e.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).entity("Erro ao cancelar evento").build();
        }

    }

    @PUT
    @Path(value = "/evento/censurar/{id}")
    @Produces(value = MediaType.APPLICATION_JSON + ";charset=UTF-8")
    public Response censurar(@PathParam("id") String id) {

        try {
            EventoModel model = eventoController.buscarPorId(id);

            //Alterar registro
            eventoController.censurar(model);

            //Retorna Status Code OK com a model de leitura com a modificação
            return Response.status(Response.Status.OK).entity(new EventoLeitura(model)).build();

        } catch (Exception e) {
            System.out.println("Erro" + e.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).entity("Erro ao censurar evento").build();
        }
    }

    @PUT
    @Path(value = "/evento/destacar/{id}")
    @Produces(value = MediaType.APPLICATION_JSON + ";charset=UTF-8")
    public Response destacar(@PathParam("id") String id) {

        try {
            EventoModel model = eventoController.buscarPorId(id);

            //Alterar registro
            eventoController.destacar(model);

            //Retorna Status Code OK com a model de leitura com a modificação
            return Response.status(Response.Status.OK).entity(new EventoLeitura(model)).build();

        } catch (Exception e) {
            System.out.println("Erro" + e.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).entity("Erro ao censurar evento").build();
        }
    }

    @PUT
    @Path(value = "/evento/descancelar/{id}")
    @Produces(value = MediaType.APPLICATION_JSON + ";charset=UTF-8")
    public Response descancelar(@PathParam("id") String id) {

        try {
            EventoModel model = eventoController.buscarPorId(id);

            //Alterar registro
            eventoController.descancelar(model);

            //Retorna Status Code OK com a model de leitura com a modificação
            return Response.status(Response.Status.OK).entity(new EventoLeitura(model)).build();

        } catch (Exception e) {
            System.out.println("Erro" + e.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).entity("Erro ao descancelar evento").build();
        }
    }
    
     @PUT
    @Path(value = "/evento/descensurar/{id}")
    @Produces(value = MediaType.APPLICATION_JSON + ";charset=UTF-8")
    public Response descensurar(@PathParam("id") String id) {

        try {
            EventoModel model = eventoController.buscarPorId(id);

            //Alterar registro
            eventoController.descensurar(model);

            //Retorna Status Code OK com a model de leitura com a modificação
            return Response.status(Response.Status.OK).entity(new EventoLeitura(model)).build();

        } catch (Exception e) {
            System.out.println("Erro" + e.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).entity("Erro ao descancelar evento").build();
        }
    }

    @PUT
    @Path(value = "/evento/retirarDestaque/{id}")
    @Produces(value = MediaType.APPLICATION_JSON + ";charset=UTF-8")
    public Response retirarDestaque(@PathParam("id") String id) {

        try {
            EventoModel model = eventoController.buscarPorId(id);

            //Alterar registro
            eventoController.retirarDestaque(model);

            //Retorna Status Code OK com a model de leitura com a modificação
            return Response.status(Response.Status.OK).entity(new EventoLeitura(model)).build();

        } catch (Exception e) {
            System.out.println("Erro" + e.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).entity("Erro ao retirar destaque evento").build();
        }
    }
}
