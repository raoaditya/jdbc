public class EmployeeHolder implements EmployeeOperations {
    private Employee[] employees;
    private int employeeCount;
    private static final int ARRAY_SIZE = 2;

    {
        employees = new Employee[ARRAY_SIZE];
        employeeCount = 0;
    }

    @Override
    public boolean add(Employee employee) {
        for (int i = 0; i < employeeCount; i++) {
            if (this.employees[i].getEno() == employee.getEno()) {
                return false;
            }
        }

        if (employeeCount >= ARRAY_SIZE) {
            Employee[] newArr = new Employee[this.employees.length + ARRAY_SIZE];
            System.arraycopy(this.employees, 0, newArr, 0, this.employeeCount);
            this.employees = newArr;
        }

        employees[employeeCount] = employee;
        employeeCount++;
        return true;
    }

    @Override
    public void update(Employee employee) {
        for (int i = 0; i < this.employees.length; i++) {
            if (this.employees[i].getEno() == employee.getEno()) {

                employees[i].setEname(employee.getEname());
                employees[i].setSalary(employee.getSalary());
                employees[i].setAge(employee.getAge());
                employees[i].setAddress(employee.getAddress());

                break;
            }
        }
    }

    @Override
    public int delete(int employeeId) {
        Employee[] empArr = null;
        boolean empFound = false;
        int searchIndex = -1;

        for (int i = 0; i < this.employeeCount; i++) {
            if (employees[i].getEno() == employeeId) {
                empFound = true;
                searchIndex = i;
                break;
            }
        }

        if (empFound) {
            empArr = new Employee[this.employees.length - 1];
            System.arraycopy(this.employees, 0, empArr, 0, searchIndex);
            System.arraycopy(this.employees, searchIndex + 1, empArr, searchIndex, this.employees.length - searchIndex - 1);

            this.employees = empArr;

            employeeCount--;

            return employeeId;
        } else {
            return -1;
        }
    }

    @Override
    public void display(int employeeId) {
        EmployeeUtil.display(find(employeeId));
    }

    @Override
    public void displayAll() {
        System.out.println("\nALL EMPLOYEES :");
        /*for(int i=0;i<this.employeeCount;i++) {
            System.out.println(employees[i]);
        }*/
        EmployeeUtil.displayAll(employees);
    }

    @Override
    public int increaseSalary(int empoyeeId, float increasePercentage) {
        return EmployeeUtil.increaseSalary(empoyeeId, increasePercentage);
    }

    public int increaseSalaries(int[] empoyeeId, float increasePercentage) {
//        return EmployeeUtil.increaseSalary(empoyeeId, increasePercentage);
        return 0;
    }

    public static void main(String[] args) {

        EmployeeHolder holder = new EmployeeHolder();

        holder.add(new Employee("Ram", 1, 55000, 45));
        holder.add(new Employee("Harry", 2, 40000, 25));
        holder.add(new Employee("Mak", 3, 60000, 22));
        holder.add(new Employee("Messi", 4, 52000, 31));
        holder.add(new Employee("Alonso", 5, 98000, 34));

        Employee e = new Employee("Manohar", 3, 100000, 38);

        holder.displayAll();

        System.out.println("DELETING 100 : " + holder.delete(100));
        System.out.println("DELETING 2 : " + holder.delete(2));

        holder.displayAll();

        holder.update(e);
        holder.displayAll();
    }

    private Employee find(int employeeId) {
        for (int i = 0; i < this.employeeCount; i++) {
            if (employees[i].getEno() == employeeId) {
                return employees[i];
            }
        }

        return null;
    }
}
