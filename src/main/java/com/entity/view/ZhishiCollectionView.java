package com.entity.view;

import org.apache.tools.ant.util.DateUtils;
import com.annotation.ColumnInfo;
import com.entity.ZhishiCollectionEntity;
import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.util.Date;
import com.utils.DateUtil;

/**
* 知识收藏
* 后端返回视图实体辅助类
* （通常后端关联的表或者自定义的字段需要返回使用）
*/
@TableName("zhishi_collection")
public class ZhishiCollectionView extends ZhishiCollectionEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	//当前表
	/**
	* 类型的值
	*/
	@ColumnInfo(comment="类型的字典表值",type="varchar(200)")
	private String zhishiCollectionValue;

	//级联表 用户
		/**
		* 用户名称
		*/

		@ColumnInfo(comment="用户名称",type="varchar(200)")
		private String yonghuName;
		/**
		* 用户手机号
		*/

		@ColumnInfo(comment="用户手机号",type="varchar(200)")
		private String yonghuPhone;
		/**
		* 用户身份证号
		*/

		@ColumnInfo(comment="用户身份证号",type="varchar(200)")
		private String yonghuIdNumber;
		/**
		* 用户头像
		*/

		@ColumnInfo(comment="用户头像",type="varchar(200)")
		private String yonghuPhoto;
		/**
		* 用户邮箱
		*/

		@ColumnInfo(comment="用户邮箱",type="varchar(200)")
		private String yonghuEmail;
		/**
		* 逻辑删除
		*/

		@ColumnInfo(comment="逻辑删除",type="int(11)")
		private Integer yonghuDelete;
	//级联表 健康小知识
		/**
		* 知识标题
		*/

		@ColumnInfo(comment="知识标题",type="varchar(200)")
		private String zhishiName;
		/**
		* 知识封面
		*/

		@ColumnInfo(comment="知识封面",type="varchar(200)")
		private String zhishiPhoto;
		/**
		* 赞
		*/

		@ColumnInfo(comment="赞",type="int(11)")
		private Integer zanNumber;
		/**
		* 踩
		*/

		@ColumnInfo(comment="踩",type="int(11)")
		private Integer caiNumber;
		/**
		* 知识类型
		*/
		@ColumnInfo(comment="知识类型",type="int(11)")
		private Integer zhishiTypes;
			/**
			* 知识类型的值
			*/
			@ColumnInfo(comment="知识类型的字典表值",type="varchar(200)")
			private String zhishiValue;
		/**
		* 知识热度
		*/

		@ColumnInfo(comment="知识热度",type="int(11)")
		private Integer zhishiClicknum;
		/**
		* 知识介绍
		*/

		@ColumnInfo(comment="知识介绍",type="longtext")
		private String zhishiContent;
		/**
		* 是否上架
		*/
		@ColumnInfo(comment="是否上架",type="int(11)")
		private Integer shangxiaTypes;
			/**
			* 是否上架的值
			*/
			@ColumnInfo(comment="是否上架的字典表值",type="varchar(200)")
			private String shangxiaValue;
		/**
		* 逻辑删除
		*/

		@ColumnInfo(comment="逻辑删除",type="int(11)")
		private Integer zhishiDelete;



	public ZhishiCollectionView() {

	}

	public ZhishiCollectionView(ZhishiCollectionEntity zhishiCollectionEntity) {
		try {
			BeanUtils.copyProperties(this, zhishiCollectionEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



	//当前表的
	/**
	* 获取： 类型的值
	*/
	public String getZhishiCollectionValue() {
		return zhishiCollectionValue;
	}
	/**
	* 设置： 类型的值
	*/
	public void setZhishiCollectionValue(String zhishiCollectionValue) {
		this.zhishiCollectionValue = zhishiCollectionValue;
	}


	//级联表的get和set 用户

		/**
		* 获取： 用户名称
		*/
		public String getYonghuName() {
			return yonghuName;
		}
		/**
		* 设置： 用户名称
		*/
		public void setYonghuName(String yonghuName) {
			this.yonghuName = yonghuName;
		}

		/**
		* 获取： 用户手机号
		*/
		public String getYonghuPhone() {
			return yonghuPhone;
		}
		/**
		* 设置： 用户手机号
		*/
		public void setYonghuPhone(String yonghuPhone) {
			this.yonghuPhone = yonghuPhone;
		}

		/**
		* 获取： 用户身份证号
		*/
		public String getYonghuIdNumber() {
			return yonghuIdNumber;
		}
		/**
		* 设置： 用户身份证号
		*/
		public void setYonghuIdNumber(String yonghuIdNumber) {
			this.yonghuIdNumber = yonghuIdNumber;
		}

		/**
		* 获取： 用户头像
		*/
		public String getYonghuPhoto() {
			return yonghuPhoto;
		}
		/**
		* 设置： 用户头像
		*/
		public void setYonghuPhoto(String yonghuPhoto) {
			this.yonghuPhoto = yonghuPhoto;
		}

		/**
		* 获取： 用户邮箱
		*/
		public String getYonghuEmail() {
			return yonghuEmail;
		}
		/**
		* 设置： 用户邮箱
		*/
		public void setYonghuEmail(String yonghuEmail) {
			this.yonghuEmail = yonghuEmail;
		}

		/**
		* 获取： 逻辑删除
		*/
		public Integer getYonghuDelete() {
			return yonghuDelete;
		}
		/**
		* 设置： 逻辑删除
		*/
		public void setYonghuDelete(Integer yonghuDelete) {
			this.yonghuDelete = yonghuDelete;
		}
	//级联表的get和set 健康小知识

		/**
		* 获取： 知识标题
		*/
		public String getZhishiName() {
			return zhishiName;
		}
		/**
		* 设置： 知识标题
		*/
		public void setZhishiName(String zhishiName) {
			this.zhishiName = zhishiName;
		}

		/**
		* 获取： 知识封面
		*/
		public String getZhishiPhoto() {
			return zhishiPhoto;
		}
		/**
		* 设置： 知识封面
		*/
		public void setZhishiPhoto(String zhishiPhoto) {
			this.zhishiPhoto = zhishiPhoto;
		}

		/**
		* 获取： 赞
		*/
		public Integer getZanNumber() {
			return zanNumber;
		}
		/**
		* 设置： 赞
		*/
		public void setZanNumber(Integer zanNumber) {
			this.zanNumber = zanNumber;
		}

		/**
		* 获取： 踩
		*/
		public Integer getCaiNumber() {
			return caiNumber;
		}
		/**
		* 设置： 踩
		*/
		public void setCaiNumber(Integer caiNumber) {
			this.caiNumber = caiNumber;
		}
		/**
		* 获取： 知识类型
		*/
		public Integer getZhishiTypes() {
			return zhishiTypes;
		}
		/**
		* 设置： 知识类型
		*/
		public void setZhishiTypes(Integer zhishiTypes) {
			this.zhishiTypes = zhishiTypes;
		}


			/**
			* 获取： 知识类型的值
			*/
			public String getZhishiValue() {
				return zhishiValue;
			}
			/**
			* 设置： 知识类型的值
			*/
			public void setZhishiValue(String zhishiValue) {
				this.zhishiValue = zhishiValue;
			}

		/**
		* 获取： 知识热度
		*/
		public Integer getZhishiClicknum() {
			return zhishiClicknum;
		}
		/**
		* 设置： 知识热度
		*/
		public void setZhishiClicknum(Integer zhishiClicknum) {
			this.zhishiClicknum = zhishiClicknum;
		}

		/**
		* 获取： 知识介绍
		*/
		public String getZhishiContent() {
			return zhishiContent;
		}
		/**
		* 设置： 知识介绍
		*/
		public void setZhishiContent(String zhishiContent) {
			this.zhishiContent = zhishiContent;
		}
		/**
		* 获取： 是否上架
		*/
		public Integer getShangxiaTypes() {
			return shangxiaTypes;
		}
		/**
		* 设置： 是否上架
		*/
		public void setShangxiaTypes(Integer shangxiaTypes) {
			this.shangxiaTypes = shangxiaTypes;
		}


			/**
			* 获取： 是否上架的值
			*/
			public String getShangxiaValue() {
				return shangxiaValue;
			}
			/**
			* 设置： 是否上架的值
			*/
			public void setShangxiaValue(String shangxiaValue) {
				this.shangxiaValue = shangxiaValue;
			}

		/**
		* 获取： 逻辑删除
		*/
		public Integer getZhishiDelete() {
			return zhishiDelete;
		}
		/**
		* 设置： 逻辑删除
		*/
		public void setZhishiDelete(Integer zhishiDelete) {
			this.zhishiDelete = zhishiDelete;
		}


	@Override
	public String toString() {
		return "ZhishiCollectionView{" +
			", zhishiCollectionValue=" + zhishiCollectionValue +
			", zhishiName=" + zhishiName +
			", zhishiPhoto=" + zhishiPhoto +
			", zanNumber=" + zanNumber +
			", caiNumber=" + caiNumber +
			", zhishiClicknum=" + zhishiClicknum +
			", zhishiContent=" + zhishiContent +
			", zhishiDelete=" + zhishiDelete +
			", yonghuName=" + yonghuName +
			", yonghuPhone=" + yonghuPhone +
			", yonghuIdNumber=" + yonghuIdNumber +
			", yonghuPhoto=" + yonghuPhoto +
			", yonghuEmail=" + yonghuEmail +
			", yonghuDelete=" + yonghuDelete +
			"} " + super.toString();
	}
}
