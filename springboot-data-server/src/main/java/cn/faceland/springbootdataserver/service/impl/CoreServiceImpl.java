package cn.faceland.springbootdataserver.service.impl;


import cn.faceland.springbootdataserver.common.webDev.DbExecuteTra;
import cn.faceland.springbootdataserver.common.webDev.DbQuery;
import cn.faceland.springbootdataserver.service.CoreService;
import cn.faceland.springbootdataserver.vo.ColumnVo;
import cn.faceland.springbootdataserver.vo.InsertVo;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author watermelon
 * @Date 2019/10/21 0021.
 */
@Service
public class CoreServiceImpl implements CoreService {

    @Override
    public Object insert(JSONObject param) {
        //table,method,methodExt,param(JSONArray)
        String table = param.getString("table");
        Integer methodExt = param.getInteger("methodExt");
        List<ColumnVo> list = JSONArray.parseArray(param.getString("param"), ColumnVo.class);

        InsertVo insertVo = insertParamToVo(list);
        // TODO: 2019/11/28 判断sb1 和 sb2都不能为空

        //methodExt == 1 all  ； 0 选择性，不为null或者空
        DbExecuteTra dbExecuteTra = new DbExecuteTra(null);
        dbExecuteTra.beginTra();
        int i = dbExecuteTra.exe("insert into user() values()");
        if(i == 1){
            DbQuery db = dbExecuteTra.getDbQuery("select LAST_INSERT_ID() as lastId");
            if(db.row() > 0){
                return db.get(0,"lastId");
            }
        }
        if(!dbExecuteTra.commitTra()){
            dbExecuteTra.rollbackTra();
        }
        return null;
    }

    @Override
    public Object update(JSONObject param) {
        return null;
    }

    @Override
    public Object delete(JSONObject param) {
        return null;
    }

    @Override
    public Object search(JSONObject param) {
        return null;
    }

    @Override
    public InsertVo insertParamToVo(List<ColumnVo> list) {
        InsertVo insertVo = new InsertVo();
        StringBuffer sb1 = new StringBuffer();
        StringBuffer sb2 = new StringBuffer();

        for(ColumnVo columnVo : list){
            sb1.append(columnVo.getName()).append(",");
            sb2.append("'").append(columnVo.getValue()).append("',");
        }
        String s1 = sb1.toString();
        if(s1.length() > 0){
            s1 = s1.substring(0, s1.length()-1);
        }

        String s2 = sb2.toString();
        if(s2.length() > 0){
            s2 = s2.substring(0, s2.length()-1);
        }
        insertVo.setColumStr(s1);
        insertVo.setValueStr(s2);

        return insertVo;
    }

    @Override
    public Object execute(JSONObject param) {
        // TODO: 2019/11/28 校验json并获取 ：table,method,methodExt,param(JSONArray)
        Integer method = param.getInteger("method");
        switch (method){
            case 1:
                return insert(param);
            case 2:
                return update(param);
            case 3:
                return delete(param);
            case 4:
                return search(param);
            default:
                return null;
        }
    }


}