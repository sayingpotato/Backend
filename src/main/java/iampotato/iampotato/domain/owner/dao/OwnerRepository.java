package iampotato.iampotato.domain.owner.dao;

import iampotato.iampotato.domain.owner.domain.Owner;
import iampotato.iampotato.domain.owner.domain.OwnerStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class OwnerRepository {

    private final EntityManager em;

    public void save(Owner owner) {
        em.persist(owner);
    }

    public Optional<Owner> findByLoginId(String loginId) {   //LoginId를 통해 모든 Customer 조회
        List<Owner> result = em.createQuery("select o from Owner o where o.loginId = :loginId", Owner.class)    //:loginId라고 하여 밑에서 setParameter를 통해 "loginId"의 value와 바인딩
                .setParameter("loginId", loginId)   //위에 있는 :loginId가 여기 파리미터의 Key값과 바인딩 되어서 value가 위로 넘어가게 됌
                .getResultList();
        return result.stream().findAny();
    }

    public Optional<Owner> findById(String username) {
        List<Owner> result = em.createQuery("select o from Owner o" +
                        " where o.id = :username", Owner.class)
                .setParameter("username", username)
                .getResultList();
        return result.stream().findAny();
    }

    public List<Owner> findUnauthorizedOwners() {
        return em.createQuery("select o from Owner o " +
                        "where o.ownerStatus = :ownerStatus", Owner.class)
                .setParameter("ownerStatus", OwnerStatus.UNAUTHORIZED)
                .getResultList();
    }

    public Optional<Owner> findOwnerStores(String ownerId, int offset, int limit) {
        List<Owner> result = em.createQuery("select o from Owner o " +
                        "left join fetch o.ownerStores os " +
                        "where o.id = :ownerId", Owner.class)
                .setParameter("ownerId", ownerId)
                .setFirstResult(offset)
                .setMaxResults(limit)
                .getResultList();

        return result.stream().findAny();
    }
}
