package com.pao.laboratory03.exercise.service;

import com.pao.laboratory03.exceptions.StudentNotFoundException;
import com.pao.laboratory03.exercise.model.Student;
import com.pao.laboratory03.exercise.model.Subject;

import java.util.*;

public class StudentService {
    private List<Student> students = new ArrayList<>();

    private StudentService() {}

    private static class Holder {
        private static final StudentService INSTANCE = new StudentService();
    }

    public static StudentService getInstance() {
        return Holder.INSTANCE;
    }

    public void addStudent(String name, int age) {
        for (Student s : students) {
            if (s.getName().equalsIgnoreCase(name)) {
                throw new RuntimeException("Studentul cu numele " + name + " există deja.");
            }
        }
        students.add(new Student(name, age));
    }

    public Student findByName(String name) {
        return students.stream()
                .filter(s -> s.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElseThrow(() -> new StudentNotFoundException("Studentul " + name + " nu a fost găsit."));
    }

    public void addGrade(String studentName, Subject subject, double grade) {
        Student s = findByName(studentName);
        s.addGrade(subject, grade);
    }

    public void printAllStudents() {
        if (students.isEmpty()) { System.out.println("Nu există studenți."); return; }
        students.forEach(System.out::println);
    }

    public void printTopStudents() {
        students.stream()
                .sorted((s1, s2) -> Double.compare(s2.getAverage(), s1.getAverage()))
                .forEach(System.out::println);
    }

    public Map<Subject, Double> getAveragePerSubject() {
        Map<Subject, Double> sums = new HashMap<>();
        Map<Subject, Integer> counts = new HashMap<>();

        for (Student s : students) {
            s.getGrades().forEach((sub, grade) -> {
                sums.put(sub, sums.getOrDefault(sub, 0.0) + grade);
                counts.put(sub, counts.getOrDefault(sub, 0) + 1);
            });
        }

        Map<Subject, Double> averages = new HashMap<>();
        sums.forEach((sub, total) -> averages.put(sub, total / counts.get(sub)));
        return averages;
    }
}