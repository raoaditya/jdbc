
public class Employee
{
    private String ename;
    private int eno;
    private float salary;
    private int age;
    private static String companyname = "xyz company";
    private Address address;

    public Address getAddress()
    {
        return address;
    }

    public void setAddress(Address address)
    {
        this.address = address;
    }

    public Employee() {
    }

    public Employee(String ename, int eno, float salary, int age )
    {
        this.ename = ename;
        this.eno = eno;
        this.salary = salary;
        this.age = age;

    }

    public String getEname() {
        return ename;
    }

    public void setEname(String ename) {
        this.ename = ename;
    }

    public int getEno() {
        return eno;
    }

    public void setEno(int eno) {
        this.eno = eno;
    }

    public float getSalary() {
        return salary;
    }

    public void setSalary(float salary)
    {
        this.salary = salary;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "eno=" + eno +
                ", ename='" + ename + '\'' +
                ", salary=" + salary +
                ", age=" + age +
                ", address=" + address +
                '}';
    }
}
