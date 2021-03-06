package com.platz.http.leitura;

import com.platz.model.ContaModel;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Anderson
 */
@XmlRootElement
public class ContaLeitura {
    
    private String id;
    private String email;
    private String inativo;
    private String perfil;
    private String bloqueado;
    private String ultimoAcesso;
    private String dataCadastro;
    private String token;
    private String tokenAndroid;

    //Construtores
    public ContaLeitura() {
    }
    
    public ContaLeitura(ContaModel model) {
        setId(model.getId());
        setEmail(model.getEmail());
        setDataCadastro(model.getDataCadastro());
        setPerfil(model.getPerfil().getLabel());
        setInativo(model.getInativo());
        setBloqueado(model.getBloqueado());
        setUltimoAcesso(model.getUltimoAcesso());
        setToken(model.getToken());
        setTokenAndroid(model.getTokenAndroid());
    }

    //Métodos
    public List<ContaLeitura> converterLista(List<ContaModel> modelList) {
        
        List<ContaLeitura> lista = new ArrayList<>();
        
        for (ContaModel model : modelList) {
            
            ContaLeitura conta = new ContaLeitura(model);
            lista.add(conta);
        }
        return lista;
    }

    //Getters and Setters
    public String getId() {
        return id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getDataCadastro() {
        return dataCadastro;
    }
    
    public void setDataCadastro(String dataCadastro) {
        this.dataCadastro = dataCadastro;
    }
    
    public String getInativo() {
        return inativo;
    }
    
    public void setInativo(String inativo) {
        this.inativo = inativo;
    }
    
    public String getBloqueado() {
        return bloqueado;
    }
    
    public void setBloqueado(String bloqueado) {
        this.bloqueado = bloqueado;
    }
    
    public String getUltimoAcesso() {
        return ultimoAcesso;
    }
    
    public void setUltimoAcesso(String ultimoAcesso) {
        this.ultimoAcesso = ultimoAcesso;
    }
    
    public String getPerfil() {
        return perfil;
    }
    
    public void setPerfil(String perfil) {
        this.perfil = perfil;
    }
    
    public String getToken() {
        return token;
    }
    
    public void setToken(String token) {
        this.token = token;
    }
    
    public String getTokenAndroid() {
        return tokenAndroid;
    }
    
    public void setTokenAndroid(String tokenAndroid) {
        this.tokenAndroid = tokenAndroid;
    }
    
}
