
package com.controller;

import java.io.File;
import java.math.BigDecimal;
import java.net.URL;
import java.text.SimpleDateFormat;
import com.alibaba.fastjson.JSONObject;
import java.util.*;
import org.springframework.beans.BeanUtils;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.context.ContextLoader;
import javax.servlet.ServletContext;
import com.service.TokenService;
import com.utils.*;
import java.lang.reflect.InvocationTargetException;

import com.service.DictionaryService;
import org.apache.commons.lang3.StringUtils;
import com.annotation.IgnoreAuth;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.entity.*;
import com.entity.view.*;
import com.service.*;
import com.utils.PageUtils;
import com.utils.R;
import com.alibaba.fastjson.*;

/**
 * 医生预约
 * 后端接口
 * @author
 * @email
*/
@RestController
@Controller
@RequestMapping("/yishengYuyue")
public class YishengYuyueController {
    private static final Logger logger = LoggerFactory.getLogger(YishengYuyueController.class);

    private static final String TABLE_NAME = "yishengYuyue";

    @Autowired
    private YishengYuyueService yishengYuyueService;


    @Autowired
    private TokenService tokenService;

    @Autowired
    private ChatService chatService;//客服聊天
    @Autowired
    private DictionaryService dictionaryService;//字典
    @Autowired
    private ForumService forumService;//论坛
    @Autowired
    private GuanliliuyService guanliliuyService;//每日体温
    @Autowired
    private NewsService newsService;//公告资讯
    @Autowired
    private YishengService yishengService;//医生
    @Autowired
    private YonghuService yonghuService;//用户
    @Autowired
    private ZhishiService zhishiService;//健康小知识
    @Autowired
    private ZhishiCollectionService zhishiCollectionService;//知识收藏
    @Autowired
    private ZhishiLiuyanService zhishiLiuyanService;//知识留言
    @Autowired
    private UsersService usersService;//管理员


    /**
    * 后端列表
    */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params, HttpServletRequest request){
        logger.debug("page方法:,,Controller:{},,params:{}",this.getClass().getName(),JSONObject.toJSONString(params));
        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永不会进入");
        else if("用户".equals(role))
            params.put("yonghuId",request.getSession().getAttribute("userId"));
        else if("医生".equals(role))
            params.put("yishengId",request.getSession().getAttribute("userId"));
        CommonUtil.checkMap(params);
        PageUtils page = yishengYuyueService.queryPage(params);

        //字典表数据转换
        List<YishengYuyueView> list =(List<YishengYuyueView>)page.getList();
        for(YishengYuyueView c:list){
            //修改对应字典表字段
            dictionaryService.dictionaryConvert(c, request);
        }
        return R.ok().put("data", page);
    }

    /**
    * 后端详情
    */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id, HttpServletRequest request){
        logger.debug("info方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        YishengYuyueEntity yishengYuyue = yishengYuyueService.selectById(id);
        if(yishengYuyue !=null){
            //entity转view
            YishengYuyueView view = new YishengYuyueView();
            BeanUtils.copyProperties( yishengYuyue , view );//把实体数据重构到view中
            //级联表 医生
            //级联表
            YishengEntity yisheng = yishengService.selectById(yishengYuyue.getYishengId());
            if(yisheng != null){
            BeanUtils.copyProperties( yisheng , view ,new String[]{ "id", "createTime", "insertTime", "updateTime", "yonghuId"
, "yishengId"});//把级联的数据添加到view中,并排除id和创建时间字段,当前表的级联注册表
            view.setYishengId(yisheng.getId());
            }
            //级联表 用户
            //级联表
            YonghuEntity yonghu = yonghuService.selectById(yishengYuyue.getYonghuId());
            if(yonghu != null){
            BeanUtils.copyProperties( yonghu , view ,new String[]{ "id", "createTime", "insertTime", "updateTime", "yonghuId"
, "yishengId"});//把级联的数据添加到view中,并排除id和创建时间字段,当前表的级联注册表
            view.setYonghuId(yonghu.getId());
            }
            //修改对应字典表字段
            dictionaryService.dictionaryConvert(view, request);
            return R.ok().put("data", view);
        }else {
            return R.error(511,"查不到数据");
        }

    }

    /**
    * 后端保存
    */
    @RequestMapping("/save")
    public R save(@RequestBody YishengYuyueEntity yishengYuyue, HttpServletRequest request){
        logger.debug("save方法:,,Controller:{},,yishengYuyue:{}",this.getClass().getName(),yishengYuyue.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永远不会进入");
        else if("用户".equals(role))
            yishengYuyue.setYonghuId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));
        else if("医生".equals(role))
            yishengYuyue.setYishengId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));

        Wrapper<YishengYuyueEntity> queryWrapper = new EntityWrapper<YishengYuyueEntity>()
            .eq("yisheng_id", yishengYuyue.getYishengId())
            .eq("yonghu_id", yishengYuyue.getYonghuId())
            .in("yisheng_yuyue_yesno_types", new Integer[]{1,2})
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        YishengYuyueEntity yishengYuyueEntity = yishengYuyueService.selectOne(queryWrapper);
        if(yishengYuyueEntity==null){
            yishengYuyue.setYishengYuyueYesnoTypes(1);
            yishengYuyue.setCreateTime(new Date());
            yishengYuyueService.insert(yishengYuyue);
            return R.ok();
        }else {
            if(yishengYuyueEntity.getYishengYuyueYesnoTypes()==1)
                return R.error(511,"有相同的待审核的数据");
            else if(yishengYuyueEntity.getYishengYuyueYesnoTypes()==2)
                return R.error(511,"有相同的审核通过的数据");
            else
                return R.error(511,"表中有相同数据");
        }
    }

    /**
    * 后端修改
    */
    @RequestMapping("/update")
    public R update(@RequestBody YishengYuyueEntity yishengYuyue, HttpServletRequest request) throws NoSuchFieldException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        logger.debug("update方法:,,Controller:{},,yishengYuyue:{}",this.getClass().getName(),yishengYuyue.toString());
        YishengYuyueEntity oldYishengYuyueEntity = yishengYuyueService.selectById(yishengYuyue.getId());//查询原先数据

        String role = String.valueOf(request.getSession().getAttribute("role"));
//        if(false)
//            return R.error(511,"永远不会进入");
//        else if("用户".equals(role))
//            yishengYuyue.setYonghuId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));
//        else if("医生".equals(role))
//            yishengYuyue.setYishengId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));

            yishengYuyueService.updateById(yishengYuyue);//根据id更新
            return R.ok();
    }


    /**
    * 审核
    */
    @RequestMapping("/shenhe")
    public R shenhe(@RequestBody YishengYuyueEntity yishengYuyueEntity, HttpServletRequest request){
        logger.debug("shenhe方法:,,Controller:{},,yishengYuyueEntity:{}",this.getClass().getName(),yishengYuyueEntity.toString());

        YishengYuyueEntity oldYishengYuyue = yishengYuyueService.selectById(yishengYuyueEntity.getId());//查询原先数据

//        if(yishengYuyueEntity.getYishengYuyueYesnoTypes() == 2){//通过
//            yishengYuyueEntity.setYishengYuyueTypes();
//        }else if(yishengYuyueEntity.getYishengYuyueYesnoTypes() == 3){//拒绝
//            yishengYuyueEntity.setYishengYuyueTypes();
//        }
        yishengYuyueService.updateById(yishengYuyueEntity);//审核

        return R.ok();
    }

    /**
    * 删除
    */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids, HttpServletRequest request){
        logger.debug("delete:,,Controller:{},,ids:{}",this.getClass().getName(),ids.toString());
        List<YishengYuyueEntity> oldYishengYuyueList =yishengYuyueService.selectBatchIds(Arrays.asList(ids));//要删除的数据
        yishengYuyueService.deleteBatchIds(Arrays.asList(ids));

        return R.ok();
    }


    /**
     * 批量上传
     */
    @RequestMapping("/batchInsert")
    public R save( String fileName, HttpServletRequest request){
        logger.debug("batchInsert方法:,,Controller:{},,fileName:{}",this.getClass().getName(),fileName);
        Integer yonghuId = Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId")));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //.eq("time", new SimpleDateFormat("yyyy-MM-dd").format(new Date()))
        try {
            List<YishengYuyueEntity> yishengYuyueList = new ArrayList<>();//上传的东西
            Map<String, List<String>> seachFields= new HashMap<>();//要查询的字段
            Date date = new Date();
            int lastIndexOf = fileName.lastIndexOf(".");
            if(lastIndexOf == -1){
                return R.error(511,"该文件没有后缀");
            }else{
                String suffix = fileName.substring(lastIndexOf);
                if(!".xls".equals(suffix)){
                    return R.error(511,"只支持后缀为xls的excel文件");
                }else{
                    URL resource = this.getClass().getClassLoader().getResource("static/upload/" + fileName);//获取文件路径
                    File file = new File(resource.getFile());
                    if(!file.exists()){
                        return R.error(511,"找不到上传文件，请联系管理员");
                    }else{
                        List<List<String>> dataList = PoiUtil.poiImport(file.getPath());//读取xls文件
                        dataList.remove(0);//删除第一行，因为第一行是提示
                        for(List<String> data:dataList){
                            //循环
                            YishengYuyueEntity yishengYuyueEntity = new YishengYuyueEntity();
//                            yishengYuyueEntity.setYishengId(Integer.valueOf(data.get(0)));   //医生 要改的
//                            yishengYuyueEntity.setYonghuId(Integer.valueOf(data.get(0)));   //用户 要改的
//                            yishengYuyueEntity.setYishengYuyueText(data.get(0));                    //预约理由 要改的
//                            yishengYuyueEntity.setYishengYuyueTime(sdf.parse(data.get(0)));          //预约时间 要改的
//                            yishengYuyueEntity.setYishengYuyueYesnoTypes(Integer.valueOf(data.get(0)));   //预约状态 要改的
//                            yishengYuyueEntity.setYishengYuyueYesnoText(data.get(0));                    //治疗方案 要改的
//                            yishengYuyueEntity.setCreateTime(date);//时间
                            yishengYuyueList.add(yishengYuyueEntity);


                            //把要查询是否重复的字段放入map中
                        }

                        //查询是否重复
                        yishengYuyueService.insertBatch(yishengYuyueList);
                        return R.ok();
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            return R.error(511,"批量插入数据异常，请联系管理员");
        }
    }




    /**
    * 前端列表
    */
    @IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params, HttpServletRequest request){
        logger.debug("list方法:,,Controller:{},,params:{}",this.getClass().getName(),JSONObject.toJSONString(params));

        CommonUtil.checkMap(params);
        PageUtils page = yishengYuyueService.queryPage(params);

        //字典表数据转换
        List<YishengYuyueView> list =(List<YishengYuyueView>)page.getList();
        for(YishengYuyueView c:list)
            dictionaryService.dictionaryConvert(c, request); //修改对应字典表字段

        return R.ok().put("data", page);
    }

    /**
    * 前端详情
    */
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id, HttpServletRequest request){
        logger.debug("detail方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        YishengYuyueEntity yishengYuyue = yishengYuyueService.selectById(id);
            if(yishengYuyue !=null){


                //entity转view
                YishengYuyueView view = new YishengYuyueView();
                BeanUtils.copyProperties( yishengYuyue , view );//把实体数据重构到view中

                //级联表
                    YishengEntity yisheng = yishengService.selectById(yishengYuyue.getYishengId());
                if(yisheng != null){
                    BeanUtils.copyProperties( yisheng , view ,new String[]{ "id", "createDate"});//把级联的数据添加到view中,并排除id和创建时间字段
                    view.setYishengId(yisheng.getId());
                }
                //级联表
                    YonghuEntity yonghu = yonghuService.selectById(yishengYuyue.getYonghuId());
                if(yonghu != null){
                    BeanUtils.copyProperties( yonghu , view ,new String[]{ "id", "createDate"});//把级联的数据添加到view中,并排除id和创建时间字段
                    view.setYonghuId(yonghu.getId());
                }
                //修改对应字典表字段
                dictionaryService.dictionaryConvert(view, request);
                return R.ok().put("data", view);
            }else {
                return R.error(511,"查不到数据");
            }
    }


    /**
    * 前端保存
    */
    @RequestMapping("/add")
    public R add(@RequestBody YishengYuyueEntity yishengYuyue, HttpServletRequest request){
        logger.debug("add方法:,,Controller:{},,yishengYuyue:{}",this.getClass().getName(),yishengYuyue.toString());
        Wrapper<YishengYuyueEntity> queryWrapper = new EntityWrapper<YishengYuyueEntity>()
            .eq("yisheng_id", yishengYuyue.getYishengId())
            .eq("yonghu_id", yishengYuyue.getYonghuId())
            .eq("yisheng_yuyue_text", yishengYuyue.getYishengYuyueText())
            .in("yisheng_yuyue_yesno_types", new Integer[]{1,2})
            .eq("yisheng_yuyue_yesno_text", yishengYuyue.getYishengYuyueYesnoText())
//            .notIn("yisheng_yuyue_types", new Integer[]{102})
            ;
        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        YishengYuyueEntity yishengYuyueEntity = yishengYuyueService.selectOne(queryWrapper);
        if(yishengYuyueEntity==null){
            yishengYuyue.setYishengYuyueYesnoTypes(1);
            yishengYuyue.setCreateTime(new Date());
        yishengYuyueService.insert(yishengYuyue);

            return R.ok();
        }else {
            if(yishengYuyueEntity.getYishengYuyueYesnoTypes()==1)
                return R.error(511,"有相同的待审核的数据");
            else if(yishengYuyueEntity.getYishengYuyueYesnoTypes()==2)
                return R.error(511,"有相同的审核通过的数据");
            else
                return R.error(511,"表中有相同数据");
        }
    }

}

