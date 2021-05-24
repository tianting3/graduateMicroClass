package com.tt.wkkt.vo.resp;

import java.util.List;

/**
 * @Author tianting
 * @Description
 * @Param
 * @return
 **/
public class ShowReviewQuestionRespVO {
    private int reviewId;

    private int questionId;

    private char[] uploadAnswer;

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

    public char[] getUploadAnswer() {
        return uploadAnswer;
    }

    public void setUploadAnswer(char[] uploadAnswer) {
        this.uploadAnswer = uploadAnswer;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
