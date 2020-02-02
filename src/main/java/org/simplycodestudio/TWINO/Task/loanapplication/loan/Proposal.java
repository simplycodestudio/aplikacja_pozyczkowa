package org.simplycodestudio.TWINO.Task.loanapplication.loan;

import org.joda.time.DateTime;

public class Proposal {

    private Integer id;
    private Integer loanId;
    private String borrowerIP;
    private DateTime applicationSubmissionDate;

    public Proposal(){};

    public Proposal(Integer id, Integer loanId, String borrowerIP, DateTime applicationSubmissionDate) {
        this.id = id;
        this.loanId = loanId;
        this.borrowerIP = borrowerIP;
        this.applicationSubmissionDate = applicationSubmissionDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getLoanId() {
        return loanId;
    }

    public void setLoanId(Integer loanId) {
        this.loanId = loanId;
    }

    public String getBorrowerIP() {
        return borrowerIP;
    }

    public void setBorrowerIP(String borrowerIP) {
        this.borrowerIP = borrowerIP;
    }

    public DateTime getApplicationSubmissionDate() {
        return applicationSubmissionDate;
    }

    public void setApplicationSubmissionDate(DateTime applicationSubmissionDate) {
        this.applicationSubmissionDate = applicationSubmissionDate;
    }

    @Override
    public String toString() {
        return "Proposal{" +
                "id=" + id +
                ", loanId=" + loanId +
                ", borrowerIP='" + borrowerIP + '\'' +
                ", applicationSubmissionDate=" + applicationSubmissionDate +
                '}';
    }
}
