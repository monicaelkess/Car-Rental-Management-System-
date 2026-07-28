import java.util.Scanner;

public class Main {

    static final int MAX_CARS = 20;
    static final int MAX_CUSTOMERS = 20;
    static Car[] cars = new Car[MAX_CARS];
    static int carArrayCount = 0;

    static Customer[] customers = new Customer[MAX_CUSTOMERS];
    static int customerArrayCount = 0;

    static double totalIncome = 0.0;

    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        printWelcomeBanner();

        boolean running = true;
        while (running) {
            printMenu();
            int choice = readInt("Enter your choice: ");

            switch (choice) {
                case 1:
                    addRegularCar();
                    break;
                case 2:
                    addLuxuryCar();
                    break;
                case 3:
                    addCustomer();
                    break;
                case 4:
                    displayAllCars();
                    break;
                case 5:
                    displayAvailableCars();
                    break;
                case 6:
                    rentCar();
                    break;
                case 7:
                    returnCar();
                    break;
                case 8:
                    searchCarById();
                    break;
                case 9:
                    searchCarByBrand();
                    break;
                case 10:
                    displayAllCustomers();
                    break;
                case 11:
                    displayStatistics();
                    break;
                case 0:
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }

        printGoodbye();
        scanner.close();
    }
    static void printWelcomeBanner() {
        System.out.println("========================================");
        System.out.println("   WELCOME TO SPEEDWAY RENTALS SYSTEM");
        System.out.println("      Reliable. Accurate. Simple.");
        System.out.println("========================================");
    }

    static void printMenu() {
        System.out.println("\n========================================");
        System.out.println(" SPEEDWAY RENTALS SYSTEM");
        System.out.println("========================================");
        System.out.println("1. Add Regular Car");
        System.out.println("2. Add Luxury Car");
        System.out.println("3. Add Customer");
        System.out.println("4. Display All Cars");
        System.out.println("5. Display Available Cars");
        System.out.println("6. Rent a Car");
        System.out.println("7. Return a Car");
        System.out.println("8. Search Car by ID");
        System.out.println("9. Search Car by Brand");
        System.out.println("10. Display All Customers");
        System.out.println("11. Display Statistics");
        System.out.println("0. Exit");
        System.out.println("========================================");
    }

    static void printGoodbye() {
        System.out.println("\n========================================");
        System.out.println(" Thank you for using SpeedWay Rentals!");
        System.out.println(" Total Cars Registered      : " + Car.getCarCount());
        System.out.println(" Total Customers Registered : " + Customer.getCount());
        System.out.println(" Total Income Earned        : $" + String.format("%.2f", totalIncome));
        System.out.println("========================================");
    }

    static void addRegularCar() {
        System.out.println("\n--- Add Regular Car ---");

        if (carArrayCount >= MAX_CARS) {
            System.out.println("Error: The fleet is full. Cannot add more cars.");
            return;
        }

        int id = readInt("Enter Car ID: ");
        if (findCarIndexById(id) != -1) {
            System.out.println("Error: A car with ID " + id + " already exists.");
            return;
        }

        String brand = readString("Enter Brand: ");
        String model = readString("Enter Model: ");

        int year = readInt("Enter Year (1990-2026): ");
        if (year < 1990 || year > 2026) {
            System.out.println("Error: Manufacturing year must be between 1990 and 2026.");
            return;
        }

        double price = readDouble("Enter Price per Day: ");
        if (price <= 0) {
            System.out.println("Error: Price per day must be greater than zero.");
            return;
        }

        Car car = new Car(id, brand, model, year, price);
        cars[carArrayCount] = car;
        carArrayCount++;

        System.out.println("Success: Regular car added with ID " + id + ".");
    }

    static void addLuxuryCar() {
        System.out.println("\n--- Add Luxury Car ---");

        if (carArrayCount >= MAX_CARS) {
            System.out.println("Error: The fleet is full. Cannot add more cars.");
            return;
        }

        int id = readInt("Enter Car ID: ");
        if (findCarIndexById(id) != -1) {
            System.out.println("Error: A car with ID " + id + " already exists.");
            return;
        }

        String brand = readString("Enter Brand: ");
        String model = readString("Enter Model: ");

        int year = readInt("Enter Year (1990-2026): ");
        if (year < 1990 || year > 2026) {
            System.out.println("Error: Manufacturing year must be between 1990 and 2026.");
            return;
        }

        double price = readDouble("Enter Price per Day: ");
        if (price <= 0) {
            System.out.println("Error: Price per day must be greater than zero.");
            return;
        }

        double insuranceFee = readDouble("Enter Insurance Fee: ");
        if (insuranceFee < 0) {
            System.out.println("Error: Insurance fee cannot be negative.");
            return;
        }

        LuxuryCar car = new LuxuryCar(id, brand, model, year, price, insuranceFee);
        cars[carArrayCount] = car;
        carArrayCount++;

        System.out.println("Success: Luxury car added with ID " + id + " (minimum rental: "
                + car.getMinRentalDays() + " days).");
    }


    static void addCustomer() {
        System.out.println("\n--- Add Customer ---");

        if (customerArrayCount >= MAX_CUSTOMERS) {
            System.out.println("Error: Customer list is full. Cannot add more customers.");
            return;
        }

        int id = readInt("Enter Customer ID: ");
        if (findCustomerIndexById(id) != -1) {
            System.out.println("Error: A customer with ID " + id + " already exists.");
            return;
        }

        String name = readString("Enter Name: ");
        String phone = readString("Enter Phone Number: ");

        Customer customer = new Customer(id, name, phone);
        customers[customerArrayCount] = customer;
        customerArrayCount++;

        System.out.println("Success: Customer \"" + name + "\" added with ID " + id + ".");
    }


    static void displayAllCars() {
        System.out.println("\n--- All Cars in Fleet ---");

        if (carArrayCount == 0) {
            System.out.println("The fleet is currently empty.");
            return;
        }

        for (int i = 0; i < carArrayCount; i++) {
            System.out.println((i + 1) + ". " + cars[i]);
        }
    }

    static void displayAvailableCars() {
        System.out.println("\n--- Available Cars ---");

        int availableCount = 0;
        for (int i = 0; i < carArrayCount; i++) {
            if (cars[i].isAvailable()) {
                availableCount++;
                System.out.println(availableCount + ". " + cars[i]);
            }
        }

        if (availableCount == 0) {
            System.out.println("No cars are currently available.");
        } else {
            System.out.println("Total available cars: " + availableCount);
        }
    }


    static void rentCar() {
        System.out.println("\n--- Rent a Car ---");

        int custId = readInt("Enter Customer ID: ");
        int custIdx = findCustomerIndexById(custId);
        if (custIdx == -1) {
            System.out.println("Error: Customer not found.");
            return;
        }
        Customer customer = customers[custIdx];

        if (customer.hasCar()) {
            System.out.println("Error: This customer already holds a car. Return it before renting another.");
            return;
        }

        int carId = readInt("Enter Car ID: ");
        int carIdx = findCarIndexById(carId);
        if (carIdx == -1) {
            System.out.println("Error: Car not found.");
            return;
        }
        Car car = cars[carIdx];

        if (!car.isAvailable()) {
            System.out.println("Error: This car is already rented.");
            return;
        }

        int days = readInt("Enter Number of Rental Days: ");
        if (days <= 0) {
            System.out.println("Error: Number of rental days must be greater than zero.");
            return;
        }

        if (car instanceof LuxuryCar) {
            LuxuryCar luxuryCar = (LuxuryCar) car;
            if (days < luxuryCar.getMinRentalDays()) {
                System.out.println("Error: This luxury car cannot be rented for fewer than "
                        + luxuryCar.getMinRentalDays() + " days.");
                return;
            }
        }

        double cost = car.calculateCost(days);
        car.setAvailable(false);
        customer.rentCar(car.getId(), days, cost);
        totalIncome += cost;

        System.out.println("\n--- Rental Receipt ---");
        System.out.println("Customer : " + customer.getName());
        System.out.println("Car      : " + car.getBrand() + " " + car.getModel());
        System.out.println("Days     : " + days);
        System.out.println("Total Cost (incl. 14% tax): $" + String.format("%.2f", cost));
    }


    static void returnCar() {
        System.out.println("\n--- Return a Car ---");

        int custId = readInt("Enter Customer ID: ");
        int custIdx = findCustomerIndexById(custId);
        if (custIdx == -1) {
            System.out.println("Error: Customer not found.");
            return;
        }
        Customer customer = customers[custIdx];

        if (!customer.hasCar()) {
            System.out.println("Error: This customer has no car to return.");
            return;
        }

        int rentedCarId = customer.getRentedCarId();
        int carIdx = findCarIndexById(rentedCarId);

        String carLabel = "Car ID " + rentedCarId;
        if (carIdx != -1) {
            cars[carIdx].setAvailable(true);
            carLabel = cars[carIdx].getBrand() + " " + cars[carIdx].getModel();
        }

        customer.returnCar();

        System.out.println("Success: " + carLabel + " has been returned and is now available.");
    }


    static void searchCarById() {
        System.out.println("\n--- Search Car by ID ---");

        int id = readInt("Enter Car ID: ");
        int idx = findCarIndexById(id);

        if (idx == -1) {
            System.out.println("Car not found.");
        } else {
            System.out.println(cars[idx]);
        }
    }

    static void searchCarByBrand() {
        System.out.println("\n--- Search Car by Brand ---");

        String brand = readString("Enter Brand: ");
        int matches = 0;

        for (int i = 0; i < carArrayCount; i++) {
            if (cars[i].getBrand().equalsIgnoreCase(brand)) {
                matches++;
                System.out.println(matches + ". " + cars[i]);
            }
        }

        if (matches == 0) {
            System.out.println("No car of brand \"" + brand + "\" was found.");
        } else {
            System.out.println("Total matches found: " + matches);
        }
    }

    static void displayAllCustomers() {
        System.out.println("\n--- All Customers ---");

        if (customerArrayCount == 0) {
            System.out.println("No customers registered.");
            return;
        }

        for (int i = 0; i < customerArrayCount; i++) {
            Customer c = customers[i];
            String carHeld = "None";

            if (c.hasCar()) {
                int carIdx = findCarIndexById(c.getRentedCarId());
                if (carIdx != -1) {
                    carHeld = cars[carIdx].getBrand() + " " + cars[carIdx].getModel()
                            + " (" + c.getNumberOfRentedDays() + " days)";
                }
            }

            System.out.println((i + 1) + ". " + c + " | Car Held: " + carHeld);
        }
    }


    static void displayStatistics() {
        System.out.println("\n--- Office Statistics ---");

        if (carArrayCount == 0) {
            System.out.println("No cars in the system yet. Statistics unavailable.");
            System.out.println("Total Income So Far: $" + String.format("%.2f", totalIncome));
            return;
        }

        int rentedCount = 0;
        double priceSum = 0.0;
        Car mostExpensive = cars[0];

        for (int i = 0; i < carArrayCount; i++) {
            Car c = cars[i];
            priceSum += c.getPricePerDay();

            if (!c.isAvailable()) {
                rentedCount++;
            }

            if (c.getPricePerDay() > mostExpensive.getPricePerDay()) {
                mostExpensive = c;
            }
        }

        double averagePrice = priceSum / carArrayCount;

        System.out.println("Total Income                : $" + String.format("%.2f", totalIncome));
        System.out.println("Number of Cars Rented Now    : " + rentedCount);
        System.out.println("Most Expensive Car           : " + mostExpensive.getBrand()
                + " " + mostExpensive.getModel() + " ($" + String.format("%.2f", mostExpensive.getPricePerDay()) + "/day)");
        System.out.println("Average Daily Price          : $" + String.format("%.2f", averagePrice));
    }


    static int findCarIndexById(int id) {
        for (int i = 0; i < carArrayCount; i++) {
            if (cars[i].getId() == id) {
                return i;
            }
        }
        return -1;
    }

    static int findCustomerIndexById(int id) {
        for (int i = 0; i < customerArrayCount; i++) {
            if (customers[i].getId() == id) {
                return i;
            }
        }
        return -1;
    }


    static int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            String line = scanner.nextLine().trim();
            try {
                return Integer.parseInt(line);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a whole number.");
            }
        }
    }

    static double readDouble(String prompt) {
        while (true) {
            System.out.print(prompt);
            String line = scanner.nextLine().trim();
            try {
                return Double.parseDouble(line);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }
    }

    static String readString(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }
}