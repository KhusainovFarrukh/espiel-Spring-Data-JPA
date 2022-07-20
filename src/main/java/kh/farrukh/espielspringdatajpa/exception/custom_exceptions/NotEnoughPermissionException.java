package kh.farrukh.espielspringdatajpa.exception.custom_exceptions;

import kh.farrukh.espielspringdatajpa.exception.ApiException;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import static kh.farrukh.espielspringdatajpa.utils.constants.ExceptionMessages.EXCEPTION_NOT_ENOUGH_PERMISSION;

@Getter
public class NotEnoughPermissionException extends ApiException {

    public NotEnoughPermissionException() {
        super(
                "You don't have enough permission",
                HttpStatus.FORBIDDEN,
                EXCEPTION_NOT_ENOUGH_PERMISSION,
                new Object[]{}
        );
    }
}