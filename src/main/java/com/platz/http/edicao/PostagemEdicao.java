package com.platz.http.edicao;

import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author 15153770
 */
@XmlRootElement
public class PostagemEdicao {
    private String conteudo;

    public PostagemEdicao() {
    }

    public PostagemEdicao(String conteudo) {
        setConteudo(conteudo);
    }

    
    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }
    
    
}
