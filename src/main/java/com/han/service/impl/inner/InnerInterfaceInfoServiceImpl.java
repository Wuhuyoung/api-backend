package com.han.service.impl.inner;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.han.apicommon.model.entity.InterfaceInfo;
import com.han.apicommon.service.InnerInterfaceInfoService;
import com.han.common.ErrorCode;
import com.han.exception.BusinessException;
import com.han.mapper.InterfaceInfoMapper;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.DubboService;

import javax.annotation.Resource;
@DubboService
public class InnerInterfaceInfoServiceImpl implements InnerInterfaceInfoService {

    @Resource
    private InterfaceInfoMapper interfaceInfoMapper;

    @Override
    public InterfaceInfo getInterfaceInfo(String url, String method) {
        if (StringUtils.isAnyEmpty(url, method)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        LambdaQueryWrapper<InterfaceInfo> lqw = new LambdaQueryWrapper<>();
        lqw.eq(InterfaceInfo::getUrl, url);
        lqw.eq(InterfaceInfo::getMethod, method);
        return interfaceInfoMapper.selectOne(lqw);
    }
}
