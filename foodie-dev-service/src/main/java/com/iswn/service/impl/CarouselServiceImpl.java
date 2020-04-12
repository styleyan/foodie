package com.iswn.service.impl;

import com.iswn.mapper.CarouselMapper;
import com.iswn.pojo.Carousel;
import com.iswn.service.CarouselService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CarouselServiceImpl implements CarouselService {
    @Autowired
    private CarouselMapper carouselMapper;

    @Override
    public List<Carousel> queryAll(Integer isShow) {
        List<Carousel> result = carouselMapper.selectAll(isShow);

        if (result == null) {
            result = new ArrayList<>();
        }
        return result;
    }
}
