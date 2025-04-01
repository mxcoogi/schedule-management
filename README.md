## 프로젝트 개요

___

### 프로젝트 : 일정관리 API 만들기 - develop

### 프로젝트 목적

- Spring MVC 구조 이해
- JPA를 활용한 CRUD
- Cookie/Session 과 ServletFilter를 활용한 인증/인가 구현
- valid, validated 어노테이션으로 검증 구현

### 기술 스택

- Java 17
- Spring MVC 3.4.4
- JPA
- MySQL Community Server 9.2.0

____

### 기능 요구사항

#### 일정관련

- 일정 생성
- 일정 단건 조회
- 일정 전체 조회
- 일정 업데이트
- 일정 삭제

#### 유저관련

- 유저 생성
- 유저 조회
- 유저 업데이트
- 유저 삭제

___

### ERD

![](image/schedule-erd2.png)

### API

# API 명세서

| 기능       | Method   | URL                         | Request                                                                                                                                             | Response                                                                                                                                                                                                                                   | Status Code                                                        |
|----------|----------|-----------------------------|-----------------------------------------------------------------------------------------------------------------------------------------------------|--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|--------------------------------------------------------------------|
| 서버 연결확인  | `POST`   | `/ping`                     |                                                                                                                                                     | 성공: `{ "message": "pong" }`                                                                                                                                                                                                                | `200 OK`,<br> `400 Bad Request`,<br>`500 Internal Server Error`    |
| 일정 생성    | `POST`   | `/schedules`                | `{`<br>`"scheduleTitle": "string",`<br>`"scheduleContents" : "string", `<br>`"userEmail" : "string", `<br>` "userPassword":"string"`<br>`}`         | `성공`<br>`{ `<br>`"scheduleId : "Long", `<br>`"scheduleTitle" : "string", `<br>`"scheduleContents":"string",`<br>`"userName":"string", `<br>`"createdAt":"LocalDateTime", `<br>`"ureatedAt":"LocalDateTime" `<br>`}`                        | `201 OK`,<br> `400 Bad Request`                                    | 
| 일정 단건 조회 | `GET`    | `/schedules/{scheduleId}`   |                                                                                                                                                     | `성공` <br> `{ `<br>`"scheduleId : "Long", `<br>`"scheduleTitle" : "string", `<br>`"scheduleContents":"string",`<br>`"userName":"string", `<br>`"createdAt":"LocalDateTime", `<br>`"ureatedAt":"LocalDateTime" `<br>`}`                      | `200 OK`,<br> `404 Not Found`                                      |
| 일정 전체 조회 | `GET`    | `/schedules/users/{userId}` |                                                                                                                                                     | `성공` <br> `[`<br>`  { `<br>`"scheduleId : "Long", `<br>`"scheduleTitle" : "string", `<br>`"scheduleContents":"string",`<br>`"userName":"string", `<br>`"createdAt":"LocalDateTime", `<br>`"ureatedAt":"LocalDateTime" `<br>`} ... `<br>`]` | `200 OK`,<br> `404 Not Found`                                      |
| 일정 수정    | `PUT`    | `/schedules/{scheduleId}`   | `{`<br>`"updateScheduleTitle" : "string",`<br>`"updateScheduleContents" : "string",`<br>`"userEmail" : "string",`<br>`"userPassword":"string"`<br>`}` | `성공` <br> `{ `<br>`"scheduleId : "Long", `<br>`"scheduleTitle" : "string", `<br>`"scheduleContents":"string",`<br>`"userName":"string", `<br>`"createdAt":"LocalDateTime", `<br>`"ureatedAt":"LocalDateTime" `<br>`}`                      | `200 OK`,<br> `404 Not Found`,<br> `401 UnAuthorized`              |
| 일정 삭제    | `DELETE` | `/schedules/{scheduleId}`   | `{`<br>`"userEmail" : "string", `<br>` "userPassword":"string"`<br>` }`                                                                             |                                                                                                                                                                                                                                            | `204 No Content`,<br> `404 Not Found`,<br> `401 UnAuthorized`      |
| 회원가입     | `POST`   | `/auth/sign-up`             | `{`<br>`"userName":"string",`<br>`"userEmail":"string",`<br>` "userPassword":"string"`<br>`}`                                                       | `성공` <br> `{`<br>` "userId" : "Long", `<br>` "userName":"string",`<br>` "userEmail" : "string",`<br>` "createdAt":"LocalDateTime", `<br>`"ureatedAt":"LocalDateTime" `<br>`}`                                                              | `201 OK`,<br> `400 Bad Request`,<br> `409 Confilct User`           |
| 로그인      | `POST`   | `/auth/login`               | `{`<br>`"userEmail":"string",`<br>` "userPassword":"string"`<br>`}`                                                                                 | `성공` <br> `{`<br>` "userId" : "Long", `<br>` "userName":"string",`<br>` "userEmail" : "string",`<br>` "createdAt":"LocalDateTime", `<br>`"ureatedAt":"LocalDateTime" `<br>`}`                                                              | `201 OK`,<br> `404 Not Found User`                                 |
| 로그아웃     | `POST`   | `/auth/logout`              |                                                                                                                                                     | `성공` <br> `{`<br>` "userId" : "Long", `<br>` "userName":"string",`<br>` "userEmail" : "string",`<br>` "createdAt":"LocalDateTime", `<br>`"ureatedAt":"LocalDateTime" `<br>`}`                                                              | `201 OK`,<br> `404 Not Found User`                                 |
| 유저 조회    | `GET`    | `/users/{userId}`           |                                                                                                                                                     | `성공` <br> `{`<br>` "userId" : "Long", `<br>` "userName":"string",`<br>` "userEmail" : "string",`<br>` "createdAt":"LocalDateTime", `<br>`"ureatedAt":"LocalDateTime" `<br>`}`                                                              | `201 OK`,<br> `404 Not Found User`                                 |
| 유저 정보 수정 | `PUT`    | `/users`                    | `{`<br>`"updateUserName":"string"`<br>`}`                                                  | `성공` <br> `{`<br>` "userId" : "Long", `<br>` "userName":"string",`<br>` "userEmail" : "string",`<br>` "createdAt":"LocalDateTime", `<br>`"ureatedAt":"LocalDateTime" `<br>`}`                                                              | `200 OK`,<br> `404 Not Found User`,<br> `401 UnAuthorized`         |
| 유저 삭제    | `DELETE` | `/users`                    | `{`<br>`"userPassword":"string"`<br>`}`                                                                                 |                                                                                                                                                                                                                                            | `204 No Content`,<br> `404 Not Found User`,<br> `401 UnAuthorized` |