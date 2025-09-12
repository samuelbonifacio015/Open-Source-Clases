package com.acme.hello.platform.generic.domain.model.entity;

import lombok.Builder;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

import java.util.UUID;

@Getter
@Builder
public class Developer {
    private final UUID id = UUID.randomUUID();
    private final String FirstName;
    private final String lastName;

    private Developer(String firstName, String lastName) {
        this.FirstName = StringUtils.trimToEmpty(firstName);
        this.lastName = StringUtils.trimToEmpty(firstName);
    }
}
