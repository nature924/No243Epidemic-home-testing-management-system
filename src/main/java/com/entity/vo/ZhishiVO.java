package com.entity.vo;

import com.entity.ZhishiEntity;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

/**
 * 健康小知识
 * 手机端接口返回实体辅助类
 * （主要作用去除一些不必要的字段）
 */
@TableName("zhishi")
public class ZhishiVO implements Serializable {
    private static final long serialVersionUID = 1L;


    /**
     * 主键
     */

    @TableField(value = "id")
    private Integer id;


    /**
     * 知识标题
     */

    @TableField(value = "zhishi_name")
    private String zhishiName;


    /**
     * 知识封面
     */

    @TableField(value = "zhishi_photo")
    private String zhishiPhoto;


    /**
     * 赞
     */

    @TableField(value = "zan_number")
    private Integer zanNumber;


    /**
     * 踩
     */

    @TableField(value = "cai_number")
    private Integer caiNumber;


    /**
     * 知识类型
     */

    @TableField(value = "zhishi_types")
    private Integer zhishiTypes;


    /**
     * 知识热度
     */

    @TableField(value = "zhishi_clicknum")
    private Integer zhishiClicknum;


    /**
     * 知识介绍
     */

    @TableField(value = "zhishi_content")
    private String zhishiContent;


    /**
     * 是否上架
     */

    @TableField(value = "shangxia_types")
    private Integer shangxiaTypes;


    /**
     * 逻辑删除
     */

    @TableField(value = "zhishi_delete")
    private Integer zhishiDelete;


    /**
     * 录入时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat

    @TableField(value = "insert_time")
    private Date insertTime;


    /**
     * 创建时间  show1 show2 photoShow
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat

    @TableField(value = "create_time")
    private Date createTime;


    /**
	 * 设置：主键
	 */
    public Integer getId() {
        return id;
    }


    /**
	 * 获取：主键
	 */

    public void setId(Integer id) {
        this.id = id;
    }
    /**
	 * 设置：知识标题
	 */
    public String getZhishiName() {
        return zhishiName;
    }


    /**
	 * 获取：知识标题
	 */

    public void setZhishiName(String zhishiName) {
        this.zhishiName = zhishiName;
    }
    /**
	 * 设置：知识封面
	 */
    public String getZhishiPhoto() {
        return zhishiPhoto;
    }


    /**
	 * 获取：知识封面
	 */

    public void setZhishiPhoto(String zhishiPhoto) {
        this.zhishiPhoto = zhishiPhoto;
    }
    /**
	 * 设置：赞
	 */
    public Integer getZanNumber() {
        return zanNumber;
    }


    /**
	 * 获取：赞
	 */

    public void setZanNumber(Integer zanNumber) {
        this.zanNumber = zanNumber;
    }
    /**
	 * 设置：踩
	 */
    public Integer getCaiNumber() {
        return caiNumber;
    }


    /**
	 * 获取：踩
	 */

    public void setCaiNumber(Integer caiNumber) {
        this.caiNumber = caiNumber;
    }
    /**
	 * 设置：知识类型
	 */
    public Integer getZhishiTypes() {
        return zhishiTypes;
    }


    /**
	 * 获取：知识类型
	 */

    public void setZhishiTypes(Integer zhishiTypes) {
        this.zhishiTypes = zhishiTypes;
    }
    /**
	 * 设置：知识热度
	 */
    public Integer getZhishiClicknum() {
        return zhishiClicknum;
    }


    /**
	 * 获取：知识热度
	 */

    public void setZhishiClicknum(Integer zhishiClicknum) {
        this.zhishiClicknum = zhishiClicknum;
    }
    /**
	 * 设置：知识介绍
	 */
    public String getZhishiContent() {
        return zhishiContent;
    }


    /**
	 * 获取：知识介绍
	 */

    public void setZhishiContent(String zhishiContent) {
        this.zhishiContent = zhishiContent;
    }
    /**
	 * 设置：是否上架
	 */
    public Integer getShangxiaTypes() {
        return shangxiaTypes;
    }


    /**
	 * 获取：是否上架
	 */

    public void setShangxiaTypes(Integer shangxiaTypes) {
        this.shangxiaTypes = shangxiaTypes;
    }
    /**
	 * 设置：逻辑删除
	 */
    public Integer getZhishiDelete() {
        return zhishiDelete;
    }


    /**
	 * 获取：逻辑删除
	 */

    public void setZhishiDelete(Integer zhishiDelete) {
        this.zhishiDelete = zhishiDelete;
    }
    /**
	 * 设置：录入时间
	 */
    public Date getInsertTime() {
        return insertTime;
    }


    /**
	 * 获取：录入时间
	 */

    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }
    /**
	 * 设置：创建时间  show1 show2 photoShow
	 */
    public Date getCreateTime() {
        return createTime;
    }


    /**
	 * 获取：创建时间  show1 show2 photoShow
	 */

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

}
