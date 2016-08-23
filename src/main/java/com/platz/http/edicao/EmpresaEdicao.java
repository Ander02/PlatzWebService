package com.platz.http.edicao;

import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Anderson
 */
@XmlRootElement
public class EmpresaEdicao {
    
    private String cnpj;
    private String nomeFantasia;
    private String razaoSocial;
    private String telefone;
    private String telefone2;
    private String imagemPerfil;

    public EmpresaEdicao() {
    }

    public EmpresaEdicao(String cnpj, String nomeFantasia, String razaoSocial, String telefone, String telefone2, String imagemPerfil) {
        setCnpj(cnpj);
        setNomeFantasia(nomeFantasia);
        setRazaoSocial(razaoSocial);
        setTelefone(telefone);
        setTelefone2(telefone2);
        setImagemPerfil(imagemPerfil);
    }
    
    
    
    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getNomeFantasia() {
        return nomeFantasia;
    }

    public void setNomeFantasia(String nomeFantasia) {
        this.nomeFantasia = nomeFantasia;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getTelefone2() {
        return telefone2;
    }

    public void setTelefone2(String telefone2) {
        this.telefone2 = telefone2;
    }

    public String getImagemPerfil() {
        return imagemPerfil;
    }

    public void setImagemPerfil(String imagemPerfil) {
        this.imagemPerfil = imagemPerfil;
    }   
    
}
