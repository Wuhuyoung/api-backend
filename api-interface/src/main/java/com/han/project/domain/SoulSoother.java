package com.han.project.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 心灵鸡汤
 * @TableName soul_soother
 */
@TableName(value ="soul_soother")
@Data
public class SoulSoother implements Serializable {
    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 文案
     */
    private String content;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}