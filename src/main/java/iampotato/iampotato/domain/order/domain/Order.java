package iampotato.iampotato.domain.order.domain;

import iampotato.iampotato.domain.customer.domain.Customer;
import iampotato.iampotato.domain.orderitem.domain.OrderItem;
import iampotato.iampotato.domain.review.domain.Review;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Order <-> OrderItem = 1 : N
 * Order -> Review = 1 : 1
 * Order -> Customer = N : 1
 */
@Entity
@Table(name = "orders")
@Builder
@AllArgsConstructor
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Order {

    @Id
    @GeneratedValue
    @Column(name = "order_id")
    private Long id;

    @Builder.Default
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> orderItems = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    // cascade 를 설정해줘야 review 를 지워줄 수 있습니다. review 의 경우 Order 에 제약조건이 있어, review 단독으로 drop 이 불가능합니다.
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "review_id")
    private Review review;

    private int totalPrice;

    private int totalPeople;

    private String qrValue;

    private LocalDateTime createdDate;

    private LocalDateTime modifiedDate;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus; // 주문 상태 [ORDER, WAIT_QR, FINISH]


    // == 연관관계 메서드 == //
    public void addOrderItem(OrderItem orderItem) {
        orderItems.add(orderItem);
        orderItem.setOrder(this);
    }

    // 임시 메소드
    public void updateCollection(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }
}
