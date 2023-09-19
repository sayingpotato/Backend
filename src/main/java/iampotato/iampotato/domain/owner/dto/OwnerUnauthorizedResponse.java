package iampotato.iampotato.domain.owner.dto;

import iampotato.iampotato.domain.owner.domain.Owner;
import lombok.Data;

@Data
public class OwnerUnauthorizedResponse {

    private String id;

    private String ssn;

    private String businessNumber;

    public OwnerUnauthorizedResponse(Owner owner) {
        id = owner.getId();
        ssn = owner.getSsn();
        businessNumber = owner.getOwnerBusinessNumber();
    }
}
