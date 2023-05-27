package iampotato.iampotato.global.util;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorResult {

    public static final int CODE_CLIENT_ERROR = 400;
    public static final int CODE_SERVER_ERROR = 500;

    private int httpCode;
    private String errorCode;
    private String message;
}