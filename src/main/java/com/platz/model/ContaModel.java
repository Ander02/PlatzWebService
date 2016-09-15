package com.platz.model;

import com.platz.http.cadastro.ContaCadastro;
import com.platz.http.edicao.ContaEdicao;
import com.platz.util.DataUtil;
import com.platz.util.EncriptAES;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import org.bson.types.ObjectId;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;

/**
 *
 * @author Anderson
 */
@Entity
@Table(name = "conta")
public class ContaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private ObjectId id;

    @Email(message = "Email inválido")
    @NotNull(message = "O email não pode ser nulo")
    private String email;
    @Length(min = 32, max = 32, message = "A senha deve ter 32 caracteres")
    @NotNull(message = "A senha não pode ser nula")
    private String senha;

    @NotNull(message = "O perfil não pode ser nulo")
    private Perfil perfil;

    @Temporal(TemporalType.TIMESTAMP)
    private Date inativo;

    @Temporal(TemporalType.TIMESTAMP)
    private Date bloqueado;

    @Temporal(TemporalType.TIMESTAMP)
    private Date ultimoAcesso;
    
    private String token = null;

    //Construtores
    public ContaModel() {
    }

    public ContaModel(ContaCadastro conta) {
        setEmail(conta.getEmail());
        setSenha(conta.getSenha());
        setPerfil(conta.getPerfil());
    }

    public ContaModel(ContaEdicao conta) {
        setSenha(conta.getSenha());
    }

    //Getters and setters
    public ObjectId getObjectId() {
        return this.id;
    }

    public String getId() {
        return id.toHexString();
    }

    public void setId(String id) {
        this.id = new ObjectId(id);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {

        String senhaEncriptada;

        senhaEncriptada = senha;
        // Parte da criptografia
        try {

            //Encripta a senha com o método encrypt, retornando um array de bytes que será convertido para string
            senhaEncriptada = new EncriptAES().byteParaString(new EncriptAES().encrypt(senha, EncriptAES.getChaveEncriptacao()));

        } catch (Exception e) {
            System.out.println("Erro ao Criptografar senha");
            e.printStackTrace();
        }
        //Seta a senha encriptada
        this.senha = senhaEncriptada;
    }

    public String getUltimoAcesso() {
        if (this.ultimoAcesso != null) {
            return new DataUtil().converterData(this.ultimoAcesso);
        }
        return null;
    }

    public void setUltimoAcesso(Date ultimoAcesso) {
        this.ultimoAcesso = ultimoAcesso;
    }

    public String getDataCadastro() {
        return new DataUtil().converterData(id.getDate());
    }

    public Date getInativoDate() {
        return inativo;
    }

    public String getInativo() {
        return new DataUtil().converterData(this.inativo);
    }

    public void setInativo(Date inativo) {
        this.inativo = inativo;
    }

    public Date getBloqueadoDate() {
        return bloqueado;
    }

    public String getBloqueado() {
        return new DataUtil().converterData(this.bloqueado);
    }

    public void setBloqueado(Date bloqueado) {
        this.bloqueado = bloqueado;
    }

    public Perfil getPerfil() {
        return perfil;
    }

    public void setPerfil(int perfil) {
        switch (perfil) {
            case 0:
                this.perfil = Perfil.ADMINISTRADOR;
                break;
            case 1:
                this.perfil = Perfil.EMPRESA;
                break;
            case 2:
                this.perfil = Perfil.USUARIO;
                break;
            default:
                this.perfil = Perfil.USUARIO;
                break;
        }
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
