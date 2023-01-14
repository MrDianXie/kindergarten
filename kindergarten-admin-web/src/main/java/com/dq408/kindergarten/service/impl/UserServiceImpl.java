package com.dq408.kindergarten.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dq408.kindergarten.dao.UserDao;
import com.dq408.kindergarten.domain.User;
import com.dq408.kindergarten.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserDao, User> implements UserService {
}
