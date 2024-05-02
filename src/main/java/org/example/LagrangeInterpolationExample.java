package org.example;

import org.apache.commons.math3.analysis.polynomials.PolynomialFunctionLagrangeForm;

public class LagrangeInterpolationExample {

    public static void main() {
        double[] x = {0,0.1,0.2, 1.0, 3.0, 4.0, 5.0};
        double[] y = {-1.0,0.0,-1.0, -1.0, 5.0, 4.0, 5.0};

        PolynomialFunctionLagrangeForm lagrangeInterpolator = new PolynomialFunctionLagrangeForm(x, y);

        double interpolatedValue = lagrangeInterpolator.value(2);
        for (int i=0;i<10;i++){
            System.out.println(lagrangeInterpolator.value(i));

        }
    }
}
