package com.jakers.mustneed.core.domain;

import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/**
 * 최상위 자식 엔터티
 */
@Getter
@MappedSuperclass
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public abstract class ChildEntity implements Serializable {

    // 식별자
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 최초 등록자
    @CreatedBy
    @Column(name = "REG_ID", length = 36, nullable = false, updatable = false)
    protected String regId;

    // 최초 등록일시
    @CreatedDate
    @Column(name = "REG_AT", nullable = false, updatable = false)
    protected LocalDateTime regAt;

    // 최종 변경자
    @LastModifiedBy
    @Column(name = "UDT_ID", length = 36, nullable = false)
    protected String udtId;

    // 최종 변경일시
    @LastModifiedDate
    @Column(name = "UDT_AT", nullable = false)
    protected LocalDateTime udtAt;
}
