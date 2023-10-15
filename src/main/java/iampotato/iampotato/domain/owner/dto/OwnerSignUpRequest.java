package iampotato.iampotato.domain.owner.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OwnerSignUpRequest {

    private String loginId;

    private String password;

    private String nickname;

    private String ownerBusinessNumber;
}
