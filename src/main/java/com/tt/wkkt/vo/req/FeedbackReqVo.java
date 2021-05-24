package com.tt.wkkt.vo.req;

/**
 * @Author tianting
 * @Description
 * @Param
 * @return
 **/
public class FeedbackReqVo {

    private String userName;

    private String tPropose;

    private  String wPropose;

    private String feedback;

    private String way;

    private String atmosphere;

    private String coursePropose;

    private String teacherUser;

    public String getCoursePropose() {
        return coursePropose;
    }

    public void setCoursePropose(String coursePropose) {
        this.coursePropose = coursePropose;
    }

    public String getTeacherUser() {
        return teacherUser;
    }

    public void setTeacherUser(String teacherUser) {
        this.teacherUser = teacherUser;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String gettPropose() {
        return tPropose;
    }

    public void settPropose(String tPropose) {
        this.tPropose = tPropose;
    }

    public String getwPropose() {
        return wPropose;
    }

    public void setwPropose(String wPropose) {
        this.wPropose = wPropose;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }



    public String getWay() {
        return way;
    }

    public void setWay(String way) {
        this.way = way;
    }

    public String getAtmosphere() {
        return atmosphere;
    }

    public void setAtmosphere(String atmosphere) {
        this.atmosphere = atmosphere;
    }

    @Override
    public String toString() {
        return "FeedbackReqVo{" +
                "userName='" + userName + '\'' +
                ", tPropose='" + tPropose + '\'' +
                ", wPropose='" + wPropose + '\'' +
                ", feedback='" + feedback + '\'' +
                ", way='" + way + '\'' +
                ", atmosphere='" + atmosphere + '\'' +
                '}';
    }
}
