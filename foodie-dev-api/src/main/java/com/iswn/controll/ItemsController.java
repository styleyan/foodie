package com.iswn.controll;

import com.iswn.exception.http.RequestBadException;
import com.iswn.pojo.Items;
import com.iswn.pojo.ItemsImg;
import com.iswn.pojo.ItemsParam;
import com.iswn.pojo.ItemsSpec;
import com.iswn.service.ItemService;
import com.iswn.utils.JsonResult;
import com.iswn.utils.PagedGridResult;
import com.iswn.vo.ItemInfoVO;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/items")
public class ItemsController extends BaseController {
    final static Logger logger = LoggerFactory.getLogger(ItemsController.class);

    @Autowired
    private ItemService itemService;

    /**
     * 查询商品详情  bingan-1002
     * @return
     */
    @GetMapping("/info/{itemId}")
    public JsonResult itemInfo(@PathVariable("itemId") String itemId) {
        if (StringUtils.isBlank(itemId)) {
            throw new RequestBadException("商品id不能为空");
        }

        Items items = itemService.queryItemById(itemId);
        ItemsParam itemsParam = itemService.queryItemParam(itemId);
        List<ItemsImg> imgList = itemService.queryItemImgList(itemId);
        List<ItemsSpec> specList = itemService.queryItemSpecList(itemId);

        ItemInfoVO itemInfoVO = new ItemInfoVO();

        itemInfoVO.setItem(items);
        itemInfoVO.setItemImgList(imgList);
        itemInfoVO.setItemSpecList(specList);
        itemInfoVO.setItemParam(itemsParam);

        return JsonResult.success(itemInfoVO);
    }

    /**
     * 查询商品评价等级
     */
    @GetMapping("/commentLevel")
    public JsonResult commentLevel(@RequestParam("itemId") String itemId) {
        if (StringUtils.isBlank(itemId)) {
            throw new RequestBadException("商品参数不能为空");
        }

        return JsonResult.success(itemService.queryCommentCounts(itemId));
    }

    /**
     * 查询商品评论
     * @return
     */
    @GetMapping("/comments")
    public JsonResult comments(
            @RequestParam String itemId,
            @RequestParam Integer level,
            @RequestParam Integer page,
            @RequestParam(value = "pageSize") Integer pageSize) {
        if (StringUtils.isBlank(itemId)) {
            throw new RequestBadException("商品id不能为空");
        }

        if (page == null) {
            page = 1;
        }

        if (pageSize == null) {
            pageSize = COMMENT_PAGE_SIZE;
        }

        PagedGridResult grid = itemService.queryPagedComments(itemId, level, page, pageSize);

        return JsonResult.success(grid);
    }

    @GetMapping("/search")
    public JsonResult searchItems(
            @RequestParam("keywords") String keywords,
            @RequestParam("sort") String sort,
            @RequestParam("page") Integer page,
            @RequestParam("pageSize") Integer pageSize
            ) {

        if (StringUtils.isBlank(keywords)) {
            throw new RequestBadException("搜索商品不能为空");
        }
        if (page == null) {
            page = 1;
        }

        if (pageSize == null) {
            pageSize = PAGE_SIZE;
        }

        PagedGridResult result = itemService.searchItems(keywords, sort, page, pageSize);
        return JsonResult.success(result);
    }
}
