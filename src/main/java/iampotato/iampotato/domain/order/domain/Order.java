package iampotato.iampotato.domain.order.domain;

import iampotato.iampotato.domain.orderitem.domain.OrderItem;
import iampotato.iampotato.domain.review.domain.Review;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Order <-> OrderItem = 1 : N
 * Order -> Review = 1 : 1
 */
@Entity
@Table(name = "orders")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Order {

    @Id @GeneratedValue
    @Column(name = "order_id")
    private Long id;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> orderItems = new ArrayList<>();

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "review_id")
    private Review review;

    private int totalPrice;

    private int totalPeople;

    private String qrValue;

    private LocalDateTime createdDate;

    private LocalDateTime modifiedDate;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus; // 주문 상태 [ORDER, WAIT_QR, FINISH]
}
