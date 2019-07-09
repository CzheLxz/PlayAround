package com.hairuo.tbk.repository;

import com.hairuo.tbk.entity.Buser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


public interface BuserRepository extends JpaRepository<Buser, Integer> {


    /**
     * @Description 新增
     * @Param 用户名 用户密码 手机号
     */
    @Modifying
    @Transactional
    @Query(value = "insert into b_user(user_name,user_password,user_phone,user_uuid) values(?1,?2,?3,?4)", nativeQuery = true)
    int saveBuser(String userName, String userPassword, String userPhone, String userUuid);

    /**
     * @Description 获取所有用户集合
     */
    List<Buser> findAll();

    /**
     * @Description 根据用户名和用户密码查询
     */
    Buser getBuserByUserNameAndUserPassword(String userName, String userPassword);

    /**
     * @Description 根据用户名查询
     */
    Buser getBuserByUserName(String userName);

    /**
     * @Description 根据用户标识修改 用户名 用户密码 用户电话
     * @Author czhe
     */
    @Query(value = "update b_user set user_name = :user_name,user_password = :user_password,user_phone = :user_phone where user_uuid = :user_uuid", nativeQuery = true)
    @Modifying
    @Transactional
    int updateBuserByUserUuid(@Param("user_name") String userName, @Param("user_password") String userPassword, @Param("user_phone") String userPhone, @Param("user_uuid") String userUuid);

    /**
     * @Description 根据用户标识删除
     * @Author czhe
     */
    @Modifying
    @Transactional
    int removeBuserByUserUuid(String userUuid);

}
