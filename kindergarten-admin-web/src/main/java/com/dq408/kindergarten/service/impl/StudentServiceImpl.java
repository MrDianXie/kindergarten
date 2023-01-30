package com.dq408.kindergarten.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dq408.kindergarten.dao.StudentDao;
import com.dq408.kindergarten.domain.Student;
import com.dq408.kindergarten.service.StudentService;
import org.springframework.stereotype.Service;

/**
 * @author XIE_HRZGZ
 */
@Service
public class StudentServiceImpl extends ServiceImpl<StudentDao, Student> implements StudentService {
}
