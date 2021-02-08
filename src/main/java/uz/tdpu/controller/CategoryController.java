package uz.tdpu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.tdpu.entity.Category;
import uz.tdpu.model.Result;
import uz.tdpu.payload.*;
import uz.tdpu.service.CategoryService;

import java.util.List;

@RestController
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @GetMapping("/getAllByParent/{id}")
    public HttpEntity<?> getAllByPArent(@PathVariable Long id) {
        return ResponseEntity.ok(categoryService.bringChildren(id));
    }

    @GetMapping("/getAll")
    public HttpEntity<?> getAll() {
        return ResponseEntity.ok(categoryService.getAllCategory());
    }

    @PostMapping("/add/category")
    public HttpEntity<?> addCategory(@RequestBody ReqCategory reqCategory) {
      return ResponseEntity.ok(categoryService.addCategoryParent(reqCategory));
    }

    @PostMapping("/add/children")
    public HttpEntity<?> addChildren(@RequestBody ReqChildren reqChildren) {
        return ResponseEntity.ok(categoryService.addChildren(reqChildren));
    }

    @PutMapping("/edit/category/{id}")
    public Result editCategory(@PathVariable Long id, @RequestBody ReqEditCategory reqEditCategory){
        return categoryService.editCategory(id, reqEditCategory);
    }

    @DeleteMapping("/delete/category/{id}")
    public Result deleteCategory(@PathVariable Long id){
        return categoryService.deleteCategory(id);
    }

    @PutMapping("/edit/children/{id}")
    public Result editChildren(@PathVariable Long id, @RequestBody ReqEditChildren reqEditChildren){
        return categoryService.editChildren(id, reqEditChildren);
    }

    @DeleteMapping("/delete/children/{id}")
    public Result deleteChildren(@PathVariable Long id){
        return categoryService.deleteChildren(id);
    }

}
