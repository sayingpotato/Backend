package iampotato.iampotato.domain.store.dto.detail;

import iampotato.iampotato.domain.item.domain.Item;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class StoreDetailItems {

    Long id;

    String name;

    List<StoreDetailItemOptions> storeDetailItemOptions;

    public StoreDetailItems(Item item) {
        this.id = item.getId();
        this.name = item.getName();
        this.storeDetailItemOptions = item.getItemOptions().getItemOptions().stream()
                .map(StoreDetailItemOptions::new)
                .collect(Collectors.toList());
    }
}
