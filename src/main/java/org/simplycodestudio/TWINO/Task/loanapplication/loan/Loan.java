package org.simplycodestudio.TWINO.Task.loanapplication.loan;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.simplycodestudio.TWINO.Task.loanapplication.utils.ValidateRequiredCases;


import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.Max;
import javax.validation.constraints.Positive;
import java.time.LocalDate;


@Data
@AllArgsConstructor
public class Loan {

    private Integer id;
    @Positive(message = "Pożyczka musi dotyczyć kwoty większej niż 0")
    private Integer amount;
    @FutureOrPresent
    private LocalDate repaymentDate;
    private boolean wasProlonged;
}
