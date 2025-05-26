package com.example.schedulemanagement.schedules;

import com.example.schedulemanagement.comments.Comment;
import com.example.schedulemanagement.comments.CommentRepository;
import com.example.schedulemanagement.exception.ValidateFailException;
import com.example.schedulemanagement.users.User;
import com.example.schedulemanagement.users.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ScheduleService {
    private final ScheduleRepository scheduleRepository;
    private final UserRepository userRepository;
    private final CommentRepository commentRepository;

    public ScheduleResponseDto save(Long userId, String title, String contents) {
        User user = userRepository.findByIdOrElseThrow(userId);

        Todo todo = new Todo(title, contents);
        todo.setUser(user);

        Todo savedTodo = scheduleRepository.save(todo);

        return new ScheduleResponseDto(savedTodo);
    }

    public ScheduleResponseDto findScheduleById(Long todoId) {
        Todo todo = scheduleRepository.findByIdOrElseThrow(todoId);
        return new ScheduleResponseDto(todo);
    }

    public Page<SchedulePageResponseDto> findAllSchedules(int page, int size) {
        // page 설정
        Pageable pageable = PageRequest.of(page - 1, size, Sort.by("updatedAt").descending());

        // 전체 스케줄 리스트, 정렬 적용된 상태
        Page<Todo> todoPage = scheduleRepository.findAll(pageable);
        List<Long> todoIdList = todoPage.getContent().stream().map(Todo::getId).toList();
        // 각 스케줄 commentCount
        List<Object[]> result = commentRepository.countCommentGroupByTodoId(todoIdList);
        Map<Long, Long> commentCountMap = new HashMap<>();
        for(Object[] row : result) {
            Long todoId = (Long) row[0];
            Long count = (Long) row[1];
            commentCountMap.put(todoId, count);
        }
        System.out.println(result.size());
        // map 을 이용해 일정별로 댓글 수를 포함한 SchedulePageResponseDto 를 생성해서
        // 결과들을 Page 로 묶어 다시 리턴
        return todoPage.map(todo -> {
            Long commentCount = commentCountMap.getOrDefault(todo.getId(), 0L);
            return new SchedulePageResponseDto(todo, commentCount);
        });
    }

    @Transactional
    public ScheduleResponseDto updateSchedule(Long todoId, String title, String contents, Long userId) {
        Todo todo = scheduleRepository.findByIdOrElseThrow(todoId);

        checkScheduleUserId(todo, userId);

        todo.updateTitle(title);
        todo.updateContents(contents);

        return new ScheduleResponseDto(todo);
    }

    @Transactional
    public void deleteSchedule(Long todoId, Long userId) {
        Todo todo = scheduleRepository.findByIdOrElseThrow(todoId);

        checkScheduleUserId(todo, userId);

        scheduleRepository.delete(todo);
    }

    // 업데이트, 삭제하려는 일정의 userId와 요청한 userId를 비교
    private void checkScheduleUserId(Todo todo, Long userId){
        if(!todo.getUser().getId().equals(userId)){
            throw new ValidateFailException(
                    "checkScheduleUserId, Requested userId does not match with comment userId" +
                            "\nrequested userId = " + userId +
                            "\nschedule userId = " + todo.getUser().getId(),
                    "Not authorized"
            );
        }
    }
}