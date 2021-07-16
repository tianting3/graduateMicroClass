package com.tt.wkkt.service.impl;


import com.tt.wkkt.model.Student;
import com.tt.wkkt.repository.StudentRepository;
import com.tt.wkkt.service.StudentJpaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * @Author tianting
 * @Description   JPA分页
 * @Param
 * @return
 **/
@Service
@Slf4j
public class StudentJpaServiceImpl implements StudentJpaService {
    /*MQ实践sbc*/

    @Autowired
    StudentRepository studentRepository;

    @Override
    public Page<Student> getStudentWithPage(Integer page, Integer size) {
        log.info("page is {},size is {}", page, size);
        if (page <= 0) {
            page = 1;
        }
        PageRequest pageRequest = PageRequest.of(page - 1, size);

        return studentRepository.findAll(pageRequest);
    }
}
