# Schedule-Management

API 명세서  
: https://www.notion.so/1f98976d5bc98097bf43f09b74332d74?v=1f98976d5bc9818d98a4000c8524edc6&pvs=4  

![Schedule Management ERD.png](image/Schedule%20Management%20ERD.png)  

<pre>
src 디렉터리 구조

src
 ┣ main
 ┃ ┣ java
 ┃ ┃ ┗ com
 ┃ ┃ ┃ ┗ example
 ┃ ┃ ┃ ┃ ┗ schedulemanagement
 ┃ ┃ ┃ ┃ ┃ ┣ comments
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ Comment.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ CommentController.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ CommentRepository.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ CommentRequestDto.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ CommentResponseDto.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ CommentService.java
 ┃ ┃ ┃ ┃ ┃ ┣ config
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ PasswordEncoder.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ WebConfig.java
 ┃ ┃ ┃ ┃ ┃ ┣ exception
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ ErrorResponseDto.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ GlobalExceptionHandler.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ NotFoundException.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ ValidateFailException.java
 ┃ ┃ ┃ ┃ ┃ ┣ filter
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ CustomFilter.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ LoginFilter.java
 ┃ ┃ ┃ ┃ ┃ ┣ schedules
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ ScheduleController.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ ScheduleRepository.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ ScheduleRequestDto.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ ScheduleResponseDto.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ ScheduleService.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ Todo.java
 ┃ ┃ ┃ ┃ ┃ ┣ users
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ User.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ UserController.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ UserLoginRequestDto.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ UserRepository.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ UserRequestDto.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ UserResponseDto.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ UserService.java
 ┃ ┃ ┃ ┃ ┃ ┣ BaseEntity.java
 ┃ ┃ ┃ ┃ ┃ ┣ Const.java
 ┃ ┃ ┃ ┃ ┃ ┗ ScheduleManagementApplication.java
 ┃ ┗ resources
 ┃ ┃ ┣ static
 ┃ ┃ ┣ templates
 ┃ ┃ ┗ application.properties
 ┗ test
 ┃ ┗ java
 ┃ ┃ ┗ com
 ┃ ┃ ┃ ┗ example
 ┃ ┃ ┃ ┃ ┗ schedulemanagement
 ┃ ┃ ┃ ┃ ┃ ┗ ScheduleManagementApplicationTests.java
</pre>