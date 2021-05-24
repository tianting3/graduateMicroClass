package com.tt.wkkt.model;

import java.util.Date;

/**
 * @Author tianting
 * @Description
 * @Param
 * @return
 **/
public class ReviewPaper {
    private int id;
    private int paperId;
    private String teacherUser;
    private String studentUser;
    private String paperName;
    private int totalGrade;
    private int status;
    private int type;
    private String uploadTime;

    public String getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(String uploadTime) {
        this.uploadTime = uploadTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPaperId() {
        return paperId;
    }

    public void setPaperId(int paperId) {
        this.paperId = paperId;
    }

    public String getTeacherUser() {
        return teacherUser;
    }

    public void setTeacherUser(String teacherUser) {
        this.teacherUser = teacherUser;
    }

    public String getStudentUser() {
        return studentUser;
    }

    public void setStudentUser(String studentUser) {
        this.studentUser = studentUser;
    }

    public String getPaperName() {
        return paperName;
    }

    public void setPaperName(String paperName) {
        this.paperName = paperName;
    }

    public int getTotalGrade() {
        return totalGrade;
    }

    public void setTotalGrade(int totalGrade) {
        this.totalGrade = totalGrade;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
