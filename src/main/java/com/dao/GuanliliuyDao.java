package com.dao;

import com.entity.GuanliliuyEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.view.GuanliliuyView;

/**
 * 每日体温 Dao 接口
 *
 * @author 
 */
public interface GuanliliuyDao extends BaseMapper<GuanliliuyEntity> {

   List<GuanliliuyView> selectListView(Pagination page,@Param("params")Map<String,Object> params);

}
