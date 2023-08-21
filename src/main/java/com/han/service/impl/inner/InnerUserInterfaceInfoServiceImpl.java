package com.han.service.impl.inner;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.han.apicommon.model.entity.UserInterfaceInfo;
import com.han.apicommon.service.InnerUserInterfaceInfoService;
import com.han.service.UserInterfaceInfoService;
import org.apache.dubbo.config.annotation.DubboService;

import javax.annotation.Resource;
@DubboService
public class InnerUserInterfaceInfoServiceImpl implements InnerUserInterfaceInfoService {

    @Resource
    private UserInterfaceInfoService userInterfaceInfoService;

    @Override
    public boolean invokeCount(long userId, long interfaceInfoId) {
        return userInterfaceInfoService.invokeCount(userId, interfaceInfoId);
    }

    @Override
    public boolean hasLeftInvokeCount(long userId, long interfaceInfoId) {
        LambdaQueryWrapper<UserInterfaceInfo> lqw = new LambdaQueryWrapper<>();
        lqw.eq(UserInterfaceInfo::getUserId, userId);
        lqw.eq(UserInterfaceInfo::getInterfaceInfoId, interfaceInfoId);
        UserInterfaceInfo userInterfaceInfo = userInterfaceInfoService.getOne(lqw);
        return userInterfaceInfo != null && userInterfaceInfo.getLeftNum() > 0;
    }
}
