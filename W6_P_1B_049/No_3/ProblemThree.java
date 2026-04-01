package No_3;

import java.util.Arrays;

public class ProblemThree {
    public static void main(String[] args) {
        int[] arr = { 12, 4, 3, 1, 9, 657 };
        int n = 3; // Mencari elemen terbesar ke-3

        int ans = Arrays.stream(arr) // [1] Arrays.stream() - ubah array menjadi IntStream
                .boxed() // [2] boxed() - konversi IntStream ke Stream<Integer>
                .sorted((a, b) -> Integer.compare(b, a)) // Urutkan descending
                .skip(n - 1) // [3] skip() - lewati n-1 elemen pertama
                .findFirst() // [4] findFirst() - ambil elemen pertama yang tersisa
                .orElse(0); // [5] orElse() - nilai default jika stream kosong

        System.out.println("Elemen terbesar ke-" + n + " adalah: " + ans);
    }
}
