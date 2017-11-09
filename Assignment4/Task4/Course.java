package com.assign4;

public class Course { // score 2

    private String title;
    private int numberOfStudent = 0;
    private Student[] registeredStudent = new Student[10];

    public Course(String title) {
        this.title = title;
    }

    public boolean isFull() {
        if (getNumberOfStudent() < 10) return false;
        else return true;
    }

    public void registerStudent(Student student) {
        if (student == null) return;
        if (isFull()) {
            System.out.println("Sorry, the course is full.");
        } else {

            for (Student s : registeredStudent) {
                if (s != null && s.getId() == student.getId()) {
                    System.out.println("Sorry, you have already registered");
                    return;
                }
            }

            registeredStudent[getNumberOfStudent()] = student;
            numberOfStudent++;
            System.out.println("Congratulations! " + student.getName() + " Registered successfully");
        }
    }

    public Student[] getRegisteredStudent() {
        return registeredStudent;
    }

    public String getTitle() {
        return title;
    }

    public int getNumberOfStudent() {
        return numberOfStudent;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setRegistered(Student[] registeredStudent) {
        this.registeredStudent = registeredStudent;
    }
}
