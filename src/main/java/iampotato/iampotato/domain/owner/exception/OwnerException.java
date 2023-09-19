package iampotato.iampotato.domain.owner.exception;

import lombok.Getter;

@Getter
public class OwnerException extends RuntimeException {

    private final OwnerExceptionGroup ownerExceptionGroup;

    public OwnerException(OwnerExceptionGroup ownerExceptionGroup) {
        this.ownerExceptionGroup = ownerExceptionGroup;
    }
}
