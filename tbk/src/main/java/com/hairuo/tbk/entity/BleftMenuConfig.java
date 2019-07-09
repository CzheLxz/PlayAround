package com.hairuo.tbk.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author czhe
 * @description 左侧菜单配置信息
 */

@Entity
@Table(name = "b_left_menu_config")
public class BleftMenuConfig implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "b_show", nullable = false)
    private String bShow;

    @Column(nullable = false)
    private String url;

    @Column(nullable = false)
    private String remark;

    @ManyToOne
    @JoinColumn(name = "user_uuid", referencedColumnName = "user_uuid")
    private Buser buser;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getbShow() {
        return bShow;
    }

    public void setbShow(String bShow) {
        this.bShow = bShow;
    }

    public Buser getBuser() {
        return buser;
    }

    public void setBuser(Buser buser) {
        this.buser = buser;
    }
}
