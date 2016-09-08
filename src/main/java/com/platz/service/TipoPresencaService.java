/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.platz.service;

import com.platz.controller.TipoPresencaController;
import com.platz.http.leitura.TipoPresencaLeitura;
import com.platz.model.TipoPresenca;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author 15153770
 */
@Path("")
public class TipoPresencaService {
     private final TipoPresencaController presencaController = new TipoPresencaController();

    @GET
    @Path(value = "/tipoPresencas")
    @Produces(value = MediaType.APPLICATION_JSON + ";charset=UTF-8")
    public Response listarTodos() {
        try {
            //Lista com todas as PresencaModel cadastradas
            List<TipoPresenca> presencaEnumList = presencaController.listarTodos();

            //Lista de Perfis de Leitura baseado na lista de perfis de enum
            List<TipoPresencaLeitura> listaDeLeitura = new TipoPresencaLeitura().converterLista(presencaEnumList);

            //Retorna a lista com um Status Code OK
            return Response.ok(listaDeLeitura).build();

        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());

            //Retorna uma BadRequest ao usuário
            return Response.status(Response.Status.BAD_REQUEST).entity("Erro ao listar tipo de presenças").build();
        }
    }
}
