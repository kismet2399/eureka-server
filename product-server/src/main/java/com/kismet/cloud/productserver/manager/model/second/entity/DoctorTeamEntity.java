package com.kismet.cloud.productserver.manager.model.second.entity;

import java.time.LocalDate;

import lombok.Data;

/**
 * @author kismet
 * @since 2020/3/26
 */
@Data
public class DoctorTeamEntity {

    /**
     * 必填-家庭医生团队ID  主键：县级区划+业务系统主键
     */
    String jtysTeamId;
    /**
     * 必填-基层机构ID  《河南省医疗卫生信息标准V2.3（标准编码集-医疗机构标准编码）
     */
    String organizationId;
    /**
     * 必填-家庭医生团队负责人ID  县级区划+业务系统主键
     */
    String familyDoctorId;
    /**
     * 必填-县级行政区划代码  《河南省医疗卫生信息标准V2.3（标准编码集-行政区划编码）
     */
    String proviceDistrictCode;
    /**
     * 必填-组织机构代码  优先填写统一社会信用代码，无统一社会信用代码的填写组织机构代码
     */
    String organizationCode;
    /**
     * 必填-家庭医生团队名称
     */
    String teamName;
    /**
     * 家庭医生团队负责人姓名
     */
    String presideName;
    /**
     * 负责人身份证件类别代码  CV02.01.101身份证件类别代码
     */
    String presideidCardType;
    /**
     * 负责人身份证件号码
     */
    String presideidCardNo;
    /**
     * 必填-数据上传时间
     */
    LocalDate uploadDate;
    /**
     * 必填-数据更新时间
     */
    LocalDate updateDate;
    /**
     * 必填-创建时间
     */
    LocalDate createTime;
    /**
     * 必填-最后修改时间
     */
    LocalDate lastUpdateDtime;
}