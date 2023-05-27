package iampotato.iampotato.domain.item.domain;

import iampotato.iampotato.domain.discount.domain.Discount;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Getter
@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Items {

    @OneToMany(mappedBy = "store", cascade = CascadeType.ALL)
    private List<Item> items = new ArrayList<>();

    public Items(Item... items) {
        this.items = List.of(items);
    }
}
