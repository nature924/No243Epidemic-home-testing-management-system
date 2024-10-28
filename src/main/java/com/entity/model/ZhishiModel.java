package com.entity.model;

import com.entity.ZhishiEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import java.io.Serializable;


/**
 * 健康小知识
 * 接收传参的实体类
 *（实际开发中配合移动端接口开发手动去掉些没用的字段， 后端一般用entity就够用了）
 * 取自ModelAndView 的model名称
 */
public class ZhishiModel implements Serializable {
    private static final long serialVersionUID = 1L;




    /**
     * 主键
     */
    private Integer id;


    /**
     * 知识标题
     */
    private String zhishiName;


    /**
     * 知识封面
     */
    private String zhishiPhoto;


    /**
     * 赞
     */
    private Integer zanNumber;


    /**
     * 踩
     */
    private Integer caiNumber;


    /**
     * 知识类型
     */
    private Integer zhishiTypes;


    /**
     * 知识热度
     */
    private Integer zhishiClicknum;


    /**
     * 知识介绍
     */
    private String zhishiContent;


    /**
     * 是否上架
     */
    private Integer shangxiaTypes;


    /**
     * 逻辑删除
     */
    private Integer zhishiDelete;


    /**
     * 录入时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    private Date insertTime;


    /**
     * 创建时间  show1 show2 photoShow
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
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
	 * 获取：创建时间  show1 show2 photoShow
	 */
    public Date getCreateTime() {
        return createTime;
    }


    /**
	 * 设置：创建时间  show1 show2 photoShow
	 */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    }
