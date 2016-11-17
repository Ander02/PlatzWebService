package com.platz.controller;

import com.platz.dao.CurtidaDao;
import com.platz.model.CurtidaModel;
import com.platz.model.EventoModel;
import com.platz.model.UsuarioModel;
import java.util.List;

/**
 *
 * @author Anderson
 */
public class CurtidaController {

    private final CurtidaDao curtidosDao = new CurtidaDao();

    public void curtir(CurtidaModel model) {        
        curtidosDao.cadastrar(model);
    }

    public void descurtir(CurtidaModel model) {
        curtidosDao.excluir(model);
    }

    public List<CurtidaModel> listarTodos() {
        return curtidosDao.listarTodos(CurtidaModel.class);
    }

    public CurtidaModel buscarPorId(String id) {
        return curtidosDao.buscarPorId(CurtidaModel.class, id);
    }

    public List<CurtidaModel> buscarPeloEvento(EventoModel evento) {
        return curtidosDao.buscarPorEvento(evento);
    }

    public List<CurtidaModel> buscarPeloUsuario(UsuarioModel usuario) {
        return curtidosDao.buscarPorUsuario(usuario);
    }

    public CurtidaModel buscarPorEventoEUsuario(UsuarioModel usuario, EventoModel evento) {
        return curtidosDao.buscarPorEventoEUsuario(usuario, evento);             
    }

}
