package com.jack.springbootdubbocontrol.model;

import com.jack.bean.Moview;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;

@Mapper
public interface IMoviewModel {
    @SelectProvider(type = CategoryDynaSqlProvider.class,method = "addMoview")
    void addMoview(Moview moview);

    @SelectProvider(type = CategoryDynaSqlProvider.class,method = "serachMovieByName")
    List<Moview> searchMoviesByName(String name);

    @SelectProvider(type = CategoryDynaSqlProvider.class,method = "serachMovieByUrl")
    Moview serachMoviesByUrl(String url);

    @SelectProvider(type = CategoryDynaSqlProvider.class,method = "serachMovie")
    List<Moview> searchMovies();
}
