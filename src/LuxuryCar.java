
public class LuxuryCar extends Car {

    private double insuranceFee;
    private int minRentalDays;

    public LuxuryCar(int id, String brand, String model, int year, double pricePerDay) {
        this(id, brand, model, year, pricePerDay, 0.0);
    }


    public LuxuryCar(int id, String brand, String model, int year, double pricePerDay, double insuranceFee) {
        super(id, brand, model, year, pricePerDay);
        this.insuranceFee = insuranceFee;
        this.minRentalDays = 3;
    }

    public double getInsuranceFee() {
        return insuranceFee;
    }

    public int getMinRentalDays() {
        return minRentalDays;
    }


    @Override
    public double calculateCost(int days) {
        double subtotal = (getPricePerDay() * days) + insuranceFee;
        return subtotal + (subtotal * getTaxRate());
    }

    @Override
    public String toString() {
        return super.toString() +
                " | [LUXURY] Insurance Fee: $" + String.format("%.2f", insuranceFee) +
                " | Min Rental Days: " + minRentalDays;
    }
}