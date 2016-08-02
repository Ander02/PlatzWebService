package com.platz.service;

import com.platz.controller.CategoriaController;
import com.platz.http.categoria.CategoriaCadastro;
import com.platz.http.categoria.CategoriaEdicao;
import com.platz.http.categoria.CategoriaLeitura;
import com.platz.model.CategoriaModel;
import java.util.List;
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
public class CategoriaService {

    private final CategoriaController categoriaController = new CategoriaController();

    @POST
    @Path("/categoria")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
    public Response cadastrar(CategoriaCadastro categoria) {

        // Instanciar Entity
        CategoriaModel model = new CategoriaModel();

        try {
            // Settar o nome da entity baseado no nome da categoria passada
            model.setNome(categoria.getNome());

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

    @GET
    @Path("/categorias")
    @Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
    public Response listarTodos() {
        try {
            //Lista com todas as CategoriaEntity cadastradas
            List<CategoriaModel> models = categoriaController.listarTodos();

            //Lista de Categorias de Leitura baseado na lista de entidades
            List<CategoriaLeitura> listaDeCategorias = new CategoriaLeitura().converterLista(models);

            //Retorna a lista com um Status Code OK
            return Response.ok(listaDeCategorias).build();

        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
            //Retorna uma BadRequest ao usuário
            return Response.status(Response.Status.BAD_REQUEST).entity("Erro ao listar categorias").build();
        }
    }

    @GET
    @Path("/categoria/{id}")
    @Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
    public Response buscarPeloId(@PathParam("id") String id) {

        //Busca uma entidade baseado pelo id
        CategoriaModel model = categoriaController.buscarPorId(id);

        //Verifica se a entidade returnada não é nula
        if (model != null) {

            //Retorna um Status Code OK com a categoria de leitura
            return Response.ok(new CategoriaLeitura(model)).build();

        }

        //Se a entity for nula retorna um Status Code Not Found 
        return Response.status(Response.Status.NOT_FOUND).entity("Categoria não encontrada").build();
    }

    @GET
    @Path("/categorias/{nome}")
    @Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
    public Response buscarPeloNome(@PathParam("nome") String nome) {

        try {

            //Buscar Entitys pelo nome
            List<CategoriaModel> models = categoriaController.buscarPeloNome(nome);

            //Lista de Categorias de Leitura baseado na lista de entidades
            List<CategoriaLeitura> listaDeCategorias = new CategoriaLeitura().converterLista(models);

            //Retorna a lista com um Status Code OK
            return Response.ok(listaDeCategorias).build();

        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
            //Retorna uma BadRequest ao usuário
            return Response.status(Response.Status.BAD_REQUEST).entity("Erro ao listar categorias").build();
        }
    }

    @PUT
    @Path("/categoria/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
    public Response alterar(@PathParam("id") String id, CategoriaEdicao categoria) {

        CategoriaModel model = new CategoriaModel();

        try {

            //Settar informações na entity
            //model.setId(id);
            model.setNome(categoria.getNome());

            //Alterar registro
            categoriaController.alterar(model);

            //Retorna Status Code OK com a entity de leitura com a modificação
            return Response.status(Response.Status.OK).entity(new CategoriaLeitura(model)).build();

        } catch (Exception e) {
            System.out.println("Erro" + e.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).entity("Erro ao alterar categoria").build();
        }
    }

    @DELETE
    @Path("/categoria/{id}")
    @Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
    public Response deletar(@PathParam("id") String id) {

        try {

            CategoriaModel model = categoriaController.buscarPorId(id);

            categoriaController.excluir(model);

            return Response.status(Response.Status.NO_CONTENT).build();

        } catch (Exception e) {
            System.out.println("Erro" + e.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).entity("Erro ao alterar categoria").build();
        }

    }

}
