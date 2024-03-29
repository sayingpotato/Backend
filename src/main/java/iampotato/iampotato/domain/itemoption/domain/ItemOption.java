package iampotato.iampotato.domain.itemoption.domain;

import iampotato.iampotato.domain.item.domain.Item;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * ItemOption -> Item = N : 1
 */
@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ItemOption {

    @Id
    @GeneratedValue
    @Column(name = "item_option_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    private ItemOptionCategory category; // 사이즈, 토핑추가, 맵기정도 등 카테고리 구분하기 위함

    private String name;

    private int price;

    private String description;

    private LocalDateTime createdDate;

    private LocalDateTime modifiedDate;

    @Enumerated(EnumType.STRING)
    private ItemOptionStatus status;
}
