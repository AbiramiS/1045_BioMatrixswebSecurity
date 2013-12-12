package com.yourcompany.struts;

/**
 * Project: Signature Verification
 * @author Ajay R, Keshav Kumar HK and Sachin Sudheendra
 */

import java.util.ArrayList;
import java.util.Scanner;
import java.util.LinkedList;
import java.io.*;

public class DataIO {
    public static SignatureData readData(String folderName, String fileName) throws IOException {
        FileInputStream ip = new FileInputStream(folderName + "\\" + fileName);
        LinkedList<Double> xData = new LinkedList<Double>();
        LinkedList<Double> yData = new LinkedList<Double>();
        Scanner sc = new Scanner(ip);
        while (sc.hasNext()) {
            xData.add(sc.nextDouble());
            yData.add(sc.nextDouble());
        }
        ip.close();
        sc.close();
        return new SignatureData(xData, yData, 0);
    }

    public static void writeData(SignatureData signatureData, String folderName, String fileName) throws IOException {
        LinkedList<Double> x = signatureData.getX();
        LinkedList<Double> y = signatureData.getY();
        File file = new File(folderName);
        if (!file.isDirectory()) {
            createDir(folderName);
        }
        FileOutputStream op = new FileOutputStream(folderName + "\\" + fileName);
        PrintWriter pw = new PrintWriter(op);
        for (int i = 0; i < x.size(); i++) {
            pw.println(x.get(i) + " " + y.get(i));
        }
        pw.close();
        op.close();
    }

    private static void createDir(String folderName) {
        File file = new File(folderName);
        if (!file.mkdir()) {
            throw new RuntimeException("Cannot Create Directory");
        }
    }
}
