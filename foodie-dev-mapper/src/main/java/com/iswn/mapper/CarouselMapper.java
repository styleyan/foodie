package com.iswn.mapper;

import com.iswn.pojo.Carousel;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarouselMapper {
    List<Carousel> selectAll(Integer isShow);
}