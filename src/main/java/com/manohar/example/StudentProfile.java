package com.manohar.example;

import jakarta.persistence.*;

@Entity
public class StudentProfile {

    @Id
    @GeneratedValue
    private Integer id;

    private String bio;

    @OneToOne
    @JoinColumn(name = "student_id")
    private Student student;

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public StudentProfile(Integer id, String bio) {
        this.id = id;
        this.bio = bio;
    }

    public StudentProfile() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }
}
