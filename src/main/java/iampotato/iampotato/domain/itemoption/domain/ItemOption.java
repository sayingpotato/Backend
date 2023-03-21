package iampotato.iampotato.domain.itemoption.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ItemOption {

    @Id @GeneratedValue
    @Column(name = "item_option_id")
    private Long id;

    private String category; // 사이즈, 토핑추가, 맵기정도 등 카테고리 구분하기 위함

    private String name;

    private int price;

    private String description;

    private LocalDateTime createdDate;

    private LocalDateTime modifiedDate;

    @Enumerated(EnumType.STRING)
    private ItemOptionStatus status;
}
