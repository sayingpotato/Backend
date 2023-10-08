package iampotato.iampotato.domain.customer.dto;

import iampotato.iampotato.domain.customer.domain.CustomerStatus;
import iampotato.iampotato.domain.owner.domain.OwnerStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@AllArgsConstructor
public class TokenResponse {
    private String grantType;
    private String accessToken;

    private CustomerStatus customerStatus;

    private OwnerStatus ownerStatus;

    //    private String refreshToken;
}