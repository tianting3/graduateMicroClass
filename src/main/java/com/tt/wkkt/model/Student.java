package com.tt.wkkt.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @Author tianting
 * @Description
 * @Param
 * @return
 **/
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "student")
public class Student implements Serializable {
    @Id/*主键*/
    @GeneratedValue(strategy= GenerationType.IDENTITY)/*自增*/
    @Column(name = "id")
    private int id;
    @Column(name = "student_name")
    private  String studentName;
    @Column(name = "user_name")
    private String userName;
    @Column(name = "password")
    private String password;


}
