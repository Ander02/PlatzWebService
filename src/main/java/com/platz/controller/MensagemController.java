package com.platz.controller;

import com.platz.dao.MensagemDao;
import com.platz.model.AssuntoModel;
import com.platz.model.MensagemModel;
import java.util.List;

/**
 *
 * @author Anderson
 */
public class MensagemController {

    private final MensagemDao mensagemDao = new MensagemDao();

    public void cadastrar(MensagemModel model) {
        mensagemDao.cadastrar(model);
    }

    public List<MensagemModel> listarTodos() {
        return mensagemDao.listarTodos(MensagemModel.class);
    }

    public MensagemModel buscarPorId(String id) {
        return mensagemDao.buscarPorId(MensagemModel.class, id);
    }

    public void alterar(MensagemModel model) {
        mensagemDao.alterar(model);
    }

    public void excluir(MensagemModel model) {
        mensagemDao.excluir(model);
    }

    public List<MensagemModel> buscarPeloEmail(String email) {
        return mensagemDao.buscarPeloEmail(email);
    }

    public List<MensagemModel> buscarPeloAssunto(AssuntoModel assunto) {
        return mensagemDao.buscarPeloAssunto(assunto);
    }

    public List<MensagemModel> buscarMarcadas() {
        return mensagemDao.buscarMarcadas();
    }
}
