package CRUD;

import java.util.*;

public class EmployeeCRUD {
    private List<Employee> employees = new ArrayList<>();

    public void addEmployee(Employee e) {
        employees.add(e);
    }

    public void displayEmployees() {
        for (Employee e : employees) {
            System.out.println(e);
        }
    }

    public void updateEmployee(int id, String newName, String newDept) {
        for (Employee e : employees) {
            if (e.getId() == id) {
                e.setName(newName);
                e.setDepartment(newDept);
                break;
            }
        }
    }

    public void deleteEmployee(int id) {
        employees.removeIf(e -> e.getId() == id);
    }

    public static void main(String[] args) {
        EmployeeCRUD crud = new EmployeeCRUD();
        crud.addEmployee(new Employee(1, "Mahima", "SDE"));
        crud.addEmployee(new Employee(2, "Manasa", "SDE"));

        System.out.println("All Employees:");
        crud.displayEmployees();

        crud.updateEmployee(2, "Manasa", "Finance");
        System.out.println("After Update:");
        crud.displayEmployees();

        crud.deleteEmployee(1);
        System.out.println("After Delete:");
        crud.displayEmployees();
    }
}

