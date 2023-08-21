package com.han.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.han.apicommon.model.entity.InterfaceInfo;

/**
* @author 86183
* @description 针对表【interface_info】的数据库操作Service
* @createDate 2023-03-14 18:47:32
*/
public interface InterfaceInfoService extends IService<InterfaceInfo> {
    /**
     * 新增或更新时校验
     * @param interfaceInfo
     * @param add
     */
    void validInterfaceInfo(InterfaceInfo interfaceInfo, boolean add);

}
