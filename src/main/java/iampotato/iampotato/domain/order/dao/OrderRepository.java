package iampotato.iampotato.domain.order.dao;

import iampotato.iampotato.domain.order.domain.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class OrderRepository {

    private final EntityManager em;

    public List<Order> findOrder(String userId) {

        return em.createQuery("select distinct o from Order o" +
                        " left join fetch o.orderItems oi" +
                        " where o.customer.id = :id" +
                        " order by o.createdDate desc", Order.class)
                .setParameter("id", userId)
                .getResultList();
    }
}
