package iampotato.iampotato.domain.customer.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class SignInRequest {    //로그인 시 Request Body

    private String loginId;
    private String password;
}