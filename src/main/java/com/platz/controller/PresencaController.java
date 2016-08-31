package com.platz.controller;

import com.platz.dao.PresencaDao;
import com.platz.http.edicao.PresencaEdicao;
import com.platz.model.ContaModel;
import com.platz.model.EventoModel;
import com.platz.model.PresencaModel;
import com.platz.model.TipoPresenca;
import java.util.List;

/**
 *
 * @author 15153770
 */
public class PresencaController {

    private final PresencaDao presencaDao = new PresencaDao();

    public void cadastrar(PresencaModel model) {
        presencaDao.cadastrar(model);
    }

    public List<PresencaModel> listarTodos() {
        return presencaDao.listarTodos(PresencaModel.class);
    }

    public PresencaModel buscarPorId(String id) {
        return presencaDao.buscarPorId(PresencaModel.class, id);
    }

    public void alterar(PresencaModel model, PresencaEdicao presenca) {
        if (presenca.getTipoPresenca() != null) {
            switch (presenca.getTipoPresenca()) {
                case 0:
                    model.setTipoPresenca(TipoPresenca.SIM);
                    break;
                case 1:
                    model.setTipoPresenca(TipoPresenca.TALVEZ);
                    break;
                case 2:
                    model.setTipoPresenca(TipoPresenca.NAO);
                    break;
                default:
                    break;
            }
        }
    }

    public void excluir(PresencaModel model) {
        presencaDao.excluir(model);
    }

    public List<PresencaModel> buscarPeloEvento(EventoModel evento) {
        return presencaDao.buscarPorEvento(evento);
    }

    public List<PresencaModel> buscarPeloConta(ContaModel conta) {
        return presencaDao.buscarPorConta(conta);
    }
}
