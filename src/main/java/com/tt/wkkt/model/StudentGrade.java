package com.tt.wkkt.model;

import java.io.Serializable;

/**
 * @Author tianting
 * @Description  成绩表
 * @Param
 * @return
 **/
public class StudentGrade implements Serializable {

    private static final long serialVersionUID=1L;
    private int id;
    private  String studentUser;
    private int competitiveGrade;
    private int examGrade;
    private int courseId;
    private String courseName;
    private String teacherUser;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStudentUser() {
        return studentUser;
    }

    public void setStudentUser(String studentUser) {
        this.studentUser = studentUser;
    }

    public int getCompetitiveGrade() {
        return competitiveGrade;
    }

    public void setCompetitiveGrade(int competitiveGrade) {
        this.competitiveGrade = competitiveGrade;
    }

    public int getExamGrade() {
        return examGrade;
    }

    public void setExamGrade(int examGrade) {
        this.examGrade = examGrade;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getTeacherUser() {
        return teacherUser;
    }

    public void setTeacherUser(String teacherUser) {
        this.teacherUser = teacherUser;
    }

    @Override
    public String toString() {
        return "StudentGrade{" +
                "id=" + id +
                ", studentUser='" + studentUser + '\'' +
                ", competitiveGrade=" + competitiveGrade +
                ", examGrade=" + examGrade +
                ", courseId=" + courseId +
                ", courseName='" + courseName + '\'' +
                ", teacherUser='" + teacherUser + '\'' +
                '}';
    }
}
