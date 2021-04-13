package com.tt.wkkt.model;

/**
 * @Author tianting
 * @Description
 * @Param
 * @return
 **/
public class Question {
    private int id;
    private String question;
    private String items;
    private String answer;
    private String type;
    private String degree;
    private String points;
    private String userName;
    private String questionScore;

    public String getQuestionScore() {
        return questionScore;
    }

    public void setQuestionScore(String questionScore) {
        this.questionScore = questionScore;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getItems() {
        return items;
    }

    public void setItems(String items) {
        this.items = items;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public String getPoints() {
        return points;
    }

    public void setPoints(String points) {
        this.points = points;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String toString() {
        return "Question{" +
                "id=" + id +
                ", question='" + question + '\'' +
                ", items='" + items + '\'' +
                ", answer='" + answer + '\'' +
                ", type='" + type + '\'' +
                ", degree='" + degree + '\'' +
                ", points='" + points + '\'' +
                ", userName='" + userName + '\'' +
                ", questionScore='" + questionScore + '\'' +
                '}';
    }
}
