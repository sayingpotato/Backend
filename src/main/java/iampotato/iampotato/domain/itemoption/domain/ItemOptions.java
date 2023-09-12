package iampotato.iampotato.domain.itemoption.domain;

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
public class ItemOptions {

    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL)
    private List<ItemOption> itemOptions = new ArrayList<>();

    public ItemOptions(ItemOption... itemOptions) {
        this.itemOptions = List.of(itemOptions);
    }

    public ItemOptions(List<ItemOption> itemOptions) {
        this.itemOptions = itemOptions;
    }

    public List<OrderItem> createOrderItemForItemOption(Order order) {
        return itemOptions.stream()
                .map(io -> OrderItem.createOrderItemForItemOption(io, order))
                .collect(Collectors.toList());
    }
}
