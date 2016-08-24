package com.platz.http.leitura;

import com.platz.model.TipoPresenca;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author 15153770
 */
@XmlRootElement
public class TipoPresencaLeitura {

    private int codigo;
    private String nome;

    public TipoPresencaLeitura() {
    }

    public TipoPresencaLeitura(TipoPresenca tipoPresenca) {
        setCodigo(tipoPresenca.ordinal());
        setNome(tipoPresenca.getLabel());
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

}