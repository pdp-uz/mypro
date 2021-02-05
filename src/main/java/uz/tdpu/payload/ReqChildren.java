package uz.tdpu.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.tdpu.entity.Category;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReqChildren {

    private String name;

    private Long parentId;

    private Integer age;

    private String fullName;



}
