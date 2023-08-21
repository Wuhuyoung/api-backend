package com.han.controller;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.han.apicommon.model.entity.InterfaceInfo;
import com.han.apicommon.model.entity.UserInterfaceInfo;
import com.han.constant.UserConstant;
import com.han.annotation.AuthCheck;
import com.han.common.BaseResponse;
import com.han.common.ErrorCode;
import com.han.common.ResultUtils;
import com.han.exception.BusinessException;
import com.han.mapper.UserInterfaceInfoMapper;
import com.han.model.vo.InterfaceInfoVO;
import com.han.service.InterfaceInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 统计分析控制器
 *
 * @author han
 */
@RestController
@RequestMapping("/analysis")
@Slf4j
public class AnalysisController {

    @Resource
    private UserInterfaceInfoMapper userInterfaceInfoMapper;

    @Resource
    private InterfaceInfoService interfaceInfoService;

    @GetMapping("/top/interface/invoke")
    @AuthCheck(mustRole = UserConstant.ADMIN_ROLE)
    public BaseResponse<List<InterfaceInfoVO>> listTopInvokeInterface() {
        List<UserInterfaceInfo> topUserInterfaceList = userInterfaceInfoMapper.listTopInvokeInterface(4);
        // 根据interfaceInfoId分组
        Map<Long, List<UserInterfaceInfo>> map = topUserInterfaceList.stream()
                .collect(Collectors.groupingBy(UserInterfaceInfo::getInterfaceInfoId));
        LambdaQueryWrapper<InterfaceInfo> lqw = new LambdaQueryWrapper<>();
        lqw.in(InterfaceInfo::getId, map.keySet());
        List<InterfaceInfo> interfaceInfoList = interfaceInfoService.list(lqw);
        if (interfaceInfoList == null) {
            throw new BusinessException(ErrorCode.SYSTEM_ERROR);
        }
        List<InterfaceInfoVO> interfaceInfoVOList = interfaceInfoList.stream().map(interfaceInfo -> {
            InterfaceInfoVO vo = new InterfaceInfoVO();
            BeanUtil.copyProperties(interfaceInfo, vo);
            vo.setTotalNum(map.get(interfaceInfo.getId()).get(0).getTotalNum());
            return vo;
        }).collect(Collectors.toList());
        return ResultUtils.success(interfaceInfoVOList);
    }
}
