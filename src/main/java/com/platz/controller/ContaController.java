package com.platz.controller;

import com.platz.dao.ContaDao;
import com.platz.http.cadastro.Login;
import com.platz.model.ContaModel;
import com.platz.model.Perfil;
import com.platz.util.EncriptAES;
import com.platz.util.TokenUtil;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Anderson
 */
public class ContaController {

    private final ContaDao contaDao = new ContaDao();

    public ContaModel getConta(String token) {
        return contaDao.getConta(token);
    }

    public ContaModel getConta(String email, String senha) {
        return contaDao.getConta(email, senha);
    }

    public void cadastrar(ContaModel model) {
        contaDao.cadastrar(model);
    }

    public List<ContaModel> listarTodos() {
        return contaDao.listarTodos(ContaModel.class);
    }

    public List<ContaModel> listarAdministradores() {
        return contaDao.buscarPeloPerfilAtivosEBloqeuados(Perfil.ADMINISTRADOR);
    }

    public List<ContaModel> listarUsuario() {
        return contaDao.buscarPeloPerfilAtivosEBloqeuados(Perfil.USUARIO);
    }

    public List<ContaModel> listarEmpresa() {
        return contaDao.buscarPeloPerfilAtivosEBloqeuados(Perfil.EMPRESA);
    }

    public List<ContaModel> listarAdministradoresBloqueados() {
        return contaDao.buscarPeloPerfilBloqueados(Perfil.ADMINISTRADOR);
    }

    public List<ContaModel> listarUsuarioBloqueados() {
        return contaDao.buscarPeloPerfilBloqueados(Perfil.USUARIO);
    }

    public List<ContaModel> listarEmpresaBloqueados() {
        return contaDao.buscarPeloPerfilBloqueados(Perfil.EMPRESA);
    }

    public List<ContaModel> listarAdministradoresInativos() {
        return contaDao.buscarPeloPerfilInativos(Perfil.ADMINISTRADOR);
    }

    public List<ContaModel> listarUsuarioInativos() {
        return contaDao.buscarPeloPerfilInativos(Perfil.USUARIO);
    }

    public List<ContaModel> listarEmpresaInativos() {
        return contaDao.buscarPeloPerfilInativos(Perfil.EMPRESA);
    }

    public ContaModel buscarPorId(String id) {
        return contaDao.buscarPorId(ContaModel.class, id);
    }

    public ContaModel buscarPeloEmail(String email) {
        return contaDao.buscarPeloEmail(email);
    }

    public boolean verificaEmail(String email) {
        if (contaDao.buscarPeloEmail(email) != null) {
            return true;
        }
        return false;
    }

    public List<ContaModel> buscarAtivos() {
        return contaDao.buscarAtivos();
    }

    public List<ContaModel> buscarInativos() {
        return contaDao.buscarInativos();
    }

    public void alterar(ContaModel model) {
        contaDao.alterar(model);
    }

    public void inativar(ContaModel model) {

        if (model.getInativo() == null) {
            model.setInativo(new Date());
        }

        contaDao.alterar(model);
    }

    public void bloquear(ContaModel model) {
        if (model.getBloqueado() == null) {
            model.setBloqueado(new Date());
            contaDao.alterar(model);
        }
    }

    public void ativar(ContaModel model) {
        if (model.getInativo() != null) {
            model.setInativo(null);
            contaDao.alterar(model);
        }
    }

    public void desbloquear(ContaModel model) {
        if (model.getBloqueado() != null) {
            model.setBloqueado(null);
            contaDao.alterar(model);
        }
    }

    public void logoff(ContaModel model) {
        model.setToken(null);
        this.alterar(model);
    }

    public void alterarSenha(ContaModel model, String senha) {
        model.setSenha(senha);
        this.alterar(model);
    }

    public ContaModel login(Login login) {
        try {
            String senhaEncriptada = new EncriptAES().byteParaString(new EncriptAES().encrypt(login.getSenha(), EncriptAES.getChaveEncriptacao()));

            //autenticar usuario
            ContaModel model = this.getConta(login.getEmail(), senhaEncriptada);
            if (model != null && model.getBloqueado() == null) {
                String token = new TokenUtil().criarToken(model.getId());
                model.setToken(token);
                model.setUltimoAcesso(new Date());
                this.alterar(model);
                return model;
            }
            return null;

        } catch (Exception e) {
            System.out.println("Erro ao logar: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

}
