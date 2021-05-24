package com.tt.wkkt.model;

/**
 * @Author tianting
 * @Description
 * @Param
 * @return
 **/
public class ShowReviewQuestion {

    private int reviewId;

    private int questionId;

    private String uploadAnswer;

    private int id;

    public int getReviewId() {
        return reviewId;
    }

    public void setReviewId(int reviewId) {
        this.reviewId = reviewId;
    }

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public String getUploadAnswer() {
        return uploadAnswer;
    }

    public void setUploadAnswer(String uploadAnswer) {
        this.uploadAnswer = uploadAnswer;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
