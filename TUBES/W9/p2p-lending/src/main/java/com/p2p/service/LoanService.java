package com.p2p.service;

import com.p2p.domain.*;
import java.math.BigDecimal;

public class LoanService {
    public Loan createLoan(Borrower borrower, BigDecimal amount) {
        validateBorrower(borrower);
        // Tambahan validasi amount
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Loan amount must be positive");
        }
        Loan loan = new Loan();
        if (borrower.getCreditScore() >= 600) {
            loan.approve();
        } else {
            loan.reject();
        }
        return loan;
    }

    private void validateBorrower(Borrower borrower) {
        if (!borrower.isVerified()) {
            throw new IllegalArgumentException("Borrower not verified");
        }
    }
}
