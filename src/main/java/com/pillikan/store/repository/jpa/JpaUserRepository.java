package com.pillikan.store.repository.jpa;

import com.pillikan.store.model.User;
import com.pillikan.store.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class JpaUserRepository implements UserRepository {
    private final EntityManager em;

    @Override
    public User getById(int id) {
        return em.find(User.class, id);
    }

    @Override
    public List<User> getAll() {
        return em.createNamedQuery(User.ALL, User.class).getResultList();
    }

    @Override
    public User getByEmail(String email) {
        return em.createNamedQuery(User.BY_EMAIL, User.class)
                .setParameter(1, email)
                .getSingleResult();
    }

    @Transactional
    @Override
    public User create(User user) {
        em.persist(user);
        return user;
    }

    @Transactional
    @Override
    public boolean delete(int id) {
        return em.createNamedQuery(User.DELETE)
                .setParameter(1, id)
                .executeUpdate() != 0;
    }
}
