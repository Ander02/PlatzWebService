package com.platz.http.edicao;

import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Anderson
 */
@XmlRootElement
public class EventoEdicao {

    private String nome;
    private String detalhes;
    private String idade;
    private String dataInicio;
    private String dataFim;
    private Integer lotacaoMin;
    private Integer lotacaoMax;
    private Double preco;
    private List<String> categoriasId;
    private List<String> imagensId;
    private Boolean destaque;

    public EventoEdicao() {
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDetalhes() {
        return detalhes;
    }

    public void setDetalhes(String detalhes) {
        this.detalhes = detalhes;
    }

    public String getIdade() {
        return idade;
    }

    public void setIdade(String idade) {
        this.idade = idade;
    }

    public String getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(String dataInicio) {
        this.dataInicio = dataInicio;
    }

    public String getDataFim() {
        return dataFim;
    }

    public void setDataFim(String dataFim) {
        this.dataFim = dataFim;
    }

    public Integer getLotacaoMin() {
        return lotacaoMin;
    }

    public void setLotacaoMin(Integer lotacaoMin) {
        this.lotacaoMin = lotacaoMin;
    }

    public Integer getLotacaoMax() {
        return lotacaoMax;
    }

    public void setLotacaoMax(Integer lotacaoMax) {
        this.lotacaoMax = lotacaoMax;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public List<String> getCategoriasId() {
        return categoriasId;
    }

    public void setCategoriasId(List<String> categoriasId) {
        this.categoriasId = categoriasId;
    }

    public List<String> getImagensId() {
        return imagensId;
    }

    public void setImagensId(List<String> imagensId) {
        this.imagensId = imagensId;
    }

    public Boolean getDestaque() {
        return destaque;
    }

    public void setDestaque(Boolean destaque) {
        this.destaque = destaque;
    }

}
