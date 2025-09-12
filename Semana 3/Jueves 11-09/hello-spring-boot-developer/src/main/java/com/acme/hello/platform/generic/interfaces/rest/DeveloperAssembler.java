package com.acme.hello.platform.generic.interfaces.rest;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import com.acme.hello.platform.generic.domain.model.entity.Developer;
import com.acme.hello.platform.generic.interfaces.rest.GreetDeveloperRequest;

public class DeveloperAssembler {
    public static Developer toEntityFromRequest(GreetDeveloperRequest request) {
        if (ObjectUtils.isEmpty(request) ||
                StringUtils.isAnyBlank(request.firstName(), request.lastName())) {
            return null;
        }
        return Developer.builder()
                .FirstName(request.firstName())
                .lastName(request.lastName())
                .build();
    }
}
