package com.clientui.clientui.exceptions;

import feign.Response;
import feign.codec.ErrorDecoder;
import org.springframework.stereotype.Component;


public class CustomErrorDecoder  implements ErrorDecoder {
    private final ErrorDecoder defaultErrorDecoder = new Default();
    @Override
    public Exception decode(String invoqueur, Response reponse) {
        if (reponse.status() == 400){
            return new ProductBadRequestException("Requête incorrecte");
        }
        return defaultErrorDecoder.decode(invoqueur, reponse);
    }
}
