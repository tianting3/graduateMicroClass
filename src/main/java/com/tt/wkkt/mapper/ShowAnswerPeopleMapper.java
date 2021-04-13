package com.tt.wkkt.mapper;

import com.tt.wkkt.vo.resp.AnswerPeopleResp;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @Author tianting
 * @Description
 * @Param
 * @return
 **/
public interface ShowAnswerPeopleMapper {
    void insertPeople(@Param("userName") String userName);

    List<AnswerPeopleResp> selectSuccessPeople();

    void reCompetive();
}
