package com.assign7;

public class Controller extends Thread {

    private Device device;
    private Sensor heat;
    private Sensor pressure;

    public Controller(Device device, Sensor heat, Sensor pressure) {
        this.device = device;
        this.heat = heat;
        this.pressure = pressure;
    }

    @Override
    public void run() {
        device.startup();
        synchronized (device) {
            while (true) {
                try {
                    device.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("heat -> " + heat.getValue() + " , pressure -> " + pressure.getValue());
                if (heat.getValue() > 70 || pressure.getValue() > 100) {
                    device.shutdown();
                    break;
                }
            }
        }
    }
}
