package com.kismet.cloud.productserver.manager.model.primary.entity;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * @author kismet
 * @since 2020/3/26
 */
@Data
public class CyOffsetStore implements Serializable {
    /**
     * 业务id
     */
    private Long id;

    /**
     * 创建时间
     */
    private Date gmtCreated;

    /**
     * 修改时间
     */
    private Date gmtModified;

    /**
     * 逻辑删除,0.未删除 1.已删除
     */
    private Integer isDeleted;

    /**
     * 消费偏移量
     */
    private Integer consumeOffset;

    /**
     * 主题
     */
    private String topic;

    private static final long serialVersionUID = 1L;
}
