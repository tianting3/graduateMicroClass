package com.tt.wkkt.model;

import java.util.Date;

/**
 * @Author tianting
 * @Description  提问表
 * @Param
 * @return
 **/
public class AskQuestion {
    private int id;
    private String userName;
    private String time;
    private String teacherName;
    private String askQuestion;//提问的问题
    private int reply;//是否已解答

    public int getReply() {
        return reply;
    }

    public void setReply(int reply) {
        this.reply = reply;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }


    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getAskQuestion() {
        return askQuestion;
    }

    public void setAskQuestion(String askQuestion) {
        this.askQuestion = askQuestion;
    }
}
