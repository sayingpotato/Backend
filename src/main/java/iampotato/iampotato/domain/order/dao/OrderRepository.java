package iampotato.iampotato.domain.order.dao;

import iampotato.iampotato.domain.order.domain.Order;
import iampotato.iampotato.domain.order.dto.OrderDailyItemResponse;
import iampotato.iampotato.domain.order.dto.OrderDailyRevenueResponse;
import iampotato.iampotato.domain.order.dto.OrderDiscountsResponse;
import iampotato.iampotato.domain.order.dto.OrderRecentDiscountsResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class OrderRepository {

    private final EntityManager em;

    public void save(Order order) {
        em.persist(order);
    }

    public Order findById(Long orderId) {
        return em.find(Order.class, orderId);
    }

    public List<Order> findOrders(String userId, int offset, int limit) {

        return em.createQuery("select distinct o from Order o" +
                        " left join fetch o.orderItems oi" +
                        " where o.customer.id = :id" +
                        " order by o.createdDate desc", Order.class)
                .setParameter("id", userId)
                .setFirstResult(offset)
                .setMaxResults(limit)
                .getResultList();
    }

    public List<Order> findOrderRequest(String ownerId, Long storeId, int offset, int limit) {
        return em.createQuery("select distinct o from Order o" +
                        " left join fetch o.orderItems oi" +
                        " join oi.item.store.ownerStores os" +
                        " where oi.item.store.id = :storeId" +
                        " and os.owner.id = :ownerId" +
                        " order by o.createdDate desc", Order.class)
                .setParameter("storeId", storeId)
                .setParameter("ownerId", ownerId)
                .setFirstResult(offset)
                .setMaxResults(limit)
                .getResultList();
    }

    public OrderDiscountsResponse findTotalDiscounts(String userId) {

        return em.createQuery("select new iampotato.iampotato.domain.order.dto.OrderDiscountsResponse(coalesce(sum(o.discountPrice), 0L)) from Order o " +
                        "where o.customer.id = :id ", OrderDiscountsResponse.class)
                .setParameter("id", userId)
                .getSingleResult();
    }

    public List<OrderRecentDiscountsResponse> findRecentDiscounts(String userId) {

        return em.createQuery("select distinct new iampotato.iampotato.domain.order.dto.OrderRecentDiscountsResponse(o.discountPrice, s.name, o.createdDate) from Order o " +
                        "left join o.orderItems oi " +
                        "join oi.item.store s " +
                        "where o.customer.id = :userId", OrderRecentDiscountsResponse.class)
                .setParameter("userId", userId)
                .getResultList();
    }

    public List<OrderDailyRevenueResponse> findDailyRevenues(String ownerId, Long storeId) {
        return em.createQuery(
                        "select new iampotato.iampotato.domain.order.dto.OrderDailyRevenueResponse( " +
                                "function('DAYNAME', o.createdDate), " +
                                "function('DATE_FORMAT', o.createdDate, '%H:00:00'), " +
                                "function('ROUND', count(o.id)/4.0), " +
                                "function('ROUND', sum(o.totalPrice)/4.0)) " +
                                "from Order o " +
                                "left join o.orderItems oi " +
                                "join oi.item.store.ownerStores os " +
                                "where oi.item.store.id = :storeId " +
                                "and os.owner.id = :ownerId " +
                                "and oi.itemOption.id = null " +
                                "and o.createdDate >= :oneMonthAgo " +
                                "group by function('DAYNAME', o.createdDate), function('DATE_FORMAT', o.createdDate, '%H:00:00')" +
                                "order by function('DAYNAME', o.createdDate), function('DATE_FORMAT', o.createdDate, '%H:00:00')", OrderDailyRevenueResponse.class)
                .setParameter("storeId", storeId)
                .setParameter("ownerId", ownerId)
                .setParameter("oneMonthAgo", LocalDateTime.now().minusMonths(1))
                .getResultList();
    }

    public List<OrderDailyItemResponse> findDailyItems(String ownerId, Long storeId) {
        return em.createQuery(
                        "select new iampotato.iampotato.domain.order.dto.OrderDailyItemResponse( " +
                                "function('DAYNAME', o.createdDate), " +
                                "i.id, " +
                                "i.name, " +
                                "function('ROUND', count(*)/4.0)) " +
                                "from Order o " +
                                "left join o.orderItems oi " +
                                "join oi.item i " +
                                "join i.store.ownerStores os " +
                                "where i.store.id = :storeId " +
                                "and os.owner.id = :ownerId " +
                                "and oi.itemOption.id = null " +
                                "and function('DAYNAME', o.createdDate) = function('DAYNAME', :targetDay)" +
                                "and o.createdDate >= :oneMonthAgo " +
                                "group by function('DAYNAME', o.createdDate), i.id, i.name " +
                                "order by i.id", OrderDailyItemResponse.class)
                .setParameter("storeId", storeId)
                .setParameter("ownerId", ownerId)
                .setParameter("targetDay", LocalDateTime.now())
                .setParameter("oneMonthAgo", LocalDateTime.now().minusMonths(1))
                .getResultList();
    }

    public List<OrderDailyItemResponse> findDailyItemsTomorrow(String ownerId, Long storeId) {
        return em.createQuery(
                        "select new iampotato.iampotato.domain.order.dto.OrderDailyItemResponse( " +
                                "function('DAYNAME', o.createdDate), " +
                                "i.id, " +
                                "i.name, " +
                                "function('ROUND', count(*)/4.0)) " +
                                "from Order o " +
                                "left join o.orderItems oi " +
                                "join oi.item i " +
                                "join i.store.ownerStores os " +
                                "where i.store.id = :storeId " +
                                "and os.owner.id = :ownerId " +
                                "and oi.itemOption.id = null " +
                                "and function('DAYNAME', o.createdDate) = function('DAYNAME', :targetDay)" +
                                "and o.createdDate >= :oneMonthAgo " +
                                "group by function('DAYNAME', o.createdDate), i.id, i.name " +
                                "order by i.id", OrderDailyItemResponse.class)
                .setParameter("storeId", storeId)
                .setParameter("ownerId", ownerId)
                .setParameter("targetDay", LocalDateTime.now().plusDays(1))
                .setParameter("oneMonthAgo", LocalDateTime.now().minusMonths(1))
                .getResultList();
    }
}
