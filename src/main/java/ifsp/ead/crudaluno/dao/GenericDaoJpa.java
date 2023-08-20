package ifsp.ead.crudaluno.dao;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

public class GenericDaoJpa <T, K> implements GenericDao <T, K> {

    private final EntityManager entityManager;
    private final Class<T> entityClass;

    public GenericDaoJpa(Class<T> entityClass) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("Alunos-PU");
        this.entityManager = entityManagerFactory.createEntityManager();
        this.entityClass = entityClass;
    }

    @Override
    public void create(T entity) {
        entityManager.getTransaction().begin();
        entityManager.persist(entity);
        entityManager.getTransaction().commit();
    }

    @Override
    public void update(T entity) {
        entityManager.getTransaction().begin();
        entityManager.merge(entity);
        entityManager.getTransaction().commit();
    }

    @Override
    public void delete(K key) {
        T entity = find(key);
        if(entity!=null){
            entityManager.getTransaction().begin();
            entityManager.remove(entity);
            entityManager.getTransaction().commit();
        }
    }

    @Override
    public T find(K key) {
        return entityManager.find(entityClass, key);
    }

    @Override
    public List<T> findAll() {
        CriteriaQuery<T> criteriaQuery = entityManager.getCriteriaBuilder().createQuery(entityClass);
        criteriaQuery.select(criteriaQuery.from(entityClass));
        return entityManager.createQuery(criteriaQuery).getResultList();
    }
    @Override
    public List<T> findByName(String nome){
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

        CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(entityClass);
        Root<T> root = criteriaQuery.from(entityClass);

        Predicate predicate = criteriaBuilder.like(root.get("nome"), "%" + nome + "%");
        criteriaQuery.where(predicate);

        return entityManager.createQuery(criteriaQuery).getResultList();
    }
}
