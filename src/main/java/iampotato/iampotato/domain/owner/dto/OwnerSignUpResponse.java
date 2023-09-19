package iampotato.iampotato.domain.owner.dto;

import iampotato.iampotato.domain.owner.domain.Owner;
import lombok.Data;

@Data
public class OwnerSignUpResponse {

    private String id;

    public OwnerSignUpResponse(Owner owner) {
        this.id = owner.getId();
    }
}
