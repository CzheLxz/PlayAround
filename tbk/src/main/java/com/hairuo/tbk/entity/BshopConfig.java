package com.hairuo.tbk.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author czhe
 * @description 商城信息表
 */

@Entity
@Table(name = "b_shop_config")
public class BshopConfig implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String logo;

    @Column(nullable = false)
    private String welcome;

    @Column(nullable = false)
    private String record;

    @Column(nullable = false)
    private String remark;

    @OneToOne
    @JoinColumn(name = "user_uuid", referencedColumnName = "user_uuid")
    private Buser buser;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getWelcome() {
        return welcome;
    }

    public void setWelcome(String welcome) {
        this.welcome = welcome;
    }

    public String getRecord() {
        return record;
    }

    public void setRecord(String record) {
        this.record = record;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Buser getBuser() {
        return buser;
    }

    public void setBuser(Buser buser) {
        this.buser = buser;
    }
}
