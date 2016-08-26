package com.platz.controller;

import com.platz.dao.EventoDao;
import com.platz.model.EventoModel;

/**
 *
 * @author Anderson
 */
public class EventoController {
    
    private final EventoDao eventoDao = new EventoDao();
    
    public void cadastrar(EventoModel model) {
        eventoDao.cadastrar(model);
    }
    
}
