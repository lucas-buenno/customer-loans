package com.estudo.desafio_emprestimo.service;

import com.estudo.desafio_emprestimo.controller.dto.CustomerDTO;
import com.estudo.desafio_emprestimo.controller.dto.CustomerLoansDTO;
import com.estudo.desafio_emprestimo.models.Loans;
import com.estudo.desafio_emprestimo.repositories.LoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class LoansService {

    @Autowired
    private LoanRepository loanRepository;

    public CustomerLoansDTO LoansEligibility(CustomerDTO dto) {

        if (dto.UF() == null || dto.income() == null || dto.age() == null) {
            throw new IllegalArgumentException("The fields \"Age\", \"UF\" and \"Income\" are mandatory.");
        }
        List<Loans> loans = new ArrayList<>();
        checkLoansEligibility(dto, loans);
        return customerLoansDTO(dto, loans);

    }

    public void checkLoansEligibility(CustomerDTO customerdto, List<Loans> loans ) {

        if (isEligibleForPersonal(customerdto)) {
            Loans personal = loanRepository.findByType("PERSONAL".toUpperCase());
            loans.add(personal);
        }

        if (isEligibleForConsignment(customerdto)) {
            Loans consignment = loanRepository.findByType("CONSIGNMENT".toUpperCase());
            loans.add(consignment);
        }

        if (isEligibleForGuaranteed(customerdto)) {
            Loans guaranteed = loanRepository.findByType("GUARANTEED".toUpperCase());
            loans.add(guaranteed);
        }
    }

    public boolean isEligibleForPersonal(CustomerDTO customerDTO) {
        if (customerDTO.income() <= 3000.00) {
            return true;
        }

        else if (customerDTO.income() >= 3000
                && customerDTO.income() <= 5000
                && customerDTO.age() < 30
                && customerDTO.UF().equals("SP".toUpperCase())
                ) {
            return true;
        }

        return false;
    }

    public boolean isEligibleForConsignment(CustomerDTO customerDTO) {
        return customerDTO.income() >= 5000.00;
    }

    public boolean isEligibleForGuaranteed(CustomerDTO customerDTO) {
        if (customerDTO.income() <= 3000.00) {
            return true;
        } else if (customerDTO.income() >= 3000
                && customerDTO.income() <= 5000
                && customerDTO.age() < 30
                && customerDTO.UF().equals("SP".toUpperCase())
        ) {
            return true;
        }
        return false;
    }

    public CustomerLoansDTO customerLoansDTO(CustomerDTO dto, List<Loans> loans) {
        CustomerLoansDTO customerLoansDTO = new CustomerLoansDTO();
        customerLoansDTO.setCustomer(dto.name());
        customerLoansDTO.setLoansCustomer(loans);
        return customerLoansDTO;
    }

}
