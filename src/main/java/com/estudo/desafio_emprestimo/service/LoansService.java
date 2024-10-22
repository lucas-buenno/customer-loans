package com.estudo.desafio_emprestimo.service;

import com.estudo.desafio_emprestimo.controller.dto.CustomerDTO;
import com.estudo.desafio_emprestimo.controller.dto.CustomerLoansDTO;
import com.estudo.desafio_emprestimo.models.Loans;
import com.estudo.desafio_emprestimo.models.enums.BrasilUFsEnum;
import com.estudo.desafio_emprestimo.models.enums.LoansTypePlans;
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
            Loans personal = loanRepository.findByTypePlan(LoansTypePlans.PERSONAL);
            loans.add(personal);
        }

        if (isEligibleForConsignment(customerdto)) {
            Loans consignment = loanRepository.findByTypePlan(LoansTypePlans.CONSIGNMENT);
            loans.add(consignment);
        }

        if (isEligibleForGuaranteed(customerdto)) {
            Loans guaranteed = loanRepository.findByTypePlan(LoansTypePlans.GUARANTEED);
            loans.add(guaranteed);
        }
    }

    public void isUfExists(String uf) {
        try {
            BrasilUFsEnum.valueOf(uf.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException ("Por favor, informe uma UF válida.");
        }
    }

    public boolean isEligibleForPersonal(CustomerDTO customerDTO) {

        isUfExists(customerDTO.UF());
        BrasilUFsEnum uf = BrasilUFsEnum.valueOf(customerDTO.UF().toUpperCase());

        if (customerDTO.income() <= 3000.00) {
            return true;
        }

        if (customerDTO.income() >= 3000
                && customerDTO.income() <= 5000
                && customerDTO.age() < 30
                && uf.equals(BrasilUFsEnum.SP)
                ) {
            return true;
        }

        return false;
    }

    public boolean isEligibleForConsignment(CustomerDTO customerDTO) {
        isUfExists(customerDTO.UF());
        return customerDTO.income() >= 5000.00;
    }

    public boolean isEligibleForGuaranteed(CustomerDTO customerDTO) {
        isUfExists(customerDTO.UF());
        BrasilUFsEnum uf = BrasilUFsEnum.valueOf(customerDTO.UF().toUpperCase());

        if (customerDTO.income() <= 3000.00) {
            return true;
        } else if (customerDTO.income() >= 3000
                && customerDTO.income() <= 5000
                && customerDTO.age() < 30
                && uf.equals(BrasilUFsEnum.SP)
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
