package iampotato.iampotato.domain.store.dto.maplist;

import iampotato.iampotato.domain.discount.domain.Discount;
import lombok.Data;

/**
 * StoreMapListResponse 에서 사용되는 Discount
 */
@Data
public class StoreMapListDiscount {

    private int people;

    private int discountRatio;

    public StoreMapListDiscount(Discount discount) {
        this.people = discount.getPeople();
        this.discountRatio = discount.getDiscountRatio();
    }
}
