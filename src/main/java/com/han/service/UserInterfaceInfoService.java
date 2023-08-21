package com.han.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.han.apicommon.model.entity.UserInterfaceInfo;

/**
* @author 86183
* @description 针对表【user_interface_info】的数据库操作Service
* @createDate 2023-03-17 08:46:55
*/
public interface UserInterfaceInfoService extends IService<UserInterfaceInfo> {
    /**
     * 新增或更新时校验
     * @param userInterfaceInfo
     * @param add
     */
    void validUserInterfaceInfo(UserInterfaceInfo userInterfaceInfo, boolean add);

    /**
     * 统计调用次数
     * @param userId
     * @param interfaceInfoId
     * @return
     */
    boolean invokeCount(long userId, long interfaceInfoId);

}
