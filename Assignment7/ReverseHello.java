package com.assign7;

public class ReverseHello extends Thread {

    private static final int FIRST_THREAD = 1;
    private static final int MAX_THREAD = 50;
    private int count;

    public ReverseHello(int count) {
        this.count = count;
    }

    @Override
    public void run() {

        try {
            if (count <= MAX_THREAD) {
                createNewThread(count++);
            }
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }

    }

    private void createNewThread(int ref) throws InterruptedException {
        ReverseHello reverseHelloThread = new ReverseHello(count);
        reverseHelloThread.start();
        reverseHelloThread.join();
        System.out.println("Hello from Thread " + (count - 1));
    }

    public static void main(String[] args) throws InterruptedException {
        ReverseHello firstThread = new ReverseHello(FIRST_THREAD);
        firstThread.start();
    }
}
