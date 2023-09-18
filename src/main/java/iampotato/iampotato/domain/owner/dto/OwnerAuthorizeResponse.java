package iampotato.iampotato.domain.owner.dto;

import iampotato.iampotato.domain.owner.domain.Owner;
import lombok.Data;

@Data
public class OwnerAuthorizeResponse {

    private String id;

    public OwnerAuthorizeResponse(Owner owner) {
        this.id = owner.getId();
    }
}
