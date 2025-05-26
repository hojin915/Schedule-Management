# Schedule-Management

사용자의 HTTP 요청에 통해서 일정을 작성할 수 있습니다. 

유저, 일정, 댓글 각각에 대한 CRUD 기능을 구현하였으며, 요청과 응답 예시는 API 명세서에서 확인할 수 있습니다.

Controller, Service, Repository 로 나눠진 layered 구조이며

비즈니스 로직은 도메인을 기준으로 Entity, DTO, controller, service, repository 를 모아서 관리하고,

공통 모듈은 설정(config), 예외(exception), 필터(filter), 유틸리티(utils) 로 나누어서 관리합니다.

API 명세서에서 (인증제외) 라고 표시된 항목 외에는 기본적으로 로그인 인증이 필요합니다.

## API 명세서   
https://www.notion.so/1f98976d5bc98097bf43f09b74332d74?v=1f98976d5bc9818d98a4000c8524edc6&pvs=4  

## DB ERD
![Schedule Management ERD.png](image/Schedule%20Management%20ERD.png)

<pre>
src 디렉터리 구조

\---src
    +---main
    |   +---java
    |   |   \---com
    |   |       \---example
    |   |           \---schedulemanagement
    |   |               |   ScheduleManagementApplication.java
    |   |               |
    |   |               +---comments
    |   |               |       Comment.java
    |   |               |       CommentController.java
    |   |               |       CommentRepository.java
    |   |               |       CommentRequestDto.java
    |   |               |       CommentResponseDto.java
    |   |               |       CommentService.java
    |   |               |
    |   |               +---config
    |   |               |       PasswordEncoder.java
    |   |               |       WebConfig.java
    |   |               |
    |   |               +---exception
    |   |               |       ErrorResponseDto.java
    |   |               |       GlobalExceptionHandler.java
    |   |               |       NotFoundException.java
    |   |               |       ValidateFailException.java
    |   |               |
    |   |               +---filter
    |   |               |       CustomFilter.java
    |   |               |       LoginFilter.java
    |   |               |
    |   |               +---schedules
    |   |               |       ScheduleController.java
    |   |               |       SchedulePageResponseDto.java
    |   |               |       ScheduleRepository.java
    |   |               |       ScheduleRequestDto.java
    |   |               |       ScheduleResponseDto.java
    |   |               |       ScheduleService.java
    |   |               |       Todo.java
    |   |               |
    |   |               +---users
    |   |               |       User.java
    |   |               |       UserController.java
    |   |               |       UserLoginRequestDto.java
    |   |               |       UserRepository.java
    |   |               |       UserRequestDto.java
    |   |               |       UserResponseDto.java
    |   |               |       UserService.java
    |   |               |
    |   |               \---utils
    |   |                       BaseEntity.java
    |   |                       Const.java
    |   |                       PageDto.java
    |   |
    |   \---resources
    |       |   application.properties
    |       |
    |       +---static
    |       \---templates
    \---test
        \---java
            \---com
                \---example
                    \---schedulemanagement
                            ScheduleManagementApplicationTests.java


</pre>

## 기술 스택
- 언어: Java 17
- 프레임워크: Spring Boot 3.4.5
- 데이터베이스: MySQL

- 라이브러리
  - Spring Data JPA: ORM(Object Relational Mapping)
  - Bean Validation: 입력값 검증
  - Bcrypt: 비밀번호 암호화