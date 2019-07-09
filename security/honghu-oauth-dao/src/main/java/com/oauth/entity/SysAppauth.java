package com.oauth.entity;

import java.util.Date;

public class SysAppauth {
    private Integer id;

    private Date addtime;

    private Boolean deletestatus;

    private String clientId;

    private String clientSecret;

    private String scope;

    private String version;

    private String sndchnlno;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getAddtime() {
        return addtime;
    }

    public void setAddtime(Date addtime) {
        this.addtime = addtime;
    }

    public Boolean getDeletestatus() {
        return deletestatus;
    }

    public void setDeletestatus(Boolean deletestatus) {
        this.deletestatus = deletestatus;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId == null ? null : clientId.trim();
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret == null ? null : clientSecret.trim();
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version == null ? null : version.trim();
    }

    public String getSndchnlno() {
        return sndchnlno;
    }

    public void setSndchnlno(String sndchnlno) {
        this.sndchnlno = sndchnlno == null ? null : sndchnlno.trim();
    }
}