package CRUD_WITH_JDBC;

import java.sql.*;

public class EmployeeJDBC {

    private static final String URL = "jdbc:mysql://localhost:3306/testdb";
    private static final String USER = "root";
    private static final String PASS = "password";

    public void addEmployee(Employee e) {
        String sql = "INSERT INTO employee (id, name, department) VALUES (?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, e.getId());
            pstmt.setString(2, e.getName());
            pstmt.setString(3, e.getDepartment());
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void displayEmployees() {
        String sql = "SELECT * FROM employee";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                System.out.println(rs.getInt("id") + " " +
                        rs.getString("name") + " " +
                        rs.getString("department"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void updateEmployee(int id, String newName, String newDept) {
        String sql = "UPDATE employee SET name = ?, department = ? WHERE id = ?";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, newName);
            pstmt.setString(2, newDept);
            pstmt.setInt(3, id);
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void deleteEmployee(int id) {
        String sql = "DELETE FROM employee WHERE id = ?";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        EmployeeJDBC crud = new EmployeeJDBC();
        Employee e1 = new Employee(1, "Alice", "HR");
        Employee e2 = new Employee(2, "Bob", "IT");

        crud.addEmployee(e1);
        crud.addEmployee(e2);

        System.out.println("All Employees:");
        crud.displayEmployees();

        crud.updateEmployee(2, "Bobby", "Finance");
        System.out.println("After Update:");
        crud.displayEmployees();

        crud.deleteEmployee(1);
        System.out.println("After Delete:");
        crud.displayEmployees();
    }
}

