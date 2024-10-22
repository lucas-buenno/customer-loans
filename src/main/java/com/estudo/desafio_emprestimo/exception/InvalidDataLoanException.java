package com.estudo.desafio_emprestimo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

public class InvalidDataLoanException extends LoanException {


    private final String detail;

    public InvalidDataLoanException(String detail) {
        this.detail = detail;
    }

    @Override
    public ProblemDetail toProblemDetail() {
        var problemDetail = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        problemDetail.setDetail(detail);
        return problemDetail;
    }
}
