package com.tt.wkkt.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tt.wkkt.mapper.ManageQuestionMapper;
import com.tt.wkkt.model.Question;
import com.tt.wkkt.service.ManageQuestionService;
import com.tt.wkkt.vo.req.PartQuestionReqVO;
import com.tt.wkkt.vo.resp.QuestionRespVO;
import org.mapstruct.Qualifier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
 * @Author tianting
 * @Description
 * @Param
 * @return
 **/
@Service
public class ManageQuestionServiceImpl implements ManageQuestionService {

    @Autowired
    ManageQuestionMapper manageQuestionMapper;
    public HashMap<String,Object> pageQuestion(PartQuestionReqVO PartQuestionReqVO,Integer pageNum,Integer pageSize){
        /*开启分页*/
        PageHelper.startPage(pageNum,pageSize);
        List<QuestionRespVO> list = manageQuestionMapper.selectAllByuserName(PartQuestionReqVO);
        for(int i=0;i<list.size();i++){
                if (list.get(i).getDegree().equals("1")){
                    list.get(i).setDegree("简单");
                }else if (list.get(i).getDegree().equals("2")){
                    list.get(i).setDegree("中等");
                }else{
                    list.get(i).setDegree("难");
                }
                if (list.get(i).getType().equals("1")){
                    list.get(i).setType("单选题");
                }else  if (list.get(i).getType().equals("2")){
                    list.get(i).setType("多选题");
                }else  if (list.get(i).getType().equals("3")){
                    list.get(i).setType("判断题");
                }else  if (list.get(i).getType().equals("4")){
                    list.get(i).setType("问答题");
                }else  {
                    list.get(i).setType("讨论题");
                }
            }
        PageInfo<QuestionRespVO> info=new PageInfo<>(list);
        HashMap<String,Object> map=new HashMap<>();
        map.put("data",info.getList());/*返回指定页面的数据*/
        map.put("nowPage",pageNum);/*返回当前页面*/
        map.put("total",((int)info.getTotal()+pageSize-1)/pageSize);//返回总页面数
        map.put("size",info.getTotal());
        System.out.println(map.get("size"));
        return map;
    }

    @Override
    public List<QuestionRespVO> allQuestion(PartQuestionReqVO question) {
        return manageQuestionMapper.selectAllByuserName(question);
    }

    @Override
    public boolean deleteQuestion(int id) {

        return manageQuestionMapper.deleteById(id);
    }

    @Override
    public boolean addQuestion(Question question) {
        return manageQuestionMapper.addQuestion(question);
    }

    /*点击修改页面，返回修改前的数据*/
    @Override
    public Question updateQuestion(int id) {
        Question re=manageQuestionMapper.selectById(id);

        return re;
    }

    @Override
    public boolean updateById(Question question) {
        return manageQuestionMapper.updateById(question);
    }

    @Override
    public List<Question> partQuestion(PartQuestionReqVO partQuestionReqVO) {
        return manageQuestionMapper.selectByTypeOrDegree(partQuestionReqVO);
    }

    @Override
    public boolean deleteMany(List ids) {
        return manageQuestionMapper.deleteManyById(ids);
    }

}
