package com.platz.controller;

import com.platz.dao.MensagemDao;
import com.platz.model.AssuntoModel;
import com.platz.model.MensagemModel;
import java.util.Date;
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

    public List<MensagemModel> buscarPeloEmail(String email) {
        return mensagemDao.buscarPeloEmail(email);
    }

    public List<MensagemModel> buscarPeloAssunto(AssuntoModel assunto) {
        return mensagemDao.buscarPeloAssunto(assunto);
    }

    public List<MensagemModel> buscarMarcadas() {
        return mensagemDao.buscarMarcadas();
    }

    public List<MensagemModel> buscarExluidas() {
        return mensagemDao.buscarExcluidas();
    }

    public void marcar(MensagemModel model) {
        model.setMarcado(true);
        mensagemDao.alterar(model);
    }

    public void desmarcar(MensagemModel model) {
        model.setMarcado(false);
        mensagemDao.alterar(model);
    }

    //Marcar como apagada
    public void deletar(MensagemModel model) {
        model.setDeletado(new Date());
        mensagemDao.alterar(model);
    }

    //Recupera a mensagem sinalizada como apagada
    public void recuperar(MensagemModel model) {
        model.setDeletado(null);
        mensagemDao.alterar(model);
    }

    public void alterar(MensagemModel model) {
        mensagemDao.alterar(model);
    }

    //Apagar do Banco
    public void excluir(MensagemModel model) {
        mensagemDao.excluir(model);
    }

}
