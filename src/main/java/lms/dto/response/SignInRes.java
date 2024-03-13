package lms.dto.response;

import lms.entities.Role;

public record SignInRes(Long id, String token, String email, Role role) {
}
