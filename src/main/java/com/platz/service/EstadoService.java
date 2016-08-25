package com.platz.service;

import com.platz.controller.EstadoController;
import com.platz.http.cadastro.EstadoCadastro;
import com.platz.http.leitura.CidadeLeitura;
import com.platz.http.leitura.EstadoLeitura;
import com.platz.model.CidadeModel;
import com.platz.model.EstadoModel;
import java.util.List;
import javax.ws.rs.Consumes;
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
public class EstadoService {
    
    private final EstadoController estadoController = new EstadoController();
    
    @POST
    @Path(value = "/estado")
    @Consumes(value = MediaType.APPLICATION_JSON)
    @Produces(value = MediaType.APPLICATION_JSON + ";charset=UTF-8")
    public Response cadastrar(EstadoCadastro estado) {
        
        try {
            //Settar informações na model baseado na Conta de Cadastro passada
            EstadoModel model = new EstadoModel(estado);
            
            estadoController.cadastrar(model);

            // Retorna a resposta para o cliente com o Status Code CREATED e a Conta de Leitura
            return Response.status(Response.Status.CREATED).entity(new EstadoLeitura(model)).build();
            
        } catch (Exception e) {

            // Envia erro pelo console
            System.out.println("Erro: " + e.getMessage());
            //Retorna uma BadRequest ao usuário
            return Response.status(Response.Status.BAD_REQUEST).entity("Erro ao cadastrar estado").build();
        }
    }
    
    @GET
    @Path(value = "/estados")
    @Produces(value = MediaType.APPLICATION_JSON + ";charset=UTF-8")
    public Response listarTodos() {
        try {
            //Lista com todas as AssuntoEntity cadastradas
            List<EstadoModel> models = estadoController.listarTodos();

            //Lista de Assuntos de Leitura baseado na lista de models
            List<EstadoLeitura> listaDeLeitura = new EstadoLeitura().converterLista(models);

            //Retorna a lista com um Status Code OK
            return Response.ok(listaDeLeitura).build();
            
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
            //Retorna uma BadRequest ao usuário
            return Response.status(Response.Status.BAD_REQUEST).entity("Erro ao listar estados").build();
        }
    }
    
    @GET
    @Path(value = "/estado/{id}")
    @Produces(value = MediaType.APPLICATION_JSON + ";charset=UTF-8")
    public Response buscarPeloId(@PathParam("id") String id) {
        EstadoModel model = estadoController.buscarPorId(id);

        //Verifica se a model retornada não é nula
        if (model != null) {

            //Retorna um Status Code OK com a conta de leitura
            return Response.ok(new EstadoLeitura(model)).build();
            
        }

        //Se a model for nula retorna um Status Code Not Found
        return Response.status(Response.Status.NOT_FOUND).entity("Cidade não encontrada").build();
    }
    
    @GET
    @Path(value = "/estado/uf/{uf}")
    @Produces(value = MediaType.APPLICATION_JSON + ";charset=UTF-8")
    public Response buscarPelaUf(@PathParam("uf") String uf) {
        
        try {
            EstadoModel model = estadoController.buscarPelaUf(uf);

            //Verifica se a model retornada não é nula
            if (model != null) {

                //Retorna um Status Code OK com a conta de leitura
                return Response.ok(new EstadoLeitura(model)).build();
                
            }

            //Se a model for nula retorna um Status Code Not Found
            return Response.status(Response.Status.NOT_FOUND).entity("Estado não encontrado").build();
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
            //Retorna uma BadRequest ao usuário
            return Response.status(Response.Status.BAD_REQUEST).entity("Erro ao buscar estados").build();
        }
    }
    
    @GET
    @Path(value = "/estados/{nome}")
    @Produces(value = MediaType.APPLICATION_JSON + ";charset=UTF-8")
    public Response buscarPeloNome(@PathParam("nome") String nome) {
        
        try {
            //Lista com todas as AssuntoEntity cadastradas
            List<EstadoModel> models = estadoController.buscarPeloNome(nome);

            //Lista de Assuntos de Leitura baseado na lista de models
            List<EstadoLeitura> listaDeLeitura = new EstadoLeitura().converterLista(models);

            //Retorna a lista com um Status Code OK
            return Response.ok(listaDeLeitura).build();
            
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
            //Retorna uma BadRequest ao usuário
            return Response.status(Response.Status.BAD_REQUEST).entity("Erro ao listar estados").build();
        }
    }
    
}
