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
        List<EventoModel> lista = entityManager.createQuery("Select e from EventoModel e where :categoria in elements(e.categorias)").setParameter("categoria", categoria).getResultList();
        entityManager.close();
        return lista;
    }

    @SuppressWarnings("unchecked")
    public List<EventoModel> buscarCancelados() {
        EntityManager entityManager = JPAUtil.getInstance().getEntityManager();
        List<EventoModel> lista = entityManager.createQuery("from EventoModel where cancelado!= :cancelado").setParameter("cancelado", null).getResultList();
        entityManager.close();
        return lista;
    }

    @SuppressWarnings("unchecked")
    public List<EventoModel> buscarCensurados() {
        EntityManager entityManager = JPAUtil.getInstance().getEntityManager();
        List<EventoModel> lista = entityManager.createQuery("from EventoModel where censurado!= :censurado").setParameter("censurado", null).getResultList();
        entityManager.close();
        return lista;
    }

    @SuppressWarnings("unchecked")
    public List<EventoModel> buscarDestaques() {
        EntityManager entityManager = JPAUtil.getInstance().getEntityManager();
        List<EventoModel> lista = entityManager.createQuery("from EventoModel where destaque!= true").getResultList();
        entityManager.close();
        return lista;
    }

    @SuppressWarnings("unchecked")
    public List<EventoModel> buscarNaoCancelados() {
        EntityManager entityManager = JPAUtil.getInstance().getEntityManager();
        List<EventoModel> lista = entityManager.createQuery("from EventoModel where cancelado = :cancelado").setParameter("cancelado", null).getResultList();
        entityManager.close();
        return lista;
    }

    @SuppressWarnings("unchecked")
    public List<EventoModel> buscarNaoCensurados() {
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
    public List<EventoModel> buscarNaoCanceladosENaoCensurados() {
        EntityManager entityManager = JPAUtil.getInstance().getEntityManager();
        List<EventoModel> lista = entityManager.createQuery("from EventoModel where cancelado = :cancelado and censurado = :censurado").setParameter("cancelado", null).setParameter("censurado", null).getResultList();
        entityManager.close();
        return lista;
    }

    @SuppressWarnings("unchecked")
    public List<EventoModel> buscarCanceladosECensurados() {
        EntityManager entityManager = JPAUtil.getInstance().getEntityManager();
        List<EventoModel> lista = entityManager.createQuery("from EventoModel where cancelado != :cancelado and censurado != :censurado").setParameter("cancelado", null).setParameter("cancelado", null).getResultList();
        entityManager.close();
        return lista;
    }

    @SuppressWarnings("unchecked")
    public List<EventoModel> buscarPelaIdade(int idade) {
        EntityManager entityManager = JPAUtil.getInstance().getEntityManager();
        List<EventoModel> lista = entityManager.createQuery("from EventoModel where idade >= :idade").setParameter("idade", idade).getResultList();
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
    public List<EventoModel> buscarEventosPorDia(int dia) {
        EntityManager entityManager = JPAUtil.getInstance().getEntityManager();

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.DAY_OF_MONTH, dia);
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
