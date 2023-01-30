package com.dq408.kindergarten.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dq408.kindergarten.dao.ClassandgradeDao;
import com.dq408.kindergarten.domain.Classandgrade;
import com.dq408.kindergarten.service.ClassandgradeService;
import org.springframework.stereotype.Service;

@Service
public class ClassandgradeServiceImpl extends ServiceImpl<ClassandgradeDao, Classandgrade> implements ClassandgradeService {
}
