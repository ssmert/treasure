package com.jakers.mustneed.account.repository;

import com.jakers.mustneed.account.domain.Account;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

/**
 * 커스텀 계정 레파지토리 구현체
 */
public class AccountCustomRepositoryImpl extends QuerydslRepositorySupport implements
    AccountCustomRepository {

    private final JPAQueryFactory queryFactory;

    public AccountCustomRepositoryImpl(JPAQueryFactory queryFactory) {
        super(Account.class);
        this.queryFactory = queryFactory;
    }
}
