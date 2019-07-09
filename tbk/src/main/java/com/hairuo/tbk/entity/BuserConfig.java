package com.hairuo.tbk.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author czhe
 * @description 用户配置信息表
 */
@Entity
@Table(name = "b_user_config")
public class BuserConfig implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "app_key", nullable = false)
    private String appKey;

    @Column(name = "secret_key", nullable = false)
    private String secretKey;

    @Column(name = "user_url", nullable = false)
    private String userUrl;

    @OneToOne
    @JoinColumn(name = "user_uuid", referencedColumnName = "user_uuid")
    private Buser buser;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public String getUserUrl() {
        return userUrl;
    }

    public void setUserUrl(String userUrl) {
        this.userUrl = userUrl;
    }

    public Buser getBuser() {
        return buser;
    }

    public void setBuser(Buser buser) {
        this.buser = buser;
    }
}
