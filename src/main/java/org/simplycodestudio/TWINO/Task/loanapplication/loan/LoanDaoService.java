package org.simplycodestudio.TWINO.Task.loanapplication.loan;

import org.joda.time.DateTime;
import org.simplycodestudio.TWINO.Task.loanapplication.exceptions.ProposalAlreadyProlongedException;
import org.simplycodestudio.TWINO.Task.loanapplication.exceptions.TooBigAmountAtNightZone;
import org.simplycodestudio.TWINO.Task.loanapplication.exceptions.TooManyProposalsFromTheSameAdressException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.util.ArrayList;

import java.util.Iterator;
import java.util.List;

import static org.simplycodestudio.TWINO.Task.loanapplication.utils.ValidateRequiredCases.*;

@Service
public class LoanDaoService {
    private static List<Loan> loans = new ArrayList<>();
    private static List<Proposal> proposals = new ArrayList<>();

    private static int loansCount = 1;
    private static int proposalsCount = 0;

    private Proposal proposal;

    static {

        loans.add(new Loan(1, 10000, LocalDate.of(2020, 01, 02), true));
    }

    public List<Loan> findAllLoans() {
        return loans;
    }

    public List<Proposal> findAllProposals() {
        return proposals;
    }

    public Loan save(Loan loan, HttpServletRequest request) {

        final boolean proposalWasSubmittedAtNightZone = isProposalWasSubmittedAtNightZone(DateTime.now());
        final boolean amountExceedMaxValue = isAmountExceedMaxValue(loan.getAmount());
        final boolean threeProposalsFromTheSameIP = isProposalWasSubmitedThreeTimesFromSameIp(countNumberOfProposalsFromTheSameIP(request.getRemoteAddr()));

        if (!threeProposalsFromTheSameIP) {
            if (loan.getId() == null) {
                loan.setId(++loansCount);
            }
            proposal = new Proposal(++proposalsCount, loansCount, request.getRemoteAddr(), DateTime.now());
            proposals.add(proposal);
            if (proposalWasSubmittedAtNightZone && amountExceedMaxValue) {
                loansCount--;
                throw new TooBigAmountAtNightZone("Brak możliwości potwierdzenia wniosku na maksymalną kwotę w godzinach 00:00 - 06:00");
            }
            loans.add(loan);
        } else {
            throw new TooManyProposalsFromTheSameAdressException("Zbyt wiele wniosków zostało złożonych przez Twój adres IP");
        }
        return loan;
    }

    public Loan findOneLoan(int id) {
        for (Loan loan : loans) {
            if (loan.getId() == id) {
                return loan;
            }
        }
        return null;
    }

    public int countNumberOfProposalsFromTheSameIP(String borrowerIp) {
        int theSameIpCounter = 0;
        for (Proposal proposal : proposals) {
            if (proposal.getBorrowerIP().equals(borrowerIp)) {
                ++theSameIpCounter;
            }
        }
        return theSameIpCounter;
    }


    public Loan deleteById(int id) {
        Iterator<Loan> iterator = loans.iterator();
        while (iterator.hasNext()) {
            Loan loan = iterator.next();
            if (loan.getId() == id) {
                iterator.remove();
                return loan;
            }

        }
        return null;
    }

    public Loan update(int id) {
        if (findOneLoan(id) != null) {
            LocalDate dateToProlongation = findOneLoan(id).getRepaymentDate();
            boolean loanHasAlreadyBeenProlonged = findOneLoan(id).isWasProlonged();
            if (!loanHasAlreadyBeenProlonged) {
                findOneLoan(id).setWasProlonged(true);
                LocalDate prolongedDate = prolongLoanRepaymentIfThisIsFirstTime(dateToProlongation);
                findOneLoan(id).setRepaymentDate(prolongedDate);
                return findOneLoan(id);
            } else {
                throw new ProposalAlreadyProlongedException("Ta pożyczka uzyskała już przedłużenie spłaty");
            }
        }
        return null;

    }
}
