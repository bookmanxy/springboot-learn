package cn.faceland.springbootdataserver.controller;


import cn.faceland.springbootdataserver.service.CoreService;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author watermelon
 * @since 2019-11-04
 */
@Slf4j
@RestController
@RequestMapping("/test")
public class CoreController {
    @Autowired
    private CoreService coreService;

    @PostMapping("/execute")
    public Object execute( @RequestBody @Valid JSONObject param) {
        return coreService.execute(param);
    }
}
