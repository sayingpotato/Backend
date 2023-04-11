package iampotato.iampotato.domain.store.dto;

import lombok.Data;

/**
 * StoreMapListResponse 에서 사용되는 Discount
 */
@Data
public class StoreMapListDiscount {

    private int people;

    private int discountRatio;

    public StoreMapListDiscount(int people, int discountRatio) {
        this.people = people;
        this.discountRatio = discountRatio;
    }
}
