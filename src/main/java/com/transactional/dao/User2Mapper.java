package com.transactional.dao;

import com.transactional.Entiry.User2;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @Auther: weishi.zeng
 * @Date: 2020/12/2 11:04
 * @Description:
 */
@Mapper
public interface User2Mapper {
    int insert(User2 record);
    User2 selectByPrimaryKey(Integer id);
}
