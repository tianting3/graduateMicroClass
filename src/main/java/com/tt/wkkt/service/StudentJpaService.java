package com.tt.wkkt.service;

import com.tt.wkkt.model.Student;
import org.springframework.data.domain.Page;

/**
 * @Author tianting
 * @Description
 * @Param
 * @return
 **/
public interface StudentJpaService {
    Page<Student> getStudentWithPage(Integer page, Integer size);
}
