package com.pillikan.store.repository.jpa;

import com.pillikan.store.model.Category;
import com.pillikan.store.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class JpaCategoryRepository implements CategoryRepository {
    private final EntityManager em;

    @Override
    public Category getById(int id) {
        return em.find(Category.class, id);
    }

    @Override
    public List<Category> getAll() {
        return em.createQuery("select c from Category c", Category.class)
                .getResultList();
    }

    @Override
    @Transactional
    public boolean delete(int id) {
        return em.createQuery("delete from Category c where c.id = ?1")
                .setParameter(1, id)
                .executeUpdate() != 0;
    }

    @Override
    @Transactional
    public Category create(Category category) {
        em.persist(category);
        return category;
    }
}
