package org.example.schedulemanagement.global;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {

    // 400 Bad Request
    INVALID_REQUEST(400, "잘못된 요청입니다.", HttpStatus.BAD_REQUEST),
    INVALID_INPUT_VALUE(400, "입력값이 유효하지 않습니다.", HttpStatus.BAD_REQUEST),

    // 401 Unauthorized
    UNAUTHORIZED(401, "인증이 필요합니다.", HttpStatus.UNAUTHORIZED),
    INVALID_CREDENTIALS(401, "이메일과 비밀번호가 일치하지 않습니다.", HttpStatus.UNAUTHORIZED),

    // 403 Forbidden
    FORBIDDEN(409, "접근 권한이 없습니다.", HttpStatus.FORBIDDEN),

    // 404 Not Found
    USER_NOT_FOUND(404, "사용자를 찾을 수 없습니다.", HttpStatus.NOT_FOUND),
    RESOURCE_NOT_FOUND(404, "요청한 리소스를 찾을 수 없습니다.", HttpStatus.NOT_FOUND),

    // 409 Conflict
    DUPLICATE_RESOURCE(409, "중복된 데이터가 존재합니다.", HttpStatus.CONFLICT),
    DUPLICATE_EMAIL(409, "중복된 이메일이 존재합니다.", HttpStatus.CONFLICT),


    // 500 Internal Server Error
    INTERNAL_SERVER_ERROR(500, "서버 내부 오류입니다.", HttpStatus.INTERNAL_SERVER_ERROR);

    private final int code;
    private final String message;
    private final HttpStatus httpStatus;

    ErrorCode(int code, String message, HttpStatus httpStatus) {
        this.code = code;
        this.message = message;
        this.httpStatus = httpStatus;
    }
}
