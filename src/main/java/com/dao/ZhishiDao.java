package com.dao;

import com.entity.ZhishiEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.view.ZhishiView;

/**
 * 健康小知识 Dao 接口
 *
 * @author 
 */
public interface ZhishiDao extends BaseMapper<ZhishiEntity> {

   List<ZhishiView> selectListView(Pagination page,@Param("params")Map<String,Object> params);

}
