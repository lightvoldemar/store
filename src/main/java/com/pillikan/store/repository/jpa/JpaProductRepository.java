package com.pillikan.store.repository.jpa;

import com.pillikan.store.dto.ProductDto;
import com.pillikan.store.model.Category;
import com.pillikan.store.model.Product;
import com.pillikan.store.repository.ProductRepository;
import com.pillikan.store.util.ValidationUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

import static com.pillikan.store.util.ValidationUtil.*;

@Repository
public class JpaProductRepository implements ProductRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Product getById(int id) {
        return em.find(Product.class, id);
    }

    @Override
    public List<Product> getAll() {
        return em.createQuery("select p from Product p order by p.title", Product.class)
                .getResultList();
    }

    @Override
    public List<Product> getByCategory(int categoryId) {
        String sql = "select p from Product p where p.category.id = ?1 order by p.title";
        return em.createQuery(sql, Product.class).setParameter(1, categoryId).getResultList();
    }

    @Override
    @Transactional
    public boolean delete(int id) {
        return em.createQuery("delete from Product p where p.id = ?1")
                .setParameter(1, id)
                .executeUpdate() != 0;
    }

    @Override
    @Transactional
    public Product create(Product product) {
        em.persist(product);
        return product;
    }
}
