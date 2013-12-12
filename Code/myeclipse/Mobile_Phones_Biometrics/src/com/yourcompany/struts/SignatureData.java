/**
 * Project: Signature Verification
 * @author Ajay R, Keshav Kumar HK and Sachin Sudheendra
 */

package com.yourcompany.struts;

import java.util.LinkedList;

public class SignatureData {
    LinkedList x;
    LinkedList y;
    int num;
    int penUp;

    public SignatureData() {
        x = new LinkedList<Double>();
        y = new LinkedList<Double>();
    }

    public SignatureData(LinkedList<Double> xArg, LinkedList<Double> yArg, int penUp) {
        this.x = xArg;
        this.y = yArg;
        num = xArg.size();
        this.penUp = penUp;
    }

    public int getPenUp() {
        return penUp;
    }

    public void setPenUp(int penUp) {
        this.penUp = penUp;
    }

    public LinkedList getX() {
        return x;
    }

    public void setX(LinkedList<Double> x) {
        this.x = x;
    }

    public LinkedList getY() {
        return y;
    }

    public void setY(LinkedList<Double> y) {
        this.y = y;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SignatureData that = (SignatureData) o;
        if (num != that.num) return false;
        if (penUp != that.penUp) return false;
        if (x != null ? !x.equals(that.x) : that.x != null) return false;
        if (y != null ? !y.equals(that.y) : that.y != null) return false;
        return true;
    }

    public int hashCode() {
        int result;
        result = (x != null ? x.hashCode() : 0);
        result = 31 * result + (y != null ? y.hashCode() : 0);
        result = 31 * result + num;
        result = 31 * result + penUp;
        return result;
    }
}
