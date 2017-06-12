package com.qwy.repository;

import com.qwy.model.BlogEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * Created by qwy on 17/6/12.
 * 博客管理仓库
 */
public interface BlogRepository extends JpaRepository<BlogEntity, Integer> {
    @Modifying      // 说明该方法是修改操作
    @Transactional  // 说明该方法是事务性操作
    // 定义查询
    // @Param注解用于提取参数be.userByUserId.id=:mUserId,
    @Query("update BlogEntity be set be.title=:mTitle, be.content=:mContent, be.pubDate=:mPubdate where be.id=:mId")
    public void updateBlog(@Param("mTitle") String title, @Param("mContent") String content, @Param("mPubdate") Date pubDate, @Param("mId") Integer id);
}
