package com.jakers.mustneed.core.repository;

import com.jakers.mustneed.core.domain.DomainEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * 최상위 도메인 레파지토리
 *
 * @param <ENTITY> 엔티티
 * @param <IDENTITY> 식별자
 */
@NoRepositoryBean
public interface DomainRepository<ENTITY extends DomainEntity, IDENTITY>
    extends JpaRepository<ENTITY, IDENTITY>, JpaSpecificationExecutor<ENTITY>,
    QuerydslPredicateExecutor<ENTITY> {

}
