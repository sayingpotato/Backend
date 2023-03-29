package iampotato.iampotato.domain.store.application;

import iampotato.iampotato.domain.store.dao.StoreRepository;
import iampotato.iampotato.domain.store.domain.Store;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class StoreService {

    private final StoreRepository storeRepository;

    @Transactional
    public Long registerStore(Store store) {
        storeRepository.save(store);
        return store.getId();
    }

    public Store findById(Long storeId) {
        return storeRepository.findById(storeId);
    }
}
