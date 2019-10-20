package com.junly;

/**
 * @Author: Administrator
 * @Date: 2019/10/20 0020 15:18
 * @Description:
 */

import com.junly.mapper.UserInfoMapper;
import com.junly.pojo.UserInfo;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class SampleTest {

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Test
    public void testSelect() {
        System.out.println(("----- selectAll method test ------"));
        List<UserInfo> userList = userInfoMapper.selectList(null);
        Assert.assertEquals(1, userList.size());
        userList.forEach(System.out::println);
    }

    @Test
    public void testUserInfoById(){
        UserInfo userInfo = userInfoMapper.userInfoByid(1);
        Assert.assertNotNull(userInfo);
        System.out.println(userInfo);
    }
}
