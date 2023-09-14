package com.kingmalitha.spring_testing.student;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.kingmalitha.spring_testing.school.School;
import com.kingmalitha.spring_testing.studentProfile.StudentProfile;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name = "student")
@NoArgsConstructor
@Data
@AllArgsConstructor
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String firstname;

    private String lastname;

    @Column(unique = true)
    private String email;

    private int age;

    @OneToOne(
            mappedBy = "student",
            cascade = CascadeType.ALL
    )
    private StudentProfile studentProfile;

    @ManyToOne
    @JoinColumn(
            name = "school_id"
    )
    @JsonBackReference
    private School school;

    public Student(String malitha, String sandaruwan, String mail, int age) {
    }
}
