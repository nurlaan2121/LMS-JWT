package lms.dto.response;

import lombok.*;
import org.springframework.http.HttpStatus;

/**
 * @author Mukhammed Asantegin
 */
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SimpleRes {
    private HttpStatus httpStatus;
    private String message;
}
