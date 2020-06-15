package com.kismet.cloud.orderserver.mapper;

import com.kismet.cloud.orderserver.model.CyOffsetStoreDO;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import tk.mybatis.mapper.common.BaseMapper;
import tk.mybatis.mapper.common.ConditionMapper;

/**
 * @author kismet
 * @since 2020/3/26
 */
public interface CyOffsetStoreMapper extends BaseMapper<CyOffsetStoreDO>, ConditionMapper<CyOffsetStoreDO> {

    @Select("select * from offset_store where id = #{id}")
    public List<CyOffsetStoreDO> testSelect(Long id);
}
