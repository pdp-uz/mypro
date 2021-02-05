package uz.tdpu.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReqEditChildren {

    private String name;

    private String fullName;

    private Integer age;

    private Long parentId;

}
