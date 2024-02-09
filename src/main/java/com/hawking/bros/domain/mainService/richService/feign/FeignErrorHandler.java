package com.hawking.bros.domain.mainService.richService.feign;

import feign.Response;
import feign.codec.ErrorDecoder;
import org.springframework.stereotype.Service;

@Service
public class FeignErrorHandler implements ErrorDecoder {

    @Override
    public Exception decode(String methodKey, Response response) {
        if (response.status() == 401) {
            throw  new FeignException("Ошибка авторизации");
        } else if (response.status() == 500) {
            throw  new FeignException("Ошибка сервера");
        }
        return feign.FeignException.errorStatus(methodKey, response);
    }
}
