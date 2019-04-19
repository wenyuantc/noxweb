package com.edoai.framework.noxweb.modules.user.service.impl;

import com.edoai.framework.noxweb.modules.user.entity.UserInfo;
import com.edoai.framework.noxweb.modules.user.mapper.UserInfoMapper;
import com.edoai.framework.noxweb.modules.user.service.IUserInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Nox
 * @since 2019-04-19
 */
@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements IUserInfoService {

}
