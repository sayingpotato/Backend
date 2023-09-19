package iampotato.iampotato.global.exception;

import iampotato.iampotato.domain.item.exception.ItemException;
import iampotato.iampotato.domain.order.exception.OrderException;
import iampotato.iampotato.domain.owner.exception.OwnerException;
import iampotato.iampotato.domain.store.exception.StoreException;
import iampotato.iampotato.global.util.ErrorResult;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class ExceptionController {

    @ExceptionHandler(StoreException.class)
    public ErrorResult StoreExceptionHandler(StoreException e) {
        return new ErrorResult(e.getStoreExceptionGroup().getHttpCode(),
                e.getStoreExceptionGroup().getErrorCode(),
                e.getStoreExceptionGroup().getMessage());
    }

    @ExceptionHandler(OrderException.class)
    public ErrorResult OrderExceptionHandler(OrderException e) {
        return new ErrorResult(e.getOrderExceptionGroup().getHttpCode(),
                e.getOrderExceptionGroup().getErrorCode(),
                e.getOrderExceptionGroup().getMessage());
    }

    @ExceptionHandler(ItemException.class)
    public ErrorResult ItemExceptionHandler(ItemException e) {
        return new ErrorResult(e.getItemExceptionGroup().getHttpCode(),
                e.getItemExceptionGroup().getErrorCode(),
                e.getItemExceptionGroup().getMessage());
    }

    @ExceptionHandler(OwnerException.class)
    public ErrorResult OwnerExceptionHandler(OwnerException e) {
        return new ErrorResult(e.getOwnerExceptionGroup().getHttpCode(),
                e.getOwnerExceptionGroup().getErrorCode(),
                e.getOwnerExceptionGroup().getMessage());
    }

    /**
     * MethodArgumentNotValidException.class
     * &#064;Valid 사용시에 @RequestBody 어노테이션으로 받은 파라미터의 경우 호출되는 예외입니다.
     * BindException.class
     * &#064;Valid  사용시 @ModelAttribute 어노테이션으로 받은 파라미터의 경우 발생하는 예외입니다.
     * 그 외에도 enum 에서 바인딩이 안될시 호출되는 예외입니다.
     * 예를들어 json 으로 온 값이 enum 값에 매핑되는게 없을시 호출됩니다.
     * 참고로 MethodArgumentNotValidException.class 는 BindException 을 상속받은 것입니다.
     */
    @ExceptionHandler({MethodArgumentNotValidException.class, BindException.class})
    public List<ErrorResult> MethodArgNotValidExceptionHandler(BindException e) {

        BindingResult bindingResult = e.getBindingResult();
        List<ErrorResult> errorResults = new ArrayList<>();

        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            ValidExceptionGroup validExceptionGroup = ValidExceptionGroup.findValidError(fieldError.getCode());
            errorResults.add(new ErrorResult(validExceptionGroup.getHttpCode(),
                    validExceptionGroup.getErrorCode(),
                    validExceptionGroup.getMessage()));

        }

        return errorResults;
    }

}
