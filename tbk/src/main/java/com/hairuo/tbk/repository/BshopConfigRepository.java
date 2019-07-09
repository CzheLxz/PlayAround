package com.hairuo.tbk.repository;

import com.hairuo.tbk.entity.BshopConfig;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface BshopConfigRepository extends JpaRepository<BshopConfig, Integer> {


    /**
     * @Description 新增
     * @Author czhe
     */
    @Query(value = "insert into b_shop_config(logo,record,remark,welcome,user_uuid) values(?1,?2,?3,?4,?5)", nativeQuery = true)
    @Modifying
    @Transactional
    int saveBshopConfig(String logo, String record, String remark, String welcome, String userUuid);

    /**
     * @Description 根据用户Id 修改商城logo 欢迎语 商城备案信息 备用数据
     * @Author czhe
     */
    @Query(value = "update b_shop_config set logo = :logo,welcome = :welcome,record = :record,remark = :remark where user_uuid = :user_uuid ", nativeQuery = true)
    @Modifying
    @Transactional
    int updateBshopConfigByUserUuid(@Param("logo") String logo, @Param("welcome") String welcome, @Param("record") String record, @Param("remark") String remark, @Param("user_uuid") String userUuid);

    /**
     * @Description 根据用户Id查询
     * @Ahthor czhe
     */
    @Query(value = "select * from b_shop_config where user_uuid = :user_uuid order by id", nativeQuery = true)
    BshopConfig getBshopConfigByUuid(@Param("user_uuid") String userUuid);

}
