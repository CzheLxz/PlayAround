package com.hairuo.tbk.repository;

import com.hairuo.tbk.entity.BleftMenuConfig;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface BleftMenuConfigRepository extends JpaRepository<BleftMenuConfig, Integer> {


    /**
     * @Description 新增
     * @Author czhe
     */
    @Query(value = "insert into b_left_menu_config(b_show,remark,url,user_uuid) values(?1,?2,?3,?4)", nativeQuery = true)
    @Modifying
    @Transactional
    int saveBleftMenuConfig(String bShow, String remark, String url, String userUuid);


    /**
     * @Description 根据用户Id查询
     */
    @Query(value = "select * from b_left_menu_config where user_uuid = :user_uuid order by id", nativeQuery = true)
    List<BleftMenuConfig> findAllByUserUuid(@Param("user_uuid") String userUuid);

    /**
     * @Description 根据自增标识Id删除
     */
    @Transactional
    @Modifying
    int removeBleftMenuConfigById(Integer id);

    /**
     * @Description 根据Id修改名称 点击路径 备注
     */
    @Query(value = "update b_left_menu_config set b_show = :b_show,url = :url,remark = :remark where id = :id", nativeQuery = true)
    @Modifying
    @Transactional
    int updateBleftMenuConfigById(@Param("b_show") String bShow, @Param("url") String url, @Param("remark") String remark, @Param("id") Integer id);
}
