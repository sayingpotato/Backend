package iampotato.iampotato.domain.customer.dto;

import iampotato.iampotato.domain.customer.domain.CustomerStatus;
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

    //    private String refreshToken;
}
