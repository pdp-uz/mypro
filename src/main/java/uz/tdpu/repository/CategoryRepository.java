package uz.tdpu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uz.tdpu.entity.Category;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    @Query(value = "select * from category where parent_id is NULL",nativeQuery = true)
    List<Category> findAllByParentIsNull();

    @Query(value = "select * from category where category.parent_id = :parent_id", nativeQuery = true)
    List<Category> findAllByParent(Long parent_id);

}
