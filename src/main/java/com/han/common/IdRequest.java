package com.han.common;

import lombok.Data;

import java.io.Serializable;

/**
 * 传递id的请求
 *
 * @author han
 */
@Data
public class IdRequest implements Serializable {
    /**
     * id
     */
    private Long id;

    private static final long serialVersionUID = 1L;
}