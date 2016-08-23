package com.platz.http.leitura;

import com.platz.model.EmpresaModel;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Anderson
 */
@XmlRootElement
public class EmpresaLeitura {

    private String id;
    private String cnpj;
    private String nomeFantasia;
    private String razaoSocial;
    private String telefone;
    private String telefone2;
    private String imagemPerfil;
    private String dataCadastro;
    private ContaLeitura conta;

    //Contrutores
    public EmpresaLeitura() {
    }

    public EmpresaLeitura(EmpresaModel model) {
        setId(model.getId());
        setCnpj(model.getCnpj());
        setNomeFantasia(model.getNomeFantasia());
        setRazaoSocial(model.getRazaoSocial());
        setTelefone(model.getTelefone());
        setTelefone2(model.getTelefone2());
        setImagemPerfil(model.getImagemPerfil());
        setDataCadastro(model.getDataCadatro());
        setConta(new ContaLeitura(model.getConta()));
    }

    public EmpresaLeitura(String id, String cnpj, String nomeFantasia, String razaoSocial, String telefone, String telefone2, String imagemPerfil, String dataCadastro, ContaLeitura conta) {
        setId(id);
        setCnpj(cnpj);
        setNomeFantasia(nomeFantasia);
        setRazaoSocial(razaoSocial);
        setTelefone(telefone);
        setTelefone2(telefone2);
        setImagemPerfil(imagemPerfil);
        setDataCadastro(dataCadastro);
        setConta(conta);
    }
    

    //Métodos
    public List<EmpresaLeitura> converterLista(List<EmpresaModel> modelList) {

        List<EmpresaLeitura> lista = new ArrayList<>();

        for (EmpresaModel model : modelList) {
            EmpresaLeitura empresa = new EmpresaLeitura(model);
            lista.add(empresa);
        }
        return lista;
    }

    //Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
        return dataCadastro;
    }

    public void setDataCadastro(String dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public ContaLeitura getConta() {
        return conta;
    }

    public void setConta(ContaLeitura conta) {
        this.conta = conta;
    }

}
