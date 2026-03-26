package com.hexabank.account;

import org.flywaydb.core.Flyway;
import org.flywaydb.core.api.MigrationInfo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
class FlywayMigrationIT {

    @Autowired
    private Flyway flyway;

    @Test
    void migrationsAreApplied() {
        MigrationInfo[] applied = flyway.info().applied();
        assertThat(applied).isNotEmpty();

        List<String> versions = Arrays.stream(applied)
                .map(m -> m.getVersion() == null ? "" : m.getVersion().getVersion())
                .collect(Collectors.toList());

        // Our V1 migration should be applied
        assertThat(versions).contains("1");
    }
}
