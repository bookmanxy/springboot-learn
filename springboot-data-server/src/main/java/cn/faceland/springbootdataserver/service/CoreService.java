package cn.faceland.springbootdataserver.service;


import cn.faceland.springbootdataserver.vo.ColumnVo;
import cn.faceland.springbootdataserver.vo.InsertVo;
import com.alibaba.fastjson.JSONObject;

import java.util.List;

/**
 * @Author watermelon
 * @Date 2019/10/21 0021.
 */
public interface CoreService {
    /**
     * 核心入口
     *
     * @param param
     * @return
     */
    Object execute(JSONObject param);

    /**
     * 执行插入操作
     *
     * @param param
     * @return
     */
    Object insert(JSONObject param);

    Object update(JSONObject param);
    Object delete(JSONObject param);
    Object search(JSONObject param);

    /**
     * 新增的参数变实体类数组
     *
     * @return
     */
    InsertVo insertParamToVo(List<ColumnVo> list);
}