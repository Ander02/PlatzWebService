package com.platz.http.edicao;

import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author 15153766
 */
@XmlRootElement
public class ContaEdicao {
    
    private String senha;

    public ContaEdicao() {
    }

    public ContaEdicao(String senha) {
        setSenha(senha);
    }
    

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }  
    
}
