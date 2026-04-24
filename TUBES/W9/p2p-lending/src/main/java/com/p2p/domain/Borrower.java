package com.p2p.domain;

public class Borrower {

    // Status verifikasi KYC
    private boolean verified;

    // Nilai credit score borrower
    private int creditScore;

    // Minimal skor kredit borrower
    public static final int MIN_CREDIT_SCORE_FOR_LOAN = 600;

    // Constructor untuk inisialisasi data borrower
    public Borrower(boolean verified, int creditScore) {
        this.verified = verified;
        this.creditScore = creditScore;
    }

    // Getter untuk mengecek apakah borrower sudah verified
    public boolean isVerified() {
        return verified;
    }

    // Getter untuk mengambil credit score
    public int getCreditScore() {
        return creditScore;
    }

    public boolean canApplyLoan() {
        return verified;
    }

    public boolean hasGoodCreditScore() {
        return creditScore >= MIN_CREDIT_SCORE_FOR_LOAN;
    }
}
// Class ini merepresentasikan pinjaman
