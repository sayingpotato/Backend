package iampotato.iampotato.domain.customer.dto;

import lombok.Data;

@Data
public class SignUpResponse {
    private Long id;

    public SignUpResponse(Long id) {
        this.id = id;
    }
}
