package uz.tdpu.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import uz.tdpu.entity.Category;
import uz.tdpu.model.Result;
import uz.tdpu.payload.*;
import uz.tdpu.repository.CategoryRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.SortedMap;

@Service
public class CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    public List<ResCategory> getAllCategory() {
        List<Category> categoryList = categoryRepository.findAllByParentIsNull();
        List<ResCategory> resCategoryList = new ArrayList<ResCategory>();
        if(!categoryList.isEmpty()) {
            for (int i = 0; i < categoryList.size(); i ++) {
                Category category = categoryList.get(i);
                resCategoryList.add(new ResCategory(category.getName(), bringChildren(category.getId())));
            }
        }
        return resCategoryList;
    }
    public List<ResChildren> bringChildren(Long parent_id){
        List<Category> categoryList = categoryRepository.findAllByParent(parent_id);
        List<ResChildren> resCategoryChildList = new ArrayList<ResChildren>();
        if(!categoryList.isEmpty()) {
            for (int i = 0; i < categoryList.size(); i ++) {
                Category category = categoryList.get(i);
                resCategoryChildList.add(new ResChildren(
                        category.getId(),
                        category.getName(),
                        category.getAge(),
                        category.getFullName(),
                        category.getParentId(),
                        bringChildren(category.getId())));
            }
        }
        return resCategoryChildList;
    }
    public Category addChildren(ReqChildren reqChildren) {
        return categoryRepository.save(new Category(
                reqChildren.getName(),
                reqChildren.getAge(),
                reqChildren.getFullName(),
                reqChildren.getParentId()));
    }
    public Category addCategoryParent(ReqCategory reqCategory){
        return categoryRepository.save(new Category(reqCategory.getName(), null));
    }
    public Result editCategory(@PathVariable Long id, @RequestBody ReqEditCategory reqEditCategory){
        Optional<Category> byId = categoryRepository.findById(id);
        if (byId.isPresent()){
            Category category = byId.get();
            category.setId(id);
            category.setName(reqEditCategory.getName());
            categoryRepository.save(category);
            return new Result(true, "Succesfully edited category");
        }else
            return new Result(false, "Category not found!");
    }
    public Result deleteCategory(@PathVariable Long id) {
        Optional<Category> byId = categoryRepository.findById(id);
        if (byId.isPresent()) {
            Category category = byId.get();
            List<Category> allByParent = categoryRepository.findAllByParent(category.getId());
            if (!allByParent.isEmpty()){
                categoryRepository.deleteAll(allByParent);
                categoryRepository.deleteById(id);
                return new Result(true, "Successfully deleted category");
            }
            else return new Result(false, "Error deleted!");

            } else return new Result(false, "Category not found!");
    }

    public Result editChildren(@PathVariable Long id, @RequestBody ReqEditChildren reqEditChildren){
        List<Category> allByParent = categoryRepository.findAllByParent(reqEditChildren.getParentId());
        Category editCategory=new Category();
        if (!allByParent.isEmpty()){
            for (int i = 0; i < allByParent.size(); i++) {
                Category category = allByParent.get(i);
                if (category.getId()==id){
                    editCategory=category;
                }
            }
            editCategory.setId(id);
            editCategory.setName(reqEditChildren.getName());
            editCategory.setParentId(reqEditChildren.getParentId());
            editCategory.setFullName(reqEditChildren.getFullName());
            editCategory.setAge(reqEditChildren.getAge());
            categoryRepository.save(editCategory);
            return new Result(true, "Successfully edited category child");
        }else return new Result(false,"Edited error!");
    }
    public Result deleteChildren(@PathVariable Long id){
        Optional<Category> byId = categoryRepository.findById(id);
        if (byId.isPresent()){
            categoryRepository.deleteById(id);
            return new Result(true, "Successfully deleted category child");
        }else return new Result(false, "Error is invalid1");
    }

}
