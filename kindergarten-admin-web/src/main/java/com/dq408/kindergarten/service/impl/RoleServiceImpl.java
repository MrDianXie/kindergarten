package com.dq408.kindergarten.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dq408.kindergarten.dao.RoleDao;
import com.dq408.kindergarten.domain.Role;
import com.dq408.kindergarten.service.RoleService;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl extends ServiceImpl<RoleDao, Role> implements RoleService {
}
