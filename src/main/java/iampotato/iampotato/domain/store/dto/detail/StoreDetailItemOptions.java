package iampotato.iampotato.domain.store.dto.detail;

import iampotato.iampotato.domain.itemoption.domain.ItemOption;
import lombok.Data;

@Data
public class StoreDetailItemOptions {

    Long id;

    int price;

    String name;

    public StoreDetailItemOptions(ItemOption itemOption) {
        this.id = itemOption.getId();
        this.price = itemOption.getPrice();
        this.name = itemOption.getName();
    }
}
