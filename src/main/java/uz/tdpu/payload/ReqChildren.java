package uz.tdpu.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;
import uz.tdpu.entity.Category;
import uz.tdpu.entity.Images;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReqChildren {

    private String name;

    private Long parentId;

    private Integer age;

    private String fullName;

   private Long imageId;

}
