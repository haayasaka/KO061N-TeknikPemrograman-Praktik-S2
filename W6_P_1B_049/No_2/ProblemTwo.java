package No_2;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ProblemTwo {
    public static void main(String[] args) {
        List<Student> students = Arrays.asList(
                new Student(101, "Joe"),
                new Student(103, "Zulkifli"),
                new Student(102, "Riza"),
                new Student(104, "Alice"),
                new Student(105, "Joshua"));

        List<Student> ans = students.stream() // [1] stream() - membuka stream
                .sorted((s1, s2) -> { // [2] sorted() - melakukan pengurutan
                    // Logika: Jika nama sama, bandingkan ID
                    if (s1.getName().equalsIgnoreCase(s2.getName())) {
                        return Integer.compare(s1.getId(), s2.getId()); // [3] Integer.compare() - bandingkan dua ID
                                                                        // secara aman
                    }
                    // Jika nama berbeda, bandingkan Nama
                    else {
                        return s1.getName().compareToIgnoreCase(s2.getName()); // [4] compareToIgnoreCase() - bandingkan
                                                                               // string tanpa mempedulikan case
                    }
                })
                .collect(Collectors.toList()); // [5] collect() - mengumpulkan hasil

        for (Student student : ans) {
            System.out.println(student);
        }
    }
}
