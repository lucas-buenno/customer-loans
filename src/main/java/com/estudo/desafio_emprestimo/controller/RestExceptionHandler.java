package com.estudo.desafio_emprestimo.controller;

import com.estudo.desafio_emprestimo.exception.LoanException;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(LoanException.class)
    public ProblemDetail handlerLoanException(LoanException loanException) {
        return loanException.toProblemDetail();
    }
}
