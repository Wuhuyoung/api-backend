package com.han.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.han.apicommon.model.entity.UserInterfaceInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
* @author 86183
* @description 针对表【user_interface_info】的数据库操作Mapper
* @createDate 2023-03-17 08:46:55
* @Entity com.han.model.entity.UserInterfaceInfo
*/
@Mapper
public interface UserInterfaceInfoMapper extends BaseMapper<UserInterfaceInfo> {


    List<UserInterfaceInfo> listTopInvokeInterface(int limit);
}




