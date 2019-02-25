package com.jack.springbootdubbocontrol.util;

import com.jack.bean.Moview;
import com.jack.crawl.bean.Links;
import com.jack.crawl.bean.Page;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.*;

public class PageParserUtil {

    /**
     * 根据选择器获取页面中的元素为多个
     *
     * @return
     */
    public static Elements getElements(Page page, String cssSelector) {
        return page.getDoc().select(cssSelector);
    }

    public static Element getElement(Page page, String cssSelector, int index) {
        Elements element = getElements(page, cssSelector);
        int realIndex = index;
        if (index < 0) {
            realIndex = element.size() + index;
        }
        return element.get(realIndex);
    }

    /**
     * 获取满足选择器的元素中的链接 选择器cssSelector必须定位到具体的超链接
     * 例如我们想抽取id为content的div中的所有超链接，这里
     * 就要将cssSelector定义为div[id=content] a
     * 放入set 中 防止重复；
     *
     * @param cssSelector
     * @return
     */
    public static List<Moview> getLinks(Page page, String cssSelector) {
        Set<String> urlSet = new HashSet<>();
        List<Moview> moviews = new ArrayList<>();
        Elements es = getElements(page, cssSelector);
        Iterator iterator = es.iterator();
        while (iterator.hasNext()) {
            Moview moview = new Moview();
            Element element = (Element) iterator.next();
            if (element.hasAttr("href")) {
                String url = element.attr("abs:href");
                /**
                 * 使用set集合去掉重复的url
                 */
                if (!urlSet.add(url)) {
                    continue;
                }
                moview.type = 3;
                String text = element.text();
                if(null!=text&&!"".equals(text)){
                    moview.name = text;

                    if (url.contains("vod-detail-id")) {
                        moview.url = url;
                        moview.type = 1;
                    } else if (url.contains("vod-search-pg-1")) {
                        moview.url = url;
                        moview.type = 2;
                        Links.addUnvisitedUrlQueue(url);
                    } else {
                        Links.addUnvisitedUrlQueue(url);
                        continue;
                    }
                }else {
                    continue;
                }

                /*if (element.hasAttr("img")) {
                    String alt = element.attr("alt");
                    if (null != alt && !"".equals(alt)) {
                        alt = alt.replace("点击播放", "");
                        System.out.println(alt);
                        moview.name = alt;
                    }
                } else {
                    String title = element.attr("title");
                    if (null != title && !"".equals(title)) {
                        moview.name = title;
                    }
                }*/

            }
            moviews.add(moview);
        }
        return moviews;
    }


    /**
     * 获取网页中满足指定css选择器的所有元素的指定属性的集合
     * 例如通过getAttrs("img[src]","abs:src")可获取网页中所有图片的链接
     *
     * @param cssSelector
     * @param attrName
     * @return
     */
    public static ArrayList<String> getAttrs(Page page, String cssSelector, String attrName) {
        ArrayList<String> result = new ArrayList<String>();
        Elements eles = getElements(page, cssSelector);
        for (Element ele : eles) {
            if (ele.hasAttr(attrName)) {
                result.add(ele.attr(attrName));
            }
        }
        return result;
    }
}
