package com.platz.model;

import com.platz.dao.CategoriaDao;
import com.platz.dao.EmpresaDao;
import com.platz.http.cadastro.EventoCadastro;
import com.platz.util.DataUtil;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import org.bson.types.ObjectId;
import org.hibernate.validator.constraints.Length;

/**
 *
 * @author 15153770
 */
@Entity
@Table(name = "evento")
public class EventoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    ObjectId id;

    @NotNull(message = "O evento deve ter um nome")
    @Length(min = 3, max = 70, message = "O nome do evento deve ter entre 3 a 70 caracteres")
    private String nome;
    @Length(max = 512, message = "Detalhes deve ter no maximo 512 caracteres")
    private String detalhes;
    private int idade = 0;
    @Temporal(TemporalType.TIMESTAMP)
    @Future
    @NotNull(message = "A data e/ou hora de inicio deve ser informada")
    private Date dataInicio;
    @Temporal(TemporalType.TIMESTAMP)
    @Future
    @NotNull(message = "A data e/ou hora de termino deve ser informada")
    private Date dataFim;
    private Integer lotacaoMin = 0;
    private Integer lotacaoMax;
    private Double preco = 0.0;
    @NotNull(message = "A empresa deve ser informada")
    @ManyToOne
    private EmpresaModel empresa;
    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.REFRESH})
    @NotNull(message = "Informe ao menos uma categoria")
    private List<CategoriaModel> categorias = new ArrayList<>();
    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.REFRESH})
    private List<ImagemModel> imagens = new ArrayList<>();
    @Temporal(TemporalType.TIMESTAMP)
    private Date cancelado = null;
    @Temporal(TemporalType.TIMESTAMP)
    private Date censurado = null;
    private Boolean destaque = false;
    private EnderecoModel endereco;

    public EventoModel() {
    }

    public EventoModel(EventoCadastro evento) {
        setNome(evento.getNome());
        setDetalhes(evento.getDetalhes());
        setIdade(evento.getIdade());
        setDataInicio(evento.getDataInicio());
        setDataFim(evento.getDataFim());
        setLotacaoMin(evento.getLotacaoMin());
        setLotacaoMax(evento.getLotacaoMax());
        setPreco(evento.getPreco());
        setEmpresa(new EmpresaDao().buscarPorId(EmpresaModel.class, evento.getEmpresaId()));

        for (String categoriaId : evento.getCategoriasId()) {
            CategoriaModel categoria = new CategoriaDao().buscarPorId(CategoriaModel.class, categoriaId);

            categorias.add(categoria);
        }

        setDestaque(evento.getDestaque());
        setEndereco(new EnderecoModel(evento.getEndereco()));

    }

    //getters and setters
    public String getId() {
        return id.toHexString();
    }

    public void setId(String id) {
        this.id = new ObjectId(id);
    }

    public ObjectId getObjectId() {
        return this.id;
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

    public Date getDataInicioDate() {
        return this.dataInicio;
    }

    public String getDataInicio() {
        return new DataUtil().converterData(this.dataInicio);
    }

    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    public void setDataInicio(String dataInicio) {
        this.dataInicio = new DataUtil().converterData(dataInicio);
    }

    public Date getDataFimDate() {
        return this.dataFim;
    }

    public String getDataFim() {
        return new DataUtil().converterData(this.dataFim);
    }

    public void setDataFim(Date dataFim) {
        this.dataFim = dataFim;
    }

    public void setDataFim(String dataFim) {
        this.dataFim = new DataUtil().converterData(dataFim);
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

    public EmpresaModel getEmpresa() {
        return empresa;
    }

    public void setEmpresa(EmpresaModel empresa) {
        this.empresa = empresa;
    }

    public List<CategoriaModel> getCategorias() {
        return categorias;
    }

    public void setCategorias(List<CategoriaModel> categorias) {
        this.categorias = categorias;
    }

    public List<ImagemModel> getImagens() {
        return imagens;
    }

    public void setImagens(List<ImagemModel> imagens) {
        this.imagens = imagens;
    }

    public Date getCanceladoDate() {
        return this.cancelado;
    }

    public String getCancelado() {
        return new DataUtil().converterData(this.cancelado);
    }

    public void setCancelado(Date cancelado) {
        this.cancelado = cancelado;
    }

    public void setCancelado(String cancelado) {
        this.cancelado = new DataUtil().converterData(cancelado);
    }

    public Date getCensuradoDate() {
        return this.censurado;
    }

    public String getCensurado() {
        return new DataUtil().converterData(this.censurado);
    }

    public void setCensurado(Date censurado) {
        this.censurado = censurado;
    }

    public void setCensurado(String censurado) {
        this.censurado = new DataUtil().converterData(censurado);
    }

    public Boolean getDestaque() {
        return destaque;
    }

    public void setDestaque(Boolean destaque) {
        this.destaque = destaque;
    }

    public String getDataCadastro() {
        return new DataUtil().converterData(id.getDate());
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public EnderecoModel getEndereco() {
        return endereco;
    }

    public void setEndereco(EnderecoModel endereco) {
        this.endereco = endereco;
    }

}
