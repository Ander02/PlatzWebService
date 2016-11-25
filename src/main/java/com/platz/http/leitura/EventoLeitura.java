package com.platz.http.leitura;

import com.platz.controller.AvaliacaoController;
import com.platz.controller.CurtidaController;
import com.platz.controller.PresencaController;
import com.platz.model.EventoModel;
import com.platz.model.TipoPresenca;
import java.util.ArrayList;
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
    private String imagemCapa;
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
    private EnderecoLeitura endereco;
    private double media = 0.0;
    private double mediaArredondada = 0.0;
    private int participacaoSim = 0;
    private int participacaoTalvez = 0;
    private int participacaoNao = 0;
    private int curtidas = 0;

    public EventoLeitura() {
    }

    public EventoLeitura(EventoModel model) {
        setId(model.getId());
        setNome(model.getNome());
        setDetalhes(model.getDetalhes());
        setImagemCapa(model.getImagemCapa());
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
        setEndereco(new EnderecoLeitura(model.getEndereco()));
        setMedia(new AvaliacaoController().mediaPorEvento(model));
        setMediaArredondada(Math.round(getMedia()));
        setParticipacaoSim(new PresencaController().buscartipoPresenca(TipoPresenca.SIM, model).size());
        setParticipacaoNao(new PresencaController().buscartipoPresenca(TipoPresenca.NAO, model).size());
        setParticipacaoTalvez(new PresencaController().buscartipoPresenca(TipoPresenca.TALVEZ, model).size());
        setCurtidas(new CurtidaController().buscarPeloEvento(model).size());
    }

    //Métodos
    public List<EventoLeitura> converterLista(List<EventoModel> modelList) {

        List<EventoLeitura> lista = new ArrayList<>();

        for (EventoModel model : modelList) {

            EventoLeitura evento = new EventoLeitura(model);
            lista.add(evento);
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

    public String getImagemCapa() {
        return imagemCapa;
    }

    public void setImagemCapa(String imagemCapa) {
        this.imagemCapa = imagemCapa;
    }

    public EnderecoLeitura getEndereco() {
        return endereco;
    }

    public void setEndereco(EnderecoLeitura endereco) {
        this.endereco = endereco;
    }

    public double getMedia() {
        return media;
    }

    public void setMedia(double media) {
        this.media = media;
    }

    public double getMediaArredondada() {
        return mediaArredondada;
    }

    public void setMediaArredondada(double mediaArredondada) {
        this.mediaArredondada = mediaArredondada;
    }

    public int getParticipacaoSim() {
        return participacaoSim;
    }

    public void setParticipacaoSim(int participacaoSim) {
        this.participacaoSim = participacaoSim;
    }

    public int getParticipacaoTalvez() {
        return participacaoTalvez;
    }

    public void setParticipacaoTalvez(int participacaoTalvez) {
        this.participacaoTalvez = participacaoTalvez;
    }

    public int getParticipacaoNao() {
        return participacaoNao;
    }

    public void setParticipacaoNao(int participacaoNao) {
        this.participacaoNao = participacaoNao;
    }

    public int getCurtidas() {
        return curtidas;
    }

    public void setCurtidas(int curtidas) {
        this.curtidas = curtidas;
    }

}
