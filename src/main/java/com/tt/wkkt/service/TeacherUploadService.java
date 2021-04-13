package com.tt.wkkt.service;

import com.tt.wkkt.common.Result;
import com.tt.wkkt.vo.req.FilesReqVO;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;

/**
 * @Author tianting
 * @Description
 * @Param
 * @return
 **/
public interface TeacherUploadService {
    Result upload(MultipartFile file);

    Result uploadImage(MultipartFile file);

    boolean teacherFile(FilesReqVO filesReqVO);

    HashMap<String,Object> queryByTeacherName(String teacherName,Integer pageNum,Integer pageSize);

    boolean deleteManyFilesById(List list);

    boolean deleteFile(int id);
}
