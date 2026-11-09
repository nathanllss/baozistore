package com.uninter.baozistore.core.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.time.OffsetDateTime;

@Entity
@Table(name = "client_tb")
@Data
@ToString(of= "id")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EntityListeners(AuditingEntityListener.class)
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;
    private String name;
    private LocalDate clientSince;

    @CreatedBy
    private Long createdByUserId;

    @CreatedDate
    private OffsetDateTime createdAt;

    @LastModifiedDate
    private OffsetDateTime lastModifiedAt;

    @LastModifiedBy
    private Long lastModifiedByUserId;

}
