package iampotato.iampotato.domain.discount.dao;

import iampotato.iampotato.domain.discount.domain.Discount;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
public class DiscountRepository {

    private final EntityManager em;

    public void save(Discount discount) {
        em.persist(discount);
    }
}
