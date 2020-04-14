package com.iswn.controll;

import com.iswn.exception.http.RequestBadException;
import com.iswn.pojo.Items;
import com.iswn.pojo.ItemsImg;
import com.iswn.pojo.ItemsParam;
import com.iswn.pojo.ItemsSpec;
import com.iswn.service.ItemService;
import com.iswn.utils.JsonResult;
import com.iswn.vo.ItemInfoVO;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/items")
public class ItemsController {
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
            new RequestBadException("商品id不能为空");
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
}
