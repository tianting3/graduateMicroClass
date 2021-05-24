package com.tt.wkkt.vo.req;

import java.util.Map;

/**
 * @Author tianting
 * @Description
 * @Param
 * @return
 **/
public class SendPaperReqVO {
    private Map<String,Object> map;
    private String paperId;

    public Map<String, Object> getMap() {
        return map;
    }

    public void setMap(Map<String, Object> map) {
        this.map = map;
    }

    public String getPaperId() {
        return paperId;
    }

    public void setPaperId(String paperId) {
        this.paperId = paperId;
    }
}
