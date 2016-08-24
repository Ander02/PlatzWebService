package com.platz.http.leitura;

import com.platz.model.EventoModel;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author 15153770
 */
@XmlRootElement
public class EventoLeitura {

    private String id;
    private String nome;
    private String detalhes;
    private int idade;
    private String dataInicio;
    private String dataFim;
    private int lotacaoMin;
    private int lotacaoMax;
    private Double preco;
    private EmpresaLeitura empresa;
    private List<CategoriaLeitura> categorias;
    private List<ImagemLeitura> imagens;
    private String cancelado;
    private String censurado;
    private boolean destaque;
    private String dataCadastro;

    public EventoLeitura() {
    }

    public EventoLeitura(EventoModel model) {
        setId(model.getId());
        setNome(model.getNome());
        setDetalhes(model.getDetalhes());
        setIdade(model.getIdade());
        setDataInicio(model.getDataInicio());
        setDataFim(model.getDataFim());
        setLotacaoMin(model.getLotacaoMin());
        setLotacaoMax(model.getLotacaoMax());
        setPreco(model.getPreco());
        setEmpresa(new EmpresaLeitura(model.getEmpresa()));
        setCategorias(new CategoriaLeitura().converterLista(model.getCategorias()));
        setImagens(new ImagemLeitura().converterLista(model.getImagens()));
        setCancelado(model.getCancelado());
        setCensurado(model.getCensurado());
        setDestaque(model.getDestaque());
        setDataCadastro(model.getDataCadastro());
    }

    //Métodos
    
    
    //Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public EmpresaLeitura getEmpresa() {
        return empresa;
    }

    public void setEmpresa(EmpresaLeitura empresa) {
        this.empresa = empresa;
    }

    public List<CategoriaLeitura> getCategorias() {
        return categorias;
    }

    public void setCategorias(List<CategoriaLeitura> categorias) {
        this.categorias = categorias;
    }

    public List<ImagemLeitura> getImagens() {
        return imagens;
    }

    public void setImagens(List<ImagemLeitura> imagens) {
        this.imagens = imagens;
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

    public boolean isDestaque() {
        return destaque;
    }

    public void setDestaque(boolean destaque) {
        this.destaque = destaque;
    }

    public String getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(String dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public int getLotacaoMin() {
        return lotacaoMin;
    }

    public void setLotacaoMin(int lotacaoMin) {
        this.lotacaoMin = lotacaoMin;
    }

    public int getLotacaoMax() {
        return lotacaoMax;
    }

    public void setLotacaoMax(int lotacaoMax) {
        this.lotacaoMax = lotacaoMax;
    }

}
