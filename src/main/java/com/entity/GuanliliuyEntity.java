package com.entity;

import com.annotation.ColumnInfo;
import javax.validation.constraints.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.lang.reflect.InvocationTargetException;
import java.io.Serializable;
import java.util.*;
import org.apache.tools.ant.util.DateUtils;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.beanutils.BeanUtils;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.utils.DateUtil;


/**
 * 每日体温
 *
 * @author 
 * @email
 */
@TableName("guanliliuy")
public class GuanliliuyEntity<T> implements Serializable {
    private static final long serialVersionUID = 1L;


	public GuanliliuyEntity() {

	}

	public GuanliliuyEntity(T t) {
		try {
			BeanUtils.copyProperties(this, t);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    @ColumnInfo(comment="主键",type="int(11)")
    @TableField(value = "id")

    private Integer id;


    /**
     * 用户
     */
    @ColumnInfo(comment="用户",type="int(11)")
    @TableField(value = "yonghu_id")

    private Integer yonghuId;


    /**
     * 体温
     */
    @ColumnInfo(comment="体温",type="decimal(10,2)")
    @TableField(value = "guanliliuy_tiwen")

    private Double guanliliuyTiwen;


    /**
     * 图片
     */
    @ColumnInfo(comment="图片",type="varchar(200)")
    @TableField(value = "guanliliuy_photo")

    private String guanliliuyPhoto;


    /**
     * 有无咳嗽
     */
    @ColumnInfo(comment="有无咳嗽",type="int(200)")
    @TableField(value = "keshou_types")

    private Integer keshouTypes;


    /**
     * 有无外出
     */
    @ColumnInfo(comment="有无外出",type="int(200)")
    @TableField(value = "wuaichu_types")

    private Integer wuaichuTypes;


    /**
     * 外出地点
     */
    @ColumnInfo(comment="外出地点",type="varchar(200)")
    @TableField(value = "guanliliuy_didian")

    private String guanliliuyDidian;


    /**
     * 是否接触病患
     */
    @ColumnInfo(comment="是否接触病患",type="int(200)")
    @TableField(value = "binghuan_types")

    private Integer binghuanTypes;


    /**
     * 是否出入高风险区域
     */
    @ColumnInfo(comment="是否出入高风险区域",type="int(200)")
    @TableField(value = "gaofengxian_types")

    private Integer gaofengxianTypes;


    /**
     * 备注
     */
    @ColumnInfo(comment="备注",type="longtext")
    @TableField(value = "guanliliuy_content")

    private String guanliliuyContent;


    /**
     * 添加时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    @ColumnInfo(comment="添加时间",type="timestamp")
    @TableField(value = "insert_time",fill = FieldFill.INSERT)

    private Date insertTime;


    /**
     * 创建时间  listShow
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    @ColumnInfo(comment="创建时间",type="timestamp")
    @TableField(value = "create_time",fill = FieldFill.INSERT)

    private Date createTime;


    /**
	 * 获取：主键
	 */
    public Integer getId() {
        return id;
    }
    /**
	 * 设置：主键
	 */

    public void setId(Integer id) {
        this.id = id;
    }
    /**
	 * 获取：用户
	 */
    public Integer getYonghuId() {
        return yonghuId;
    }
    /**
	 * 设置：用户
	 */

    public void setYonghuId(Integer yonghuId) {
        this.yonghuId = yonghuId;
    }
    /**
	 * 获取：体温
	 */
    public Double getGuanliliuyTiwen() {
        return guanliliuyTiwen;
    }
    /**
	 * 设置：体温
	 */

    public void setGuanliliuyTiwen(Double guanliliuyTiwen) {
        this.guanliliuyTiwen = guanliliuyTiwen;
    }
    /**
	 * 获取：图片
	 */
    public String getGuanliliuyPhoto() {
        return guanliliuyPhoto;
    }
    /**
	 * 设置：图片
	 */

    public void setGuanliliuyPhoto(String guanliliuyPhoto) {
        this.guanliliuyPhoto = guanliliuyPhoto;
    }
    /**
	 * 获取：有无咳嗽
	 */
    public Integer getKeshouTypes() {
        return keshouTypes;
    }
    /**
	 * 设置：有无咳嗽
	 */

    public void setKeshouTypes(Integer keshouTypes) {
        this.keshouTypes = keshouTypes;
    }
    /**
	 * 获取：有无外出
	 */
    public Integer getWuaichuTypes() {
        return wuaichuTypes;
    }
    /**
	 * 设置：有无外出
	 */

    public void setWuaichuTypes(Integer wuaichuTypes) {
        this.wuaichuTypes = wuaichuTypes;
    }
    /**
	 * 获取：外出地点
	 */
    public String getGuanliliuyDidian() {
        return guanliliuyDidian;
    }
    /**
	 * 设置：外出地点
	 */

    public void setGuanliliuyDidian(String guanliliuyDidian) {
        this.guanliliuyDidian = guanliliuyDidian;
    }
    /**
	 * 获取：是否接触病患
	 */
    public Integer getBinghuanTypes() {
        return binghuanTypes;
    }
    /**
	 * 设置：是否接触病患
	 */

    public void setBinghuanTypes(Integer binghuanTypes) {
        this.binghuanTypes = binghuanTypes;
    }
    /**
	 * 获取：是否出入高风险区域
	 */
    public Integer getGaofengxianTypes() {
        return gaofengxianTypes;
    }
    /**
	 * 设置：是否出入高风险区域
	 */

    public void setGaofengxianTypes(Integer gaofengxianTypes) {
        this.gaofengxianTypes = gaofengxianTypes;
    }
    /**
	 * 获取：备注
	 */
    public String getGuanliliuyContent() {
        return guanliliuyContent;
    }
    /**
	 * 设置：备注
	 */

    public void setGuanliliuyContent(String guanliliuyContent) {
        this.guanliliuyContent = guanliliuyContent;
    }
    /**
	 * 获取：添加时间
	 */
    public Date getInsertTime() {
        return insertTime;
    }
    /**
	 * 设置：添加时间
	 */

    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }
    /**
	 * 获取：创建时间  listShow
	 */
    public Date getCreateTime() {
        return createTime;
    }
    /**
	 * 设置：创建时间  listShow
	 */

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "Guanliliuy{" +
            ", id=" + id +
            ", yonghuId=" + yonghuId +
            ", guanliliuyTiwen=" + guanliliuyTiwen +
            ", guanliliuyPhoto=" + guanliliuyPhoto +
            ", keshouTypes=" + keshouTypes +
            ", wuaichuTypes=" + wuaichuTypes +
            ", guanliliuyDidian=" + guanliliuyDidian +
            ", binghuanTypes=" + binghuanTypes +
            ", gaofengxianTypes=" + gaofengxianTypes +
            ", guanliliuyContent=" + guanliliuyContent +
            ", insertTime=" + DateUtil.convertString(insertTime,"yyyy-MM-dd") +
            ", createTime=" + DateUtil.convertString(createTime,"yyyy-MM-dd") +
        "}";
    }
}
