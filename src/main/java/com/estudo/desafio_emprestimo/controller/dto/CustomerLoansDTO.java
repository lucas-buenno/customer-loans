package com.estudo.desafio_emprestimo.controller.dto;

import com.estudo.desafio_emprestimo.models.Loans;

import java.util.ArrayList;
import java.util.List;


public class CustomerLoansDTO {


    private String customer;
    private List<Loans> loansCustomer;


    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public List<Loans> getLoansCustomer() {
        return loansCustomer;
    }

    public void setLoansCustomer(List<Loans> loansCustomer) {
        this.loansCustomer = loansCustomer;
    }
}
