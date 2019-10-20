package com.junly.Utils;

import me.chanjar.weixin.mp.api.WxMpMessageHandler;
import me.chanjar.weixin.mp.api.WxMpMessageRouter;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutTextMessage;

/**
 * @Author: Administrator
 * @Date: 2019/10/20 0020 22:06
 * @Description:
 */
public class WxMpUtil {

    public static void text(
            WxMpMessageRouter wxMpMessageRouter,
            boolean async,
            String ask,
            String answer) {
        WxMpMessageHandler handler = (wxMessage, context, wxMpService, sessionManager) -> {
            WxMpXmlOutTextMessage m
                    = WxMpXmlOutMessage
                    .TEXT()
                    .content(answer)
                    .fromUser(wxMessage.getToUser())
                    .toUser(wxMessage.getFromUser())
                    .build();
            return m;
        };
        wxMpMessageRouter
                .rule()
                .async(async)
                .content(ask)
                .handler(handler)
                .end();
    }

}
