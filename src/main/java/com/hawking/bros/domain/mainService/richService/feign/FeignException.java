package com.hawking.bros.domain.mainService.richService.feign;

public class FeignException extends RuntimeException {

    public FeignException() {
        super();
    }

    public FeignException(String message) {
        super(message);
    }

}
