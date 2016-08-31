package com.platz.controller;

import com.platz.dao.EventoDao;
import com.platz.model.CategoriaModel;
import com.platz.model.EmpresaModel;
import com.platz.model.EventoModel;
import java.util.Date;
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

    public List<EventoModel> buscarPelaEmpresa(EmpresaModel model) {
        return eventoDao.buscarPelaEmpresa(model);
    }

    public List<EventoModel> buscarPelaCategoria(CategoriaModel model) {
        return eventoDao.buscarPelaCategoria(model);
    }

    public List<EventoModel> buscarCancelados() {
        return eventoDao.buscarCancelados();
    }

    public List<EventoModel> buscarCensurados() {
        return eventoDao.buscarCensurados();
    }

    public List<EventoModel> buscarDestaques() {
        return eventoDao.buscarDestaques();
    }

    public List<EventoModel> buscarNaoCancelados() {
        return eventoDao.buscarNaoCancelados();
    }

    public List<EventoModel> buscarNaoCensurados() {
        return eventoDao.buscarNaoCensurados();
    }

    public List<EventoModel> buscarSemDestaques() {
        return eventoDao.buscarSemDestaques();
    }

    public List<EventoModel> buscarNaoCanceladosENaoCensurados() {
        return eventoDao.buscarNaoCanceladosENaoCensurados();
    }

    public List<EventoModel> buscarCanceladosECensurados() {
        return eventoDao.buscarCanceladosECensurados();
    }

    public List<EventoModel> buscarPelaIdade(int idade) {
        return eventoDao.buscarPelaIdade(idade);
    }

    public List<EventoModel> buscarEventosPassados() {
        return eventoDao.buscarPeloEventosPassados();
    }

    public List<EventoModel> buscarEventosFuturos() {
        return eventoDao.buscarPeloEventosFuturos();
    }

    public List<EventoModel> buscarEventosDaSemana() {
        return eventoDao.buscarEventosDaSemana();
    }

    public List<EventoModel> buscarPeloValorMaximo(Double valor) {
        return eventoDao.buscarPeloValorMaximo(valor);
    }

    public List<EventoModel> buscarGratuitos() {
        return eventoDao.buscarGratuitos();
    }

    public void alterar(EventoModel model) {
        eventoDao.alterar(model);
    }

    public void cancelar(EventoModel model) {
        model.setCancelado(new Date());
        eventoDao.alterar(model);
    }

    public void censurar(EventoModel model) {
        model.setCensurado(new Date());
        eventoDao.alterar(model);
    }

    public void destacar(EventoModel model) {
        model.setDestaque(true);
        eventoDao.alterar(model);
    }

    public void descancelar(EventoModel model) {
        Date data = null;
        model.setCancelado(data);
        eventoDao.alterar(model);
    }

    public void descensurar(EventoModel model) {
        Date data = null;
        model.setCensurado(data);
        eventoDao.alterar(model);
    }

    public void retirarDestacar(EventoModel model) {
        model.setDestaque(false);
        eventoDao.alterar(model);
    }

}
