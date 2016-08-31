package com.platz.http.cadastro;

import com.platz.model.Perfil;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Anderson
 */
@XmlRootElement
public class EmpresaCadastro {
     
    private String cnpj;
    private String nomeFantasia;
    private String razaoSocial;
    private String telefone;
    private String telefone2;
    private String imagemPerfil;
    private ContaCadastro conta;
    private EnderecoCadastro endereco;

    public EmpresaCadastro() { 
    }

    public EmpresaCadastro(String cnpj, String nomeFantasia, String razaoSocial, String telefone, String telefone2, String imagemPerfil, ContaCadastro conta) {
        setCnpj(cnpj);
        setNomeFantasia(nomeFantasia);
        setRazaoSocial(razaoSocial);
        setTelefone(telefone);
        setTelefone2(telefone2);
        setImagemPerfil(imagemPerfil);
        conta.setPerfil(Perfil.EMPRESA.ordinal());
        setConta(conta);
    }
    
   
    //Getters and Setters
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

    public ContaCadastro getConta() {
        return conta;
    }

    public void setConta(ContaCadastro conta) {
        this.conta = conta;
    }

    public String getImagemPerfil() {
        return imagemPerfil;
    }

    public void setImagemPerfil(String imagemPerfil) {
        this.imagemPerfil = imagemPerfil;
    }

    public EnderecoCadastro getEndereco() {
        return endereco;
    }

    public void setEndereco(EnderecoCadastro endereco) {
        this.endereco = endereco;
    }

    
    
}
