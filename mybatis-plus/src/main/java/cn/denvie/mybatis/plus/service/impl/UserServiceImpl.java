/*
 * Copyright © 2020-2020 尛飛俠（Denvie） All rights reserved.
 */

package cn.denvie.mybatis.plus.service.impl;

import cn.denvie.mybatis.plus.mapper.UserMapper;
import cn.denvie.mybatis.plus.model.User;
import cn.denvie.mybatis.plus.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Collection;

/**
 * UserServiceImpl。
 *
 * @author denvie
 * @since 2020/9/6
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
}
