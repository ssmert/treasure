package com.jakers.mustneed.account.domain;

import com.jakers.mustneed.core.domain.RootEntity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

/**
 * 계정 엔티티
 */
@Entity
@Getter
@Setter
@RequiredArgsConstructor(staticName = "of")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "ACCOUNT", uniqueConstraints = {
    @UniqueConstraint(name = "U_AUTH_ID", columnNames = {"AUTH_ID"})})
public class Account extends RootEntity {

    /**
     * 인증 아이디
     */
    @NonNull
    @Column(name = "AUTH_ID", length = 100, nullable = false)
    private String authId;

    /**
     * 이메일 아이디
     */
    @NonNull
    @Column(name = "EMAIL_ID", length = 100, nullable = false)
    private String emailId;

    /**
     * 비밀번호
     */
    @NonNull
    @Column(name = "PW", nullable = false)
    private String pw;

}
