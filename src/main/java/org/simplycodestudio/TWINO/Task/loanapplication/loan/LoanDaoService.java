package org.simplycodestudio.TWINO.Task.loanapplication.loan;

import org.joda.time.DateTime;
import org.simplycodestudio.TWINO.Task.loanapplication.utils.DateConverterUtil;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Component
public class LoanDaoService {
    private static List<Loan> loans = new ArrayList<>();
    private static List<Proposal> proposals = new ArrayList<>();

    private static int loansCount = 1;
    static {

        loans.add(new Loan(1, 10000, DateConverterUtil.formatDate(new Date())));
    }

    public List<Loan> findAll() {
        return loans;
    }

    public Loan save(Loan loan) {
        if (loan.getId()==null){
            loan.setId(++loansCount);
        }
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

}
