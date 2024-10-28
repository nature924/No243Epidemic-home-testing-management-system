
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
 * 健康小知识
 * 后端接口
 * @author
 * @email
*/
@RestController
@Controller
@RequestMapping("/zhishi")
public class ZhishiController {
    private static final Logger logger = LoggerFactory.getLogger(ZhishiController.class);

    private static final String TABLE_NAME = "zhishi";

    @Autowired
    private ZhishiService zhishiService;


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
    private YishengYuyueService yishengYuyueService;//医生报名
    @Autowired
    private YonghuService yonghuService;//用户
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
        params.put("zhishiDeleteStart",1);params.put("zhishiDeleteEnd",1);
        CommonUtil.checkMap(params);
        PageUtils page = zhishiService.queryPage(params);

        //字典表数据转换
        List<ZhishiView> list =(List<ZhishiView>)page.getList();
        for(ZhishiView c:list){
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
        ZhishiEntity zhishi = zhishiService.selectById(id);
        if(zhishi !=null){
            //entity转view
            ZhishiView view = new ZhishiView();
            BeanUtils.copyProperties( zhishi , view );//把实体数据重构到view中
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
    public R save(@RequestBody ZhishiEntity zhishi, HttpServletRequest request){
        logger.debug("save方法:,,Controller:{},,zhishi:{}",this.getClass().getName(),zhishi.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永远不会进入");

        Wrapper<ZhishiEntity> queryWrapper = new EntityWrapper<ZhishiEntity>()
            .eq("zhishi_name", zhishi.getZhishiName())
            .eq("zhishi_video", zhishi.getZhishiVideo())
            .eq("zan_number", zhishi.getZanNumber())
            .eq("cai_number", zhishi.getCaiNumber())
            .eq("zhishi_types", zhishi.getZhishiTypes())
            .eq("shangxia_types", zhishi.getShangxiaTypes())
            .eq("zhishi_delete", 1)
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        ZhishiEntity zhishiEntity = zhishiService.selectOne(queryWrapper);
        if(zhishiEntity==null){
            zhishi.setZhishiClicknum(1);
            zhishi.setShangxiaTypes(1);
            zhishi.setZhishiDelete(1);
            zhishi.setInsertTime(new Date());
            zhishi.setCreateTime(new Date());
            zhishiService.insert(zhishi);
            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }

    /**
    * 后端修改
    */
    @RequestMapping("/update")
    public R update(@RequestBody ZhishiEntity zhishi, HttpServletRequest request) throws NoSuchFieldException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        logger.debug("update方法:,,Controller:{},,zhishi:{}",this.getClass().getName(),zhishi.toString());
        ZhishiEntity oldZhishiEntity = zhishiService.selectById(zhishi.getId());//查询原先数据

        String role = String.valueOf(request.getSession().getAttribute("role"));
//        if(false)
//            return R.error(511,"永远不会进入");
        if("".equals(zhishi.getZhishiPhoto()) || "null".equals(zhishi.getZhishiPhoto())){
                zhishi.setZhishiPhoto(null);
        }
        if("".equals(zhishi.getZhishiVideo()) || "null".equals(zhishi.getZhishiVideo())){
                zhishi.setZhishiVideo(null);
        }

            zhishiService.updateById(zhishi);//根据id更新
            return R.ok();
    }



    /**
    * 删除
    */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids, HttpServletRequest request){
        logger.debug("delete:,,Controller:{},,ids:{}",this.getClass().getName(),ids.toString());
        List<ZhishiEntity> oldZhishiList =zhishiService.selectBatchIds(Arrays.asList(ids));//要删除的数据
        ArrayList<ZhishiEntity> list = new ArrayList<>();
        for(Integer id:ids){
            ZhishiEntity zhishiEntity = new ZhishiEntity();
            zhishiEntity.setId(id);
            zhishiEntity.setZhishiDelete(2);
            list.add(zhishiEntity);
        }
        if(list != null && list.size() >0){
            zhishiService.updateBatchById(list);
        }

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
            List<ZhishiEntity> zhishiList = new ArrayList<>();//上传的东西
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
                            ZhishiEntity zhishiEntity = new ZhishiEntity();
//                            zhishiEntity.setZhishiName(data.get(0));                    //知识标题 要改的
//                            zhishiEntity.setZhishiPhoto("");//详情和图片
//                            zhishiEntity.setZhishiVideo(data.get(0));                    //知识视频 要改的
//                            zhishiEntity.setZanNumber(Integer.valueOf(data.get(0)));   //赞 要改的
//                            zhishiEntity.setCaiNumber(Integer.valueOf(data.get(0)));   //踩 要改的
//                            zhishiEntity.setZhishiTypes(Integer.valueOf(data.get(0)));   //知识类型 要改的
//                            zhishiEntity.setZhishiClicknum(Integer.valueOf(data.get(0)));   //知识热度 要改的
//                            zhishiEntity.setZhishiContent("");//详情和图片
//                            zhishiEntity.setShangxiaTypes(Integer.valueOf(data.get(0)));   //是否上架 要改的
//                            zhishiEntity.setZhishiDelete(1);//逻辑删除字段
//                            zhishiEntity.setInsertTime(date);//时间
//                            zhishiEntity.setCreateTime(date);//时间
                            zhishiList.add(zhishiEntity);


                            //把要查询是否重复的字段放入map中
                        }

                        //查询是否重复
                        zhishiService.insertBatch(zhishiList);
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
    * 个性推荐
    */
    @IgnoreAuth
    @RequestMapping("/gexingtuijian")
    public R gexingtuijian(@RequestParam Map<String, Object> params, HttpServletRequest request){
        logger.debug("gexingtuijian方法:,,Controller:{},,params:{}",this.getClass().getName(),JSONObject.toJSONString(params));
        CommonUtil.checkMap(params);
        List<ZhishiView> returnZhishiViewList = new ArrayList<>();

        //查看收藏
        Map<String, Object> params1 = new HashMap<>(params);params1.put("sort","id");params1.put("yonghuId",request.getSession().getAttribute("userId"));
        PageUtils pageUtils = zhishiCollectionService.queryPage(params1);
        List<ZhishiCollectionView> collectionViewsList =(List<ZhishiCollectionView>)pageUtils.getList();
        Map<Integer,Integer> typeMap=new HashMap<>();//购买的类型list
        for(ZhishiCollectionView collectionView:collectionViewsList){
            Integer zhishiTypes = collectionView.getZhishiTypes();
            if(typeMap.containsKey(zhishiTypes)){
                typeMap.put(zhishiTypes,typeMap.get(zhishiTypes)+1);
            }else{
                typeMap.put(zhishiTypes,1);
            }
        }
        List<Integer> typeList = new ArrayList<>();//排序后的有序的类型 按最多到最少
        typeMap.entrySet().stream().sorted((o1, o2) -> o2.getValue() - o1.getValue()).forEach(e -> typeList.add(e.getKey()));//排序
        Integer limit = Integer.valueOf(String.valueOf(params.get("limit")));
        for(Integer type:typeList){
            Map<String, Object> params2 = new HashMap<>(params);params2.put("zhishiTypes",type);
            PageUtils pageUtils1 = zhishiService.queryPage(params2);
            List<ZhishiView> zhishiViewList =(List<ZhishiView>)pageUtils1.getList();
            returnZhishiViewList.addAll(zhishiViewList);
            if(returnZhishiViewList.size()>= limit) break;//返回的推荐数量大于要的数量 跳出循环
        }
        //正常查询出来商品,用于补全推荐缺少的数据
        PageUtils page = zhishiService.queryPage(params);
        if(returnZhishiViewList.size()<limit){//返回数量还是小于要求数量
            int toAddNum = limit - returnZhishiViewList.size();//要添加的数量
            List<ZhishiView> zhishiViewList =(List<ZhishiView>)page.getList();
            for(ZhishiView zhishiView:zhishiViewList){
                Boolean addFlag = true;
                for(ZhishiView returnZhishiView:returnZhishiViewList){
                    if(returnZhishiView.getId().intValue() ==zhishiView.getId().intValue()) addFlag=false;//返回的数据中已存在此商品
                }
                if(addFlag){
                    toAddNum=toAddNum-1;
                    returnZhishiViewList.add(zhishiView);
                    if(toAddNum==0) break;//够数量了
                }
            }
        }else {
            returnZhishiViewList = returnZhishiViewList.subList(0, limit);
        }

        for(ZhishiView c:returnZhishiViewList)
            dictionaryService.dictionaryConvert(c, request);
        page.setList(returnZhishiViewList);
        return R.ok().put("data", page);
    }

    /**
    * 前端列表
    */
    @IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params, HttpServletRequest request){
        logger.debug("list方法:,,Controller:{},,params:{}",this.getClass().getName(),JSONObject.toJSONString(params));

        CommonUtil.checkMap(params);
        PageUtils page = zhishiService.queryPage(params);

        //字典表数据转换
        List<ZhishiView> list =(List<ZhishiView>)page.getList();
        for(ZhishiView c:list)
            dictionaryService.dictionaryConvert(c, request); //修改对应字典表字段

        return R.ok().put("data", page);
    }

    /**
    * 前端详情
    */
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id, HttpServletRequest request){
        logger.debug("detail方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        ZhishiEntity zhishi = zhishiService.selectById(id);
            if(zhishi !=null){

                //点击数量加1
                zhishi.setZhishiClicknum(zhishi.getZhishiClicknum()+1);
                zhishiService.updateById(zhishi);

                //entity转view
                ZhishiView view = new ZhishiView();
                BeanUtils.copyProperties( zhishi , view );//把实体数据重构到view中

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
    public R add(@RequestBody ZhishiEntity zhishi, HttpServletRequest request){
        logger.debug("add方法:,,Controller:{},,zhishi:{}",this.getClass().getName(),zhishi.toString());
        Wrapper<ZhishiEntity> queryWrapper = new EntityWrapper<ZhishiEntity>()
            .eq("zhishi_name", zhishi.getZhishiName())
            .eq("zhishi_video", zhishi.getZhishiVideo())
            .eq("zan_number", zhishi.getZanNumber())
            .eq("cai_number", zhishi.getCaiNumber())
            .eq("zhishi_types", zhishi.getZhishiTypes())
            .eq("zhishi_clicknum", zhishi.getZhishiClicknum())
            .eq("shangxia_types", zhishi.getShangxiaTypes())
            .eq("zhishi_delete", zhishi.getZhishiDelete())
//            .notIn("zhishi_types", new Integer[]{102})
            ;
        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        ZhishiEntity zhishiEntity = zhishiService.selectOne(queryWrapper);
        if(zhishiEntity==null){
                zhishi.setZanNumber(1);
                zhishi.setCaiNumber(1);
            zhishi.setZhishiClicknum(1);
            zhishi.setZhishiDelete(1);
            zhishi.setInsertTime(new Date());
            zhishi.setCreateTime(new Date());
        zhishiService.insert(zhishi);

            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }

}

