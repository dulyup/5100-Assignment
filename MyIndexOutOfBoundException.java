package com.assign6;

public class MyIndexOutOfBoundException extends Exception {

    /**
     * lowerBound- the lowest legal index value.
     * upperBound- the highest legal index value.
     * index- the current index value.
     */
    private int lowerBound;
    private int upperBound;
    private int index;

    public MyIndexOutOfBoundException(int lowerBound, int upperBound) {
        this.lowerBound = lowerBound;
        this.upperBound = upperBound;
    }

    @Override
    public String toString() {
        String str = "Error Message: Index: " + index + ", but Lower bound: " + lowerBound + ", Upper bound: " + upperBound;
        return str;
    }

    public static void main(String[] args) {
        try {
            MyIndexOutOfBoundException indexOutOfBoundException = new MyIndexOutOfBoundException(0, 9);
            indexOutOfBoundException.setIndex(10);
            if (indexOutOfBoundException.getIndex() > indexOutOfBoundException.getUpperBound() || indexOutOfBoundException.getIndex() < indexOutOfBoundException.getLowerBound()) {
                throw indexOutOfBoundException;
            }
        } catch (MyIndexOutOfBoundException e) {
            e.printStackTrace();
        }
    }

    public int getLowerBound() {
        return lowerBound;
    }

    public int getUpperBound() {
        return upperBound;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}

