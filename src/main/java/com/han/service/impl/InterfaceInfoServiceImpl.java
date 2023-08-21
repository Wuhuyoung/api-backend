package com.han.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.han.apicommon.model.entity.InterfaceInfo;
import com.han.common.ErrorCode;
import com.han.exception.BusinessException;
import com.han.service.InterfaceInfoService;
import com.han.mapper.InterfaceInfoMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

/**
* @author 86183
* @description 针对表【interfaceInfo_info】的数据库操作Service实现
* @createDate 2023-03-14 18:47:32
*/
@Service
public class InterfaceInfoServiceImpl extends ServiceImpl<InterfaceInfoMapper, InterfaceInfo>
    implements InterfaceInfoService{

    @Override
    public void validInterfaceInfo(InterfaceInfo interfaceInfo, boolean add) {
        if (interfaceInfo == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }

        String name = interfaceInfo.getName();
        String description = interfaceInfo.getDescription();
        String method = interfaceInfo.getMethod();
        String requestHeader = interfaceInfo.getRequestHeader();
        String responseHeader = interfaceInfo.getResponseHeader();
        String url = interfaceInfo.getUrl();


        // 创建时，所有参数必须非空
        if (add) {
            if (StringUtils.isAnyBlank(name, method, requestHeader, responseHeader, url)) {
                throw new BusinessException(ErrorCode.PARAMS_ERROR);
            }
        }
        //接口名长度不能超过50
        if (name.length() > 50) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "内容过长");
        }

        //todo 新增接口的判断条件
    }
}




