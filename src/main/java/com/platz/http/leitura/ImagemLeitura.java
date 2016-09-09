package com.platz.http.leitura;

import com.platz.model.ImagemModel;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author 15153770
 */
@XmlRootElement
public class ImagemLeitura {

    
    private String url;
    private String deletado;
    private String dataCadastro;

    public ImagemLeitura() {
    }

    public ImagemLeitura(ImagemModel model) {
        setUrl(model.getUrl());       
        setDeletado(model.getDeletado());
        setDataCadastro(model.getDataCadastro());
    }

    public ImagemLeitura(String url, String deletado, String dataCadastro) {
        
        setUrl(url);
        setDeletado(deletado);
        setDataCadastro(dataCadastro);
    }

    //Métodos
    public List<ImagemLeitura> converterLista(List<ImagemModel> modelList) {

        List<ImagemLeitura> lista = new ArrayList<>();

        for (ImagemModel model : modelList) {

            ImagemLeitura imagem = new ImagemLeitura(model);
            lista.add(imagem);
        }
        return lista;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDeletado() {
        return deletado;
    }

    public void setDeletado(String deletado) {
        this.deletado = deletado;
    }

    public String getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(String dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

}
