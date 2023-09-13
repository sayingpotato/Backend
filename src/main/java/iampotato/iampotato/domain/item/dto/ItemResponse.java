package iampotato.iampotato.domain.item.dto;

import iampotato.iampotato.domain.item.domain.Item;
import iampotato.iampotato.domain.item.domain.ItemCategory;
import iampotato.iampotato.domain.item.domain.ItemPopularity;
import iampotato.iampotato.domain.item.domain.ItemStatus;
import iampotato.iampotato.domain.itemoption.domain.ItemOption;
import iampotato.iampotato.domain.itemoption.domain.ItemOptionCategory;
import iampotato.iampotato.domain.itemoption.domain.ItemOptionStatus;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class ItemResponse {

    private List<ItemDetailOption> itemOptions;

    private ItemCategory category;

    private String name;

    private int price;

    private String img;

    private String description;

    private ItemPopularity popularity;

    private ItemStatus status;

    @Data
    public static class ItemDetailOption {

        private ItemOptionCategory category;

        private String name;

        private int price;

        private String description;

        private ItemOptionStatus status;

        public ItemDetailOption(ItemOption itemOption) {
            this.category = itemOption.getCategory();
            this.name = itemOption.getName();
            this.price = itemOption.getPrice();
            this.description = itemOption.getDescription();
            this.status = itemOption.getStatus();
        }
    }

    public ItemResponse(Item item) {
        this.category = item.getCategory();
        this.name = item.getName();
        this.price = item.getPrice();
        this.img = item.getImg();
        this.description = item.getDescription();
        this.popularity = item.getPopularity();
        this.status = item.getStatus();

        this.itemOptions = item.getItemOptions().getItemOptions().stream()
                .map(ItemDetailOption::new)
                .collect(Collectors.toList());
    }
}
