package com.tt.wkkt.service.impl;

import com.tt.wkkt.mapper.ShowAnswerPeopleMapper;
import com.tt.wkkt.service.CompetitiveAnswerService;
import com.tt.wkkt.vo.resp.AnswerPeopleResp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @Author tianting
 * @Description
 * @Param
 * @return
 **/
@Service

public class CompetitiveAnswerServiceImpl implements CompetitiveAnswerService {
    boolean flag=false;
    @Autowired
    ShowAnswerPeopleMapper showAnswerPeopleMapper;
    @Override
    public  List<AnswerPeopleResp> showCompetitive(String user) {
        synchronized(this){
            if (flag==false){
                showAnswerPeopleMapper.insertPeople(user);
                this.flag=true;/*老师按重置的时候 this.flag=false;*/
            }

        }
        List<AnswerPeopleResp> list=showAnswerPeopleMapper.selectSuccessPeople();
        return list;
    }

    @Override
    public void resetCompetive() {
        showAnswerPeopleMapper.reCompetive();//删除之前抢答成功的人
        this.flag=false;
    }

    @Override
    public List<AnswerPeopleResp> getSuccessStudent() {
        List<AnswerPeopleResp> list=showAnswerPeopleMapper.selectSuccessPeople();
        return list;
    }
}
