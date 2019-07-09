package com.glz.authentication.oauth.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

/**
 * @author czhe
 */
@WebFilter(urlPatterns = {"/oauth/token"})
public class TokenFilter implements Filter {

    @Override
    public void destroy() {

    }

    /**
     * 拦截获取存储登录信息
     */
    public static final ThreadLocal<AuthMessage> t = new ThreadLocal<>();

    @Override
    public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2)
            throws IOException, ServletException {

        if (arg0 != null) {
            HttpServletRequest hsr = (HttpServletRequest) arg0;
            t.set(new AuthMessage(hsr.getParameter("username"), hsr.getParameter("client_id"),
                    hsr.getParameter("grant_type"), hsr.getRemoteAddr()));
        }
        arg2.doFilter(arg0, arg1);
    }

    /**
     * 用户信息存储
     *
     * @author czhe
     */
    public class AuthMessage {
        private String userName;
        private String clientId;
        private String grantType;
        private String ip;

        public AuthMessage(String userName, String clientId, String grantType, String ip) {
            this.ip = ip;
            this.userName = userName;
            this.clientId = clientId;
            this.grantType = grantType;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getClientId() {
            return clientId;
        }

        public void setClientId(String clientId) {
            this.clientId = clientId;
        }

        public String getGrantType() {
            return grantType;
        }

        public void setGrantType(String grantType) {
            this.grantType = grantType;
        }

        public String getIp() {
            return ip;
        }

        public void setIp(String ip) {
            this.ip = ip;
        }

        @Override
        public String toString() {
            return "AuthMessage{" +
                    "userName='" + userName + '\'' +
                    ", clientId='" + clientId + '\'' +
                    ", grantType='" + grantType + '\'' +
                    ", ip='" + ip + '\'' +
                    '}';
        }
    }

    @Override
    public void init(FilterConfig arg0) throws ServletException {

    }

}
