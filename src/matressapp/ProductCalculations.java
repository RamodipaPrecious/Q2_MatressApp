package matressapp;

public class ProductCalculations implements IProductCalculations {

    private double price;

    // Constructor
    public ProductCalculations(double price) {
        this.price = price;
    }

    // Calculate VAT (15%)
    @Override
    public double GetVat() {
        return price * 0.15;
    }

    // Calculate Commission (5%)
    @Override
    public double GetCommission() {
        return price * 0.05;
    }

    // Calculate Total Sale Amount
    @Override
    public double GetTotal() {
        return price + GetVat() + GetCommission();
    }

}