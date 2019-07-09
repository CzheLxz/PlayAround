package com.glz.authentication.oauth.adapter;

import java.util.concurrent.TimeUnit;

import com.glz.authentication.oauth.GLZRedisTokenStore;
import com.glz.authentication.service.SysLoginLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.TokenGranter;

import com.glz.authentication.oauth.GLZTokenServices;
import com.glz.authentication.oauth.exception.handle.GLZWebResponseExceptionTranslator;
import com.glz.authentication.utils.TokenUtil;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {

    @Autowired
    public SysLoginLogService sysLoginLogService;

    @Autowired
    @Qualifier("authenticationManagerBean")
    public AuthenticationManager authenticationManager;

    @Autowired
    public UserDetailsService userDetailsService;

    @Autowired
    public ClientDetailsService clientDetailsService;

    @Autowired
    public GLZRedisTokenStore glzRedisTokenStore;

    @Autowired
    public GLZTokenServices tokenServices;

    @Autowired
    private GLZWebResponseExceptionTranslator glzWebResponseExceptionTranslator;

    @Autowired
    private GLZDefaultAccessTokenConverter glzDefaultAccessTokenConverter;

    @Autowired
    TokenUtil tokenUtil;

    /**
     * 生成指定tokenServices，否则会出现多个tokenServices错误
     *
     * @return
     */
//	@Bean
//	@Primary
//	public DefaultTokenServices tokenServices() {
//		DefaultTokenServices tokenServices = new DefaultTokenServices();
//		tokenServices.setTokenStore(tokenStore);
//		tokenServices.setSupportRefreshToken(true);// 是否支持刷新token
//	tokenServices.setAccessTokenValiditySeconds((int) TimeUnit.MINUTES.toSeconds(10)); // 分钟
//		return tokenServices;
//	}
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.tokenStore(glzRedisTokenStore).authenticationManager(this.authenticationManager)
                .userDetailsService(userDetailsService);

        tokenServices.setClientDetailsService(endpoints.getClientDetailsService());
        tokenServices.setTokenEnhancer(endpoints.getTokenEnhancer());
        tokenServices.setSupportRefreshToken(true);// 是否支持刷新token
        tokenServices.setAccessTokenValiditySeconds((int) TimeUnit.MINUTES.toSeconds(10)); // 分钟

        endpoints.tokenServices(tokenServices);
        endpoints.allowedTokenEndpointRequestMethods(HttpMethod.GET, HttpMethod.POST);
        // 接口映射
        // endpoints.pathMapping("/oauth/token", "/oa/sb");

        endpoints.exceptionTranslator(glzWebResponseExceptionTranslator);

        //代理方式扩展/oauth/token返回信息
        TokenGranter tokenGranter = endpoints.getTokenGranter();
        GLZTokenGranterProxy glzTokenGranterProxy = new GLZTokenGranterProxy(tokenGranter);
        glzTokenGranterProxy.setTokenUtil(tokenUtil);
        glzTokenGranterProxy.setSysLoginLogService(sysLoginLogService);

        endpoints.tokenGranter(glzTokenGranterProxy);

        //扩展/oauth/check_token返回信息
        endpoints.accessTokenConverter(glzDefaultAccessTokenConverter);
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        // clients.inMemory().withClient("hdy").authorizedGrantTypes("refresh_token",
        // "password")
        // .scopes("all", "read",
        // "write").resourceIds(ResourceProperties.RESOURCE_ID).secret("123456").and()
        // .withClient("hjq").authorizedGrantTypes("refresh_token",
        // "password").scopes("all", "read", "write")
        // .resourceIds(ResourceProperties.RESOURCE_ID).secret("123456");
        clients.withClientDetails(clientDetailsService);
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
        // 允许对客户端进行表单身份验证
        // oauthServer.checkTokenAccess("isAuthenticated()");
        oauthServer.tokenKeyAccess("permitAll()").checkTokenAccess("permitAll()");
        oauthServer.allowFormAuthenticationForClients();
    }
}