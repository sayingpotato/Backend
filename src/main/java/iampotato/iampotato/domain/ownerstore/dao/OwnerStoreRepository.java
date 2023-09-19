package iampotato.iampotato.domain.ownerstore.dao;

import iampotato.iampotato.domain.ownerstore.domain.OwnerStore;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
public class OwnerStoreRepository {

    private final EntityManager em;

    public void save(OwnerStore ownerStore) {
        em.persist(ownerStore);
    }
}
