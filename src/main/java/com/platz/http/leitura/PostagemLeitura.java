package com.platz.http.leitura;

import com.platz.model.PostagemModel;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author 15153770
 */
@XmlRootElement
public class PostagemLeitura {
    
    private String id;
    private String conteudo;
    private ContaLeitura conta;
    private EventoLeitura evento;
    private String dataCadastro;
    private String deletado;
    private String censurado;
    
    public PostagemLeitura() {
    }
    
    public PostagemLeitura(PostagemModel model) {
        setId(model.getId());
        setConteudo(model.getConteudo());
        setConta(new ContaLeitura(model.getConta()));
        setEvento(new EventoLeitura(model.getEvento()));
        setDataCadastro(model.getDataCadastro());
        setDeletado(model.getDeletado());
        setCensurado(model.getCensurado());      
    }
    
    public PostagemLeitura(String id, String conteudo, ContaLeitura contaLeitura, EventoLeitura evento, String dataCadastro, String deletado, String censurado) {
        setId(id);
        setConteudo(conteudo);
        setConta(contaLeitura);
        setEvento(evento);
        setDataCadastro(dataCadastro);
        setDeletado(deletado);
        setCensurado(censurado);
    }

    //Métodos
    public List<PostagemLeitura> converterLista(List<PostagemModel> modelList) {
        
        List<PostagemLeitura> lista = new ArrayList<>();
        
        for (PostagemModel model : modelList) {
            
            PostagemLeitura postagem = new PostagemLeitura(model);
            lista.add(postagem);
        }
        return lista;
    }
    
    public String getId() {
        return id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    public String getConteudo() {
        return conteudo;
    }
    
    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }
    
    public EventoLeitura getEvento() {
        return evento;
    }
    
    public void setEvento(EventoLeitura evento) {
        this.evento = evento;
    }
    
    public String getDataCadastro() {
        return dataCadastro;
    }
    
    public void setDataCadastro(String dataCadastro) {
        this.dataCadastro = dataCadastro;
    }
    
    public String getDeletado() {
        return deletado;
    }
    
    public void setDeletado(String deletado) {
        this.deletado = deletado;
    }
    
    public String getCensurado() {
        return censurado;
    }
    
    public void setCensurado(String censurado) {
        this.censurado = censurado;
    }
    
    public ContaLeitura getConta() {
        return conta;
    }
    
    public void setConta(ContaLeitura conta) {
        this.conta = conta;
    }
    
}
