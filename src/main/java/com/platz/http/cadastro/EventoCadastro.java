package com.platz.http.cadastro;

import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Anderson
 */
@XmlRootElement
public class EventoCadastro {

    private String nome;
    private String detalhes;
    private int idade;
    private String dataFim;
    private String dataInicio;
    private Integer lotacaoMin;
    private Integer lotacaoMax;
    private Double preco;
    private String empresaId;
    private List<String> categoriasId;
    private List<String> imagensId;
    private String cancelado;
    private String censurado;
    private Boolean destaque;
    private EnderecoCadastro endereco;

    public EventoCadastro() {
    }

    public EventoCadastro(String nome, String detalhes, int idade, String dataFim, String dataInicio, Integer lotacaoMin, Integer lotacaoMax, Double preco, String empresaId, List<String> categoriasId, List<String> imagensId, String cancelado, String censurado, Boolean destaque, EnderecoCadastro endereco) {
        setNome(nome);
        setDetalhes(detalhes);
        setDestaque(destaque);
        setIdade(idade);
        setDataFim(dataFim);
        setDataInicio(dataInicio);
        setLotacaoMax(lotacaoMax);
        setLotacaoMin(lotacaoMin);
        setPreco(preco);
        setEmpresaId(empresaId);
        setCategoriasId(categoriasId);
        setImagensId(imagensId);
        setCancelado(cancelado);
        setCensurado(censurado);
        setEndereco(endereco);
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

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public String getDataFim() {
        return dataFim;
    }

    public void setDataFim(String dataFim) {
        this.dataFim = dataFim;
    }

    public String getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(String dataInicio) {
        this.dataInicio = dataInicio;
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

    public String getEmpresaId() {
        return empresaId;
    }

    public void setEmpresaId(String empresaId) {
        this.empresaId = empresaId;
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

    public String getCancelado() {
        return cancelado;
    }

    public void setCancelado(String cancelado) {
        this.cancelado = cancelado;
    }

    public String getCensurado() {
        return censurado;
    }

    public void setCensurado(String censurado) {
        this.censurado = censurado;
    }

    public Boolean getDestaque() {
        return destaque;
    }

    public void setDestaque(Boolean destaque) {
        this.destaque = destaque;
    }

    public EnderecoCadastro getEndereco() {
        return endereco;
    }

    public void setEndereco(EnderecoCadastro endereco) {
        this.endereco = endereco;
    }

}
