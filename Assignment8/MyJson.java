/* Good Work
 * Total score 10
 */
package com.assign8;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Year;
import java.util.ArrayList;


public class MyJson {

    /**
     * read from the file, create vehicle objects, add them to an arrayList and return the arrayList.
     *
     * @param file
     * @return
     * @throws FileNotFoundException
     */
    private static ArrayList<Vehicle> readAndGetVehicles(File file) throws IOException {
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);
        ArrayList<Vehicle> vehicleList = new ArrayList<>();
        br.readLine();
        while (true) {
            String line = br.readLine();
            if (line == null) {
                break;
            }
            String[] arr = line.split("~");
            vehicleList.add(new Vehicle(arr));
        }
        br.close();
        return vehicleList;
    }

    /**
     * convert its content to a json string
     *
     * @param vehicles
     * @return
     */
    public static String getJsonString(ArrayList<Vehicle> vehicles) {
        StringBuilder sb = new StringBuilder();
        sb.append("{\n" + "\"gmps-camino\" : [\n");
        for (Vehicle vehicle : vehicles) {
            sb.append(vehicle.toString() + ",\n");
        }
        sb.delete(sb.length() - 2, sb.length() - 1);
        sb.append("]\n" + "}");
        return sb.toString();
    }

    /**
     * write the input string to the file created in the path given
     *
     * @param inputToWrite
     * @param filePath
     */
    public static void writeToFile(String inputToWrite, String filePath) throws IOException {
        File fileWrite = new File(filePath + "/Question3_write.txt");
        fileWrite.createNewFile();
        FileWriter fw = new FileWriter(fileWrite);
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write(inputToWrite);
        bw.close();
    }

    public static void main(String[] args) throws IOException {
        File file = new File("/Users/lyupingdu/IdeaProjects/JavaAssign/src/com/assign8/Question3_camino.txt");
        ArrayList<Vehicle> vehicles = readAndGetVehicles(file);
        String s = getJsonString(vehicles);
        writeToFile(s, file.getParent());
    }
}

class Vehicle {

    String id;
    String webId;
    Category category;
    Year year;
    String make;
    String model;
    String trim;
    String type;
    double price;
    URL photo;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{\n");
        sb.append("\"id\" : \"" + id + "\",\n");
        sb.append("\"category\" : \"" + category + "\",\n");
        sb.append("\"year\" : \"" + year + "\",\n");
        sb.append("\"make\" : \"" + make + "\",\n");
        sb.append("\"model\" : \"" + model + "\",\n");
        sb.append("\"trim\" : \"" + trim + "\",\n");
        sb.append("\"type\" : \"" + type + "\",\n");
        sb.append("\"price\" : \"" + price + "\",\n");
        sb.append("\"photo\" : \"" + photo + "\"\n}");
        return sb.toString();

    }

    Vehicle(String[] arr) {
        this.id = arr[0];
        this.webId = arr[1];
        this.category = Category.getCategory(arr[2].toLowerCase());
        this.year = Year.parse(arr[3]);
        this.make = arr[4];
        this.model = arr[5];
        this.trim = arr[6];
        this.type = arr[7];
        this.price = Double.parseDouble(arr[8]);
        try {
            this.photo = new URL(arr[9]);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

}

enum Category {
    NEW, USED, CERTIFIED;

    public static Category getCategory(String cat) {
        switch (cat) {
            case "used":
                return USED;
            case "new":
                return NEW;
            case "certified":
                return CERTIFIED;
            default:
                throw new IllegalArgumentException();
        }
    }

    @Override
    public String toString() {
        switch (this) {
            case NEW:
                return "NEW";
            case USED:
                return "USED";
            case CERTIFIED:
                return "CERTIFIED";
            default:
                throw new IllegalArgumentException();
        }
    }
}
