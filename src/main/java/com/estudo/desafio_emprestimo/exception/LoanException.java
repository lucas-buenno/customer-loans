package com.estudo.desafio_emprestimo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

public class LoanException extends RuntimeException {

    public ProblemDetail toProblemDetail() {
        var problemDetail = ProblemDetail.forStatus(HttpStatus.INTERNAL_SERVER_ERROR);

        problemDetail.setTitle("Os dados inseridos são inválidos");
        return problemDetail;
    }
}
