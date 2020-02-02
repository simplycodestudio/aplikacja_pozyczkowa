package org.simplycodestudio.TWINO.Task.loanapplication.loan;


import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.Past;
import javax.validation.constraints.Positive;
import java.text.SimpleDateFormat;
import java.time.LocalDate;


public class Loan {


    private Integer id;

    @Positive
    private Integer amount;

    @FutureOrPresent
    private LocalDate loanStartDate;


    public Loan(Integer id, Integer amount, LocalDate loanStartDate) {
        this.id = id;
        this.amount = amount;
        this.loanStartDate= loanStartDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public LocalDate getLoanStartDate() {
        return loanStartDate;
    }

    public void setLoanStartDate(LocalDate loanStartDate) {
        this.loanStartDate = loanStartDate;
    }

    @Override
    public String toString() {
        return "Loan{" +
                "id=" + id +
                ", amount=" + amount +
                ", loanStartDate=" + loanStartDate +
                '}';
    }
}
