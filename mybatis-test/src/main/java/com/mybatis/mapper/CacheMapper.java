package com.mybatis.mapper;

import com.mybatis.entity.Address;
import org.apache.ibatis.annotations.Param;

public interface CacheMapper {

    /**
     * 根据用户id查询地址信息
     */
    Address getAddressByUserId(@Param("id") Integer id);

}
