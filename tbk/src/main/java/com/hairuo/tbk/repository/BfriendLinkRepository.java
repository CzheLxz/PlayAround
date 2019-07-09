package com.hairuo.tbk.repository;

import com.hairuo.tbk.entity.BfriendLink;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface BfriendLinkRepository extends JpaRepository<BfriendLink, Integer> {

    /**
     * @Description 新增
     * @Author czhe
     */
    @Query(value = "insert into b_friend_link(b_show,remark,url,user_uuid) values(?1,?2,?3,?4)", nativeQuery = true)
    @Modifying
    @Transactional
    int BfriendLink(String bShow, String remark, String url, String userUuid);


    /**
     * @Description 根据用户Id查询
     */
    @Query(value = "select * from b_friend_link where user_uuid = :user_uuid order by id", nativeQuery = true)
    List<BfriendLink> findAllByUserUuid(@Param("user_uuid") String userUuid);

    /**
     * @Description 根据自增标识Id删除
     */
    @Transactional
    @Modifying
    int removeBfriendLinkById(int id);

    /**
     * @Description 根据Id修改名称 点击路径 备注
     */
    @Query(value = "update b_friend_link set b_show = :b_show,url = :url,remark = :remark where id = :id", nativeQuery = true)
    @Modifying
    @Transactional
    int updateBfriendLinkById(@Param("b_show") String bShow, @Param("url") String url, @Param("remark") String remark, @Param("id") int id);

}
