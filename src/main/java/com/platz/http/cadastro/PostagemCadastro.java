package com.platz.http.cadastro;

import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author 15153770
 */
@XmlRootElement
public class PostagemCadastro {

    private String conteudo;
    private String eventoId;
    private String contaId;
    
    public PostagemCadastro() {
    }

    public PostagemCadastro(String conteudo, String eventoId, String contaId) {
        setConteudo(conteudo);
        setEventoId(eventoId);
        setContaId(contaId);
    }

    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }

    public String getEventoId() {
        return eventoId;
    }

    public void setEventoId(String eventoId) {
        this.eventoId = eventoId;
    }

    public String getContaId() {
        return contaId;
    }

    public void setContaId(String contaId) {
        this.contaId = contaId;
    }

}
