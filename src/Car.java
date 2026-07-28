/**
 * Represents a single vehicle in the SpeedWay Rentals fleet.
 * This is the parent class for all vehicle types (regular and luxury cars).
 */
public class Car {

    private int id;
    private String brand;
    private String model;
    private int year;
    private double pricePerDay;
    private boolean available;

    private static int carCount = 0;


    private static final double TAX_RATE = 0.14;


    public Car() {
        this(0, "Unknown", "Unknown", 2000, 0.0);
    }


    public Car(int id, String brand, String model, int year, double pricePerDay) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.pricePerDay = pricePerDay;
        this.available = true;
        carCount++;
    }


    public int getId() {
        return id;
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public int getYear() {
        return year;
    }

    public double getPricePerDay() {
        return pricePerDay;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public static int getCarCount() {
        return carCount;
    }

    public static double getTaxRate() {
        return TAX_RATE;
    }


    public double calculateCost(int days) {
        double subtotal = pricePerDay * days;
        return subtotal + (subtotal * TAX_RATE);
    }

    @Override
    public String toString() {
        return "Car ID: " + id +
                " | Brand: " + brand +
                " | Model: " + model +
                " | Year: " + year +
                " | Price/Day: $" + String.format("%.2f", pricePerDay) +
                " | Status: " + (available ? "Available" : "Rented");
    }
}