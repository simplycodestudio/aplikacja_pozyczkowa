package org.simplycodestudio.TWINO.Task.loanapplication.loan;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.joda.time.DateTime;

@Data
@AllArgsConstructor
public class Proposal {

    private Integer id;
    private Integer loanId;
    private String borrowerIP;
    private DateTime applicationSubmissionDate;
}
