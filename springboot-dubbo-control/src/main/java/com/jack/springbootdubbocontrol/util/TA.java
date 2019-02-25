package com.jack.springbootdubbocontrol.util;

import com.jack.bean.Moview;
import com.jack.crawl.bean.Page;
import com.jack.crawl.utils.HttpUtils;

import java.util.List;
import java.util.Map;

public class TA {
    public static void main(String[] args){
        //根据URL得到page;
        Map<Object, Object> map = HttpUtils.sendGet("http://aaccy.com/");
        String content = (String) map.get("content");
        byte[] bytes = (byte[]) content.getBytes();
        String contentType = (String) map.get("contentType");
        Page page = new Page(bytes, "http://aaccy.com/", contentType);

        List<Moview> moviews = PageParserUtil.getLinks(page, "a");
    }
}
