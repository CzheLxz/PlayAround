package com.hairuo.tbk.repository;

import com.hairuo.tbk.entity.BuserConfig;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;


public interface BuserConfigRepository extends JpaRepository<BuserConfig, Integer> {

    /**
     * @Description 新增
     * @Author czhe
     */
    @Query(value = "insert into b_user_config(app_key,secret_key,user_url,user_uuid) values(?1,?2,?3,?4)", nativeQuery = true)
    @Modifying
    @Transactional
    int saveBuserConfig(String appKey, String secretKey, String userUrl, String userUuid);

    /**
     * @Description 根据用户Id修改用户app_key 用户秘钥 用户对应网址
     * @Author che
     */
    @Query(value = "update b_user_config set app_key = :app_key,secret_key = :secret_key,user_url = :user_url where user_uuid = :user_uuid", nativeQuery = true)
    @Modifying
    @Transactional
    int updateBuserConfigByUserUuid(@Param("app_key") String appKey, @Param("secret_key") String secretKey, @Param("user_url") String userUrl, @Param("user_uuid") String userUuid);

    /**
     * @Description 根据用户Id查询
     */
    @Query(value = "select * from b_user_config where user_uuid = :user_uuid order by id", nativeQuery = true)
    BuserConfig getBuserConfigByUserUuid(@Param("user_uuid") String userUuid);

    /**
     * @Description 根据用户对应商城地址查询
     */
    @Query(value = "select * from b_user_config where user_url = :user_url order by id", nativeQuery = true)
    BuserConfig getBuserConfigByUserUrl(@Param("user_url") String userUrl);

}