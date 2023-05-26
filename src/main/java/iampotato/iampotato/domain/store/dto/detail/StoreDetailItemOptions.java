package iampotato.iampotato.domain.store.dto.detail;

import iampotato.iampotato.domain.itemoption.domain.ItemOption;
import lombok.Data;

@Data
public class StoreDetailItemOptions {

    Long id;

    String name;

    public StoreDetailItemOptions(ItemOption itemOption) {
        this.id = itemOption.getId();
        this.name = itemOption.getName();
    }
}
