package org.simplycodestudio.TWINO.Task.loanapplication.utils;

import org.joda.time.DateTime;
import org.joda.time.Interval;
import org.joda.time.ReadableInstant;
import org.simplycodestudio.TWINO.Task.loanapplication.loan.Proposal;

import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

public class ValidateRequiredCases {

    private static final ReadableInstant midnight = DateTime.now().withTimeAtStartOfDay();
    private static final ReadableInstant sixOClock = DateTime.now().withTime(6,0,0,0);
    private static Interval interval;
    private static ResourceBundle resourceBundle = ResourceBundle.getBundle("variables");
    private static Boolean switchTimeToNightMode = Boolean.parseBoolean(resourceBundle.getString("is.night"));
    private static int maxAmount = Integer.parseInt(resourceBundle.getString("amount.max"));
    private static int repaymentProlongation = Integer.parseInt(resourceBundle.getString("repayment.prolongation"));
    private static int proposalFromTheSameIP = Integer.parseInt(resourceBundle.getString("proposals.from.same.ip"));



    public static boolean isProposalWasSubmittedAtNightZone(DateTime submissionTime) {

        if (switchTimeToNightMode){
            return true;
        }
        interval = new Interval(midnight, sixOClock);
        return interval.contains(submissionTime);
    }

    public static boolean isAmountExceedMaxValue(Integer amount){
        return amount>=maxAmount;
    }

    public static boolean isProposalWasSubmitedThreeTimesFromSameIp(int borrowerIP) {
        return borrowerIP>=proposalFromTheSameIP;
    }

    public static LocalDate prolongLoanRepaymentIfThisIsFirstTime(LocalDate dateToProlongation) {
        return dateToProlongation.plusDays(repaymentProlongation);
    }
}
