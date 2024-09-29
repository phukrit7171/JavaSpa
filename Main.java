import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Each "row" represents an employee, columns are: ID, Name, Clock In, Clock Out
        String[][] employees = new String[100][4]; 
        int employeeCount = 0;

        // Add sample employees
        addEmployee(employees, employeeCount++, "1001", "John Doe");
        addEmployee(employees, employeeCount++, "1002", "Jane Smith");

        while (true) {
            System.out.println("\nEmployee Management System");
            System.out.println("1. Clock In/Out");
            System.out.println("2. Add New Employee");
            System.out.println("3. Show All Employees");
            System.out.println("4. Remove Employee");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");

            int choice = getIntegerInput(scanner);

            switch (choice) {
                case 1:
                    System.out.print("Enter employee ID: ");
                    String id = scanner.nextLine().trim();
                    clockInOut(id, employees, employeeCount);
                    break;
                case 2:
                    if (addEmployee(employees, employeeCount, scanner)) {
                        employeeCount++;
                    }
                    break;
                case 3:
                    showAllEmployees(employees, employeeCount);
                    break;
                case 4:
                    System.out.print("Enter employee ID to remove: ");
                    String idToRemove = scanner.nextLine().trim();
                    if (removeEmployee(idToRemove, employees, employeeCount)) {
                        employeeCount--;
                        System.out.println("Employee removed successfully.");
                    } else {
                        System.out.println("Employee not found.");
                    }
                    break;
                case 5:
                    System.out.println("Exiting the employee management system !!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    // ... (Other methods adapted for the multidimensional array)

    public static void clockInOut(String id, String[][] employees, int count) {
        int index = findEmployeeIndex(id, employees, count);
        if (index != -1) {
            LocalDateTime currentTime = LocalDateTime.now();
            String formattedTime = currentTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            if (employees[index][2] == null || employees[index][3] != null) {
                employees[index][2] = formattedTime; // Clock in
                employees[index][3] = null; // Reset clock out
                System.out.println(employees[index][1] + " clocked in at " + formattedTime);
            } else {
                employees[index][3] = formattedTime; // Clock out
                System.out.println(employees[index][1] + " clocked out at " + formattedTime);
            }
        } else {
            System.out.println("Employee not found.");
        }
    }

    public static void showAllEmployees(String[][] employees, int count) {
        if (count == 0) {
            System.out.println("No employees in the system.");
            return;
        }

        System.out.println("All Employees:");
        for (int i = 0; i < count; i++) {
            System.out.println("\nEmployee #" + (i + 1));
            System.out.println("ID: " + employees[i][0]);
            System.out.println("Name: " + employees[i][1]);
            System.out.println("Last Clock In: " + (employees[i][2] != null ? employees[i][2] : "N/A"));
            System.out.println("Last Clock Out: " + (employees[i][3] != null ? employees[i][3] : "N/A"));
        }
    }

    public static boolean addEmployee(String[][] employees, int count, Scanner scanner) {
        if (count >= employees.length) {
            System.out.println("Employee database is full. Cannot add more employees.");
            return false;
        }

        System.out.print("Enter new employee ID: ");
        String newId = scanner.nextLine();

        if (findEmployeeIndex(newId, employees, count) != -1) {
            System.out.println("An employee with this ID already exists.");
            return false;
        }

        System.out.print("Enter new employee name: ");
        String newName = scanner.nextLine();

        return addEmployee(employees, count, newId, newName);
    }

    private static boolean addEmployee(String[][] employees, int count, String newId, String newName) {
        employees[count][0] = newId;
        employees[count][1] = newName;
        System.out.println("Employee " + newName + " (ID: " + newId + ") added successfully.");
        return true;
    }

    private static int findEmployeeIndex(String id, String[][] employees, int count) {
        for (int i = 0; i < count; i++) {
            if (employees[i][0].equals(id)) {
                return i;
            }
        }
        return -1;
    }

    public static boolean removeEmployee(String id, String[][] employees, int count) {
        int index = findEmployeeIndex(id, employees, count);
        if (index != -1) {
            for (int i = index; i < count - 1; i++) {
                employees[i] = employees[i + 1]; 
            }
            employees[count - 1] = new String[4]; // Clear the last row
            return true;
        }
        return false;
    }

    private static int getIntegerInput(Scanner scanner) {
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
    }
}
