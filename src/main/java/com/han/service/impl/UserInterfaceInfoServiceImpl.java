package com.han.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.han.apicommon.model.entity.UserInterfaceInfo;
import com.han.exception.BusinessException;
import com.han.mapper.UserInterfaceInfoMapper;
import com.han.common.ErrorCode;
import com.han.service.UserInterfaceInfoService;
import org.springframework.stereotype.Service;

/**
* @author 86183
* @description 针对表【user_interface_info】的数据库操作Service实现
* @createDate 2023-03-17 08:46:55
*/
@Service
public class UserInterfaceInfoServiceImpl extends ServiceImpl<UserInterfaceInfoMapper, UserInterfaceInfo>
    implements UserInterfaceInfoService{

    @Override
    public void validUserInterfaceInfo(UserInterfaceInfo userInterfaceInfo, boolean add) {
        if (userInterfaceInfo == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }

        Long userId = userInterfaceInfo.getUserId();
        Long interfaceInfoId = userInterfaceInfo.getInterfaceInfoId();
        Integer leftNum = userInterfaceInfo.getLeftNum();

        // 创建时，所有参数必须非空
        if (add) {
            if (userId <= 0 || interfaceInfoId <= 0) {
                throw new BusinessException(ErrorCode.PARAMS_ERROR, "用户或接口不存在");
            }
        }
        //剩余调用次数不能小于0
        if (leftNum < 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
    }

    @Override
    public boolean invokeCount(long userId, long interfaceInfoId) {
        if (userId <= 0 || interfaceInfoId <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        LambdaUpdateWrapper<UserInterfaceInfo> lqw = new LambdaUpdateWrapper<>();
        lqw.eq(UserInterfaceInfo::getUserId, userId);
        lqw.eq(UserInterfaceInfo::getInterfaceInfoId, interfaceInfoId);
        lqw.setSql("total_num = total_num + 1, left_num = left_num - 1");
        //剩余调用次数必须大于0
        lqw.gt(UserInterfaceInfo::getLeftNum, 0);
        return this.update(lqw);
    }
}




