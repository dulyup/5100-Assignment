package com.assign4;

public class Test {
    public static void main(String[] args) {

        Student s1 = new Student("a",1);
        Student s2 = new Student("b",2);
        Student s3 = new Student("c",3);
        Student s4 = new Student("d",4);
        Student s5 = new Student("e",5);
        Student s6 = new Student("f",6);
        Student s7 = new Student("g",7);
        Student s8 = new Student("h",8);
        Student s9 = new Student("i",9);
        Student s10 = new Student("j",10);
        Student s11 = new Student("k",11);

        Course course = new Course("Java");
        System.out.println(course.isFull());

        course.registerStudent(s1);
        course.registerStudent(s2);
        course.registerStudent(s3);
        course.registerStudent(s4);
        course.registerStudent(s5);
        course.registerStudent(s6);
        course.registerStudent(s7);
        course.registerStudent(s8);
        course.registerStudent(s9);
//        course.registerStudent(s9);
        course.registerStudent(s10);
        course.registerStudent(s11);
    }


}
