package com.midland.web.dao;

import com.midland.web.model.Category;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryMapper {

    Category selectCategoryById(Integer category);

    int deleteCategoryById(Integer category);

    int updateCategoryById(Category category);
    List<Category> getCateGorysByIds(List<Integer> ids);

    Category shiftUp(Category category);

    Category shiftDown(Category category);

    int insertCategory(Category category);

    List<Category> findCategoryList(Category category);

    List<Category> getCateGorys(Category category);
    List<Category> getModels(Category category);

    List<Category> findCategoryTreeList(Category category);

    List<Category> findCategoryParentNameList(Category category);

    List<Category> findCategoryListFromCityIdById(Category category);

    List<Category> findCategoryListByIdList(@Param("ids") List list);

    Category selectCategoryParentById(Integer category);

    int batchUpdate(@Param("categoryList") List<Category> categoryList);

}
