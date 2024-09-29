import java.util.Scanner;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] employeeIds = new String[100];
        String[] employeeNames = new String[100];
        String[] clockInTimes = new String[100];
        String[] clockOutTimes = new String[100];
        int employeeCount = 0;

        // Add sample employees
        addEmployee(employeeIds, employeeNames, employeeCount++, "1001", "John Doe");
        addEmployee(employeeIds, employeeNames, employeeCount++, "1002", "Jane Smith");

        while (true) {
            System.out.println("\nEmployee Management System");
            System.out.println("1. Clock In/Out");
            System.out.println("2. Add New Employee");
            System.out.println("3. Show All Employees");
            System.out.println("4. Remove Employee"); // Added option
            System.out.println("5. Exit"); // Shifted option number
            System.out.print("Choose an option: ");

            String input = scanner.nextLine().trim();
            int choice;

            try {
                choice = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
                continue;
            }

            switch (choice) {
                case 1:
                    System.out.print("Enter employee ID: ");
                    String id = scanner.nextLine().trim();
                    clockInOut(id, employeeIds, employeeNames, clockInTimes, clockOutTimes, employeeCount);
                    break;
                case 2:
                    if (addEmployee(employeeIds, employeeNames, employeeCount, scanner)) {
                        employeeCount++;
                    }
                    break;
                case 3:
                    showAllEmployees(employeeIds, employeeNames, clockInTimes, clockOutTimes, employeeCount);
                    break;
                case 4: // Remove employee logic
                    System.out.print("Enter employee ID to remove: ");
                    String idToRemove = scanner.nextLine().trim();
                    if (removeEmployee(idToRemove, employeeIds, employeeNames, clockInTimes, clockOutTimes, employeeCount)) {
                        employeeCount--;
                        System.out.println("Employee removed successfully.");
                    } else {
                        System.out.println("Employee not found.");
                    }
                    break;
                case 5: // Exit
                    System.out.println("Exiting the employee management system !!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    public static void clockInOut(String id, String[] ids, String[] names, String[] inTimes, String[] outTimes,
            int count) {
        int index = findEmployeeIndex(id, ids, count);
        if (index != -1) {
            LocalTime currentTime = LocalTime.now();
            String formattedTime = currentTime.format(DateTimeFormatter.ofPattern("HH:mm:ss"));
            if (inTimes[index] == null || outTimes[index] != null) {
                inTimes[index] = formattedTime;
                outTimes[index] = null;
                System.out.println(names[index] + " clocked in at " + formattedTime);
            } else {
                outTimes[index] = formattedTime;
                System.out.println(names[index] + " clocked out at " + formattedTime);
            }
        } else {
            System.out.println("Employee not found.");
        }
    }

    public static void showAllEmployees(String[] ids, String[] names, String[] inTimes, String[] outTimes, int count) {
        if (count == 0) {
            System.out.println("No employees in the system.");
            return;
        }

        System.out.println("All Employees:");
        for (int i = 0; i < count; i++) {
            System.out.println("\nEmployee #" + (i + 1));
            System.out.println("ID: " + ids[i]);
            System.out.println("Name: " + names[i]);
            System.out.println("Last Clock In: " + (inTimes[i] != null ? inTimes[i] : "N/A"));
            System.out.println("Last Clock Out: " + (outTimes[i] != null ? outTimes[i] : "N/A"));
        }
    }

    public static boolean addEmployee(String[] ids, String[] names, int count, Scanner scanner) {
        if (count >= ids.length) {
            System.out.println("Employee database is full. Cannot add more employees.");
            return false;
        }

        System.out.print("Enter new employee ID: ");
        String newId = scanner.nextLine();

        if (findEmployeeIndex(newId, ids, count) != -1) {
            System.out.println("An employee with this ID already exists.");
            return false;
        }

        System.out.print("Enter new employee name: ");
        String newName = scanner.nextLine();

        return addEmployee(ids, names, count, newId, newName);
    }

    private static boolean addEmployee(String[] ids, String[] names, int count, String newId, String newName) {
        ids[count] = newId;
        names[count] = newName;
        System.out.println("Employee " + newName + " (ID: " + newId + ") added successfully.");
        return true;
    }

    private static int findEmployeeIndex(String id, String[] ids, int count) {
        for (int i = 0; i < count; i++) {
            if (ids[i].equals(id)) {
                return i;
            }
        }
        return -1;
    }
    public static boolean removeEmployee(String id, String[] ids, String[] names, String[] inTimes, String[] outTimes, int count) {
        int index = findEmployeeIndex(id, ids, count);
        if (index != -1) {
            // Shift elements to remove the employee at the given index
            for (int i = index; i < count - 1; i++) {
                ids[i] = ids[i + 1];
                names[i] = names[i + 1];
                inTimes[i] = inTimes[i + 1];
                outTimes[i] = outTimes[i + 1];
            }
            // Clear the last element
            ids[count - 1] = null;
            names[count - 1] = null;
            inTimes[count - 1] = null;
            outTimes[count - 1] = null;
            return true;
        }
        return false;
    }

}
