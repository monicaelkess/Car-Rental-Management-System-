public class Customer {

    private int id;
    private String name;
    private String phone;
    private int rentedCarId;        // -1 means the customer has no car
    private int numberOfRentedDays; // 0 when nothing is rented
    private double totalPaid;       // sum of everything ever paid


    private static int count = 0;

    public Customer(int id, String name, String phone) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.rentedCarId = -1;
        this.numberOfRentedDays = 0;
        this.totalPaid = 0.0;
        count++;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public int getRentedCarId() {
        return rentedCarId;
    }

    public int getNumberOfRentedDays() {
        return numberOfRentedDays;
    }

    public double getTotalPaid() {
        return totalPaid;
    }

    public static int getCount() {
        return count;
    }

    public boolean hasCar() {
        return rentedCarId != -1;
    }

    public void rentCar(int carId, int days, double cost) {
        this.rentedCarId = carId;
        this.numberOfRentedDays = days;
        this.totalPaid += cost;
    }

    public void returnCar() {
        this.rentedCarId = -1;
        this.numberOfRentedDays = 0;
    }

    @Override
    public String toString() {
        return "Customer ID: " + id +
                " | Name: " + name +
                " | Phone: " + phone +
                " | Total Paid: $" + String.format("%.2f", totalPaid);
    }
}