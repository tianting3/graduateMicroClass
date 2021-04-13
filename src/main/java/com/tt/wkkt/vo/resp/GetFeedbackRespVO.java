package com.tt.wkkt.vo.resp;

import java.util.Date;

/**
 * @Author tianting
 * @Description
 * @Param
 * @return
 **/
public class GetFeedbackRespVO {
    private int id;

    private String userName;

    private String tPropose;

    private String wPropose;

    private String feedback;

    private String way;

    private String atmosphere;

    private Date time;

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

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "GetFeedbackRespVO{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", tPropose='" + tPropose + '\'' +
                ", wPropose='" + wPropose + '\'' +
                ", feedback='" + feedback + '\'' +
                ", way='" + way + '\'' +
                ", atmosphere='" + atmosphere + '\'' +
                ", time=" + time +
                '}';
    }
}
