import java.sql.*;
import java.util.*;

public class DBEmployeeOperations {

    public Connection connection;
    Scanner scan = new Scanner(System.in);


    {
        try {
            connection = createConnection();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Connection createConnection() throws ClassNotFoundException, SQLException {
        //register driver
        Class.forName("com.mysql.jdbc.Driver");

        //establish connection
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/", "root", "system");

        return connection;
    }

    public boolean add() throws SQLException, ClassNotFoundException {
        Employee e = new Employee();
        System.out.println("Enter employee number:  ");
        e.setEno(scan.nextInt());
        System.out.println("Enter employee name: ");
        e.setEname(scan.next());
        System.out.println("Enter employee salary: ");
        e.setSalary(scan.nextFloat());
        System.out.println("Enter employee age: ");
        e.setAge(scan.nextInt());

        Statement statement = connection.createStatement();

        StringBuilder queryBuilder = new StringBuilder("insert into sys.employee (number, name, salary, age) values (");

        queryBuilder
                .append(e.getEno())
                .append(",'")
                .append(e.getEname())
                .append("',")
                .append(e.getSalary())
                .append(",")
                .append(e.getAge())
                .append(")");
        System.out.println(queryBuilder.toString());
        boolean success = statement.execute(queryBuilder.toString());
        return success;

    }

    public boolean update() throws SQLException {
        Employee e = new Employee();
        System.out.println("Enter employee number:  ");
        e.setEno(scan.nextInt());
        System.out.println("Enter employee name: ");
        e.setEname(scan.next());
        System.out.println("Enter employee salary: ");
        e.setSalary(scan.nextFloat());
        System.out.println("Enter employee age: ");
        e.setAge(scan.nextInt());

        Statement statement =connection.createStatement();

        StringBuilder queryBuilder = new StringBuilder("update sys.employee set name = ");
        queryBuilder.append("'").append(e.getEname()).append("',").append(" salary = ").append(e.getSalary())
                .append(", age = ").append(e.getAge()).append(" where number = ").append(e.getEno());
        boolean result = statement.execute(queryBuilder.toString());

        return result;
    }

    public void delete() throws SQLException {
        Employee e = new Employee();
        System.out.println("Enter employee number");
        e.setEno(scan.nextInt());

        Statement statement = connection.createStatement();
        StringBuilder stringBuilder = new StringBuilder("delete from sys.employee where number");
        stringBuilder.append("=").append(e.getEno());
        boolean result = statement.execute(stringBuilder.toString());
        System.out.println("Record deleted");
    }

    public void find() throws SQLException {
        Employee e = new Employee();
        System.out.println("Enter employee number: ");
        e.setEno(scan.nextInt());

        Statement statement = connection.createStatement();
        StringBuilder stringBuilder = new StringBuilder("select * from sys.employee where number =");
        stringBuilder.append(e.getEno());
        ResultSet resultSet = statement.executeQuery(stringBuilder.toString());

        if(resultSet.next()) {
            Employee e1 = new Employee();
            e1.setEno(resultSet.getInt("number"));
            e1.setEname(resultSet.getString("name"));
            e1.setSalary(resultSet.getFloat("salary"));
            e1.setAge(resultSet.getInt("age"));
            System.out.println(e1.toString());
        }

    }

    public void findAll() throws SQLException {

        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from sys.employee");

        List<Employee> employees = new ArrayList<>();

        while(resultSet.next()) {
            Employee e = new Employee();
            e.setEno(resultSet.getInt(1));
            e.setEname(resultSet.getString(2));
            e.setSalary(resultSet.getFloat(3));
            e.setAge(resultSet.getInt(4));
            employees.add(e);
        }

        for(Employee emp :employees){
            if(employees.isEmpty())
                System.out.println("No records to display");
            else
                System.out.println(emp);
        }

    }

    public void increaseSalary() throws SQLException {

        Employee e = new Employee();
        System.out.println("Enter employee number:  ");
        e.setEno(scan.nextInt());

        System.out.println("Enter increase percentage:  ");
        int percentage = scan.nextInt();

        Statement statement= connection.createStatement();
        StringBuilder stringBuilder = new StringBuilder("select number, salary from sys.employee where number =");
        stringBuilder.append(e.getEno());
        ResultSet resultSet = statement.executeQuery(stringBuilder.toString());

        if(resultSet.next()){
            Employee e1= new Employee();
            e1.setEno(resultSet.getInt("number"));
            float salary = resultSet.getFloat("salary");
            e1.setSalary(salary + (percentage*salary)/100);
            StringBuilder stringBuilder1 = new StringBuilder("update sys.employee set salary = ");
            stringBuilder1.append(e1.getSalary()).append(" where number = ").append(e1.getEno());
            statement.executeUpdate(stringBuilder1.toString());
        }

    }

    static class SortByName implements Comparator<Employee> {
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

    public void sortByName() throws SQLException {

        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from sys.employee");

        List<Employee> employees = new ArrayList<>();

        //use resultset
        while(resultSet.next()) {
            Employee e = new Employee();
            e.setEno(resultSet.getInt(1));
            e.setEname(resultSet.getString(2));
            e.setSalary(resultSet.getFloat(3));
            e.setAge(resultSet.getInt(4));
            employees.add(e);
        }

        Collections.sort(employees, new DBEmployeeOperations.SortByName());
        System.out.println("Employees sorted by name:  ");
        for(Employee e : employees)
            System.out.println(e);
    }

    public void sortByAge() throws SQLException{

        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from sys.employee");

        List<Employee> employees = new ArrayList<>();

        //use resultset
        while(resultSet.next()) {
            Employee e = new Employee();
            e.setEno(resultSet.getInt(1));
            e.setEname(resultSet.getString(2));
            e.setSalary(resultSet.getFloat(3));
            e.setAge(resultSet.getInt(4));
            employees.add(e);
        }

        Collections.sort(employees, new DBEmployeeOperations.SortByAge());
        System.out.println("Employees sorted by age:  ");
        for(Employee e :employees)
            System.out.println(e);

    }

    public void uniqueEmpName() throws SQLException {

        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select name from sys.employee ");

        List<String> employees = new ArrayList<>();

        while(resultSet.next()){
            Employee e = new Employee();
            e.setEname(resultSet.getString("name"));
            employees.add(e.getEname());
        }

        Set<String> empSet = new HashSet<>(employees);

        for (String e : empSet)
            if (!empSet.contains(e)) {
                empSet.add(e);
            }
        for(String s : empSet)
            System.out.println(s);

    }

    public void totalEmployeesSameAge() throws SQLException {

        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select number, name, salary, age from sys.employee");

        List<Employee> employees = new ArrayList<>();
        while(resultSet.next()) {
            Employee e = new Employee();
            e.setEno(resultSet.getInt(1));
            e.setEname(resultSet.getString(2));
            e.setSalary(resultSet.getFloat(3));
            e.setAge(resultSet.getInt(4));
            employees.add(e);
        }

        Employee e = new Employee();
        System.out.println("Enter age: ");
        e.setAge(scan.nextInt());
        int count=0;
        Iterator<Employee> iterator = employees.iterator();
        while (iterator.hasNext()) {
            Employee employee = (Employee) iterator.next();
            if (e.getAge() == employee.getAge())
                count++;
        }

        System.out.println("Total no of employees with "+e.getAge()+" are "+count);

    }

}
