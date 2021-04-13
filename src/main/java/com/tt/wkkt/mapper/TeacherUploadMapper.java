package com.tt.wkkt.mapper;

import com.tt.wkkt.model.FileUpload;
import com.tt.wkkt.vo.req.FilesReqVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author tianting
 * @Description
 * @Param
 * @return
 **/
public interface TeacherUploadMapper {
    boolean insertFile(FileUpload fileUpload);

    boolean insertTeacherFile(FilesReqVO reqVO);

    String queryIsExsit(String fileName);

    List<FilesReqVO> queryByTeacherName(@Param(value = "teacherName") String teacherName);

    boolean deleteManyFilesByIds(List ids);

    boolean deleteFileById(int id);

    String queryPathById(int id);

    List<String> queryPathsByIds(List ids);

    int deleteUploadByFilename(String fileName);

    int deleteUploadByFilenames(List names);
}
