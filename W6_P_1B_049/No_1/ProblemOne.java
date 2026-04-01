package No_1;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ProblemOne {
    public static void main(String[] args) {
        ArrayList<Employee> list = new ArrayList<>();
        list.add(new Employee("Alice", 50000));
        list.add(new Employee("bob", 70000));
        list.add(new Employee("rob", 40000));
        list.add(new Employee("john", 10000));

        // Melakukan proses filtering/sorting menggunakan Stream API
        List<Employee> sortedEmp = list.stream() // [1] Ubah list ke stream
                .sorted((e1, e2) -> // [2] sorted() - intermediate operation
                e1.getName().compareTo(e2.getName())) // [3] getName() - getter untuk nama
                .collect(Collectors.toList()); // [4] collect(), [5] Collectors

        for (Employee e : sortedEmp) {
            System.out.println(e);
        }
    }
}
