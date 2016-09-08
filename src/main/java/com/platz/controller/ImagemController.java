package com.platz.controller;

import com.platz.dao.ImagemDao;
import com.platz.model.ImagemModel;
import java.util.List;

/**
 *
 * @author Anderson
 */
public class ImagemController {

    public final ImagemDao imagemDao = new ImagemDao();

    public void cadastrar(ImagemModel model) {
        imagemDao.cadastrar(model);
    }

    public List<ImagemModel> listarTodos() {
        return imagemDao.listarTodos(ImagemModel.class);
    }

    public ImagemModel buscarPorId(String id) {
        return imagemDao.buscarPorId(ImagemModel.class, id);
    }

    public void alterar(ImagemModel model) {
        imagemDao.alterar(model);
    }

    public void excluir(ImagemModel model) {
        imagemDao.excluir(model);
    }

}
