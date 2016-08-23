package com.platz.http.cadastro;

import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Anderson
 */
@XmlRootElement
public class MensagemCadastro {
    
    private String assuntoId;
    private String email;
    private String conteudo;

    public MensagemCadastro() {
    }  

    public MensagemCadastro(String assuntoId, String email, String conteudo) {
        setAssuntoId(assuntoId);
        setEmail(email);
        setConteudo(conteudo);
    }
    
    //getters and Setters
    public String getAssuntoId() {
        return assuntoId;
    }

    public void setAssuntoId(String assuntoId) {
        this.assuntoId = assuntoId;
    }
    
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }


    
    
    
}
