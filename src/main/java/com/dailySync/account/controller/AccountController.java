package com.dailySync.account.controller;

import com.dailySync.account.dto.AccountListResDto;
import com.dailySync.account.service.AccountService;
import com.dailySync.common.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequiredArgsConstructor
public class AccountController {
    private final AccountService accountService;

    /**로그인 한 유저가 날짜를 date 변수에 담아 넘겨주면 해당 날짜의 accountList 를 전달 */
    @GetMapping ("account/dayList/{date}")
    public ResponseEntity<ApiResponse<AccountListResDto>> oneDayAccount(@PathVariable LocalDate date) throws Exception {
        return ApiResponse.success(accountService.getOneDayAccount(date));
    }

    //todo 확인을 위해 insert 부분 부터 만들것.
}
