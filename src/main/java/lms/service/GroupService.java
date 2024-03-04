package lms.service;

import lms.dto.request.GroupReq;
import lms.dto.request.GroupReqWithCourse;
import lms.dto.response.GroupRes;
import lms.dto.response.GroupResWithAll;

import java.util.List;

public interface GroupService {
    GroupRes save(GroupReq groupReq);

    List<GroupResWithAll> getAllGroups();

    GroupResWithAll findById(Long id);

    String update(GroupReq groupReq, Long id);

    String remove(Long id);

    GroupRes save2(GroupReqWithCourse groupReqWithCourse);

    Long getStudCunt(Long groupId);
}
