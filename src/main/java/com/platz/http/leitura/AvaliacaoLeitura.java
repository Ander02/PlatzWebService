package com.platz.http.leitura;

import com.platz.model.AvaliacaoModel;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author 15153770
 */
@XmlRootElement
public class AvaliacaoLeitura {
    private String id;
    private Integer nota;
    private UsuarioLeitura usuario;
    private EventoLeitura evento;
    private String dataCadastro;

    public AvaliacaoLeitura() {
    }

    public AvaliacaoLeitura(String id, Integer nota, UsuarioLeitura usuario, EventoLeitura evento, String dataCadastro) {
        setId(id);
        setNota(nota);
        setUsuario(usuario);
        setUsuario(usuario);
        setDataCadastro(dataCadastro);
    }   
    
    public AvaliacaoLeitura(AvaliacaoModel model) {
        setId(model.getId());
        if(model.getNota()!= null){
            setNota(model.getNota());
        }
        setUsuario(new UsuarioLeitura(model.getUsuario()));
        //setEvento(new EventoLeitura(model.getEvento()));
        setDataCadastro(model.getDataCadastro());
    }
    
    public List<AvaliacaoLeitura> converterLista(List<AvaliacaoModel> modelList) {

        List<AvaliacaoLeitura> lista = new ArrayList<>();

        for (AvaliacaoModel model : modelList) {

            AvaliacaoLeitura avaliacao = new AvaliacaoLeitura(model);
            lista.add(avaliacao);
        }
        return lista;
    }
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getNota() {
        return nota;
    }

    public void setNota(Integer nota) {
        this.nota = nota;
    }

    public UsuarioLeitura getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioLeitura usuario) {
        this.usuario = usuario;
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
    
    
}
