package lms.dto.request;

import jakarta.persistence.Column;
import lms.dto.response.GroupRes;
import lms.entities.Course;
import lms.entities.Group;
import lombok.Data;

@Data
public class GroupReq {
    private String groupName;
    private String imageLink;
    private String description;

    public GroupReq(String groupName, String imageLink, String description) {
        this.groupName = groupName;
        this.imageLink = imageLink;
        this.description = description;
    }

    public Group build() {
        return new Group(this.groupName, this.imageLink, this.description);
    }

    public GroupRes convert() {
        return new GroupRes(this.groupName,this.imageLink,this.description);
    }
}
