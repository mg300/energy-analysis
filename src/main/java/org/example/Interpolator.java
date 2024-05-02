package org.example;

import org.apache.commons.math3.analysis.interpolation.SplineInterpolator;
import org.apache.commons.math3.analysis.polynomials.PolynomialSplineFunction;

public class Interpolator {
    private double precision=0.01;
    private double start=0;
    private double end=24.5;
     public double[] getNewPrecision(){
        int size = (int) ((end - start) / precision) + 1;
        double[] result = new double[size];
        for(int i=0; i<size; i++){
            result[i] = Math.round((i*precision+start)*100)/100.00;
        }
        return result;
    }
    public double[] interpolate(double[] x, double[] y){
        SplineInterpolator interpolator = new SplineInterpolator();
        PolynomialSplineFunction function = interpolator.interpolate(x, y);
        double[] resX = getNewPrecision();
        double[] result = new double[resX.length];
        for(int i=0; i<resX.length; i++){
            result[i] = Math.round(function.value(resX[i])*100)/100.00;
        }

        return result;
    }

}
