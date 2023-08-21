package com.han.apicommon.service;

public interface InnerUserInterfaceInfoService {
    /**
     * 统计调用次数
     * @param userId
     * @param interfaceInfoId
     * @return
     */
    boolean invokeCount(long userId, long interfaceInfoId);

    /**
     * 判断用户剩余调用次数是否大于0
     * @param userId
     * @param interfaceInfoId
     * @return
     */
    boolean hasLeftInvokeCount(long userId, long interfaceInfoId);
}
