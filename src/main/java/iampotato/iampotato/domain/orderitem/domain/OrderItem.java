package iampotato.iampotato.domain.orderitem.domain;

import iampotato.iampotato.domain.item.domain.Item;
import iampotato.iampotato.domain.itemoption.domain.ItemOption;
import iampotato.iampotato.domain.order.domain.Order;
import lombok.*;

import javax.persistence.*;

/**
 * OrderItem <-> Order = N : 1
 * OrderItem -> Item = N : 1
 * OrderItem -> ItemOption = N : 1
 */
@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderItem {

    @Id
    @GeneratedValue
    @Column(name = "order_item_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_option_id")
    private ItemOption itemOption;

    private int totalPrice; // item 가격 + itemOption 가격

    public static OrderItem createOrderItemForItem(Item item, Order order) {
        OrderItem orderItem = OrderItem.builder()
                .item(item)
                .order(order)
                .totalPrice(item.getPrice())
                .build();
        order.addOrderItem(orderItem);
        return orderItem;
    }

    public static OrderItem createOrderItemForItemOption(ItemOption itemOption, Order order) {
        OrderItem orderItem = OrderItem.builder()
                .item(itemOption.getItem())
                .itemOption(itemOption)
                .order(order)
                .totalPrice(itemOption.getPrice())
                .build();
        order.addOrderItem(orderItem);
        return orderItem;
    }

    // == 연관관계 메서드 == //

    public void setOrder(Order order) {
        this.order = order;
    }
}
