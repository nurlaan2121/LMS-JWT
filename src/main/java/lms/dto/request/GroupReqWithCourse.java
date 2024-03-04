package lms.dto.request;

import lms.dto.response.GroupRes;
import lms.entities.Group;
import lombok.Data;

@Data
public class GroupReqWithCourse {
    private String groupName;
    private String imageLink;
    private String description;
    private String string;

    public Group build() {
        return new Group(this.groupName, this.imageLink, this.description);
    }

    public GroupRes convert() {
        return new GroupRes(this.groupName,this.imageLink,this.description);
    }
}
