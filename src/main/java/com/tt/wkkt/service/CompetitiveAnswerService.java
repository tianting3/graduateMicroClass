package com.tt.wkkt.service;

import com.tt.wkkt.mapper.ShowAnswerPeopleMapper;
import com.tt.wkkt.vo.resp.AnswerPeopleResp;

import java.util.List;
import java.util.Map;

/**
 * @Author tianting
 * @Description
 * @Param
 * @return
 **/
public interface CompetitiveAnswerService {
    List<AnswerPeopleResp> showCompetitive(String userName);

    void resetCompetive();

    List<AnswerPeopleResp> getSuccessStudent();
}
