package com.platz.controller;

import com.platz.dao.CategoriaDao;
import com.platz.dao.EventoDao;
import com.platz.dao.ImagemDao;
import com.platz.http.edicao.EventoEdicao;
import com.platz.model.CategoriaModel;
import com.platz.model.EmpresaModel;
import com.platz.model.EventoModel;
import com.platz.model.ImagemModel;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Anderson
 */
public class EventoController {

    private final EventoDao eventoDao = new EventoDao();

    public void cadastrar(EventoModel model) {
        List<CategoriaModel> categorias = model.getCategorias();
        eventoDao.cadastrar(model);

        for (CategoriaModel categoria : categorias) {
            categoria.getEventos().add(model);
            new CategoriaDao().alterar(categoria);
        }
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
        return model.getEventos();
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
        return eventoDao.buscarEventosPorDia(7);
    }

    public List<EventoModel> buscarEventosPorDiaLimite(int dia) {
        return eventoDao.buscarEventosPorDia(dia);
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

    public void alterar(EventoModel model, EventoEdicao evento) {

        if (evento.getNome() != null && !evento.getNome().equals("")) {
            model.setNome(evento.getNome());
        }
        if (evento.getDetalhes() != null && !evento.getDetalhes().equals("")) {
            model.setDetalhes(evento.getDetalhes());
        }

        if (evento.getIdade() != null) {
            model.setIdade(evento.getIdade());
        }

        if (evento.getImagemCapa() != null && !evento.getImagemCapa().equals("")) {
            model.setImagemCapa(evento.getImagemCapa());
        }

        if (evento.getDataInicio() != null && !evento.getDataInicio().equals("")) {
            model.setDataInicio(evento.getDataInicio());
        }
        if (evento.getDataFim() != null && !evento.getDataFim().equals("")) {
            model.setDataFim(evento.getDataFim());
        }
        if (evento.getLotacaoMin() != null && !evento.getLotacaoMin().equals("")) {
            model.setLotacaoMin(evento.getLotacaoMin());
        }
        if (evento.getLotacaoMax() != null && !evento.getLotacaoMax().equals("")) {
            model.setLotacaoMax(evento.getLotacaoMax());
        }

        if (evento.getPreco() != null) {
            model.setPreco(evento.getPreco());
        }

        if (evento.getCategoriasId() != null && !evento.getCategoriasId().equals("")) {
            //Remove os eventos da categoria            
            for (CategoriaModel categoriasAntiga : model.getCategorias()) {
                if (categoriasAntiga.getEventos().contains(model)) {
                    categoriasAntiga.getEventos().remove(model);
                    new CategoriaDao().alterar(categoriasAntiga);
                }
            }

            //Adiciona os id de categoria no evento
            List<CategoriaModel> listaDeCategorias = new ArrayList<>();
            for (String categoriaId : evento.getCategoriasId()) {

                CategoriaModel categoria = new CategoriaDao().buscarPorId(CategoriaModel.class, categoriaId);

                listaDeCategorias.add(categoria);
            }
            model.setCategorias(listaDeCategorias);

            //Adiciona os eventos nas novas categorias            
            for (CategoriaModel categoriasNova : model.getCategorias()) {
                categoriasNova.getEventos().add(model);
                new CategoriaDao().alterar(categoriasNova);
            }

        }

        if (evento.getImagensId() != null && !evento.getCategoriasId().equals("")) {

            List<ImagemModel> listaDeImagens = new ArrayList<>();
            for (String imagemId : evento.getCategoriasId()) {

                ImagemModel imagem = new ImagemDao().buscarPorId(ImagemModel.class, imagemId);

                listaDeImagens.add(imagem);
            }
            model.setImagens(listaDeImagens);
        }

        if (evento.getDestaque() != null && !evento.getDestaque().equals("")) {
            model.setDestaque(evento.getDestaque());
        }

        eventoDao.alterar(model);
    }

    public void cancelar(EventoModel model) {
        if (model.getCancelado() == null) {
            model.setCancelado(new Date());
            eventoDao.alterar(model);
        }
    }

    public void censurar(EventoModel model) {
        if (model.getCensurado() == null) {
            model.setCensurado(new Date());
            eventoDao.alterar(model);
        }
    }

    public void destacar(EventoModel model) {
        if (!model.getDestaque()) {
            model.setDestaque(true);
            eventoDao.alterar(model);
        }
    }

    public void descancelar(EventoModel model) {
        if (model.getCancelado() != null) {
            Date data = null;
            model.setCancelado(data);
            eventoDao.alterar(model);
        }
    }

    public void descensurar(EventoModel model) {
        if (model.getCensurado() != null) {
            Date data = null;
            model.setCensurado(data);
            eventoDao.alterar(model);
        }
    }

    public void retirarDestaque(EventoModel model) {
        if (model.getDestaque()) {
            model.setDestaque(false);
            eventoDao.alterar(model);
        }
    }

}
