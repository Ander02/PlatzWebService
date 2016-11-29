package com.platz.model;

import com.platz.http.cadastro.EmpresaCadastro;
import com.platz.util.DataUtil;
import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import org.bson.types.ObjectId;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CNPJ;

/**
 *
 * @author 15153770
 */
@Entity
@Table(name = "empresa")
public class EmpresaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private ObjectId id;
    @NotNull
    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.REFRESH})
    private ContaModel conta;
    @CNPJ
    @NotNull(message = "O CNPJ deve ser informado")
    private String cnpj;

    @Length(max = 50, message = "Onome fantasia deve ter no maximo 50 caracteres")
    @NotNull(message = "O nome fantasia deve ser informado")
    private String nomeFantasia;

    @NotNull(message = "a razao social deve ser informada")
    @Length(max = 70, message = "A razao Social deve ter no maximo 70 caracteres")
    private String razaoSocial;

    @NotNull(message = "O telefone deve ser informado")
    @Length(min = 10, max = 15, message = "O telefone deve ter entre 10 e 15 caracteres")
    private String telefone;

    @Length(min = 10, max = 15, message = "O telefone deve ter entre 10 e 15 caracteres")
    private String telefone2;

    private String imagemPerfil;
    @Embedded
    private EnderecoModel endereco;

    public EmpresaModel() {
    }

    public EmpresaModel(EmpresaCadastro empresa) {
        setCnpj(empresa.getCnpj());
        setNomeFantasia(empresa.getNomeFantasia());
        setRazaoSocial(empresa.getRazaoSocial());
        setTelefone(empresa.getTelefone());
        setTelefone2(empresa.getTelefone2());
        setImagemPerfil(empresa.getImagemPerfil());
        setConta(new ContaModel(empresa.getConta()));
        setEndereco(new EnderecoModel(empresa.getEndereco()));
    }

    public String getId() {
        return id.toHexString();
    }

    public void setId(String id) {
        this.id = new ObjectId(id);
    }

    public ObjectId getObjectId() {
        return this.id;
    }

    public ContaModel getConta() {
        return conta;
    }

    public void setConta(ContaModel conta) {
        this.conta = conta;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getNomeFantasia() {
        return nomeFantasia;
    }

    public void setNomeFantasia(String nomeFantasia) {
        this.nomeFantasia = nomeFantasia;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getTelefone2() {
        return telefone2;
    }

    public void setTelefone2(String telefone2) {
        this.telefone2 = telefone2;
    }

    public String getImagemPerfil() {
        return imagemPerfil;
    }

    public void setImagemPerfil(String imagemPerfil) {
        this.imagemPerfil = imagemPerfil;
    }

    public String getDataCadastro() {
        return new DataUtil().converterData(id.getDate());
    }

    public EnderecoModel getEndereco() {
        return endereco;
    }

    public void setEndereco(EnderecoModel endereco) {
        this.endereco = endereco;
    }

}
