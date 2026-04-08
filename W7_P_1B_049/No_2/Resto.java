package No_2;

class Resto {
    private int chickenStock = 100;

    // SOLUSI: Menambahkan 'synchronized' agar proses pengecekan stok dan 
    // pengurangan stok menjadi atomik (tidak bisa disela oleh thread lain).
    // SEBELUMNYA: Terjadi Race Condition karena beberapa thread bisa masuk
    // ke blok 'if' secara bersamaan sebelum stok dikurangi.
    public synchronized void serveCustomer(String cashierName) {
        if (chickenStock > 0) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
            }

            chickenStock--;
            System.out.println(cashierName + " berhasil menjual 1 ayam. Sisa stok: " + chickenStock);
        } else {
            System.out.println(cashierName + " gagal: Stok Habis!");
        }
    }

    public int getRemainingStock() {
        return chickenStock;
    }
}
