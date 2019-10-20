package com.junly.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.junly.pojo.UserInfo;
import org.apache.ibatis.annotations.Param;

/**
 * @Author: Administrator
 * @Date: 2019/10/20 0020 15:16
 * @Description:
 */
public interface UserInfoMapper extends BaseMapper<UserInfo> {

    /**
     * @description 通过id查找用户信息;
     * @author zhoujunli
     * @date 2019/10/20
     * @param id
     * @return com.junly.pojo.UserInfo
     */
    UserInfo userInfoByid(@Param(value = "id") Integer id);

}
