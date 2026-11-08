package org.transactionfeeprocessor.exception;

import lombok.Getter;

@Getter
public class RuleNotFoundException extends RuntimeException {

    private final String errorMessage;
    private final int errorCode;

    public RuleNotFoundException(int errorCode, String errorMessage){
        super(errorMessage);

        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

}
