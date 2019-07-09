package com.glz.authentication.oauth.adapter;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

import com.glz.authentication.oauth.filters.TokenFilter;
import com.glz.authentication.service.SysLoginLogService;
import com.glz.authentication.utils.ResponseCode;
import com.glz.authentication.utils.TokenUtil;
import com.oauth.entity.AuthShopuser;
import com.oauth.entity.SysLoginLog;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.TokenGranter;
import org.springframework.security.oauth2.provider.TokenRequest;

public class GLZTokenGranterProxy implements TokenGranter {

    private SysLoginLogService sysLoginLogService;

    public void setSysLoginLogService(SysLoginLogService sysLoginLogService) {
        this.sysLoginLogService = sysLoginLogService;
    }

    TokenGranter tokenGranter;

    private TokenUtil tokenUtil;

    public TokenUtil getTokenUtil() {
        return tokenUtil;
    }

    public void setTokenUtil(TokenUtil tokenUtil) {
        this.tokenUtil = tokenUtil;
    }

    public GLZTokenGranterProxy(TokenGranter tokenGranter) {
        this.tokenGranter = tokenGranter;
    }

    @Override
    public OAuth2AccessToken grant(String grantType, TokenRequest tokenRequest) {
        OAuth2AccessToken grant = tokenGranter.grant(grantType, tokenRequest);
        if (grant instanceof DefaultOAuth2AccessToken) {
            DefaultOAuth2AccessToken g = (DefaultOAuth2AccessToken) grant;
            Map<String, Object> map = ResponseCode.getMap(ResponseCode.RESPONSE_CODEEO0000);
            AuthShopuser authShopuser = tokenUtil.getAuth(g.getValue());
            map.put("userName", authShopuser.getUsername());
            map.put("mobile", authShopuser.getMobile());
            if (authShopuser.getSex() == 0) {
                map.put("sex", "女");
            } else if (authShopuser.getSex() == 1) {
                map.put("sex", "男");
            } else if (authShopuser.getSex() == -1) {
                map.put("sex", "保密");
            }
            g.setAdditionalInformation(map);
            //登录成功日志
            if ("password".equals(grantType)) {
                SysLoginLog sysLoginLog = new SysLoginLog();
                sysLoginLog.setLogId(UUID.randomUUID().toString().replaceAll("-", ""));
                sysLoginLog.setUserId(String.valueOf(authShopuser.getId()));
                sysLoginLog.setUsername(authShopuser.getUsername());
                sysLoginLog.setSndchnlno(authShopuser.getSndchnlno());
                TokenFilter.AuthMessage object = TokenFilter.t.get();
                sysLoginLog.setIpAddress(object.getIp());
                sysLoginLog.setLoginTime(new Date());
                sysLoginLogService.insertSelective(sysLoginLog);

            }
        }
        return grant;
    }

}
