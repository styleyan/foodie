package com.iswn.controll;

import com.iswn.enums.YesOrNoEnum;
import com.iswn.exception.http.RequestBadException;
import com.iswn.service.CarouselService;
import com.iswn.service.CategoryService;
import com.iswn.utils.JsonResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/index")
public class IndexController {
    final static Logger logger = LoggerFactory.getLogger(IndexController.class);

    @Autowired
    private CarouselService carouselService;
    @Autowired
    private CategoryService categoryService;

    /**
     * 查询所有列表
     * @return
     */
    @GetMapping("carousel")
    public JsonResult carousel() {
        logger.info("查询 carouse 结果");
        return JsonResult.success(carouselService.queryAll(YesOrNoEnum.YES.getType()));
    }

    /**
     * 首页分类展示需求
     * 1. 第一次刷新主页查询大分类, 渲染展示到首页
     * 2. 如果鼠标上移到大分类，则加载其子分类的内容，如果已经存在子分类，则不需要加载（赖加载）
     * @return
     */
    @GetMapping("/cats")
    public JsonResult cats() {
        logger.info("查询 cats 结果");
        return JsonResult.success(categoryService.queryAllRootLevelCat());
    }

    /**
     * 查询二级分类数据
     * @return
     */
    @GetMapping("/subCat/{rootCatId}")
    public JsonResult subCat(@PathVariable Integer rootCatId) {
        logger.info("查询 subCat 结果");
        if (rootCatId == null) {
            throw new RequestBadException("分类不存在");
        }
        return JsonResult.success(categoryService.getSubCatList(rootCatId));
    }

    /**
     * 查询每个一级分类下的最新6条商品数据
     * @return
     */
    @GetMapping("/sixNewItems/{rootCatId}")
    public JsonResult sixNewItems(@PathVariable("rootCatId") Integer rootCatId) {
        if (rootCatId == null) {
            throw new RequestBadException("一级分类id不能为空");
        }

        return JsonResult.success(categoryService.getSixNewItemsLazy(rootCatId));
    }
}
