package com.platz.controller;

import com.platz.dao.EventoDao;
import com.platz.model.EventoModel;
import java.util.List;

/**
 *
 * @author Anderson
 */
public class EventoController {

    private final EventoDao eventoDao = new EventoDao();

    public void cadastrar(EventoModel model) {
        eventoDao.cadastrar(model);
    }

    public List<EventoModel> listarTodos() {
        return eventoDao.listarTodos(EventoModel.class);
    }

    public EventoModel buscarPeloId(String id) {
        return eventoDao.buscarPorId(EventoModel.class, id);
    }

}
