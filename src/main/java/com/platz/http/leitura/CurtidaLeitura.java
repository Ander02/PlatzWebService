package com.platz.http.leitura;

import com.platz.model.CurtidaModel;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Anderson
 */
@XmlRootElement
public class CurtidaLeitura {

    private String id;
    private boolean curtido;
    private UsuarioLeitura usuario;
    private EventoLeitura evento;
    private String dataCadastro;

    public CurtidaLeitura() {
    }

    public CurtidaLeitura(String id, boolean curtido, UsuarioLeitura usuario, EventoLeitura evento, String dataCadastro) {
        setId(id);
        setCurtido(curtido);
        setUsuario(usuario);
        setDataCadastro(dataCadastro);
    }

    public CurtidaLeitura(CurtidaModel model) {
        setId(model.getId());
        setCurtido(model.getCurtido());
        setUsuario(new UsuarioLeitura(model.getUsuario()));
        setEvento(new EventoLeitura(model.getEvento()));
        setDataCadastro(model.getDataCadastro());
    }

    public List<CurtidaLeitura> converterLista(List<CurtidaModel> modelList) {

        List<CurtidaLeitura> lista = new ArrayList<>();

        for (CurtidaModel model : modelList) {

            CurtidaLeitura avaliacao = new CurtidaLeitura(model);
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

    public boolean isCurtido() {
        return curtido;
    }

    public void setCurtido(boolean curtido) {
        this.curtido = curtido;
    }

}
