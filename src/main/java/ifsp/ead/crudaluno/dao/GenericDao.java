package ifsp.ead.crudaluno.dao;
import java.util.List;

public interface GenericDao <T,K> {
    void create(T entity);

    void update(T entity);

    void delete(K key);

    T find(K key);

    List<T> findAll();

    List<T> findByName(String nome);
}