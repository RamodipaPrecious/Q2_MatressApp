package matressapp;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class ProductCalculationsTest {

    @Test
    public void GetVat_WithValidData_ReturnsCorrectValue() {

        ProductCalculations calculation = new ProductCalculations(3500);

        assertEquals(525.0, calculation.GetVat(), 0.001);
    }

    @Test
    public void GetTotal_WithValidData_ReturnsCorrectValue() {

        ProductCalculations calculation = new ProductCalculations(3500);

        assertEquals(4200.0, calculation.GetTotal(), 0.001);
    }

}