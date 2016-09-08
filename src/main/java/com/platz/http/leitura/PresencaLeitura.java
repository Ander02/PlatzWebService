package com.platz.http.leitura;

import com.platz.model.PresencaModel;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author 15153770
 */
@XmlRootElement
public class PresencaLeitura {

    private String id;
    private EventoLeitura evento;
    private ContaLeitura conta;
    private String tipoPresenca;
    private String dataCadastro;

    public PresencaLeitura() {
    }

    public PresencaLeitura(PresencaModel model) {
        setId(model.getId());
        setEvento(new EventoLeitura(model.getEvento()));
        setConta(new ContaLeitura(model.getConta()));
        setTipoPresenca(model.getTipoPresenca().getLabel());
        setDataCadastro(model.getDataCadastro());
    }

    public PresencaLeitura(String id, EventoLeitura evento, ContaLeitura conta, String tipoPresenca, String dataCadastro) {
        setId(id);
        setEvento(evento);
        setConta(conta);
        setTipoPresenca(tipoPresenca);
        setDataCadastro(dataCadastro);
    }
    //Métodos

    public List<PresencaLeitura> converterLista(List<PresencaModel> modelList) {

        List<PresencaLeitura> lista = new ArrayList<>();

        for (PresencaModel model : modelList) {

            PresencaLeitura postagem = new PresencaLeitura(model);
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

    public EventoLeitura getEvento() {
        return evento;
    }

    public void setEvento(EventoLeitura evento) {
        this.evento = evento;
    }

    public ContaLeitura getConta() {
        return conta;
    }

    public void setConta(ContaLeitura conta) {
        this.conta = conta;
    }

    public String getTipoPresenca() {
        return tipoPresenca;
    }

    public void setTipoPresenca(String tipoPresenca) {
        this.tipoPresenca = tipoPresenca;
    }

    public String getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(String dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

}
