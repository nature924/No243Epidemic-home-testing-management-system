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
 * 健康小知识
 *
 * @author 
 * @email
 */
@TableName("zhishi")
public class ZhishiEntity<T> implements Serializable {
    private static final long serialVersionUID = 1L;


	public ZhishiEntity() {

	}

	public ZhishiEntity(T t) {
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
     * 知识标题
     */
    @ColumnInfo(comment="知识标题",type="varchar(200)")
    @TableField(value = "zhishi_name")

    private String zhishiName;


    /**
     * 知识封面
     */
    @ColumnInfo(comment="知识封面",type="varchar(200)")
    @TableField(value = "zhishi_photo")

    private String zhishiPhoto;


    /**
     * 知识视频
     */
    @ColumnInfo(comment="知识视频",type="varchar(200)")
    @TableField(value = "zhishi_video")

    private String zhishiVideo;


    /**
     * 赞
     */
    @ColumnInfo(comment="赞",type="int(11)")
    @TableField(value = "zan_number")

    private Integer zanNumber;


    /**
     * 踩
     */
    @ColumnInfo(comment="踩",type="int(11)")
    @TableField(value = "cai_number")

    private Integer caiNumber;


    /**
     * 知识类型
     */
    @ColumnInfo(comment="知识类型",type="int(11)")
    @TableField(value = "zhishi_types")

    private Integer zhishiTypes;


    /**
     * 知识热度
     */
    @ColumnInfo(comment="知识热度",type="int(11)")
    @TableField(value = "zhishi_clicknum")

    private Integer zhishiClicknum;


    /**
     * 知识介绍
     */
    @ColumnInfo(comment="知识介绍",type="longtext")
    @TableField(value = "zhishi_content")

    private String zhishiContent;


    /**
     * 是否上架
     */
    @ColumnInfo(comment="是否上架",type="int(11)")
    @TableField(value = "shangxia_types")

    private Integer shangxiaTypes;


    /**
     * 逻辑删除
     */
    @ColumnInfo(comment="逻辑删除",type="int(11)")
    @TableField(value = "zhishi_delete")

    private Integer zhishiDelete;


    /**
     * 录入时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    @ColumnInfo(comment="录入时间",type="timestamp")
    @TableField(value = "insert_time",fill = FieldFill.INSERT)

    private Date insertTime;


    /**
     * 创建时间
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
	 * 获取：知识标题
	 */
    public String getZhishiName() {
        return zhishiName;
    }
    /**
	 * 设置：知识标题
	 */

    public void setZhishiName(String zhishiName) {
        this.zhishiName = zhishiName;
    }
    /**
	 * 获取：知识封面
	 */
    public String getZhishiPhoto() {
        return zhishiPhoto;
    }
    /**
	 * 设置：知识封面
	 */

    public void setZhishiPhoto(String zhishiPhoto) {
        this.zhishiPhoto = zhishiPhoto;
    }
    /**
	 * 获取：知识视频
	 */
    public String getZhishiVideo() {
        return zhishiVideo;
    }
    /**
	 * 设置：知识视频
	 */

    public void setZhishiVideo(String zhishiVideo) {
        this.zhishiVideo = zhishiVideo;
    }
    /**
	 * 获取：赞
	 */
    public Integer getZanNumber() {
        return zanNumber;
    }
    /**
	 * 设置：赞
	 */

    public void setZanNumber(Integer zanNumber) {
        this.zanNumber = zanNumber;
    }
    /**
	 * 获取：踩
	 */
    public Integer getCaiNumber() {
        return caiNumber;
    }
    /**
	 * 设置：踩
	 */

    public void setCaiNumber(Integer caiNumber) {
        this.caiNumber = caiNumber;
    }
    /**
	 * 获取：知识类型
	 */
    public Integer getZhishiTypes() {
        return zhishiTypes;
    }
    /**
	 * 设置：知识类型
	 */

    public void setZhishiTypes(Integer zhishiTypes) {
        this.zhishiTypes = zhishiTypes;
    }
    /**
	 * 获取：知识热度
	 */
    public Integer getZhishiClicknum() {
        return zhishiClicknum;
    }
    /**
	 * 设置：知识热度
	 */

    public void setZhishiClicknum(Integer zhishiClicknum) {
        this.zhishiClicknum = zhishiClicknum;
    }
    /**
	 * 获取：知识介绍
	 */
    public String getZhishiContent() {
        return zhishiContent;
    }
    /**
	 * 设置：知识介绍
	 */

    public void setZhishiContent(String zhishiContent) {
        this.zhishiContent = zhishiContent;
    }
    /**
	 * 获取：是否上架
	 */
    public Integer getShangxiaTypes() {
        return shangxiaTypes;
    }
    /**
	 * 设置：是否上架
	 */

    public void setShangxiaTypes(Integer shangxiaTypes) {
        this.shangxiaTypes = shangxiaTypes;
    }
    /**
	 * 获取：逻辑删除
	 */
    public Integer getZhishiDelete() {
        return zhishiDelete;
    }
    /**
	 * 设置：逻辑删除
	 */

    public void setZhishiDelete(Integer zhishiDelete) {
        this.zhishiDelete = zhishiDelete;
    }
    /**
	 * 获取：录入时间
	 */
    public Date getInsertTime() {
        return insertTime;
    }
    /**
	 * 设置：录入时间
	 */

    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }
    /**
	 * 获取：创建时间
	 */
    public Date getCreateTime() {
        return createTime;
    }
    /**
	 * 设置：创建时间
	 */

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "Zhishi{" +
            ", id=" + id +
            ", zhishiName=" + zhishiName +
            ", zhishiPhoto=" + zhishiPhoto +
            ", zhishiVideo=" + zhishiVideo +
            ", zanNumber=" + zanNumber +
            ", caiNumber=" + caiNumber +
            ", zhishiTypes=" + zhishiTypes +
            ", zhishiClicknum=" + zhishiClicknum +
            ", zhishiContent=" + zhishiContent +
            ", shangxiaTypes=" + shangxiaTypes +
            ", zhishiDelete=" + zhishiDelete +
            ", insertTime=" + DateUtil.convertString(insertTime,"yyyy-MM-dd") +
            ", createTime=" + DateUtil.convertString(createTime,"yyyy-MM-dd") +
        "}";
    }
}
