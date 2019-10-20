package com.junly.message;

import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpMessageHandler;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutTextMessage;

import java.util.Map;

/**
 * @Author: Administrator
 * @Date: 2019/10/20 0020 22:17
 * @Description:
 */
public class NoMessage implements WxMpMessageHandler {

    @Override
    public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage, Map<String, Object> context, WxMpService wxMpService, WxSessionManager sessionManager)
            throws WxErrorException {
//        MessageSource source = Environment.getI18n();
//        String msg = source.getMessage("message.noregex", null, Locale.getDefault());
        WxMpXmlOutTextMessage m
                = WxMpXmlOutMessage
                .TEXT()
                .content("message.noregex")
                .fromUser(wxMessage.getToUser())
                .toUser(wxMessage.getFromUser())
                .build();
        return m;
    }

}
