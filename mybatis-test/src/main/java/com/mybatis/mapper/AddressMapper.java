package com.mybatis.mapper;

import com.mybatis.entity.Address;
import org.apache.ibatis.annotations.Param;

public interface AddressMapper {

    /**
     * 根据用户id查询地址信息
     */
    Address getAddressByUserId(@Param("userId") Integer userId);

    Address getAddressAndUserById(@Param("id") Integer id);

    Address getAddressById(@Param("id") Integer id);
}
