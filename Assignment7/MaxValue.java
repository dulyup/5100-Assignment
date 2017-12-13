package com.assign7;

class MaxValueThread extends Thread { // score 2
    private int num;
    private int[] arr;
    private int max = Integer.MIN_VALUE;
    private static final int THREADS_NUM = 4;

    public MaxValueThread(int num, int[] arr) {
        this.num = num;
        this.arr = arr;
    }

    @Override
    public void run() {
        for (int i = num; i < arr.length; i += THREADS_NUM) {
            max = Math.max(arr[i], max);
        }
    }

    public int getMax() {
        return max;
    }
}

public class MaxValue {

    private static final int THREADS_NUM = 4;

    public static int findMaxValue(int[] arr) throws InterruptedException {
        MaxValueThread[] threads = new MaxValueThread[THREADS_NUM];
        for (int i = 0; i < THREADS_NUM; i++) {
            threads[i] = new MaxValueThread(i, arr);
            threads[i].start();
        }
        int maxValue = Integer.MIN_VALUE;
        for (int i = 0; i < THREADS_NUM; i++) {
            threads[i].join();
            maxValue = Math.max(maxValue, threads[i].getMax());
        }
        return maxValue;
    }

    public static void main(String[] args) throws InterruptedException {
        int[] arr = new int[100];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * 300);
        }
        int maxValue = findMaxValue(arr);
        System.out.println("Max Value: " + maxValue);
    }
}
