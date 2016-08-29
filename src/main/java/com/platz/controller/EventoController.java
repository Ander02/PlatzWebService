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
    
    public EventoModel buscarPorId(String id) {
        return eventoDao.buscarPorId(EventoModel.class, id);
    }
    
    public List<EventoModel> buscarPeloNome(String nome) {
        return eventoDao.buscarPeloNome(nome);
    }
    
}
