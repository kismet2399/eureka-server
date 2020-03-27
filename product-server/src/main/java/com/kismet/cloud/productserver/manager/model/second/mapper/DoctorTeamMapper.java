package com.kismet.cloud.productserver.manager.model.second.mapper;

import com.kismet.cloud.productserver.manager.model.second.entity.DoctorTeamEntity;

import org.apache.ibatis.annotations.Mapper;

/**
 * @author kismet
 * @since 2020/3/26
 */
@Mapper
public interface DoctorTeamMapper {
    /**
     * 保存一个实体，null的属性也会保存，不会使用数据库默认值
     *
     * @param entity 入库对象
     * @return 入库数目
     */
    int insert(DoctorTeamEntity entity);
    /**
     * 查询是否存在该团队
     *
     * @param teamId 主键
     * @return 查询对象
     */
    int selectByPrimaryKey(String teamId);

    /**
     * 更新一个实体
     *
     * @param doctorTeamEntity 入库对象
     * @return 查询对象
     */
    int update(DoctorTeamEntity doctorTeamEntity);
}
