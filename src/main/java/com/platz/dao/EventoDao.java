package com.platz.dao;

import com.platz.model.CategoriaModel;
import com.platz.model.EmpresaModel;
import com.platz.model.EventoModel;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author 15153770
 */
public class EventoDao extends GenericDao<EventoModel> {

    @SuppressWarnings("unchecked")
    public List<EventoModel> buscarPeloNome(String nome) {
        EntityManager entityManager = JPAUtil.getInstance().getEntityManager();
        List<EventoModel> lista = entityManager.createQuery("from EventoModel where nome like :nome").setParameter("nome", nome + "%").getResultList();
        entityManager.close();
        return lista;
    }

    @SuppressWarnings("unchecked")
    public List<EventoModel> buscarPelaEmpresa(EmpresaModel empresa) {
        EntityManager entityManager = JPAUtil.getInstance().getEntityManager();
        List<EventoModel> lista = entityManager.createQuery("from EventoModel where empresa = :empresa").setParameter("empresa", empresa).getResultList();
        entityManager.close();
        return lista;
    }

    @SuppressWarnings("unchecked")
    public List<EventoModel> buscarPelaCategoria(CategoriaModel categoria) {
        EntityManager entityManager = JPAUtil.getInstance().getEntityManager();
        List<EventoModel> lista = entityManager.createQuery("from EventoModel where categorias= :categoria").setParameter("categoria", categoria).getResultList();
        entityManager.close();
        return lista;
    }

    @SuppressWarnings("unchecked")
    public List<EventoModel> buscarPeloCancelados() {
        EntityManager entityManager = JPAUtil.getInstance().getEntityManager();
        List<EventoModel> lista = entityManager.createQuery("from EventoModel where cancelado!= :cancelado").setParameter("cancelado", null).getResultList();
        entityManager.close();
        return lista;
    }

    @SuppressWarnings("unchecked")
    public List<EventoModel> buscarPeloCensurados() {
        EntityManager entityManager = JPAUtil.getInstance().getEntityManager();
        List<EventoModel> lista = entityManager.createQuery("from EventoModel where censurado!= :censurado").setParameter("censurado", null).getResultList();
        entityManager.close();
        return lista;
    }

    @SuppressWarnings("unchecked")
    public List<EventoModel> buscarPeloDestaques() {
        EntityManager entityManager = JPAUtil.getInstance().getEntityManager();
        List<EventoModel> lista = entityManager.createQuery("from EventoModel where destaque!= true").getResultList();
        entityManager.close();
        return lista;
    }

    @SuppressWarnings("unchecked")
    public List<EventoModel> buscarPeloNaoCancelados() {
        EntityManager entityManager = JPAUtil.getInstance().getEntityManager();
        List<EventoModel> lista = entityManager.createQuery("from EventoModel where cancelado = :cancelado").setParameter("cancelado", null).getResultList();
        entityManager.close();
        return lista;
    }

    @SuppressWarnings("unchecked")
    public List<EventoModel> buscarPeloNaoCensurados() {
        EntityManager entityManager = JPAUtil.getInstance().getEntityManager();
        List<EventoModel> lista = entityManager.createQuery("from EventoModel where censurado = :censurado").setParameter("censurado", null).getResultList();
        entityManager.close();
        return lista;
    }

    @SuppressWarnings("unchecked")
    public List<EventoModel> buscarSemDestaques() {
        EntityManager entityManager = JPAUtil.getInstance().getEntityManager();
        List<EventoModel> lista = entityManager.createQuery("from EventoModel where destaque!= false").getResultList();
        entityManager.close();
        return lista;
    }

    @SuppressWarnings("unchecked")
    public List<EventoModel> buscarPeloNaoCanceladosENaoCensurados() {
        EntityManager entityManager = JPAUtil.getInstance().getEntityManager();
        List<EventoModel> lista = entityManager.createQuery("from EventoModel where cancelado = :cancelado and censurado = :censurado").setParameter("cancelado", null).setParameter("cancelado", null).getResultList();
        entityManager.close();
        return lista;
    }

    @SuppressWarnings("unchecked")
    public List<EventoModel> buscarPeloCanceladosECensurados() {
        EntityManager entityManager = JPAUtil.getInstance().getEntityManager();
        List<EventoModel> lista = entityManager.createQuery("from EventoModel where cancelado != :cancelado and censurado != :censurado").setParameter("cancelado", null).setParameter("cancelado", null).getResultList();
        entityManager.close();
        return lista;
    }

    @SuppressWarnings("unchecked")
    public List<EventoModel> buscarPelaIdade(int idade) {
        EntityManager entityManager = JPAUtil.getInstance().getEntityManager();
        List<EventoModel> lista = entityManager.createQuery("from EventoModel where idade = :idade").setParameter("idade", idade).getResultList();
        entityManager.close();
        return lista;
    }

    @SuppressWarnings("unchecked")
    public List<EventoModel> buscarPeloEventosPassados() {
        EntityManager entityManager = JPAUtil.getInstance().getEntityManager();
        List<EventoModel> lista = entityManager.createQuery("from EventoModel where dataFim < :dataFim").setParameter("dataFim", new Date()).getResultList();
        entityManager.close();
        return lista;
    }

    @SuppressWarnings("unchecked")
    public List<EventoModel> buscarPeloEventosFuturos() {
        EntityManager entityManager = JPAUtil.getInstance().getEntityManager();
        List<EventoModel> lista = entityManager.createQuery("from EventoModel where dataInicio > :dataInicio").setParameter("dataInicio", new Date()).getResultList();
        entityManager.close();
        return lista;
    }

    @SuppressWarnings("unchecked")
    public List<EventoModel> buscarEventosDaSemana() {
        EntityManager entityManager = JPAUtil.getInstance().getEntityManager();

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.DAY_OF_MONTH, 7);
        Date dataProximaSemana = calendar.getTime();

        List<EventoModel> lista = entityManager.createQuery("from EventoModel where dataInicio between :dataAtual and :dataProximaSemana").setParameter("dataAtual", new Date()).setParameter("dataProximaSemana", dataProximaSemana).getResultList();
        entityManager.close();
        return lista;
    }

    @SuppressWarnings("unchecked")
    public List<EventoModel> buscarPeloValorMaximo(Double valor) {
        EntityManager entityManager = JPAUtil.getInstance().getEntityManager();
        List<EventoModel> lista = entityManager.createQuery("from EventoModel where preco <= :preco").setParameter("preco", valor).getResultList();
        entityManager.close();
        return lista;
    }

    @SuppressWarnings("unchecked")
    public List<EventoModel> buscarGratuitos() {
        EntityManager entityManager = JPAUtil.getInstance().getEntityManager();
        List<EventoModel> lista = entityManager.createQuery("from EventoModel where preco = 0.0").getResultList();
        entityManager.close();
        return lista;
    }
}
