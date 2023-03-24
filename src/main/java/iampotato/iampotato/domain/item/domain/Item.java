package iampotato.iampotato.domain.item.domain;

import iampotato.iampotato.domain.itemoption.domain.ItemOption;
import iampotato.iampotato.domain.store.domain.Store;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Item -> ItemOption = 1 : N
 * Item <-> Store = N : 1
 */
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Item {

    @Id @GeneratedValue
    @Column(name = "item_id")
    private Long id;

    @OneToMany
    private List<ItemOption> itemOptions = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id")
    private Store store;

    private String category;

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

}
