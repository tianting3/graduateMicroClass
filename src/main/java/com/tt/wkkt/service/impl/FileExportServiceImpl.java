package com.tt.wkkt.service.impl;

import com.tt.wkkt.common.Result;
import com.tt.wkkt.mapper.FileExportMapper;
import com.tt.wkkt.model.StudentGrade;
import com.tt.wkkt.service.FileExportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tt.wkkt.util.FileUtil;
import org.apache.poi.hssf.usermodel.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @Author tianting
 * @Description
 * @Param
 * @return
 **/
@Service
public class FileExportServiceImpl implements FileExportService {

    @Autowired
    FileExportMapper fileExportMapper;

    @Override
    public List<StudentGrade> exportGrade(int courseId) {
        return fileExportMapper.selectAllGrade(courseId);
    }
}
