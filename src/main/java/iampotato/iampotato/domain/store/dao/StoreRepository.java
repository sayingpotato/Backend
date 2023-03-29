package iampotato.iampotato.domain.store.dao;

import iampotato.iampotato.domain.store.domain.Store;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
public class StoreRepository {

    private final EntityManager em;

    public void save(Store store) {
        em.persist(store);
    }

    public Store findById(Long id) {
        return em.find(Store.class, id);
    }
}
