package com.tt.wkkt.vo.req;

/**
 * @Author tianting
 * @Description
 * @Param
 * @return
 **/
public class ChoiceQuestionReqVO {
    private int id;
    private int questionId;
    private int orderNum;
    private String teacherName;
    private int paperId;

    public int getPaperId() {
        return paperId;
    }

    public void setPaperId(int paperId) {
        this.paperId = paperId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public int getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(int orderNum) {
        this.orderNum = orderNum;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    @Override
    public String toString() {
        return "ChoiceQuestionReqVO{" +
                "id=" + id +
                ", questionId=" + questionId +
                ", orderNum=" + orderNum +
                ", teacherName='" + teacherName + '\'' +
                ", paperId=" + paperId +
                '}';
    }
}
