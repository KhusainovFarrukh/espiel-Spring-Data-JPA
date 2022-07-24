package kh.farrukh.espielspringdatajpa.main.exception.custom_exceptions;

import kh.farrukh.espielspringdatajpa.main.exception.ApiException;
import kh.farrukh.espielspringdatajpa.main.utils.constants.ExceptionMessages;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class NotEnoughPermissionException extends ApiException {

    public NotEnoughPermissionException() {
        super(
                "You don't have enough permission",
                HttpStatus.FORBIDDEN,
                ExceptionMessages.EXCEPTION_NOT_ENOUGH_PERMISSION,
                new Object[]{}
        );
    }
}