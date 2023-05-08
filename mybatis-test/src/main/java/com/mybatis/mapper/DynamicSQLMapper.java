package com.mybatis.mapper;

import com.mybatis.entity.User;

import java.util.List;

public interface DynamicSQLMapper {

    User getUserByParamsWithIf(User user);

    User getUserByParamsWithWhere(User user);

    User getUserByParamsWithTrim(User user);

    List<User> getUserByParamsWithChoose(User user);

    User getUserByParamsWithSQLFragment();

}
