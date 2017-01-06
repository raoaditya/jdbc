package app;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

import static java.awt.event.PaintEvent.UPDATE;

public class EmployeeApplication {

    EmployeeOperations employeeService = new EmployeeHolder();

    public static void main(String[] args) throws IOException, SQLException, ClassNotFoundException {
        EmployeeApplication application = new EmployeeApplication();
        DBEmployeeOperations db = new DBEmployeeOperations();

        EmployeeServiceEnum choice = null;
        do {
            choice = readUserOption();

            switch (choice) {
                case ADD:
                    System.out.println("1. Enter new employee info: ");
                    db.add();
                    break;
                case UPDATE:
                    System.out.println("Update employee");
                    db.update();
                    break;
                case DELETE:
                    System.out.println("Enter employee id to be deleted:  ");
                    db.delete();
                    break;
                case DISPLAY:
                    System.out.println("Enter employee id: ");
                    db.find();
                    break;
                case DISPALYALL:
                    System.out.println("Employees information is:  ");
                    db.findAll();
                    break;
                case INCREASE_SALAARY:
                    System.out.println("Enter the new salary");
                    db.increaseSalary();
                    break;
                case SORTBYNAME:
                    db.sortByName();
                    break;
                case SORTBYAGE:
                    db.sortByAge();
                    break;
                case UNIQUEEMPLOYEENAMES:
                    db.uniqueEmpName();
                    break;
                case EMPLOYEEAGECOUNT:
                    db.totalEmployeesSameAge();
                    break;
                default:
                    System.out.println("Invalid input.");
                    break;
            }
        } while (choice != null);
    }

    private static EmployeeServiceEnum readUserOption() {
        displayMenu();
        Scanner scanner = new Scanner(System.in);
        int value = scanner.nextInt();
        EmployeeServiceEnum employeeServiceEnm = null;
        do {
            if (value == 11) {
                break;
            }

            employeeServiceEnm = EmployeeServiceEnum.resolveNameByOperationValue(value);
        } while (employeeServiceEnm == null);

        return employeeServiceEnm;
    }

    private static void displayMenu() {
        System.out.println("Select one of the following operation: \n1.Add a new employee\n2.Update employee" +
                "\n 3.Delete employee \n 4.Display employee information \n 5.Display all employees information \n 6.Increase Salary \n 7. Sort by name\n8. Sort by age\n9. Unique employee names\n10. Employees with same age\n11.Exit ");
    }

    private void deleteEmployee() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter emp no");
        int empno = scanner.nextInt();
        employeeService.delete(empno);
    }

    private void increaseSalarySalary() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter emp no");
        int empno = scanner.nextInt();
        employeeService.increaseSalary(empno, 10);
    }

    private void displayEmployees() {
        employeeService.displayAll();
    }

    private void updateEmployee() {
        Employee emp = EmployeeUtil.readEmployee();
        employeeService.update(emp);
    }

    public String addEmployee() {
        Employee emp = EmployeeUtil.readEmployee();
        boolean succes = employeeService.add(emp);
        if (succes) {
            return "Employee added successfully";
        } else {
            return "Employee already exist with given number";
        }
    }
}
