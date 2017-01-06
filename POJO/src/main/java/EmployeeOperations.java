import java.util.*;

public interface EmployeeOperations {
    boolean add(Employee employee);
    void update(Employee employee);
    int delete(int employeeId);
    void display(int employeeId);
    void displayAll();
    int increaseSalary(int empoyeeId, float increasePercentage);
}


class CollectionEmployeeOperations{

    Scanner scan = new Scanner(System.in);
    List<Employee> empList = new ArrayList<Employee>();


    static class SortByName implements Comparator<Employee>{
        public int compare(Employee e1, Employee e2){

            final int BEFORE = -1;
            final int EQUAL = 0;
            final int AFTER = 1;

            // Then compare by name
            if (e1.getEname().compareTo(e2.getEname()) > 0)
                return AFTER;
            if (e1.getEname().compareTo(e2.getEname()) < 0)
                return BEFORE;

            return 0;

        }
    }

    static class SortByAge implements Comparator<Employee>{
        public int compare(Employee e1, Employee e2){

            final int BEFORE = -1;
            final int EQUAL = 0;
            final int AFTER = 1;

            if (e1.getAge() > e2.getAge())
                return BEFORE;
            if (e1.getAge() < e2.getAge())
                return AFTER;

            return 0;

        }
    }

    public void sortByName(){
        Collections.sort(empList, new CollectionEmployeeOperations.SortByName());
        System.out.println("Employees sorted by name");
        displayAll();
    }

    public void sortByAge(){
        Collections.sort(empList, new CollectionEmployeeOperations.SortByAge());
        System.out.println("Employees sorted by age");
        displayAll();
    }

    public void uniqueEmpNames() {
        Set<String> empSet = new HashSet<String>();

        for (Employee e : empList)
            if (!empSet.contains(e.getEname())) {
                empSet.add(e.getEname());
            }
        System.out.println(empSet);
    }


    public void totalEmployeesSameAge(){
        Employee e = new Employee();
        System.out.println("Enter age: ");
        e.setAge(scan.nextInt());
        int count=0;
        Iterator<Employee> iterator = empList.iterator();
        while (iterator.hasNext()) {
            Employee employee = (Employee) iterator.next();
            if (e.getAge() == employee.getAge())
                count++;
        }
        System.out.println("Total no of employees with "+e.getAge()+" are "+count);
    }


    public void add() {
        Employee e = new Employee();
        System.out.println("Enter employee number: ");
        e.setEno(scan.nextInt());
        System.out.println("Enter employee name: ");
        e.setEname(scan.next());
        System.out.println("Enter employee salary: ");
        e.setSalary(scan.nextFloat());
        System.out.println("Enter employee age: ");
        e.setAge(scan.nextInt());

        empList.add(e);
    }

    public void update() {
        Employee e = new Employee();
        System.out.println("Enter employee number: ");
        e.setEno(scan.nextInt());
        System.out.println("Enter employee name: ");
        e.setEname(scan.next());
        System.out.println("Enter employee salary: ");
        e.setSalary(scan.nextFloat());
        System.out.println("Enter employee age: ");
        e.setAge(scan.nextInt());

        Iterator<Employee> iterator = empList.iterator();
        while (iterator.hasNext()) {
            Employee employee = (Employee) iterator.next();
            if (e.getEno() == employee.getEno()) {
                employee.setEname(e.getEname());
                employee.setSalary(e.getSalary());
                employee.setAge(e.getAge());
            }
        }
    }

    public void delete() {
        Employee e = new Employee();
        System.out.println("Enter employee number:  ");
        e.setEno(scan.nextInt());
        Iterator<Employee> iterator = empList.iterator();
        while (iterator.hasNext()) {
            Employee employee = (Employee) iterator.next();
            if (e.getEno() == employee.getEno()) {
                empList.remove(employee);
                break;
            }
        }
        System.out.println("Employee record deleted");
    }

    public void display() {
        Employee e = new Employee();
        System.out.println("Enter employee number:  ");
        e.setEno(scan.nextInt());

        Iterator<Employee> iterator = empList.iterator();
        while (iterator.hasNext()) {
            Employee employee = (Employee) iterator.next();
            if (e.getEno() == employee.getEno())
                System.out.println(employee);
        }
    }

    public void displayAll() {
        if (empList.isEmpty())
            System.out.println("No records to display");
        else
            for(Employee e : empList)
                System.out.println(e);
    }

    public void increaseSalary() {
        Employee e = new Employee();
        System.out.println("Enter employee number:  ");
        e.setEno(scan.nextInt());
        System.out.println("Enter increment percentage:  ");
        int percentage = scan.nextInt();

        Iterator<Employee> iterator = empList.iterator();
        while (iterator.hasNext()) {
            Employee employee = (Employee) iterator.next();
            if (e.getEno() == employee.getEno())
                employee.setSalary(employee.getSalary() + (employee.getSalary() * percentage) / 100);
            break;
        }
    }
}
