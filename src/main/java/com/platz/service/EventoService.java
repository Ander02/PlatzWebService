package com.platz.service;

import com.platz.controller.CategoriaController;
import com.platz.controller.EmpresaController;
import com.platz.controller.EventoController;
import com.platz.http.cadastro.EventoCadastro;
import com.platz.http.edicao.EventoEdicao;
import com.platz.http.edicao.ImagemEdicao;
import com.platz.http.leitura.EventoLeitura;
import com.platz.model.CategoriaModel;
import com.platz.model.EmpresaModel;
import com.platz.model.EventoModel;
import com.platz.model.ImagemModel;
import com.platz.model.Perfil;
import com.platz.util.DataUtil;
import com.platz.util.ImagemUtil;
import com.platz.util.PerfilAuth;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
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
import org.glassfish.jersey.media.multipart.FormDataBodyPart;
import org.glassfish.jersey.media.multipart.FormDataParam;

/**
 * @author Anderson
 */
@Path("")
public class EventoService {

    private final EventoController eventoController = new EventoController();

    @POST
    @Path(value = "/evento")
    @PerfilAuth(Perfil.EMPRESA)
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
            e.printStackTrace();
            //Retorna uma BadRequest ao usuário
            return Response.status(Response.Status.BAD_REQUEST).entity("Erro ao cadastrar evento").build();
        }
    }

    @GET
    @Path(value = "/eventos")
    @PerfilAuth(Perfil.ADMINISTRADOR)
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
            e.printStackTrace();
            //Retorna uma BadRequest ao usuário
            return Response.status(Response.Status.BAD_REQUEST).entity("Erro ao listar eventos").build();
        }
    }

    @GET
    @Path(value = "/evento/{id}")
    @PermitAll
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
    @PermitAll
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
    @PermitAll
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
    @PermitAll
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
    @DenyAll
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
    @Path(value = "/eventos/top/{max}")
    @PermitAll
    @Produces
    public Response buscarTopN(@PathParam("max") int max) {
        try {
            return Response.ok(eventoController.TopNEventos(max)).build();

        } catch (Exception e) {
            e.printStackTrace();
            //Retorna uma BadRequest ao usuário
            return Response.status(Response.Status.BAD_REQUEST).entity("Erro ao listar eventos").build();
        }
    }

    @GET
    @Path(value = "/eventos/censurados")
    @DenyAll
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
    @PermitAll
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
    @DenyAll
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
    @PermitAll
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
    @DenyAll
    @Produces(value = MediaType.APPLICATION_JSON + ";charset=UTF-8")
    public Response buscarSemDestaque() {
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
    @DenyAll
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
    @DenyAll
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
    @DenyAll
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
    @PermitAll
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
    @DenyAll
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
    @DenyAll
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
    @DenyAll
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
    @PermitAll
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
    @PermitAll
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

    @GET
    @Path("/evento/imagemCapa/{id}")
    @PermitAll
    @Produces("image/*")
    public Response baixarImagemCapa(@PathParam("id") String id) {

        try {

            EventoModel model = eventoController.buscarPorId(id);

            if (model != null) {
                if (!model.getImagemCapa().equals("") && model.getImagemCapa() != null) {
                    InputStream input = new ImagemUtil().baixarImagem(model.getImagemCapa());

                    if (input != null) {
                        return Response.ok(input).header("Content-Type", "image/png").build();
                    }
                }
                return Response.status(Response.Status.BAD_REQUEST).entity("Erro ao baixar imagem, imagem inexistente").build();
            }
            return Response.status(Response.Status.BAD_REQUEST).entity("Erro ao baixar imagem, categoria não encontrada").build();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).entity("Erro ao baixar imagem").build();
        }
    }

    @PUT
    @Path(value = "/evento/{id}")
    @PerfilAuth(Perfil.EMPRESA)
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
    @PerfilAuth(Perfil.EMPRESA)
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
    @PerfilAuth(Perfil.ADMINISTRADOR)
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
    @PerfilAuth(Perfil.EMPRESA)
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
    @PerfilAuth(Perfil.EMPRESA)
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
    @PerfilAuth(Perfil.ADMINISTRADOR)
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
    @PerfilAuth({Perfil.ADMINISTRADOR, Perfil.EMPRESA})
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

    @PUT
    @Path(value = "/evento/imagem/{id}")
    @PerfilAuth(Perfil.EMPRESA)
    @Consumes(value = MediaType.MULTIPART_FORM_DATA)
    @Produces(value = MediaType.APPLICATION_JSON + ";charset=UTF-8")
    public Response subirImagemCapa(@FormDataParam("imagemCapa") InputStream imagemCapaInputStream,
            @FormDataParam("imagemCapa") FormDataBodyPart fileMetaData,
            @PathParam("id") String id) {

        try {
            //Buscar Model pelo id
            EventoModel model = eventoController.buscarPorId(id);

            if (model != null) {

                //Montando o caminho do upload
                String diretorio = "eventos/" + model.getId() + "/";
                //Montando o nome do arquivo
                String nomeDoArquivo = "0." + fileMetaData.getMediaType().getSubtype();

                //Verificar se já existe uma imagem cadastrado
                if (model.getImagemCapa() != null && !model.getImagemCapa().equals("")) {

                    boolean ok = new ImagemUtil().deletarArquivo(model.getImagemCapa());

                    if (ok) {
                        System.out.println("Apagou arquivo antigo");
                    } else {
                        System.out.println("Não Apagou o arquivo antigo");
                        return Response.status(Response.Status.BAD_REQUEST).entity("Erro ao subir imagem").build();
                    }
                }

                //Salvar Imagem
                boolean ok = new ImagemUtil().salvarArquivo(diretorio, nomeDoArquivo, imagemCapaInputStream);

                //Se a imagem for salva sem nenhum erro atualiza a model
                if (ok) {
                    //Settar o caminho do icone na model
                    model.setImagemCapa(ImagemUtil.URL_FTP + diretorio + nomeDoArquivo);
                    //Alterar
                    eventoController.alterar(model);
                } else {
                    //Retorna uma BadRequest ao usuário
                    return Response.status(Response.Status.BAD_REQUEST).entity("Erro ao subir imagem").build();
                }

                return Response.ok(new EventoLeitura(model)).build();
            }

            System.out.println("Id não existente");
            return Response.status(Response.Status.BAD_REQUEST).entity("Erro ao subir imagem").build();

        } catch (Exception e) {
            // Envia erro pelo console
            System.out.println("Erro de upload: " + e.getMessage());
            //Retorna uma BadRequest ao usuário
            return Response.status(Response.Status.BAD_REQUEST).entity("Erro ao subir imagem").build();
        }
    }

    @PUT
    @Path(value = "/evento/imagens/{id}")
    @PerfilAuth(Perfil.EMPRESA)
    @Consumes(value = MediaType.MULTIPART_FORM_DATA)
    @Produces(value = MediaType.APPLICATION_JSON + ";charset=UTF-8")
    public Response subirImagemGaleria(@FormDataParam("imagemGaleria") InputStream imagemInputStream,
            @FormDataParam("imagemGaleria") FormDataBodyPart fileMetaData,
            @PathParam("id") String id) {

        try {

            //Buscar Model pelo id
            EventoModel model = eventoController.buscarPorId(id);

            if (model != null) {

                //Montando o caminho do upload
                String diretorio = "eventos/" + model.getId() + "/";

                System.out.println(diretorio);
                //Montando o nome do arquivo
                String nomeDoArquivo = new DataUtil().dataSemPontuacao(new Date()) + "." + fileMetaData.getMediaType().getSubtype();

                //Salvar Imagem
                boolean ok = new ImagemUtil().salvarArquivo(diretorio, nomeDoArquivo, imagemInputStream);

                //Se a imagem for salva sem nenhum erro atualiza a model
                if (ok) {
                    //Settar o caminho do icone na model
                    model.getImagens().add(new ImagemModel(ImagemUtil.URL_FTP + diretorio + nomeDoArquivo));
                    System.out.println("Adicionou a imagem");
                    //Alterar
                    eventoController.alterar(model);
                } else {
                    System.out.println("Não adicionou a imagem");
                    //Retorna uma BadRequest ao usuário
                    return Response.status(Response.Status.BAD_REQUEST).entity("Erro ao subir imagem").build();
                }

                return Response.ok(new EventoLeitura(model)).build();

            }

            System.out.println("Id não existente");
            return Response.status(Response.Status.BAD_REQUEST).entity("Erro ao subir imagem").build();

        } catch (Exception e) {
            // Envia erro pelo console
            System.out.println("Erro de upload: " + e.getMessage());
            //Retorna uma BadRequest ao usuário
            return Response.status(Response.Status.BAD_REQUEST).entity("Erro ao subir imagem").build();
        }
    }

    @DELETE
    @Path(value = "/evento/imagens/{id}")
    @PerfilAuth(Perfil.EMPRESA)
    @Consumes(value = MediaType.APPLICATION_JSON)
    @Produces(value = MediaType.APPLICATION_JSON + ";charset=UTF-8")
    public Response apagarImagemGaleria(@PathParam("id") String id, ImagemEdicao imagem) {

        try {

            EventoModel model = eventoController.buscarPorId(id);

            List<ImagemModel> listaDeImagensAtualizadas = new ArrayList<>();
            for (ImagemModel img : model.getImagens()) {

                if (img.getUrl().equals(imagem.getUrl())) {
                    img.setDeletado(new Date());
                }

                listaDeImagensAtualizadas.add(img);
            }

            model.setImagens(listaDeImagensAtualizadas);

            eventoController.alterar(model);

            return Response.ok(new EventoLeitura(model)).build();
        } catch (Exception e) {
            //Envia erro pelo console
            System.out.println("Erro de upload: " + e.getMessage());
            //Retorna uma BadRequest ao usuário
            return Response.status(Response.Status.BAD_REQUEST).entity("Erro ao subir imagem").build();
        }
    }
}
