package com.dailySync.account.service;

import com.dailySync.account.dto.AccountListResDto;
import com.dailySync.account.dto.AccountResDto;
import com.dailySync.account.entity.Account;
import com.dailySync.account.repository.AccountRepository;
import com.dailySync.account.repository.FavoriteAccountRepository;
import com.dailySync.user.entities.User;
import com.dailySync.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountService {
    private final UserRepository userRepository;
    private final AccountRepository accountRepository;
    private final FavoriteAccountRepository favoriteRepository;

    /** 예시 */
    public AccountListResDto getOneDayAccount(LocalDate date) {
        List<Account> oneDayList = accountRepository.findByUser_IdAndAccountDate(4L, date);

        return new AccountListResDto(oneDayList.stream().map(Account::toResDto).toList());
    }
}
