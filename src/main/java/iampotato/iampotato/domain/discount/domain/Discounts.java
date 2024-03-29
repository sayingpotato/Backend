package iampotato.iampotato.domain.discount.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Getter
@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Discounts {

    @OneToMany(mappedBy = "store", cascade = CascadeType.ALL)
    private List<Discount> discounts = new ArrayList<>();

    public Discounts(Discount... discounts) {
        this.discounts = List.of(discounts);
    }

    public Discount getMaxDiscountRatio() {
        Comparator<Discount> comparatorByDiscountRatio = Comparator.comparingInt(Discount::getDiscountRatio);
        return discounts.stream()
                .max(comparatorByDiscountRatio)
                .get();
    }

    public Discount getMinDiscountRatio() {
        Comparator<Discount> comparatorByDiscountRatio = Comparator.comparingInt(Discount::getDiscountRatio);
        return discounts.stream()
                .min(comparatorByDiscountRatio)
                .get();
    }
}
