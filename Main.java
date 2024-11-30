import java.time.LocalDateTime; // ภูกริช กิตตินนท์ธนา 672110154
import java.time.format.DateTimeFormatter; // ภูกริช กิตตินนท์ธนา 672110154
import java.util.InputMismatchException; // รชานนท์ อินต๊ะมี 672110157
import java.util.Scanner; // รชานนท์ อินต๊ะมี 672110157

public class Main {
    // รชานนท์ อินต๊ะมี 672110157
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            try {
                System.out.println("--Welcome to Spa Staff Menu--");
                System.out.println("1. Manage queue");
                System.out.println("2. Manage customers");
                System.out.println("3. Manage store");
                System.out.println("4. Manage employees");
                System.out.println("5. Manage payment");
                System.out.println("6. Exit");

                System.out.print("Enter: ");
                int input = sc.nextInt();
                sc.nextLine();

                switch (input) {
                    case 1:
                        ManageQueue(sc);
                        break;
                    case 2:
                        ManageCustomers(sc);
                        break;
                    case 3:
                        ManageStore(sc);
                        break;
                    case 4:
                        employeeSystem(sc);
                        break;
                    case 5:
                        ManagePayment(sc);
                        break;
                    case 6:
                        System.out.println("Exiting the program.");
                        sc.close();
                        return;
                    default:
                        System.out.println("Invalid choice. Enter again.");
                        break;
                }

            } catch (InputMismatchException e) {
                System.out.println("Integers only. Enter again.");
                sc.nextLine();
            }
        }
    }

    // Queue code
    public static void ManageQueue(Scanner sc) {
        String[] name = new String[0];
        int[] age = new int[0];
        String[] numPhone = new String[0];
        String[] timestamp = new String[0];

        while (true) {
            try {
                System.out.println("1. Request queue");
                System.out.println("2. Check queue");
                System.out.println("3. Back");
                System.out.print("Enter: ");
                int input = sc.nextInt();
                sc.nextLine();

                switch (input) {
                    case 1:
                        Object[] updatedArrays = RequestQueue(name, age, numPhone, timestamp, sc);
                        name = (String[]) updatedArrays[0];
                        age = (int[]) updatedArrays[1];
                        numPhone = (String[]) updatedArrays[2];
                        timestamp = (String[]) updatedArrays[3];
                        break;
                    case 2:
                        CheckQueue(name, age, numPhone, timestamp);
                        break;
                    case 3:
                        return;
                    default:
                        System.out.println("Invalid choice. Enter again.");
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Integers only. Enter again.");
                sc.nextLine();
            }
        }
    }

    public static Object[] RequestQueue(String[] name, int[] age, String[] numPhone, String[] timestamp, Scanner sc) {
        while (true) {
            try {
                System.out.print("Number of queue requests: ");
                int num = sc.nextInt();
                sc.nextLine();
                for (int i = 0; i < num; i++) {
                    try {
                        System.out.print("Name: ");
                        String inputName = sc.next();

                        System.out.print("Age: ");
                        int inputAge = sc.nextInt();

                        sc.nextLine();

                        System.out.print("NumberPhone: ");
                        String inputPhone = sc.next();

                        System.out.print("Timestamp: ");
                        String inputTimestamp = sc.next();

                        name = appendToArraySRG(name, inputName);
                        age = appendToArrayINT(age, inputAge);
                        numPhone = appendToArraySRG(numPhone, inputPhone);
                        timestamp = appendToArraySRG(timestamp, inputTimestamp);
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid input. Please enter again.");
                        sc.nextLine();
                        i--;
                    }
                }
                System.out.println("Successfully placed queue request!");
                break;
            } catch (InputMismatchException e) {
                System.out.println("Integers only. Enter again.");
                sc.nextLine();
            }
        }

        return new Object[] { name, age, numPhone, timestamp };
    }

    public static void CheckQueue(String[] name, int[] age, String[] numPhone, String[] timestamp) {
        System.out.println("Queue details ----------------------------");
        for (int i = 0; i < name.length; i++) {
            System.out.printf("Name: %s, Age: %d, Phone: %s, Timestamp: %s%n", name[i], age[i], numPhone[i], timestamp[i]);
        }
    }

    public static String[] appendToArraySRG(String[] array, String value) {
        String[] newArray = new String[array.length + 1];
        System.arraycopy(array, 0, newArray, 0, array.length);
        newArray[array.length] = value;
        return newArray;
    }

    public static int[] appendToArrayINT(int[] array, int value) {
        int[] newArray = new int[array.length + 1];
        System.arraycopy(array, 0, newArray, 0, array.length);
        newArray[array.length] = value;
        return newArray;
    }
    // Queue code END
    // รชานนท์ อินต๊ะมี END

    // Employee code ภูกริช กิตตินนท์ธนา 672110154
    public static void employeeSystem(Scanner scanner) {
        // Initialize the employee array with a size of 0
        String[][] employees = new String[0][4];
        int employeeCount = 0;

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
                    employees = addEmployee(employees, employeeCount, scanner);
                    employeeCount++;
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
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

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

    public static String[][] addEmployee(String[][] employees, int count, Scanner scanner) {
        if (count >= employees.length) {
            employees = resizeArray(employees);  // Resize if no space is available
        }

        System.out.print("Enter new employee ID: ");
        String newId = scanner.nextLine();

        if (findEmployeeIndex(newId, employees, count) != -1) {
            System.out.println("An employee with this ID already exists.");
            return employees;
        }

        System.out.print("Enter new employee name: ");
        String newName = scanner.nextLine();

        updateEmployee(employees, count, newId, newName);
        return employees;
    }

    private static String[][] updateEmployee(String[][] employees, int count, String newId, String newName) {
        if (count >= employees.length) {
            employees = resizeArray(employees); // Ensure resizing before adding
        }
        employees[count][0] = newId;
        employees[count][1] = newName;
        System.out.println("Employee " + newName + " (ID: " + newId + ") added successfully.");
        return employees;
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

    // Method to resize the employee array when it's full
    private static String[][] resizeArray(String[][] oldArray) {
        int newSize = (oldArray.length == 0) ? 2 : oldArray.length * 2; // Start with 2 if empty, otherwise double the size
        String[][] newArray = new String[newSize][4];
        for (int i = 0; i < oldArray.length; i++) {
            newArray[i] = oldArray[i];
        }
        // System.out.println("Array resized to " + newSize);
        return newArray;
    }

    // Employee code ภูกริช กิตตินนท์ธนา END

    // customers code
    public static void ManageCustomers(Scanner input) {
        String[] name = new String[0];
        int[] age = new int[0];
        String[] phoneNumber = new String[0];

        while (true) {
            try {
                System.out.println("\nChoose an option: " +
                        "\nEnter 1 to enter the membership registration system" +
                        "\nEnter 2 to enter the membership checking system." +
                        "\nEnter 3 to enter back");
                System.out.print("Enter your choice: ");

                int choice = input.nextInt();
                switch (choice) {
                    case 1:
                        Object[] updatedArrays = membership(name, age, phoneNumber);
                        name = (String[]) updatedArrays[0];
                        age = (int[]) updatedArrays[1];
                        phoneNumber = (String[]) updatedArrays[2];
                        break;
                    case 2:
                        checkMembership(name, age, phoneNumber);
                        break;
                    case 3:
                        return;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Integers only. Enter again.");
                input.nextLine();
            }
        }
    }

    public static Object[] membership(String[] name, int[] age, String[] phoneNumber) {
        Scanner input = new Scanner(System.in);
        System.out.println("\nMembership Registration System");

        while(true) {
            try {
                System.out.print("Number of customers to register for membership: ");
                int num = input.nextInt();
                input.nextLine();
                for (int i = 0; i < num; i++) {
                    try {
                        System.out.println("Customer "+(i+1));
                        System.out.print("Name: ");
                        String inputName = input.next();

                        System.out.print("Age: ");
                        int inputAge = input.nextInt();
                        input.nextLine();

                        System.out.print("Phone number: ");
                        String inputPhone = input.next();
                        System.out.println();

                        name = appendToArraySRG(name, inputName);
                        age = appendToArrayINT(age, inputAge);
                        phoneNumber = appendToArraySRG(phoneNumber, inputPhone);
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid input. Please enter again.");
                        input.nextLine();
                        i--;
                    }
                }

                break;
            } catch (InputMismatchException e) {
                System.out.println("Integers only. Enter again.");
                input.nextLine();
            }
        }

        return new Object[] { name, age, phoneNumber};
    }

    public static void checkMembership(String[] name, int[] age, String[] phoneNumber) {
        Scanner input = new Scanner(System.in);
        System.out.println("\nMembership Checking System");
        System.out.print("\nEnter customer's name, or phone number to check membership: ");
        String search = input.next();
        boolean found = false;

        for (int i = 0; i < name.length; i++) {
            if (name[i].equalsIgnoreCase(search) || 
phoneNumber[i].equals(search)) {

                System.out.println("Member Found:");
                System.out.println("Name: " + name[i]);
                System.out.println("Age: " + age[i]);
                System.out.println("Phone Number: " + phoneNumber[i]);
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("No member found with the information.");
        }
    }
    // customers code END

    // Payment code กชกร กิติมา 672110132
    public static void ManagePayment(Scanner scanner) {
        int itemCount = 0;
        while (true) {
            System.out.print("Please enter the number of products and services you have received: ");
            try {
                itemCount = Integer.parseInt(scanner.next());
                if (itemCount <= 0) {
                    System.out.println("The number of items must be positive. Please enter again.");
                } else {
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Integers only. Enter again.");
            }
        }

        // รับราคาสินค้าจากผู้ใช้
        double[] prices = inputPrices(itemCount, scanner);

        // คำนวณยอดรวม
        double total = 0.0;
        for (double price : prices) {
            total += price;
        }

        // ตรวจสอบว่า ลูกค้าเป็นสมาชิกหรือไม่ เพื่อใช้ในการคำนวณส่วนลด
        boolean isMember = false;
        while (true) {
            System.out.print("Do you have membership (yes/no): ");
            String memberInput = scanner.next();
            if (memberInput.equalsIgnoreCase("yes")) {
                isMember = true;
                break;
            } else if (memberInput.equalsIgnoreCase("no")) {
                isMember = false;
                break;
            } else {
                System.out.println("Please enter only 'yes' or 'no'.");
            }
        }

        // คำนวณส่วนลด (ถ้ามี)
        double finalAmount = isMember ? total * 0.9 : total;

        // พิมพ์ใบเสร็จ
        printReceipt(prices, total, isMember, finalAmount);
    }

    // รับราคาสินค้าและบริการจากผู้ใช้
    public static double[] inputPrices(int itemCount, Scanner scanner) {
        double[] prices = new double[itemCount];
        for (int i = 0; i < itemCount; i++) {
            while (true) {
                try {
                    System.out.print("Price list of items " + (i + 1) + ": ");
                    prices[i] = scanner.nextDouble();
                    if (prices[i] < 0) {
                        System.out.println("The price must be positive. Please enter again.");
                    } else {
                        break;
                    }
                } catch (Exception e) {
                    System.out.println("Invalid input. Please enter a valid price.");
                    scanner.next(); // Clear the invalid input
                }
            }
        }
        return prices;
    }

    // พิมพ์ใบเสร็จ
    public static void printReceipt(double[] prices, double total, boolean isMember, double finalAmount) {
        System.out.println("\n--- Receipt ---");
        for (int i = 0; i < prices.length; i++) {
            System.out.printf("Item %d: %.2f THB\n", (i + 1), prices[i]);
        }
        System.out.printf("Total amount of products and services: %.2f THB\n", total);
        if (isMember) {
            System.out.println("Membership discount: 10%");
        }
        System.out.printf("Total amount (Including discount): %.2f THB\n", finalAmount);
        System.out.println("-----------------------");
    }
    // Payment code กชกร กิติมา END

    // store code
    public static void ManageStore(Scanner scanner) {
        // กำหนดรายการสินค้าที่สามารถสั่งได้
        String[] availableItems = {"Scrub", "Powder", "Lotion", "Toner"};
        String[] orderedItems = new String[100];   // เก็บชื่อสินค้าที่สั่ง
        int[] orderedQuantities = new int[100];   // เก็บจำนวนสินค้าที่สั่ง
        int orderCount = 0; // ตัวนับจำนวนรายการที่สั่ง
        String itemName;
        int quantity;

        System.out.println("Inventory : Scrub, Powder, Lotion, Toner");

        while (true) {
            System.out.print("Please enter the product name for add(or type 'exit' to quit) : ");
            itemName = scanner.nextLine();
            if (itemName.equalsIgnoreCase("exit")) {
                break;
            }
            if (isValidItem(itemName, availableItems)) {
                System.out.print("Please enter the quantity : ");
                quantity = scanner.nextInt();
                scanner.nextLine();  // ล้างบรรทัดเพื่อรับข้อมูลต่อไปได้อย่างถูกต้องไม่ตกหล่น

                orderCount = addItem(itemName, quantity, orderedItems, orderedQuantities, orderCount);
            } else {
                System.out.println("No product with this name found, Please try again.");
            }
        }

        displayOrder(orderedItems, orderedQuantities, orderCount);
    }

    // ศุภกฤต แสนวงค์คำ 672110162
    // Method สำหรับเพิ่มหรืออัปเดตสินค้าและจำนวน 
    public static int addItem(String itemName, int quantity, String[] orderedItems, int[] orderedQuantities, int orderCount) {
        if (orderCount >= orderedItems.length) {
            System.out.println("Cannot add more items. Order limit reached.");
            return orderCount;
        }

        for (int i = 0; i < orderCount; i++) {
            if (orderedItems[i].equalsIgnoreCase(itemName)) {
                orderedQuantities[i] += quantity;
                System.out.println("Updated Product : " + itemName + " New Quantity : " + orderedQuantities[i]);
                return orderCount;
            }
        }

        orderedItems[orderCount] = itemName;
        orderedQuantities[orderCount] = quantity;
        System.out.println("Add Product : " + itemName + " Quantity : " + quantity);
        return orderCount + 1;
    }

    // Method สำหรับตรวจสอบว่าสินค้าอยู่ในรายการที่มีหรือไม่
    public static boolean isValidItem(String itemName, String[] availableItems) {
        for (String item : availableItems) {
            if (item.equalsIgnoreCase(itemName)) {
                return true;
            }
        }
        return false;
    }

    // Method สำหรับแสดงรายการสินค้าที่สั่ง
    public static void displayOrder(String[] orderedItems, int[] orderedQuantities, int orderCount) {
        System.out.println("Total Order List : ");
        for (int i = 0; i < orderCount; i++) {
            System.out.println("Product : " + orderedItems[i] + " Quantity : " + orderedQuantities[i]);
        }
    }
     // ศุภกฤต แสนวงค์คำ END

    // store code END
}