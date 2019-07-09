package com.glz.authentication.oauth.services;

import java.util.ArrayList;
import java.util.Arrays;

import com.glz.authentication.service.SysAppauthService;
import com.oauth.entity.SysAppauth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;
import org.springframework.stereotype.Component;

import com.glz.authentication.resource.ResourceProperties;
import org.springframework.util.StringUtils;

/**
 * 客户端查询实现类
 *
 * @author czhe
 */
@Component
@Primary
public class GLZClientDetailsService implements ClientDetailsService {

    @Autowired
    private SysAppauthService sysAppauthService;

    @Override
    public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {

        SysAppauth sysAppauth = sysAppauthService.findSysAppauthByclientId(clientId);
        if (!StringUtils.isEmpty(sysAppauth)) {
            return createClient(sysAppauth.getClientId(), sysAppauth.getClientSecret(), sysAppauth.getScope());
        }
        return null;
    }

    /**
     * @param clientId 客户端id
     * @param secret   客户端秘钥
     * @param scope    作用域，目前不需要用
     * @return
     */
    public static ClientDetails createClient(String clientId, String secret, String scope) {
        BaseClientDetails result = new BaseClientDetails();
        result.setClientId(clientId);// id
        result.setClientSecret(secret);// 密码

        String[] split = scope.split(",");
        ArrayList<String> list = new ArrayList<>();
        for (String s : split) {
            list.add(s);
        }
        result.setScope(list);// 作用域

        // 固定值不用修改
        result.setAuthorizedGrantTypes(Arrays.asList("password", "refresh_token"));
        result.setResourceIds(Arrays.asList(ResourceProperties.RESOURCE_ID));

        // 设置客户端额外消息
        // Map<String, Object> m=new HashMap<>();
        // m.put("11", "ooo");
        // m.put("22", "333");
        // result.setAdditionalInformation(m);
        return result;
    }

}
