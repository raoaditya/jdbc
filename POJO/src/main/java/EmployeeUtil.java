import java.util.Scanner;

public class EmployeeUtil {
    public static void display(Employee employee) {
        if (employee == null) {
            return;
        }
        System.out.println(employee);
    }

    public static void displayAll(Employee[] employeesArray) {
        for (Employee e: employeesArray) {
            if(e == null) break;
            System.out.println(e);
        }
    }

    public static int increaseSalary(int empoyeeId, float increasePercentage) {
        return 0;
    }

    public static Employee readEmployee() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter empno");
        int empno = scanner.nextInt();
        System.out.println("Enter empame");
        String name= scanner.next();
        System.out.println("Enter Salary");
        float salary = scanner.nextFloat();
        System.out.println("Enter age");
        int age = scanner.nextInt();

        Employee emp = new Employee(name, empno, salary, age);
        return emp;
    }
}
