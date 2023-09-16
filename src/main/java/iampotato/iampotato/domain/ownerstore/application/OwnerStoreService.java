package iampotato.iampotato.domain.ownerstore.application;

import iampotato.iampotato.domain.owner.domain.Owner;
import iampotato.iampotato.domain.ownerstore.dao.OwnerStoreRepository;
import iampotato.iampotato.domain.ownerstore.domain.OwnerStore;
import iampotato.iampotato.domain.store.domain.Store;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OwnerStoreService {

    private final OwnerStoreRepository ownerStoreRepository;

    @Transactional
    public void registerOwnerStore(Store store, Owner owner) {

        OwnerStore ownerStore = OwnerStore.builder()
                .store(store)
                .owner(owner)
                .build();

        ownerStoreRepository.save(ownerStore);
    }
}
