package iampotato.iampotato.domain.discount.application;

import iampotato.iampotato.domain.discount.dao.DiscountRepository;
import iampotato.iampotato.domain.discount.domain.Discount;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class DiscountService {

    private final DiscountRepository discountRepository;

    @Transactional
    public Long registerDiscount(Discount discount) {
        discountRepository.save(discount);
        return discount.getId();
    }
}
