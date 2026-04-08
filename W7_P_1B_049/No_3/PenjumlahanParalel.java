package No_3;

import java.util.Scanner;
import java.util.concurrent.atomic.AtomicLong;

public class PenjumlahanParalel {

    // AtomicLong digunakan sebagai akumulator total yang thread-safe
    // Operasi addAndGet() bersifat atomik sehingga tidak perlu blok synchronized
    private static AtomicLong totalSum = new AtomicLong(0);

    public static void main(String[] args) throws InterruptedException {

        Scanner sc = new Scanner(System.in);

        // Membaca input dari pengguna
        System.out.print("Masukkan Jumlah Thread : ");
        int numThreads = sc.nextInt();

        System.out.print("Masukkan Angka Akhir   : ");
        long angkaAkhir = sc.nextLong();
        sc.close();

        // Hitung ukuran rentang per thread (Divide and Conquer)
        long rentangPerThread = angkaAkhir / numThreads;

        Thread[] threads = new Thread[numThreads];

        // Buat dan konfigurasi setiap thread
        for (int i = 0; i < numThreads; i++) {
            final int threadIndex = i;

            // Tentukan batas awal dan akhir rentang untuk thread ini
            final long start = (long) threadIndex * rentangPerThread + 1;
            // Thread terakhir mengambil sisa angka (menangani pembagian tidak genap)
            final long end = (threadIndex == numThreads - 1) ? angkaAkhir
                    : (long) (threadIndex + 1) * rentangPerThread;

            threads[i] = new Thread(() -> {
                long partialSum = 0; // Variabel lokal, tidak ada konflik antar thread

                System.out.printf("[%s] Mengerjakan penjumlahan dari %d sampai %d%n",
                        Thread.currentThread().getName(), start, end);

                // Lakukan penjumlahan pada rentang yang ditugaskan
                for (long j = start; j <= end; j++) {
                    partialSum += j;
                }

                System.out.printf("[%s] Hasil parsial (%d - %d) = %d%n",
                        Thread.currentThread().getName(), start, end, partialSum);

                // Tambahkan hasil parsial ke total secara atomik (thread-safe)
                totalSum.addAndGet(partialSum);

            }, "Thread-" + (i + 1));
        }

        System.out.println(); // Baris kosong untuk keterbacaan output

        // Jalankan semua thread secara bersamaan (paralel)
        for (Thread t : threads) {
            t.start();
        }

        // Tunggu semua thread selesai sebelum mencetak hasil akhir
        for (Thread t : threads) {
            t.join();
        }

        // Cetak hasil akhir
        System.out.println("\n=== HASIL AKHIR ===");
        System.out.println("Total penjumlahan 1 s.d. " + angkaAkhir + " = " + totalSum.get());

        // Verifikasi dengan rumus matematika: n*(n+1)/2
        long expected = angkaAkhir * (angkaAkhir + 1) / 2;
        System.out.println("Verifikasi rumus n*(n+1)/2 = " + expected);
        System.out.println("Status: " + (totalSum.get() == expected ? "BENAR ✓" : "SALAH ✗"));
    }
}
