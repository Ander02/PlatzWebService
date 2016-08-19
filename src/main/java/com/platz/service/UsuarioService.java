/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.platz.service;

import com.platz.controller.UsuarioController;
import com.platz.http.cadastro.UsuarioCadastro;
import com.platz.model.UsuarioModel;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author 15153770
 */
public class UsuarioService {

    private final UsuarioController usuarioController = new UsuarioController();

    @POST
    @Path(value = "/conta")
    @Consumes(value = MediaType.APPLICATION_JSON)
    @Produces(value = MediaType.APPLICATION_JSON + ";charset=UTF-8")
    public Response cadastrar(UsuarioCadastro usuario) {
        //Instanciar uma nova model
        UsuarioModel model = new UsuarioModel();

        return null;
    }
}
