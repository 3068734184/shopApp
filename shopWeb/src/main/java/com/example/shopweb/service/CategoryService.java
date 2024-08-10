package com.example.shopweb.service;

import com.example.shopmodel.mCategory.Item;
import com.example.shopmodel.mCategory.SubItem;
import com.example.shopweb.annotaion.PageX;
import com.example.shopweb.mapper.CategoryDao;
import com.example.shopmodel.model.Category;
import com.example.shopmodel.model.CategoryQuery;
import com.github.pagehelper.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class CategoryService {

    @Autowired
    private CategoryDao categoryDao;

    @PageX
    public Page<Category> select(CategoryQuery query) {
        Page<Category> select = categoryDao.select(query);
        return select;
    }

    public int insert(Category category) {
        return categoryDao.insert(category);
    }

    public int update(Category category) {
        return categoryDao.update(category);
    }

    //软删除 逻辑删除 只改状态不删除数据
    public int softDelete(Integer id) {
        return categoryDao.softDelete(id, 0);
    }

    // 移动端分类查询
    public List<Item> selectMCategory() {
        List<Category> parent = categoryDao.selectParent();
        Map<Integer, List<Category>> childMap = new HashMap<>();
        List<Category> child = categoryDao.selectChild();

        if (child.isEmpty()) {
            log.info("child列表为空");
            return new ArrayList<>();
        }

        // 将子分类按其父ID分组
        for (Category c : child) {
            Integer parentId = c.getParentId();
            if (parentId != null) {
                if (!childMap.containsKey(parentId)) {
                    childMap.put(parentId, new ArrayList<>());
                }
                childMap.get(parentId).add(c);
            }else {
                log.info("无父分类Category=>{}", c);
            }
        }

        List<Item> items = new ArrayList<>();
        for (Category category : parent) {
            Item item = new Item();
            item.setId(category.getId());
            item.setText(category.getName());
            List<SubItem> subItems = new ArrayList<>();

            // 只添加当前父分类下的子分类
            if (childMap.containsKey(category.getId())) {
                for (Category childCategory : childMap.get(category.getId())) {
                    SubItem subItem = new SubItem();
                    subItem.setId(childCategory.getId());
                    subItem.setText(childCategory.getName());
                    subItem.setImgUrl(childCategory.getImg());
                    subItems.add(subItem);
                }
            }
            item.setSubItems(subItems);
            items.add(item);
        }
        return items;
    }

}
