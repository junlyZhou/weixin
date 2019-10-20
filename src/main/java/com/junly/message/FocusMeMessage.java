package com.junly.message;

import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpMessageHandler;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutTextMessage;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Map;

/**
 * @Author: Administrator
 * @Date: 2019/10/20 0020 22:15
 * @Description:
 */
@Slf4j
public class FocusMeMessage implements WxMpMessageHandler {

    @Override
    public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage, Map<String, Object> context, WxMpService wxMpService, WxSessionManager sessionManager) throws WxErrorException {
        // String msg = source.getMessage("message.welcome", null, Locale.getDefault());
        String event = StringUtils.isBlank(wxMessage.getEvent()) ? StringUtils.EMPTY : wxMessage.getEvent();
        WxMpXmlOutTextMessage m = null;
        if (WxConsts.EventType.SUBSCRIBE.equals(event)) {
            m
                    = WxMpXmlOutMessage
                    .TEXT()
                    .content("message.welcome")
                    .fromUser(wxMessage.getToUser())
                    .toUser(wxMessage.getFromUser())
                    .build();
        } else if (WxConsts.EventType.UNSUBSCRIBE.equals(event)) {
        }
        log.info("{} ---> {}", event, ToStringBuilder.reflectionToString(wxMessage));
        return m;
    }

}
