package iampotato.iampotato.domain.item.domain;

import iampotato.iampotato.domain.itemoption.domain.ItemOption;
import iampotato.iampotato.domain.store.domain.Store;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Item <-> Store = N : 1
 * Item <-> ItemOption = N : 1
 */
@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Item {

    @Id
    @GeneratedValue
    @Column(name = "item_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id")
    private Store store;

    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL)
    private List<ItemOption> itemOptions = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private ItemCategory category;

    private String name;

    private int price;

    private String img;

    private String description;

    @Enumerated(EnumType.STRING)
    private ItemPopularity popularity; // [NONE, MOST_FAVORITE, MANY_REORDER]

    private LocalDateTime createdDate;

    private LocalDateTime modifiedDate;

    @Enumerated(EnumType.STRING)
    private ItemStatus status; // [FOR_SALE, SOLD_OUT]


    // 임시용 함수
    public void updateCollection(List<ItemOption> itemOptions) {
        this.itemOptions = itemOptions;
    }
}
