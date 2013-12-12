/**
 * Project: Signature Verification
 * @author Ajay R, Keshav Kumar HK and Sachin Sudheendra
 */

package com.yourcompany.struts;
import java.util.LinkedList;

public class ER2 {

    private double xrBarSourceData;
    private double yrBarSourceData;
    private double xtBarTestData;
    private double ytBarTestData;

    public double getRegressionValue(SignatureData sourceData, SignatureData testData) {
        double res;
        calculateMean(sourceData.getX(), sourceData.getY(), sourceData.getNum(), testData.getX(), testData.getY(), testData.getNum());
        res = evaluateFormulae(sourceData.getX(), sourceData.getY(), sourceData.getNum(), testData.getX(), testData.getY(), testData.getNum());
        return res;
    }

    public void calculateMean(LinkedList<Double> xr, LinkedList<Double> yr, int nr, LinkedList<Double> xt, LinkedList<Double> yt, int nt) {
        double sumx = 0;
        double sumy = 0;
        for (int i = 0; i < nr; i++) {
            sumx += xr.get(i);
            sumy += yr.get(i);
        }
        xrBarSourceData = sumx / nr;
        yrBarSourceData = sumy / nr;
        sumx = 0;
        sumy = 0;
        for (int i = 0; i < nt; i++) {
            sumx += xt.get(i);
            sumy += yt.get(i);
        }
        xtBarTestData = sumx / nt;
        ytBarTestData = sumy / nt;
    }

    public double evaluateFormulae(LinkedList<Double> xr, LinkedList<Double> yr, int nr, LinkedList<Double> xt, LinkedList<Double> yt, int nt) {
        double nume;
        nume = calculateNumerator(xr, yr, nr, xt, yt, nt);
        double deno = calculateDenominator(xr, yr, nr, xt, yt, nt);
        return (nume / deno);
    }

    public double calculateNumerator(LinkedList<Double> xr, LinkedList<Double> yr, int nr, LinkedList<Double> xt, LinkedList<Double> yt, int nt) {
        double insum1 = 0;
        for (int i = 0; i < nr; i++) {
            insum1 += (xr.get(i) - xrBarSourceData) * (xt.get(i) - xtBarTestData);
        }
        double insum2 = 0;
        for (int i = 0; i < nt; i++) {
            insum2 += (yr.get(i) - yrBarSourceData) * (yt.get(i) - ytBarTestData);
        }
        double finalNumeSum = insum1 + insum2;
        return finalNumeSum * finalNumeSum;
    }

    public double calculateDenominator(LinkedList<Double> xr, LinkedList<Double> yr, int nr, LinkedList<Double> xt, LinkedList<Double> yt, int nt) {
        double xsum1 = 0;
        for (int i = 0; i < nr; i++) {
            xsum1 += (xr.get(i) - xrBarSourceData) * (xr.get(i) - xrBarSourceData);
        }
        double ysum1 = 0;
        for (int i = 0; i < nr; i++) {
            ysum1 += (yr.get(i) - yrBarSourceData) * (yr.get(i) - yrBarSourceData);
        }
        double sum1 = xsum1 + ysum1;
        double xsum2 = 0;
        for (int i = 0; i < nt; i++) {
            xsum2 += (xt.get(i) - xtBarTestData) * (xt.get(i) - xtBarTestData);
        }
        double ysum2 = 0;
        for (int i = 0; i < nt; i++) {
            ysum2 += (yt.get(i) - ytBarTestData) * (yt.get(i) - ytBarTestData);
        }
        double sum2 = xsum2 + ysum2;
        return sum1 * sum2;
    }
}
