package com.tt.wkkt.vo.req;

/**
 * @Author tianting
 * @Description
 * @Param
 * @return
 **/
public class PartQuestionReqVO {

    private String type;

    private String degree;

    private String userName;

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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String toString() {
        return "PartQuestionReqVO{" +
                "type='" + type + '\'' +
                ", degree='" + degree + '\'' +
                ", userName='" + userName + '\'' +
                '}';
    }
}
