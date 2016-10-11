package com.platz.service;

import com.platz.controller.CategoriaController;
import com.platz.http.cadastro.CategoriaCadastro;
import com.platz.http.edicao.CategoriaEdicao;
import com.platz.http.leitura.CategoriaLeitura;
import com.platz.model.CategoriaModel;
import com.platz.util.ImagemUtil;
import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.util.List;
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
public class CategoriaService {

    private final CategoriaController categoriaController = new CategoriaController();

    @POST
    @Path(value = "/categoria")
    @PermitAll
    @Consumes(value = MediaType.APPLICATION_JSON)
    @Produces(value = MediaType.APPLICATION_JSON + ";charset=UTF-8")
    public Response cadastrar(CategoriaCadastro categoria) {

        try {

            CategoriaModel model = new CategoriaModel(categoria);

            // Cadastrar categoria
            categoriaController.cadastrar(model);

            // Retorna a resposta para o cliente com o Status Code CREATED e a Categoria de Leitura
            return Response.status(Response.Status.CREATED).entity(new CategoriaLeitura(model)).build();

        } catch (Exception e) {
            // Envia erro pelo console
            System.out.println("Erro: " + e.getMessage());
            //Retorna uma BadRequest ao usuário
            return Response.status(Response.Status.BAD_REQUEST).entity("Erro ao cadastrar categoria").build();
        }
    }

    @PUT
    @Path(value = "/categoria/imagem/{id}")
    @PermitAll
//@PerfilAuth(Perfil.ADMINISTRADOR)
    @Consumes(value = MediaType.MULTIPART_FORM_DATA)
    @Produces(value = MediaType.APPLICATION_JSON + ";charset=UTF-8")
    public Response subirImagem(@FormDataParam("icone") InputStream iconeInputStream,
            @FormDataParam("icone") FormDataBodyPart fileMetaData,
            @PathParam("id") String id) {

        try {
            //Buscar Model pelo id
            CategoriaModel model = categoriaController.buscarPorId(id);

            if (model != null) {

                //Montando o caminho do upload
                String diretorio = new ImagemUtil().RAIZ + "categorias/";
                //Montando o nome do arquivo
                String nomeDoArquivo = model.getId() + "." + fileMetaData.getMediaType().getSubtype();

                //Verificar se já existe uma imagem cadastrado
                if (model.getCaminhoIcone() != null) {
                    if (!model.getCaminhoIcone().equals("")) {
                        new ImagemUtil().deletarArquivo(model.getCaminhoIcone());
                        System.out.println("Apagou arquivo antigo");
                    }
                }

                //Salvar Imagem
                boolean ok = new ImagemUtil().salvarArquivo(diretorio, nomeDoArquivo, iconeInputStream);

                //Se a imagem for salva sem nenhum erro atualiza a model
                if (ok) {
                    //Settar o caminho do icone na model
                    model.setCaminhoIcone(diretorio + nomeDoArquivo);
                    //model.setCaminhoIcone(new ImagemUtil().caminhoHttp("categoria/imagem/", model.getId()));
                    //Alterar
                    categoriaController.alterar(model);
                } else {
                    //Retorna uma BadRequest ao usuário
                    return Response.status(Response.Status.BAD_REQUEST).entity("Erro ao subir imagem").build();
                }
      
                return Response.ok(new CategoriaLeitura(model)).build();
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

    @GET
    @Path("/categoria/imagem/{id}")
    @PermitAll
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces("image/*")
    public Response baixarImagem(@PathParam("id") String id) {

        try {

            CategoriaModel model = categoriaController.buscarPorId(id);
                       
            if (model != null) {
                
                if (!model.getCaminhoIcone().equals("") && model.getCaminhoIcone() != null) {

                    File file = new File(model.getCaminhoIcone());
                                                                                
                    if (file.exists()) {
                        return Response.ok(file).build();
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

    @GET
    @Path(value = "/categorias")
    @PermitAll
    @Produces(value = MediaType.APPLICATION_JSON + ";charset=UTF-8")
    public Response listarTodos() {
        try {

            //Lista com todas as models, e converte para uma lista de leitura respondendo com um status code OK
            return Response.ok(new CategoriaLeitura().converterLista(categoriaController.listarTodos())).build();

        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());

            //Retorna uma BadRequest ao usuário
            return Response.status(Response.Status.BAD_REQUEST).entity("Erro ao listar categorias").build();
        }
    }

    @GET
    @Path(value = "/categoria/{id}")
    @PermitAll
    @Produces(value = MediaType.APPLICATION_JSON + ";charset=UTF-8")
    public Response buscarPeloId(@PathParam("id") String id) {
        CategoriaModel model = categoriaController.buscarPorId(id);

        //Verifica se a model retornada não é nula
        if (model != null) {
            //Retorna um Status Code OK com a categoria de leitura
            return Response.ok(new CategoriaLeitura(model)).build();
        }

        //Se a model for nula retorna um Status Code Not Found
        return Response.status(Response.Status.NOT_FOUND).entity("Categoria não encontrada").build();
    }

    @GET
    @Path(value = "/categorias/{nome}")
    @PermitAll
    @Produces(value = MediaType.APPLICATION_JSON + ";charset=UTF-8")
    public Response buscarPeloNome(@PathParam("nome") String nome) {
        try {

            //Buscar Models pelo nome
            List<CategoriaModel> models = categoriaController.buscarPeloNome(nome);

            //Retorna a lista com um Status Code OK
            return Response.ok(new CategoriaLeitura().converterLista(models)).build();

        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
            //Retorna uma BadRequest ao usuário
            return Response.status(Response.Status.BAD_REQUEST).entity("Erro ao listar categorias").build();
        }
    }

    @PUT
    @Path(value = "/categoria/{id}")
    //@PerfilAuth(Perfil.ADMINISTRADOR)
    @PermitAll
    @Consumes(value = MediaType.APPLICATION_JSON)
    @Produces(value = MediaType.APPLICATION_JSON + ";charset=UTF-8")
    public Response alterar(@PathParam("id") String id, CategoriaEdicao categoria) {

        try {
            CategoriaModel model = categoriaController.buscarPorId(id);

            //Alterar registro
            categoriaController.alterar(model, categoria);

            //Retorna Status Code OK com a entity de leitura com a modificação
            return Response.status(Response.Status.OK).entity(new CategoriaLeitura(model)).build();

        } catch (Exception e) {
            System.out.println("Erro" + e.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).entity("Erro ao alterar categoria").build();
        }
    }

    @DELETE
    @Path(value = "/categoria/{id}")
    //@PerfilAuth(Perfil.ADMINISTRADOR)
    @PermitAll
    @Produces(value = MediaType.APPLICATION_JSON + ";charset=UTF-8")
    public Response deletar(@PathParam("id") String id) {
        try {

            CategoriaModel model = categoriaController.buscarPorId(id);

            // new ImagemUtil().deletarArquivo(model.getCaminhoIcone());
            categoriaController.excluir(model);

            return Response.status(Response.Status.NO_CONTENT).build();

        } catch (Exception e) {
            System.out.println("Erro" + e.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).entity("Erro ao excluir categoria").build();
        }
    }

    @PUT
    @Path(value = "/categoria/recuperar/{id}")
    //@PerfilAuth(Perfil.ADMINISTRADOR)
    @PermitAll
    @Produces(value = MediaType.APPLICATION_JSON + ";charset=UTF-8")
    public Response recuperar(@PathParam("id") String id) {
        try {

            CategoriaModel model = categoriaController.buscarPorId(id);

            categoriaController.recuperar(model);

            return Response.ok(new CategoriaLeitura(model)).build();

        } catch (Exception e) {
            System.out.println("Erro" + e.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).entity("Erro ao alterar categoria").build();
        }
    }

    @GET
    @Path(value = "/categorias/excluidas")
    @PermitAll
    @Produces(value = MediaType.APPLICATION_JSON + ";charset=UTF-8")
    public Response listarExcluidos() {
        try {

            //Lista com todas as models, e converte para uma lista de leitura respondendo com um status code OK
            return Response.ok(new CategoriaLeitura().converterLista(categoriaController.listarExcluidos())).build();

        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());

            //Retorna uma BadRequest ao usuário
            return Response.status(Response.Status.BAD_REQUEST).entity("Erro ao listar categorias").build();
        }
    }

    @GET
    @Path(value = "/categorias/naoExcluidas")
    @PermitAll
    @Produces(value = MediaType.APPLICATION_JSON + ";charset=UTF-8")
    public Response listarNaoExcluidos() {
        try {

            //Lista com todas as models, e converte para uma lista de leitura respondendo com um status code OK
            return Response.ok(new CategoriaLeitura().converterLista(categoriaController.listarNaoExcluidos())).build();

        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());

            //Retorna uma BadRequest ao usuário
            return Response.status(Response.Status.BAD_REQUEST).entity("Erro ao listar categorias").build();
        }
    }

}
