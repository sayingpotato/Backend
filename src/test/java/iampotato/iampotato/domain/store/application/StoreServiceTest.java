package iampotato.iampotato.domain.store.application;

import iampotato.iampotato.domain.store.dao.StoreRepository;
import iampotato.iampotato.domain.store.domain.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class) // junit5 사용시 선언
@SpringBootTest
@Transactional
class StoreServiceTest {

    @Autowired
    StoreService storeService;

    @Autowired
    StoreRepository storeRepository;

    @Test
    void 가게_등록_정상_작동() {

        Store store = Store.createStoreWithRequiredValue("좋은원두",
                StoreCategory.CAFE,
                StorePaymentType.PREPAID,
                StoreSalesType.HALL,
                new Address("주소1", "주소2", 2, 5),
                "01000000000",
                StoreStatus.CLOSED,
                StoreDiscountInfo.TODAY_DISCOUNT);

        Long id = storeService.registerStore(store);
        Store findStore = storeRepository.findById(id);


        assertThat(store).isEqualTo(findStore);
    }
}