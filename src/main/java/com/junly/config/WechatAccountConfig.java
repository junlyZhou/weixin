package com.junly.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Created by SqMax on 2018/3/23.
 */
@Data
@Component
@ConfigurationProperties(prefix = "wechat")
public class WechatAccountConfig {

    /**
     * 公众平台id
     */
    private String mpAppId;
    /**
     * 公众平台密钥
     */
    private String mpAppSecret;

    /**
     * @description 接入公众号验证token;
     * @author zhoujunli
     * @date 2019/10/20
     */
    private String token;

    /**
     * @description 测试号
     * @author zhoujunli
     * @date 2019/10/20
     */
    private String testMpAppId;
    private String testMpAppSecret;
    private String testToken;


    /**
     * 微信模板id
     */
    private Map<String,String> templateId;


}
