package iampotato.iampotato.domain.item.exception;

import lombok.Getter;

@Getter
public class ItemException extends RuntimeException {

    private final ItemExceptionGroup itemExceptionGroup;

    public ItemException(ItemExceptionGroup itemExceptionGroup) {
        this.itemExceptionGroup = itemExceptionGroup;
    }
}
