package org.simplycodestudio.TWINO.Task.loanapplication.loan;

import org.joda.time.DateTime;

import javax.validation.constraints.Past;


public class Loan {


    private Integer id;

    private String ip;

    private Integer amount;


    private String loanStartDate;

    

    public Loan(Integer id, Integer amount, String loanStartDate) {
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

    public String getLoanStartDate() {
        return loanStartDate;
    }

    public void setLoanStartDate(String loanStartDate) {
        this.loanStartDate = loanStartDate;
    }


//    public String getIp() {
//        return ip;
//    }

    public void setIp(String ip) {
        this.ip = ip;
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
