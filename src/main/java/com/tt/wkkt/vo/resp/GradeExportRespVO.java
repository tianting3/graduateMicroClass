package com.tt.wkkt.vo.resp;

/**
 * @Author tianting
 * @Description
 * @Param
 * @return
 **/
public class GradeExportRespVO {
    private String studentUser;
    private String studentName;
    private String paperName;
    private int totalGrade;


    public String getStudentUser() {
        return studentUser;
    }

    public void setStudentUser(String studentUser) {
        this.studentUser = studentUser;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
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
}
