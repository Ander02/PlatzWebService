package com.platz.model;

import com.platz.util.DataUtil;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
    @OneToMany
    @NotNull(message = "Informe ao menos uma categoria")
    private List<CategoriaModel> categorias;
    @OneToMany
    private List<ImagemModel> imagens;
    @Temporal(TemporalType.TIMESTAMP)
    private Date cancelado = null;
    @Temporal(TemporalType.TIMESTAMP)
    private Date censurado = null;
    private Boolean destaque = false;
    //Endereco endereco

    public EventoModel() {
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

    public String getDataInicio() {
        return new DataUtil().converterData(this.dataInicio);
    }

    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    public String getDataFim() {
        return new DataUtil().converterData(this.dataFim);    
    }

    public void setDataFim(Date dataFim) {
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

    public String getCancelado() {
        return new DataUtil().converterData(this.cancelado);
    }

    public void setCancelado(Date cancelado) {
        this.cancelado = cancelado;
    }

    public String getCensurado() {
         return new DataUtil().converterData(this.censurado);
    }

    public void setCensurado(Date censurado) {
        this.censurado = censurado;
    }

    public Boolean getDestaque() {
        return destaque;
    }

    public void setDestaque(Boolean destaque) {
        this.destaque = destaque;
    }
    public String getDataCadatro() {
        return new DataUtil().converterData(id.getDate());
    }


}
