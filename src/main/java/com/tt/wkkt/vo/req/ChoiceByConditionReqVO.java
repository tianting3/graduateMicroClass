package com.tt.wkkt.vo.req;

/**
 * @Author tianting
 * @Description
 * @Param
 * @return
 **/
public class ChoiceByConditionReqVO {
    private String userName;
    private String type;
    private String degree;
    private String points;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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
}
