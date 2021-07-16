package com.tt.wkkt.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tt.wkkt.common.Result;
import com.tt.wkkt.mapper.SetTestMapper;
import com.tt.wkkt.mapper.StudentPaperMapper;
import com.tt.wkkt.model.ChangeQues;
import com.tt.wkkt.model.IssuePaper;
import com.tt.wkkt.model.TestPaper;
import com.tt.wkkt.service.SetTestService;
import com.tt.wkkt.service.WebSocket;
import com.tt.wkkt.util.KsBeanUtil;
import com.tt.wkkt.vo.req.ChoiceQuestionReqVO;
import com.tt.wkkt.vo.resp.IssuePaperNoIDReqVO;
import com.tt.wkkt.vo.resp.QuestionRespVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.tt.wkkt.common.ResultCode.OK;

/**
 * @Author tianting
 * @Description
 * @Param
 * @return
 **/
@Service
public class SetTestServiceImpl implements SetTestService {
    @Autowired
    SetTestMapper setTestMapper;

    @Autowired
    StudentPaperMapper studentPaperMapper;

    @Override
    public HashMap<String, Object> getAllScoreAndTestCount(String techerName) {
        Integer allScore = setTestMapper.querySumScoreByTeacherName(techerName);
        int testCount = setTestMapper.queryTestCount(techerName);
        HashMap<String, Object> map = new HashMap<>();
        map.put("allScore", allScore);
        map.put("testCount", testCount);
        return map;
    }

    @Override
    public Result addPaper(TestPaper testPaper) {
        Result result = new Result();
        List<String> list = setTestMapper.queryAllTestName();/*所有的测试名字*/
        if (list.contains(testPaper.getTestName())) {
            result.setCode(OK.getCode());
            result.setMsg("试卷名重复");
            return result;
        }
        boolean insertPaper = setTestMapper.insertPaper(testPaper);
        int id = setTestMapper.queryIdByPaperName(testPaper.getTestName());
        boolean updatePaperId = setTestMapper.updatePaperId(id, testPaper.getTeacherName());
        result.setCode(OK.getCode());
        result.setMsg("成功添加试卷");
        return result;
    }

    @Override
    public HashMap<String, Object> getAllPaper(String teacherName, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<TestPaper> list = setTestMapper.queryAllPaper(teacherName);
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getDegree().equals("1")) {
                list.get(i).setDegree("简单");
            } else if (list.get(i).getDegree().equals("2")) {
                list.get(i).setDegree("中等");
            } else {
                list.get(i).setDegree("难");
            }
        }
        PageInfo<TestPaper> info = new PageInfo<>(list);
        HashMap<String, Object> map = new HashMap<>();
        map.put("data", info.getList());/*返回指定页面的数据*/
        map.put("nowPage", pageNum);/*返回当前页面*/
        map.put("total", ((int) info.getTotal() + pageSize - 1) / pageSize);//返回总页面数
        map.put("size", info.getTotal());

        return map;

    }

    @Override
    public boolean judgeCanResponse(String studentUser, int paperId) {
        int isExist = studentPaperMapper.queryExistSubmit(paperId, studentUser);

        if (isExist<=0){
            return true;
        }else{
            return false;
        }

    }

    @Override
    public HashMap<String, Object> getIssuedPaper(String teacherName, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<IssuePaper> list = studentPaperMapper.queryAllPaperByTeachername(teacherName);
        PageInfo<IssuePaper> info = new PageInfo<>(list);
        HashMap<String, Object> map = new HashMap<>();
        map.put("data", info.getList());/*返回指定页面的数据*/
        map.put("nowPage", pageNum);/*返回当前页面*/
        map.put("total", ((int) info.getTotal() + pageSize - 1) / pageSize);//返回总页面数
        map.put("size", info.getTotal());

        return map;
    }

    @Override
    public boolean removePaper(int id) {
        boolean deleteChoiceTestByPaperId = setTestMapper.deleteChoiceTestByPaperId(id);
        if (deleteChoiceTestByPaperId == true) {
            setTestMapper.removeIssuePaper(id);
            return setTestMapper.deletePaperById(id);

        }
        return false;
    }

    @Override
    public boolean updatePaper(TestPaper testPaper) {
        IssuePaperNoIDReqVO issuePaper = KsBeanUtil.copyPropertiesThird(testPaper, IssuePaperNoIDReqVO.class);
        issuePaper.setPaperId(testPaper.getId());
        if (setTestMapper.updateIssueNameByPaperId(issuePaper)){
            return setTestMapper.updatePaperById(testPaper);
        }
        return false;
    }

    @Override
    public HashMap<String, Object> checkPaper(int id, int pageNum, int pageSize) {
        HashMap<String, Object> map = new HashMap<>();
        List<Integer> ids = setTestMapper.selectAllTestByPaperId(id);


        if (!ids.isEmpty()) {
            PageHelper.startPage(pageNum, pageSize);
            List<QuestionRespVO> list = setTestMapper.selectAllquestionById(ids);
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).getDegree().equals("1")) {
                    list.get(i).setDegree("简单");
                } else if (list.get(i).getDegree().equals("2")) {
                    list.get(i).setDegree("中等");
                } else {
                    list.get(i).setDegree("难");
                }
                if (list.get(i).getType().equals("1")) {
                    list.get(i).setType("单选题");
                } else if (list.get(i).getType().equals("2")) {
                    list.get(i).setType("多选题");
                } else if (list.get(i).getType().equals("3")) {
                    list.get(i).setType("判断题");
                } else if (list.get(i).getType().equals("4")) {
                    list.get(i).setType("问答题");
                } else {
                    list.get(i).setType("讨论题");
                }


            }
            PageInfo<QuestionRespVO> info = new PageInfo<>(list);

            map.put("data", info.getList());
            map.put("nowPage", pageNum);/*返回当前页面*/
            map.put("total", ((int) info.getTotal() + pageSize - 1) / pageSize);//返回总页面数
            map.put("size", info.getTotal());
        }
        return map;
    }

    @Override
    public HashMap<String, Object> getAllTest(int id) {
        int test_time = setTestMapper.getTimeByPaperId(id);
        List<Integer> ids = setTestMapper.selectAllTestByPaperId(id);
        HashMap<String, Object> map = new HashMap<>();
        if (!ids.isEmpty()) {
            List<ChangeQues> list = setTestMapper.selectquestionById(ids);
            for (ChangeQues question:list) {
                if (question.getType().equals("1")){
                    question.setType("单选题");
                }else if (question.getType().equals("2")){
                    question.setType("多选题");
                }else if (question.getType().equals("3")){
                    question.setType("判断题");
                }else if (question.getType().equals("4")){
                    question.setType("问答题");
                }else{
                    question.setType("讨论题");
                }
                if (question.getType().equals("单选题")||question.getType().equals("多选题")||question.getType().equals("判断题")){
                    String[] item = question.getItems().split("@");
                    question.setItemsArr(item);
                }

            }
            map.put("data",list);
        }
        map.put("testTime",test_time);
        return map;
    }


}