package com.tt.wkkt.model;

/**
 * @Author tianting
 * @Description
 * @Param
 * @return
 **/
public class TestPaper {
    private int id;
    private String testName;
    private int number;
    private int score;
    private int testTime;
    private String degree;
    private String teacherName;

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getTestTime() {
        return testTime;
    }

    public void setTestTime(int testTime) {
        this.testTime = testTime;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    @Override
    public String toString() {
        return "TestPaper{" +
                "id=" + id +
                ", testName='" + testName + '\'' +
                ", number=" + number +
                ", score=" + score +
                ", testTime=" + testTime +
                ", degree=" + degree +
                ", teacherName='" + teacherName + '\'' +
                '}';
    }
}
