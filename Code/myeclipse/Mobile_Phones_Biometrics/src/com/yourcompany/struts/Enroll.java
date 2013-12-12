/**
 * Project: Signature Verification
 * @author Ajay R, Keshav Kumar HK and Sachin Sudheendra
 */

package com.yourcompany.struts;
import java.util.ArrayList;
import java.util.LinkedList;

public class Enroll {
    public SignatureData enrollSignature(SignatureData signatureData) {
        LinkedList<Double> yList = signatureData.getY();
        LinkedList<Double> xList = signatureData.getX();
        
        System.out.println(yList+"()()"+xList);
        double minx, maxx;
        double miny, maxy;
        minx = min(xList);
        maxx = max(xList);
        miny = min(yList);
        maxy = max(yList);
        LinkedList<Double> xTemp = new LinkedList<Double>();
        LinkedList<Double> yTemp = new LinkedList<Double>();
        for (int i = 0; i < (signatureData.getX()).size(); i++) {
            xTemp.add((xList.get(i) - minx) / (maxx - minx));
            yTemp.add((yList.get(i) - miny) / (maxy - miny));
        }
        return new SignatureData(xTemp, yTemp, 0);
    }

    private double min(LinkedList<Double> data) {
        double minTemp = Double.MAX_VALUE;
        for (Double aDouble : data) {
            if (aDouble < minTemp)
                minTemp = aDouble;
        }
        return minTemp;
    }

    private double max(LinkedList<Double> data) {
        double maxTemp = Double.MIN_VALUE;
        for (Double aDouble : data) {
            if (aDouble > maxTemp)
                maxTemp = aDouble;
        }
        return maxTemp;
    }
}
