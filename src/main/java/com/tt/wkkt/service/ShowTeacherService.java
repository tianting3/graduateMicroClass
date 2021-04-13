package com.tt.wkkt.service;

import com.tt.wkkt.vo.resp.TeacherCourseRespVO;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.List;

/**
 * @Author tianting
 * @Description
 * @Param
 * @return
 **/
public interface ShowTeacherService {
    HashMap<String,Object> teacherList(Integer pageNum, Integer pageSize);

    HashMap<String,Object> getFiles(String userName, Integer pageNum, Integer pageSize);

    String getPathByFilename(String fileName);
}
