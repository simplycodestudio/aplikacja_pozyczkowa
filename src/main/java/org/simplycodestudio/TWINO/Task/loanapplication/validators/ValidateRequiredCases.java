package org.simplycodestudio.TWINO.Task.loanapplication.validators;

import org.joda.time.DateTime;
import org.joda.time.Interval;
import org.joda.time.ReadableInstant;
import org.simplycodestudio.TWINO.Task.loanapplication.loan.Proposal;

import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class ValidateRequiredCases {

    private static final ReadableInstant midnight = DateTime.now().withTimeAtStartOfDay();
    private static final ReadableInstant sixOClock = DateTime.now().withTime(6,0,0,0);
    private static Interval interval;
    private static ResourceBundle resourceBundle = ResourceBundle.getBundle("variables");
    private static Boolean switchTimeToNightMode = Boolean.parseBoolean(resourceBundle.getString("is.night"));
    private static Integer maxAmount = Integer.parseInt(resourceBundle.getString("amount.max"));


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

    public static boolean isProposalWasSubmitedThreeTimesFromSameIp(List<Proposal> borrowerIP) {
      return true;
    }
}
