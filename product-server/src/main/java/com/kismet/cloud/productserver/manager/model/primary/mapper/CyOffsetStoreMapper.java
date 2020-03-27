package com.kismet.cloud.productserver.manager.model.primary.mapper;

import com.kismet.cloud.productserver.manager.model.primary.entity.CyOffsetStore;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author kismet
 * @since 2020/3/26
 */
@Mapper
public interface CyOffsetStoreMapper {
    int deleteByPrimaryKey(Long id);

    int insert(CyOffsetStore record);

    int insertSelective(CyOffsetStore record);

    CyOffsetStore selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CyOffsetStore record);

    int updateByPrimaryKey(CyOffsetStore record);

    /**
     * 通过主题查记录
     * @param topic
     * @return
     */
    CyOffsetStore selectByTopic(String topic);

    /**
     * 更新offset
     * @param id
     * @param offset
     * @return
     */
    int commitOffset(@Param("id") Long id, @Param("offset") Integer offset);
}
