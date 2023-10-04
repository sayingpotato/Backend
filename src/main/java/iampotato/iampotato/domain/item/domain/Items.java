package iampotato.iampotato.domain.item.domain;

import iampotato.iampotato.domain.order.domain.Order;
import iampotato.iampotato.domain.orderitem.domain.OrderItem;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Items {

    @OneToMany(mappedBy = "store", cascade = CascadeType.ALL)
    private List<Item> items = new ArrayList<>();

    public Items(Item... items) {
        this.items = List.of(items);
    }

    public Items(List<Item> items) {
        this.items = items;
    }

    public List<OrderItem> createOrderItemForItem(Order order) {
        return items.stream()
                .map(i -> OrderItem.createOrderItemForItem(i, order))
                .collect(Collectors.toList());
    }

    public List<String> findItemsByName(String name) {
        return items.stream()
                .map(Item::getName)
                .filter(iName -> iName.contains(name))
                .collect(Collectors.toList());
    }
}
