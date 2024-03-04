package lms.dto.response;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class GroupResWithAll {
    private String groupName;
    private String imageLink;
    private String description;
    private Long coursesCount;
    private Long studentsCount;

    public GroupResWithAll(String groupName, String imageLink, String description) {
        this.groupName = groupName;
        this.imageLink = imageLink;
        this.description = description;
    }
}
