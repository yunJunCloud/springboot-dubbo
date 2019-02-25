package com.jack.springbootdubbocontrol.thread;

import com.jack.bean.Moview;
import com.jack.crawl.bean.Links;
import com.jack.crawl.bean.Page;
import com.jack.crawl.utils.HttpUtils;
import com.jack.springbootdubbocontrol.util.PageParserUtil;
import com.jack.springbootdubbocontrol.model.IMoviewModel;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class CrawThread {

    @Async
    public void crawThread(IMoviewModel iMoviewModel){
        try{
        //循环条件：待抓取的链接不空且抓取的网页不多于 1000
        while (!Links.unVisitedUrlQueueIsEmpty() && Links.getVisitedUrlNum() <= 10000) {
            //先从待访问的序列中取出第一个；
            String visitUrl = (String) Links.removeHeadOfUnVisitedUrlQueue();
            if (visitUrl == null) {
                continue;
            }

            //根据URL得到page;
            Map<Object, Object> map = HttpUtils.sendGet(visitUrl);
            String content = (String) map.get("content");
            byte[] bytes = (byte[]) content.getBytes();
            String contentType = (String) map.get("contentType");
            Page page = new Page(bytes, visitUrl, contentType);

            List<Moview> moviews = PageParserUtil.getLinks(page, "a");
            /*if (!hset.isEmpty()) {
                System.out.println("下面将打印所有a标签： ");
                for (String str : hset) {
                    System.out.println(str);
                }
            }
            FileTool.saveToLocal(page);*/
            if(null!=moviews&&moviews.size()>0) {
                for (Moview moview : moviews) {
                    if (moview.name!=null&&!moview.name.equals("")) {
                        Moview moview1 =iMoviewModel.serachMoviesByUrl(moview.url);
                        if(null!=moview1){
                            continue;
                        }
                        iMoviewModel.addMoview(moview);
                    }
                }
            }
            Links.addVisitedUrlSet(visitUrl);
        }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
