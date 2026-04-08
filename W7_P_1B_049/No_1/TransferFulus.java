package No_1;

public class TransferFulus {
    public static void main(String[] args) throws InterruptedException {
        Account acc1 = new Account();
        Account acc2 = new Account();

        // Thread 1: Menjumlahkan/ transfer fulus dari acc1 ke acc2
        Thread t1 = new Thread(() -> {
            // SOLUSI: Selalu gunakan urutan penguncian yang sama (acc1 lalu acc2)
            synchronized (acc1) { 
                System.out.println("Thread 1: Mengunci acc1...");
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    // Exception diperlukan karena Thread.sleep dapat diinterupsi oleh thread lain
                }

                synchronized (acc2) { 
                    System.out.println("Thread 1: Mengunci acc2 dan melakukan transfer...");
                    acc2.balance += acc1.balance;
                }
            }
        });

        // Thread 2: Menjumlahkan/ transfer fulus dari acc2 ke acc1
        Thread t2 = new Thread(() -> {
            // SEBELUMNYA: Terjadi DEADLOCK karena t2 mencoba mengunci acc2 dulu, sementara t1 mengunci acc1.
            // SOLUSI: Ubah urutan penguncian agar sama dengan t1 (acc1 lalu acc2).
            synchronized (acc1) { 
                System.out.println("Thread 2: Mengunci acc1...");
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                }

                synchronized (acc2) { 
                    System.out.println("Thread 2: Mengunci acc2 dan melakukan transfer...");
                    acc1.balance += acc2.balance;
                }
            }
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println("--- HASIL AKHIR ---");
        System.out.println("Saldo Akhir acc1: " + acc1.balance);
        System.out.println("Saldo Akhir acc2: " + acc2.balance);
    }
}
