package com.jack.springbootdubbofront.consumer;

import com.alibaba.dubbo.config.annotation.Reference;
import com.github.pagehelper.PageInfo;
import com.jack.bean.Moview;
import com.jack.interfaces.CrawService;
import com.jack.result.Result;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/moview")
public class MovieController {

    @Reference
    private CrawService crawService;

    @RequestMapping(value = "/craw",method = RequestMethod.POST)
    @ResponseBody
    public Result goCrawMoview(@RequestBody Map<String,Object> params){
        Result result = new Result();
        result.setCode("200");
        result.setMessage("成功");
        crawService.goCraw(params);
        return result;
    }

    @GetMapping(value = "/searchMoview")
    @ResponseBody
    public Result searchMoview(@RequestParam(value = "name",required = false) String name){
        Result result = new Result();
        Map<String,Object> map = new HashMap<>();
        String message = null;
        try {
            PageInfo<Moview> moviews = crawService.serachMoview(1,10,name);
            result.setCode("200");
            map.put("moviews",moviews);
            message = "成功";

        }catch (Exception e){
            e.printStackTrace();
            result.setCode("500");
            result.setMessage("失败");
        }
        result.setMessage(message);
        result.setMap(map);
        return result;
    }
}
