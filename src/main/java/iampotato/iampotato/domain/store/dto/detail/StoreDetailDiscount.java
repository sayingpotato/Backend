package iampotato.iampotato.domain.store.dto.detail;

import iampotato.iampotato.domain.discount.domain.Discount;
import iampotato.iampotato.domain.discount.domain.DiscountDay;
import lombok.Data;

@Data
public class StoreDetailDiscount {

    private int people;

    private int discountRatio;

    private DiscountDay discountDay;

    public StoreDetailDiscount(Discount discount) {
        this.people = discount.getPeople();
        this.discountRatio = discount.getDiscountRatio();
        this.discountDay = discount.getDiscountDay();
    }
}
