package com.estudo.desafio_emprestimo.models;

import com.estudo.desafio_emprestimo.models.enums.LoansTypePlans;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name = "loans_tb")
public class Loans {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonIgnore
    private Integer id;
    @Enumerated(EnumType.STRING)
    private LoansTypePlans typePlan;
    private Double interest_rate;


    public Loans() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Loans(LoansTypePlans typePlan, Double interest_rate) {
        this.typePlan = typePlan;
        this.interest_rate = interest_rate;
    }

    public LoansTypePlans getTypePlan() {
        return typePlan;
    }

    public void setTypePlan(LoansTypePlans typePlan) {
        this.typePlan = typePlan;
    }

    public Double getInterest_rate() {
        return interest_rate;
    }

    public void setInterest_rate(Double interest_rate) {
        this.interest_rate = interest_rate;
    }
}
