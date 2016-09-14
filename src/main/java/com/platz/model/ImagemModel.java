package com.platz.model;

import com.platz.util.DataUtil;
import java.util.Date;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

/**
 *
 * @author 15153770
 */
@Embeddable
public class ImagemModel {

    @Temporal(TemporalType.TIMESTAMP)
    private Date dataCadastro;

    @NotNull(message = "Insira uma imagem")
    private String url;

    @Temporal(TemporalType.TIMESTAMP)
    private Date deletado;

    public ImagemModel() {
    }
    
    public ImagemModel(String url) {
        setDataCadastro(new Date());
        setUrl(url);
    }

    //getters and setters
   
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Date getDeletadoDate() {
        return deletado;
    }

    public String getDeletado() {
        return new DataUtil().converterData(this.deletado);
    }

    public void setDeletado(Date deletado) {
        this.deletado = deletado;
    }

    public String getDataCadastro() {
        return new DataUtil().converterData(dataCadastro);
    }

    public void setDataCadastro(String dataCadastro) {
        this.dataCadastro = new DataUtil().converterData(dataCadastro);
    }
    
      public void setDataCadastro(Date dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

  
}
