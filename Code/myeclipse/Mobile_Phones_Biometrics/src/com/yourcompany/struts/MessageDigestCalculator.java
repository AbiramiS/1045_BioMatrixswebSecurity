package com.yourcompany.struts;

/**
 * Project: Signature Verfication
 * @author Programmers: Ajay R, Keshav Kumar HK and Sachin Sudheendra
 */


import sun.misc.BASE64Encoder;

import java.io.*;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class MessageDigestCalculator {

    private BASE64Encoder base64Encoder;

    public MessageDigestCalculator() {
        base64Encoder = new BASE64Encoder();
    }

    public byte[] computeDigest(String folderName, String fileName) throws NoSuchAlgorithmException, IOException {
        InputStream ip = new FileInputStream(folderName + "\\" + fileName);
        MessageDigest messageDigest;
        DigestInputStream digestInputStream;
        messageDigest = MessageDigest.getInstance("MD5");
        digestInputStream = new DigestInputStream(ip, messageDigest);
        byte[] buffer = new byte[8000];
        while (digestInputStream.read(buffer) != -1) ;
        ip.close();
        return messageDigest.digest();
    }

    public String fetchDigest(String folderName, String fileName) throws IOException {
        FileInputStream ip = new FileInputStream(folderName + "\\" + fileName);
        String digest = new Scanner(ip).next();
        ip.close();
        return digest;
    }

    public void recordDigestToDisk(byte[] dig, String folderName, String firstTemplateSignatureNormDigest) {
        FileOutputStream op;
        try {        	
        	File file = new File(folderName);
            if (!file.isDirectory()) {
                createDir(folderName);
            }            
            op = new FileOutputStream(folderName + "\\" + firstTemplateSignatureNormDigest);
            PrintWriter printWriter = new PrintWriter(op);
            printWriter.print(base64Encoder.encode(dig));
            printWriter.close();
            op.close();
        }
        catch (IOException e) {

        }
    }
    private static void createDir(String folderName) {
        File file = new File(folderName);
        if (!file.mkdir()) {
            throw new RuntimeException("Cannot Create Directory");
        }
    }
}
