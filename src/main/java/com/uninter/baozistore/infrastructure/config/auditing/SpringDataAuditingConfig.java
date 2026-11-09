package com.uninter.baozistore.infrastructure.config.auditing;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.auditing.DateTimeProvider;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

@Configuration
@EnableJpaAuditing(
        dateTimeProviderRef = "auditingDateTimeProvider",
        auditorAwareRef = "auditorProvider"
)
public class SpringDataAuditingConfig {

    @Bean
    public DateTimeProvider auditingDateTimeProvider() {
        return () -> Optional.of(OffsetDateTime.now(ZoneId.of("America/Sao_Paulo")).truncatedTo(ChronoUnit.MILLIS));
    }

    @Bean
    public AuditorAware<Long> auditorProvider() {
        return () -> Optional.of(1L); // no momento edições/inserts e etc sempre feitos pelo ID 1 (admin)
    }

}
