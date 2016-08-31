package com.platz.http.leitura;

import com.platz.model.UsuarioModel;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author 15153770
 */
@XmlRootElement
public class UsuarioLeitura {

    private String id;
    private String nome;
    private String cpf;
    private String imagemPerfil;
    private String telefone;
    private String dataNascimento;
    private String dataCadastro;
    private ContaLeitura conta;
    private EnderecoLeitura endereco;

    public UsuarioLeitura() {
    }

    public UsuarioLeitura(UsuarioModel model) {
        setId(model.getId());
        setNome(model.getNome());
        setCpf(model.getCpf());
        setImagemPerfil(model.getImagemPerfil());
        setTelefone(model.getTelefone());
        setDataNascimento(model.getDataNascimento());
        setDataCadastro(model.getDataCadastro());
        setConta(new ContaLeitura(model.getConta()));
        setEndereco(new EnderecoLeitura(model.getEndereco()));
    }

    //Métodos
    public List<UsuarioLeitura> converterLista(List<UsuarioModel> modelList) {

        List<UsuarioLeitura> lista = new ArrayList<>();

        for (UsuarioModel model : modelList) {

            UsuarioLeitura usuario = new UsuarioLeitura(model);
            lista.add(usuario);
        }
        return lista;
    }

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

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
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

    public String getImagemPerfil() {
        return imagemPerfil;
    }

    public void setImagemPerfil(String imagemPerfil) {
        this.imagemPerfil = imagemPerfil;
    }

    public EnderecoLeitura getEndereco() {
        return endereco;
    }

    public void setEndereco(EnderecoLeitura endereco) {
        this.endereco = endereco;
    }

}
