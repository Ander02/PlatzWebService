package com.platz.http.leitura;

import com.platz.controller.EmpresaController;
import com.platz.controller.UsuarioController;
import com.platz.model.Perfil;
import com.platz.model.PostagemModel;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author 15153770
 */
@XmlRootElement
public class PostagemLeitura {

    private String id;
    private String conteudo;
    //private ContaLeitura conta;
    private EventoLeitura evento;
    private String dataCadastro;
    private String deletado;
    private String censurado;
    private UsuarioLeitura usuario;
    private EmpresaLeitura empresa;

    public PostagemLeitura() {
    }

    public PostagemLeitura(PostagemModel model) {
        setId(model.getId());
        setConteudo(model.getConteudo());
        //setConta(new ContaLeitura(model.getConta()));
        setEvento(new EventoLeitura(model.getEvento()));
        setDataCadastro(model.getDataCadastro());
        setDeletado(model.getDeletado());
        setCensurado(model.getCensurado());
        if (model.getConta().getPerfil() == Perfil.EMPRESA) {
            setEmpresa(new EmpresaLeitura(new EmpresaController().buscarPelaConta(model.getConta())));
        } else if (model.getConta().getPerfil() == Perfil.USUARIO) {
            setUsuario(new UsuarioLeitura(new UsuarioController().buscarPelaConta(model.getConta())));
        }
    }

    //Métodos
    public List<PostagemLeitura> converterLista(List<PostagemModel> modelList) {

        List<PostagemLeitura> lista = new ArrayList<>();

        for (PostagemModel model : modelList) {

            PostagemLeitura postagem = new PostagemLeitura(model);
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

    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }

    public EventoLeitura getEvento() {
        return evento;
    }

    public void setEvento(EventoLeitura evento) {
        this.evento = evento;
    }

    public String getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(String dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public String getDeletado() {
        return deletado;
    }

    public void setDeletado(String deletado) {
        this.deletado = deletado;
    }

    public String getCensurado() {
        return censurado;
    }

    public void setCensurado(String censurado) {
        this.censurado = censurado;
    }

    public UsuarioLeitura getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioLeitura usuario) {
        this.usuario = usuario;
    }

    public EmpresaLeitura getEmpresa() {
        return empresa;
    }

    public void setEmpresa(EmpresaLeitura empresa) {
        this.empresa = empresa;
    }

}
