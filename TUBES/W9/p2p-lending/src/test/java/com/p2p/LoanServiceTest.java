package com.p2p;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.math.BigDecimal;

import com.p2p.domain.Borrower;
import com.p2p.domain.Loan;
import com.p2p.service.LoanService;

public class LoanServiceTest {
    @Test
    void shouldRejectLoanWhenBorrowerNotVerified() {

        // =====================================================
        // SCENARIO:
        // Borrower tidak terverifikasi (KYC = false)
        // Ketika borrower mengajukan pinjaman
        // Maka sistem harus menolak dengan melempar exception
        // =====================================================

        // =========================
        // Arrange (Initial Condition)
        // =========================
        // Borrower belum lolos proses KYC
        Borrower borrower = new Borrower(false, 700);

        // Service untuk pengajuan loan
        LoanService loanService = new LoanService();

        // Jumlah pinjaman valid
        BigDecimal amount = BigDecimal.valueOf(1000);

        // =========================
        // Act & Assert
        // =========================
        // Borrower mencoba mengajukan loan dan harus gagal
        assertThrows(IllegalArgumentException.class, () -> {
            loanService.createLoan(borrower, amount);
        });
    }

    @Test
    void shouldRejectLoanWhenAmountIsZeroOrNegative() {
        // Arrange
        Borrower borrower = new Borrower(true, 700);
        LoanService loanService = new LoanService();

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> loanService.createLoan(borrower, BigDecimal.ZERO));

        assertThrows(IllegalArgumentException.class, () -> loanService.createLoan(borrower, BigDecimal.valueOf(-100)));
    }

    @Test
    void shouldApproveLoanWhenCreditScoreHigh() {
        Borrower borrower = new Borrower(true, 600); // threshold = 600
        LoanService loanService = new LoanService();
        Loan loan = loanService.createLoan(borrower, BigDecimal.valueOf(1000));
        assertEquals(Loan.Status.APPROVED, loan.getStatus());
    }

    @Test
    void shouldRejectLoanWhenCreditScoreLow() {
        Borrower borrower = new Borrower(true, 599);
        LoanService loanService = new LoanService();
        Loan loan = loanService.createLoan(borrower, BigDecimal.valueOf(1000));
        assertEquals(Loan.Status.REJECTED, loan.getStatus());
    }
}
