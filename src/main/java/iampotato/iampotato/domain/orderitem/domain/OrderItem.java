package iampotato.iampotato.domain.orderitem.domain;

import iampotato.iampotato.domain.item.domain.Item;
import iampotato.iampotato.domain.itemoption.domain.ItemOption;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * OrderItem -> Item = N : 1
 * OrderItem -> ItemOption = N : 1
 */
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderItem {

    @Id @GeneratedValue
    @Column(name = "order_item_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_option_id")
    private ItemOption itemOption;

    private int totalPrice; // item 가격 + itemOption 가격
}
