package io.github.JeffersonSantanaHorbach.api.controller;

import io.github.JeffersonSantanaHorbach.api.ApiErrors;
import io.github.JeffersonSantanaHorbach.exception.BusinessRuleException;
import io.github.JeffersonSantanaHorbach.exception.PedidoNaoEncontradoException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestControllerAdvice
public class ApplicationControllerAdvice {

    @ExceptionHandler(BusinessRuleException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErrors handlerBusinessRuleException(BusinessRuleException exception){
        String mensagemErro = exception.getMessage();
        return new ApiErrors(mensagemErro);
    }

    @ExceptionHandler(PedidoNaoEncontradoException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiErrors handlePedidoNotFoundException(PedidoNaoEncontradoException exception){
    return new ApiErrors(exception.getMessage());
    }
}
