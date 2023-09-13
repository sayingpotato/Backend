package iampotato.iampotato.domain.owner.dao;

import iampotato.iampotato.domain.owner.domain.Owner;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
public class OwnerRepository {

    private final EntityManager em;

    public Owner findById(String id) {
        return em.find(Owner.class, id);
    }
}
