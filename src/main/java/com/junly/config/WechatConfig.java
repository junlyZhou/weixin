package com.junly.config;

import com.junly.Utils.WxMpUtil;
import com.junly.message.FocusMeMessage;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.mp.api.WxMpMessageRouter;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;
import me.chanjar.weixin.mp.config.impl.WxMpDefaultConfigImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * @Author: Administrator
 * @Date: 2019/10/20 0020 12:57
 * @Description:
 */
@Configuration
public class WechatConfig {

    @Autowired
    private WechatAccountConfig accountConfig;

    @Bean
    public WxMpService wxMpService(){
        WxMpServiceImpl wxMpService = new WxMpServiceImpl();
        wxMpService.setWxMpConfigStorage(wxMpConfigStorage());
        return wxMpService;
    }

    @Bean
    public WxMpDefaultConfigImpl wxMpConfigStorage(){
        WxMpDefaultConfigImpl wxMpConfigStorage=new WxMpDefaultConfigImpl();
        /*wxMpConfigStorage.setAppId(accountConfig.getMpAppId());
        wxMpConfigStorage.setSecret(accountConfig.getMpAppSecret());
        wxMpConfigStorage.setToken(accountConfig.getToken());*/
        wxMpConfigStorage.setAppId(accountConfig.getTestMpAppId());
        wxMpConfigStorage.setSecret(accountConfig.getTestMpAppSecret());
        wxMpConfigStorage.setToken(accountConfig.getTestToken());
        return wxMpConfigStorage;
    }

    @Bean
    public WxMpMessageRouter wxMpMessageRouter(WxMpService wxMpService){
        WxMpMessageRouter wxMpMessageRouter = new WxMpMessageRouter(wxMpService);

        WxMpUtil.text(wxMpMessageRouter, false, "test", "This is a test !!!");
        wxMpMessageRouter.rule()
                // .msgType(WxConsts.XML_MSG_TEXT)
                .async(false)
                .event(WxConsts.EventType.UNSUBSCRIBE)
                // .eventKey("EVENT_KEY")
                .handler(new FocusMeMessage())
                .end()
                .rule()
                .async(false)
                .event(WxConsts.EventType.SUBSCRIBE)
                .handler(new FocusMeMessage())
                .end();
        // wxMpMessageRouter.rule().handler(new NoMessage()).end();
        return wxMpMessageRouter;
    }

}
