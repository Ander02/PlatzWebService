package com.platz.http.cadastro;

import com.platz.model.Perfil;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Anderson
 */
@XmlRootElement
public class ContaCadastro {

    private String email;
    private String senha;
    private int perfil;

    //Construtores
    public ContaCadastro() {
    }

    public ContaCadastro(String email, String senha) {
        this.email = email;
        this.senha = senha;
    }

    //Getters and Setters
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public int getPerfil() {
        return perfil;
    }

    public void setPerfil(int perfil) {
        this.perfil = perfil;
    }

 
 
}
