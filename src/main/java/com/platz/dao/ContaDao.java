package com.platz.dao;

import com.platz.model.ContaModel;
import com.platz.model.Perfil;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author Anderson
 */
public class ContaDao extends GenericDao<ContaModel> {

    @Override
    public void cadastrar(ContaModel model) {

        ContaModel conta = this.buscarPeloEmail(model.getEmail());

        if (conta == null) {
            super.cadastrar(model);
        } else {
            System.out.println("Erro ao cadastrar, email já existe");
        }
    }

    public ContaModel getConta(String token) {
        try {
            EntityManager entityManager = JPAUtil.getInstance().getEntityManager();
            ContaModel model = (ContaModel) entityManager.createQuery("from ContaModel where token=:token").setParameter("token", token).getSingleResult();
            entityManager.close();
            if (model == null) {
                System.out.println("Conta não Encontrada");
                return null;
            } else {
                return model;
            }
        } catch (Exception e) {
            System.out.println("Erro ao buscar conta: " + e.getMessage());
            return null;
        }
    }
     public ContaModel getContaPorTokenAndroid(String token) {
        try {
            EntityManager entityManager = JPAUtil.getInstance().getEntityManager();
            ContaModel model = (ContaModel) entityManager.createQuery("from ContaModel where tokenAndroid=:token").setParameter("token", token).getSingleResult();
            entityManager.close();
            if (model == null) {
                System.out.println("Conta não Encontrada");
                return null;
            } else {
                return model;
            }
        } catch (Exception e) {
            System.out.println("Erro ao buscar conta: " + e.getMessage());
            return null;
        }
    }

    public ContaModel getConta(String email, String senha) {        
        try {
            EntityManager entityManager = JPAUtil.getInstance().getEntityManager();
            ContaModel model = (ContaModel) entityManager.createQuery("from ContaModel where email=:email and senha=:senha")
                    .setParameter("email", email).setParameter("senha", senha).getSingleResult();
            entityManager.close();
            if (model == null) {
                System.out.println("Conta não Encontrada");
                return null;
            } else {
                return model;
            }
        } catch (Exception e) {
            System.out.println("Erro ao buscar conta: " + e.getMessage());
            return null;
        }
    }

    public ContaModel buscarPeloEmail(String email) {

        try {
            EntityManager entityManager = JPAUtil.getInstance().getEntityManager();
            ContaModel model = (ContaModel) entityManager.createQuery("from ContaModel where email =:email")
                    .setParameter("email", email).getSingleResult();
            entityManager.close();
            return model;
        } catch (Exception e) {
            System.out.println("Erro ao buscar pelo email" + e.getMessage());
            return null;
        }
    }

    public List<ContaModel> buscarPeloPerfilAtivosEBloqeuados(Perfil perfil) {

        EntityManager entityManager = JPAUtil.getInstance().getEntityManager();
        List<ContaModel> lista = entityManager.createQuery("from ContaModel where perfil =:perfil and inativo =:inativo and bloqueado = :bloqueado").setParameter("perfil", perfil).setParameter("inativo", null).setParameter("bloqueado", null).getResultList();
        entityManager.close();
        return lista;

    }

    public List<ContaModel> buscarPeloPerfilInativos(Perfil perfil) {

        EntityManager entityManager = JPAUtil.getInstance().getEntityManager();
        List<ContaModel> lista = entityManager.createQuery("from ContaModel where perfil =:perfil and inativo != :inativo").setParameter("perfil", perfil).setParameter("inativo", null).getResultList();
        entityManager.close();
        return lista;

    }

    public List<ContaModel> buscarPeloPerfilBloqueados(Perfil perfil) {

        EntityManager entityManager = JPAUtil.getInstance().getEntityManager();
        List<ContaModel> lista = entityManager.createQuery("from ContaModel where perfil =:perfil and bloqueado != :bloqueado").setParameter("perfil", perfil).setParameter("bloqueado", null).getResultList();
        entityManager.close();
        return lista;

    }

    public List<ContaModel> buscarInativos() {
        EntityManager entityManager = JPAUtil.getInstance().getEntityManager();
        List<ContaModel> lista = entityManager.createQuery("from ContaModel where inativo !=:atividade").setParameter("atividade", null).getResultList();
        entityManager.close();

        return lista;
    }

    public List<ContaModel> buscarAtivos() {
        EntityManager entityManager = JPAUtil.getInstance().getEntityManager();
        List<ContaModel> lista = entityManager.createQuery("from ContaModel where inativo =:atividade").setParameter("atividade", null).getResultList();
        entityManager.close();

        return lista;
    }

}
