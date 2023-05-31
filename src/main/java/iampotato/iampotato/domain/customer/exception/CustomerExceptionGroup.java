package iampotato.iampotato.domain.customer.exception;

import lombok.Getter;

@Getter
public enum CustomerExceptionGroup {

    CUSTOMER_ID_NULL(400, "C001", "존재하지 않는 아이디입니다."),
    CUSTOMER_PASSWORD_WRONG(400, "C002", "비밀번호가 일치하지 않습니다."),
    CUSTOMER_DUPLICATED_ID(400, "C003", "이미 존재하는 아이디입니다."),
    CUSTOMER_DUPLICATED_NICKNAME(400, "C004", "이미 존재하는 닉네임입니다."),
    CUSTOMER_IMAGE_NULL(400, "C005", "이미지 파일이 비어있습니다."),
    CUSTOMER_IMAGE_EXTENSION_NULL(400, "C006", "이미지 파일의 확장자가 존재하지 않는 잘못된 파일입니다."),
    CUSTOMER_IMAGE_EXTENSION_WRONG(400, "C007", "이미지 파일의 확장자가 허용되지 않는 확장자입니다. jpg, png, gif 파일만 사용 가능합니다.");

    private final int httpCode;
    private final String errorCode;
    private final String message;

    CustomerExceptionGroup(int httpCode, String errorCode, String message) {
        this.httpCode = httpCode;
        this.errorCode = errorCode;
        this.message = message;
    }
}
