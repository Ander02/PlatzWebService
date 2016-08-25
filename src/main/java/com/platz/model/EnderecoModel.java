package com.platz.model;

import com.platz.dao.CidadeDao;
import com.platz.http.cadastro.EnderecoCadastro;
import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

/**
 *
 * @author 15153770
 */
@Embeddable
public class EnderecoModel {

    @NotNull(message = "o CEP não pode ser Nulo")
    @Length(min = 8, max = 9, message = "CEP invalido")
    private String cep;
    private String rua;
    private String bairro;
    private String numero;
    private String complemento = null;
    @ManyToOne
    private CidadeModel cidade;

    public EnderecoModel() {
    }

    public EnderecoModel(EnderecoCadastro endereco) {
        setCep(endereco.getCep());
        setRua(endereco.getRua());
        setBairro(endereco.getBairro());
        setNumero(endereco.getNumero());
        setCidade(new CidadeDao().buscarPorId(CidadeModel.class, endereco.getCidadeId()));
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public CidadeModel getCidade() {
        return cidade;
    }

    public void setCidade(CidadeModel cidade) {
        this.cidade = cidade;
    }

}
