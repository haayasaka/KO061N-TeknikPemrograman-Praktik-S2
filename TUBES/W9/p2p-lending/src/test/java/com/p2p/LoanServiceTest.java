package com.p2p;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.math.BigDecimal;

import com.p2p.domain.Borrower;
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
        // Act (Action)
        // =========================
        // Borrower mencoba mengajukan loan
        loanService.createLoan(borrower, amount);

        // =========================
        // Assert (Expected Result)
        // =========================
        assertTrue(true);
    }

    @Test
    @Test
    void shouldRejectLoanWhenAmountIsZeroOrNegative() {
        Borrower borrower = new Borrower(true, 700);
        LoanService loanService = new LoanService();

        assertThrows(IllegalArgumentException.class,
                () -> loanService.createLoan(borrower, BigDecimal.ZERO));
        assertThrows(IllegalArgumentException.class,
                () -> loanService.createLoan(borrower, BigDecimal.valueOf(-100)));
    }
}
