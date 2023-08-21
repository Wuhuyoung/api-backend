package com.han.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.han.apicommon.model.entity.User;
import org.apache.ibatis.annotations.Mapper;


/**
 * @Entity com.han.model.domain.User
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

}




