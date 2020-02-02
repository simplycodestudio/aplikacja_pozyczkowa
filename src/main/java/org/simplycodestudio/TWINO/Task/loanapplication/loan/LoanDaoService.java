package org.simplycodestudio.TWINO.Task.loanapplication.loan;

import org.joda.time.DateTime;
import org.simplycodestudio.TWINO.Task.loanapplication.validators.ValidateRequiredCases;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.util.ArrayList;

import java.util.Iterator;
import java.util.List;

@Component
public class LoanDaoService {
    private static List<Loan> loans = new ArrayList<>();
    private static List<Proposal> proposals = new ArrayList<>();

    private static int loansCount = 1;
    private static int proposalsCount=0;

    private Proposal proposal;
    static {

        loans.add(new Loan(1, 10000, LocalDate.of(2020,01,02)));
    }

    public List<Loan> findAll() {
        return loans;
    }

    public List<Proposal> findAllproposals() {
        return proposals;
    }

    public Loan save(Loan loan, HttpServletRequest request) {

        proposal =new Proposal();
        if (loan.getId()==null){
              loan.setId(++loansCount);
        }
        proposal.setLoanId(loansCount);
        proposal.setBorrowerIP(request.getRemoteAddr());
        proposal.setId(++proposalsCount);
        proposal.setApplicationSubmissionDate(DateTime.now());

        proposals.add(proposal);

        System.out.println(ValidateRequiredCases.isProposalWasSubmittedAtNightZone(DateTime.now()));
        System.out.println(ValidateRequiredCases.isAmountExceedMaxValue(loan.getAmount()));
        for (Proposal p: proposals) {
            System.out.println(p.getBorrowerIP());
        }
        System.out.println(ValidateRequiredCases.isProposalWasSubmitedThreeTimesFromSameIp(proposals));
        loans.add(loan);
        return loan;
    }

    public Loan findOne(int id) {
        for (Loan loan:loans) {
            if (loan.getId()==id){
                return loan;
            }

        }
        return null;
    }

    public Loan deleteById(int id) {
        Iterator<Loan> iterator = loans.iterator();
        while (iterator.hasNext()) {
            Loan loan = iterator.next();
            if (loan.getId()==id){
                iterator.remove();
                return loan;
            }

        }
        return null;
    }

    public Loan update(String loan) {

        return null;
    }
}
