package com.estudo.desafio_emprestimo.repositories;

import com.estudo.desafio_emprestimo.models.Loans;
import com.estudo.desafio_emprestimo.models.LoansTypePlans;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoanRepository extends JpaRepository<Loans, Integer> {

    Loans findByTypePlan(LoansTypePlans typePlan);
}
