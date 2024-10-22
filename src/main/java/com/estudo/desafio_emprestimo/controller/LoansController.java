package com.estudo.desafio_emprestimo.controller;

import com.estudo.desafio_emprestimo.controller.dto.CustomerDTO;
import com.estudo.desafio_emprestimo.controller.dto.CustomerLoansDTO;
import com.estudo.desafio_emprestimo.exception.InvalidDataLoanException;

import com.estudo.desafio_emprestimo.service.LoansService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoansController {

    @Autowired
    LoansService loansService;

    @PostMapping("/customer-loans")
    public ResponseEntity<?> loansEligibility(@RequestBody CustomerDTO dto) {
        try {CustomerLoansDTO customerDTO = loansService.LoansEligibility(dto);
            return ResponseEntity.ok().body(customerDTO);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }

    }

}
