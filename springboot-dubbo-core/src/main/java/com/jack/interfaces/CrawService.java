package com.jack.interfaces;

import com.github.pagehelper.PageInfo;
import com.jack.bean.Moview;

import java.util.List;
import java.util.Map;

public interface CrawService {
    void goCraw(Map<String,Object> map);

    PageInfo<Moview> serachMoview(Integer pageNum,Integer pageSize,String name);
}
