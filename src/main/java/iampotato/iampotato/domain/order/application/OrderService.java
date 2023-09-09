package iampotato.iampotato.domain.order.application;

import iampotato.iampotato.domain.order.dao.OrderRepository;
import iampotato.iampotato.domain.order.domain.Order;
import iampotato.iampotato.domain.order.exception.OrderException;
import iampotato.iampotato.domain.order.exception.OrderExceptionGroup;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    public List<Order> getOrderDetail(String userId) {

        List<Order> orders = orderRepository.findOrder(userId);

        if (orders.isEmpty()) {
            throw new OrderException(OrderExceptionGroup.ORDER_NULL);
        }

        return orders;
    }
}
