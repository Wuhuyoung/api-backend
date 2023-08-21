package com.han.model.vo;

import com.han.apicommon.model.entity.InterfaceInfo;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 接口视图
 *
 * @author han
 * @TableName interface_info
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class InterfaceInfoVO extends InterfaceInfo {

    /**
     * 总调用次数
     */
    private Integer totalNum;

    private static final long serialVersionUID = 1L;
}