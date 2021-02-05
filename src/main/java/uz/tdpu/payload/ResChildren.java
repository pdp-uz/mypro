package uz.tdpu.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.tdpu.entity.Category;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResChildren {

    private Long id;

    private String name;

    private Integer age;

    private String fullName;

    private Long parentId;

    private List<ResChildren> childs;



}
