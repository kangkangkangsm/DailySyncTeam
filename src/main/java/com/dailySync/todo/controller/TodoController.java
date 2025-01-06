package com.dailySync.todo.controller;//package com.dailySync.todo.controller;

import com.dailySync.common.ApiResponse;
import com.dailySync.todo.dto.*;
import com.dailySync.todo.service.TodoService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/todo")
public class TodoController {
    final private TodoService todoService;



    @Operation
            (
                    summary = "로그인한 유저가 선택한 달의 리스트 불러오기(초기 오늘의 월) - 일자별로 전체 리스트",
                    description = "year는 연도 month는 월 입력한다."
            )
    @GetMapping ("list/{year}/{month}")
    public ResponseEntity<ApiResponse<TodoListResDto>> getMealList(
            @PathVariable int year,
            @PathVariable int month
    ) {
        Long userId = getUserId();
        return ApiResponse.success(todoService.getUserTodolList(userId, year, month));
    }

    @PostMapping("/autoTest")
    public String autoGenerateTodoList() {
        Long userId = getUserId();
        // 자동 생성 로직 호출
        todoService.TodoLoginAutoListCreate(userId);
        return "유저아이디" + userId;
    }


    @Operation
            (
                    summary = " todoList 체크 ( checktiem null 일시 now() 반대도 마찬가지 )",
                    description ="변경완료 테스트해봐야함."
            )
    @PutMapping("/list/update/check/{id}")
    public ResponseEntity<ApiResponse<TodoListResDto>> updateCheckList(@PathVariable Long id) {
        TodoListResDto updateCheck = todoService.updateListCheck(id);
        return ApiResponse.success(updateCheck);
    }

    @Operation
            (
                    summary = " todoItem 삭제시(클라이언트입장)  status값  old로 변경 ",
                    description ="변경완료 테스트해봐야함."
            )
    @PutMapping("/item/update/status/{id}")
    public ResponseEntity<ApiResponse<TodoItemResDto>> updateStatus(@PathVariable Long id) {
        TodoItemResDto updateItem = todoService.updateStatus(id);
        return ApiResponse.success(updateItem);
    }
    @Operation
            (
                    summary = " todogroup 삭제시(클라이언트입장)  status값  old로 변경 ",
                    description ="변경완료 테스트해봐야함."
            )
    @PutMapping("/group/update/status/{id}")
    public ResponseEntity<ApiResponse<TodoGroupResDto>> updateGroupStatus(@PathVariable Long id) {
        TodoGroupResDto updateItem = todoService.updateGroupStatus(id);
        return ApiResponse.success(updateItem);
    }

    @Operation
            (
                    summary = " todoItem 정보 변경시 기존id status old처리 / 그후 새로생성 ",
                    description ="사용안함..."
            )
    @PutMapping("/item/{id}")
    public ResponseEntity<ApiResponse<TodoItemResDto>> updateItem(@PathVariable Long id, @RequestBody TodoItemReqDto reqDto) {
        Long userId = getUserId();
        TodoItemResDto updatedItem = todoService.updateItem(userId, id, reqDto);
        return ApiResponse.success(updatedItem);
    }
    @Operation
            (
                    summary = " 그룹정보 변경 ",
                    description ="변경할거없음"
            )
    @PutMapping("/group/update/{id}")
    public ResponseEntity<ApiResponse<TodoGroupResDto>> updateGroup(@PathVariable Long id, @RequestBody TodoGroupReqDto reqDto) {
        Long userId = getUserId();
        TodoGroupResDto updatedItem = todoService.updateGroup(userId,id, reqDto);
        return ApiResponse.success(updatedItem);
    }

    @Operation
            (
                    summary = " todoList 변경",
                    description ="변경완료 "
            )
    @PutMapping("/list/update/{id}")
    public ResponseEntity<ApiResponse<TodoListResDto>> updateList(@PathVariable Long id, @RequestBody TodoListReqDto reqDto ) {
        TodoListResDto updateCheck = todoService.updateList(id, reqDto);
        return ApiResponse.success(updateCheck);
    }

    //GetMapping
    @Operation(
            summary = " todoItem 정보 조회 ",
            description = "사용안함"
    )
    @GetMapping("/itemInfo/{id}")
    public ResponseEntity<?> getTodoItemInfo(@PathVariable("id") Long id) {
        TodoItemResDto rsp = todoService.getTodoItemInfo(id);
        return ApiResponse.success(rsp);
    }
    @Operation(
            summary = " todoList 정보 조회 ",
            description = "사용안함"
    )
    @GetMapping("/listInfo/{id}")
    public ResponseEntity<?> getTodoListInfo(@PathVariable("id") Long id) {
        TodoListResDto rsp = todoService.getTodoListInfo(id);
        return ApiResponse.success(rsp);
    }
    @Operation(
            summary = " todoGroup 정보 조회 ",
            description = "사용안함"
    )
    @GetMapping("/groupInfo/{id}")
    public ResponseEntity<?> getTodoGroupInfo(@PathVariable("id") Long id) {
        TodoGroupResDto rsp = todoService.getTodoGroupInfo(id);
        return ApiResponse.success(rsp);
    }

    @Operation
            (
                    summary = " userId, groupId 에 해당하는 todoItem 조회 ",
                    description ="아이디 적용 완료"
            )
    @GetMapping("/item/{groupId}")
    public ResponseEntity<?> getTodoItem(@PathVariable ("groupId") Long groupId){
        Long userId = getUserId();
        return ApiResponse.success(todoService.getTodoItem(userId, groupId));
    }

    @Operation
            (
                    summary = " userId에 해당하는 todoGroup을 조회 ",
                    description =" 아이디 적용 완료 "
            )
    @GetMapping("/group") 
    public ResponseEntity<?> getTodoGroup(){
        Long userId = getUserId();
        return ApiResponse.success(todoService.getTodoGroup(userId));
    }

    @Operation
            (
                    summary = " 금일 날짜 해당하는 todoList를 조회 ",
                    description =" 아이디 적용 완료 "
            )
    @GetMapping("/list/today")
    public ResponseEntity<?> getTodayTodoList() {
        Long userId = getUserId();
        return ApiResponse.success(todoService.getTodayList(userId));
    }
    @Operation
            (
                    summary = " 날짜 해당하는 todoList를 조회  ",
                    description =" 적용완료 테스트x "
            )
    @GetMapping("/list/{date}")
    public ResponseEntity<?> getTodoListByDate(@PathVariable LocalDate date) {
        Long userId = getUserId();
        return ApiResponse.success(todoService.getTodoList(userId, date));  // 날짜가 yyyy-MM-dd 형식으로 전달됨
    }

    //PostMapping

    @Operation
            (
                    summary = " todoList에 변동일정 추가하기 listOrder(4) 고정 ",
                    description =" 적용완료 테스트x"
            )
    @PostMapping("/list/change")
    public ResponseEntity<?> addTodoList(@RequestBody TodoListReqDto reqDto) {
        Long userId = getUserId();
        return ApiResponse.success(todoService.createTodoList(userId,reqDto));
    }

    @Operation
            (
                    summary = " todoGroup 추가 ",
                    description ="적용완료 테스트x"
            )
    @PostMapping("/group")
    public ResponseEntity<?> addTodoGroup(@RequestBody TodoGroupReqDto reqDto) {
        Long userId = getUserId();
        return ApiResponse.success(todoService.createTodoGroup(userId,reqDto));
    }

    @Operation
            (
                    summary = " todoItem 추가 ",
                    description =" 적용완료 테스트x"
            )
    @PostMapping("/item")

    public ResponseEntity<?> addTodoItem(@RequestBody TodoItemReqDto reqDto) {
        Long userId = getUserId();
        return ApiResponse.success(todoService.createTodoItem(userId, reqDto));
    }

    @Operation
    (
            summary = " memo 생성 ",
            description ="적용완료"
    )
    @PostMapping("/memo")
    public ResponseEntity<?> addTodoMemo(@RequestBody TodoMemoReqDto reqDto) {
        Long userId = getUserId();
        return ApiResponse.success(todoService.createTodoMemo(userId,reqDto));
    }

    @Operation
    (
            summary = " memo 불러오기 ",
            description ="적용완료"
    )
    @GetMapping("/memo")
    public ResponseEntity<?> getTodoMemo(){
        Long userId = getUserId();
        return ApiResponse.success(todoService.getTodoMemo(userId));
    }
    @Operation
    (
            summary = " memo 삭제하기 ",
            description ="사용안함"
    )
    @DeleteMapping("/memo/{id}")
    public ResponseEntity<?> deleteTodoItem(@PathVariable("id") Long id) {
        todoService.deleteTodoMemo(id);
        return ApiResponse.success(null);
    }

    @Operation
    (
            summary = " memo 업데이트 ",
            description ="적용완료"
    )
    @PutMapping("/memo/{id}")
    public ResponseEntity<?> putTodoItem(@PathVariable ("id") Long id, @RequestBody TodoMemoReqDto reqDto){;
        return ApiResponse.success(todoService.getTodoUpdateMemo( id, reqDto));
    }

    @Operation
            (
                    summary = " todoList 삭제 ",
                    description ="적용완료"
            )
    @DeleteMapping("/list/{id}")
    public ResponseEntity<?> deleteTodoList(@PathVariable Long id) {
        todoService.deleteTodoList(id);
        return ApiResponse.success(null);  // 성공적인 삭제는 데이터가 없으므로 null 반환
    }

    @Operation
            (
                    summary = " todoGroup 삭제 ",
                    description =" 적용완료 "
            )
    @DeleteMapping("/group/{id}")
    public ResponseEntity<?> deleteTodoGroup(@PathVariable Long id) {
        todoService.deleteTodoGroup(id);
        return ApiResponse.success(null);  // 성공적인 삭제는 데이터가 없으므로 null 반환
    }


    //------------------------------------------------------------------------

    //TodoList
    //TodoList 목록 조회(날짜기준)
    private Long getUserId() {
        return (Long) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }


}