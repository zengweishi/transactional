package com.transactional.dao;

import com.transactional.Entiry.User1;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @Auther: weishi.zeng
 * @Date: 2020/12/2 11:04
 * @Description:
 */
@Mapper
public interface User1Mapper {
    int insert(User1 record);
    User1 selectByPrimaryKey(Integer id);
}
