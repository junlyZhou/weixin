package com.junly.controller;

import com.junly.config.ProjectUrlConfig;
import com.junly.enums.ResultEnum;
import com.junly.exception.BwException;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.result.WxMpOAuth2AccessToken;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;

/**
 * @Author: Administrator
 * @Date: 2019/10/20 0020 12:15
 * @Description:
 */
@Slf4j
@RestController
@RequestMapping("/wechat")
public class WechatController {

    @Autowired
    private ProjectUrlConfig projectUrlConfig;

    @Autowired
    private WxMpService wxMpService;

    /**
     * @description 微信公众号接入
     * @author zhoujunli
     * @date 2019/10/20
     * @param signature
     * @param timestamp
     * @param nonce
     * @param echostr
     * @return java.lang.String
     */
   /* @GetMapping
    public String wechat(@RequestParam String signature,
                         @RequestParam String timestamp,
                         @RequestParam String nonce,
                         @RequestParam String echostr){
        if(wxMpService.checkSignature(timestamp, nonce, signature)){
            //url : 网页授权地址;
            //获取AccessToken;
            return echostr;
        }
        return "fail";
    }*/

    /**
     * @description 回调设置获取用户的openid;
     * @author zhoujunli
     * @date 2019/10/20
     * @param returnUrl
     * @return java.lang.String
     */
    @GetMapping("/authorize")
    public String authorize(@RequestParam("returnUrl") String returnUrl){
        //1. 配置
        //2.调用方法
        String url=projectUrlConfig.getWechatMpAuthorize()+"/wechat/userInfo";
        String redirectUrl=wxMpService.oauth2buildAuthorizationUrl(url, WxConsts.OAuth2Scope.SNSAPI_BASE, URLEncoder.encode(returnUrl));
        log.info("【微信网页授权】获取code,redirectUrl={}",redirectUrl);
        return "redirect:"+redirectUrl;//重定向到下面一个方法
    }

    @GetMapping("/userInfo")
    public String userInfo(@RequestParam("code") String code,
                           @RequestParam("state") String returnUrl){
        WxMpOAuth2AccessToken wxMpOAuth2AccessToken=new WxMpOAuth2AccessToken();
        try {
            wxMpOAuth2AccessToken=wxMpService.oauth2getAccessToken(code);
        }catch (WxErrorException e){
            log.error("【微信网页授权】,{}",e);
            throw new BwException(ResultEnum.WECHAT_MP_ERROR.getCode(),e.getError().getErrorMsg());
        }
        String openId=wxMpOAuth2AccessToken.getOpenId();
        log.info("【微信网页授权】获取openid,returnUrl={}",returnUrl);
        return "redirect:"+ returnUrl+"?openid="+openId;
    }

    @PostMapping("/msg")
    public String getCommonMsg(HttpServletRequest request) throws IOException {
        WxMpXmlMessage wxMpXmlMessage = WxMpXmlMessage.fromXml(request.getInputStream());
        String fromUser = wxMpXmlMessage.getFromUser();
        System.out.println("wxMpXmlMessage = " + wxMpXmlMessage);
        System.out.println("fromUser = " + fromUser);
        return "msg001";
    }

}
