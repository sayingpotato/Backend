package iampotato.iampotato.domain.order.application;

import iampotato.iampotato.domain.customer.dao.CustomerRepository;
import iampotato.iampotato.domain.customer.domain.Customer;
import iampotato.iampotato.domain.item.dao.ItemRepository;
import iampotato.iampotato.domain.item.domain.Items;
import iampotato.iampotato.domain.itemoption.dao.ItemOptionRepository;
import iampotato.iampotato.domain.itemoption.domain.ItemOptions;
import iampotato.iampotato.domain.order.dao.OrderRepository;
import iampotato.iampotato.domain.order.domain.Order;
import iampotato.iampotato.domain.order.domain.OrderStatus;
import iampotato.iampotato.domain.order.dto.*;
import iampotato.iampotato.domain.order.exception.OrderException;
import iampotato.iampotato.domain.order.exception.OrderExceptionGroup;
import iampotato.iampotato.domain.review.domain.Review;
import iampotato.iampotato.domain.store.dao.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    private final CustomerRepository customerRepository;

    private final ItemRepository itemRepository;

    private final StoreRepository storeRepository;

    private final ItemOptionRepository itemOptionRepository;

    public List<Order> getOrderDetail(String userId, int offset, int limit) {

        List<Order> orders = orderRepository.findOrders(userId, offset, limit);

        if (orders.isEmpty()) {
            throw new OrderException(OrderExceptionGroup.ORDER_NULL);
        }

        return orders;
    }

    public List<Order> getOrderRequest(String ownerId, OrderOwnerRequest request, int offset, int limit) {

        List<Order> orders = orderRepository.findOrderRequest(ownerId, request.getStoreId(), offset, limit);

        if (orders.isEmpty()) {
            throw new OrderException(OrderExceptionGroup.ORDER_NULL);
        }

        return orders;
    }

    @Transactional
    public Order postOrder(String userId, OrderPostRequest request) {

        Customer customer = customerRepository.findById(userId).orElseThrow();
        Items items = new Items(itemRepository.findByIds(request.getItemIds()));
        ItemOptions itemOptions = new ItemOptions(itemOptionRepository.findByIds(request.getItemOptionIds()));
        Review review = Review.builder()
                .store(storeRepository.findById(request.getStoreId()))
                .build();

        Order order = Order.builder()
                .customer(customer)
                .discountPrice(request.getDiscountPrice())
                .orderStatus(OrderStatus.ORDER)
                .totalPrice(request.getTotalPrice())
                .totalPeople(request.getTotalPeople())
                .review(review)
                .createdDate(LocalDateTime.now())
                .modifiedDate(LocalDateTime.now())
                .build();

        items.createOrderItemForItem(order);
        itemOptions.createOrderItemForItemOption(order);

        orderRepository.save(order);

        return order;
    }

    public OrderDiscountsResponse getTotalDiscounts(String userId) {

        return orderRepository.findTotalDiscounts(userId);
    }

    public List<OrderRecentDiscountsResponse> getRecentDiscounts(String userId) {

        return orderRepository.findRecentDiscounts(userId);
    }

    @Transactional
    public Order acceptOrder(OrderAcceptRequest request) {

        Order order = orderRepository.findById(request.getOrderId());

        order.acceptOrder();

        return order;
    }

    @Transactional
    public Order rejectOrder(OrderRejectRequest request) {

        Order order = orderRepository.findById(request.getOrderId());

        order.rejectOrder();

        return order;
    }

    public List<OrderDailyRevenueResponse> findDailyRevenues(String ownerId, Long storeId) {

        return orderRepository.findDailyRevenues(ownerId, storeId);
    }

    public List<OrderDailyItemResponse> findDailyItems(String ownerId, Long storeId) {

        return orderRepository.findDailyItemsByWeekly(ownerId, storeId, LocalDateTime.now());
    }

    public List<OrderDailyItemResponse> findDailyItemsTomorrow(String ownerId, Long storeId) {

        return orderRepository.findDailyItemsByWeekly(ownerId, storeId, LocalDateTime.now().plusDays(1));
    }

    public OrderProfitResponse findProfit(String ownerId, Long storeId) {

        OrderProfitResponse profitByWeekly = orderRepository.findProfitByWeekly(ownerId, storeId, LocalDateTime.now());
        OrderProfitByDayResponse profitByDay = orderRepository.findProfitByDay(ownerId, storeId, LocalDateTime.now());

        return new OrderProfitResponse(profitByWeekly.getTotalCount(),
                Math.round((profitByWeekly.getAverageCount() + profitByDay.getCount()) / 2),
                profitByWeekly.getTotalPrice(),
                Math.round((profitByWeekly.getAveragePrice() + profitByDay.getSum())) / 2);
    }

    public OrderProfitResponse findProfitTomorrow(String ownerId, Long storeId) {

        OrderProfitResponse profitByWeekly = orderRepository.findProfitByWeekly(ownerId, storeId, LocalDateTime.now().minusDays(1));
        OrderProfitByDayResponse profitByDay = orderRepository.findProfitByDay(ownerId, storeId, LocalDateTime.now().minusDays(1));

        return new OrderProfitResponse(profitByWeekly.getTotalCount(),
                Math.round((profitByWeekly.getAverageCount() + profitByDay.getCount()) / 2),
                profitByWeekly.getTotalPrice(),
                Math.round((profitByWeekly.getAveragePrice() + profitByDay.getSum())) / 2);
    }

    public List<OrderMonthlyProfitResponse> findMonthlyProfit(String ownerId, Long storeId) {

        return orderRepository.findMonthlyProfit(ownerId, storeId);
    }

    public List<OrderDailyProfitResponse> findDailyProfit(String ownerId, Long storeId) {

        return orderRepository.findDailyProfit(ownerId, storeId);
    }

    public List<OrderItemByWeekResponse> findItemByWeek(String ownerId, Long storeId) {

        return orderRepository.findDailyItemsByWeek(ownerId, storeId);
    }
}
