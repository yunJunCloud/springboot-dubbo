package com.jack.springbootdubbocontrol.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jack.bean.Moview;
import com.jack.crawl.bean.Links;
import com.jack.interfaces.CrawService;
import com.jack.springbootdubbocontrol.model.IMoviewModel;
import com.jack.springbootdubbocontrol.thread.AsyncConfig;
import com.jack.springbootdubbocontrol.thread.CrawThread;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Service
public class CrawServiceImpl implements CrawService {

    @Autowired
    private IMoviewModel iMoviewModel;

    @Autowired
    private CrawThread crawThread;
    @Override
    public void goCraw(Map<String, Object> map) {
        String[] args = new String[]{(String) map.get("seed")};
        crawling(args);
    }

    @Override
    public PageInfo<Moview> serachMoview(Integer pageNum,Integer pageSize,String name) {
        PageHelper.startPage(pageNum,pageSize);
        List<Moview> moviews = new ArrayList<>();
        if(name!=null) {
            moviews = iMoviewModel.searchMoviesByName(name);
        }else {
            moviews = iMoviewModel.searchMovies();
        }
        PageInfo<Moview> moviewPageInfo = new PageInfo<>(moviews);
        return moviewPageInfo;
    }




    /**
     * 使用种子初始化 URL 队列
     *
     * @param seeds 种子 URL
     * @return
     */
    private void initCrawlerWithSeeds(String[] seeds) {
        for (int i = 0; i < seeds.length; i++) {
            Links.addUnvisitedUrlQueue(seeds[i]);
        }
    }

    public void crawling(String[] seeds) {

        //初始化 URL 队列
        initCrawlerWithSeeds(seeds);
        /*CrawThread crawThread = new CrawThread();
        crawThread.setDaemon(true);
        crawThread.start();*/
        crawThread.crawThread(iMoviewModel);

        //定义过滤器，提取以 http://www.baidu.com 开头的链接
    /*    LinkFilter filter = new LinkFilter() {
            public boolean accept(String url) {
                if (url.startsWith("http://aaccy.com"))
                    return true;
                else
                    return false;
            }
        };*/
    }
}
