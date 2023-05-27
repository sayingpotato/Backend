package iampotato.iampotato.domain.store.exception;

import lombok.Getter;

@Getter
public class StoreException extends RuntimeException {

    private final StoreExceptionGroup storeExceptionGroup;

    public StoreException(StoreExceptionGroup storeExceptionGroup) {
        this.storeExceptionGroup = storeExceptionGroup;
    }
}