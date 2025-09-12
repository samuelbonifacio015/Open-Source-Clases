package com.acme.hello.platform.generic.interfaces.rest.controllers;

import com.acme.hello.platform.generic.domain.model.entity.Developer;
import com.acme.hello.platform.generic.interfaces.rest.assemblers.DeveloperAssembler;
import com.acme.hello.platform.generic.interfaces.rest.assemblers.GreetDeveloperAssembler;
import com.acme.hello.platform.generic.interfaces.rest.resources.GreetDeveloperRequest;
import com.acme.hello.platform.generic.interfaces.rest.resources.GreetDeveloperResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping("/api/v1/greetings")
public class GreetingsController {

    /**
     * Greet a developer by their first and last name.
     * @param firstName the first name of the developer
     * @param lastName the last name of the developer
     * @return the greeting response, or a default message if names are not provided
     */

    @GetMapping
    public ResponseEntity<GreetDeveloperResponse> greetDeveloper(
        @RequestParam(required = false) String firstName,
        @RequestParam(required = false) String lastName) {
        Developer developer = (Objects.isNull(firstName) || Objects.isNull(lastName)) ?
            null : Developer.builder().firstName(firstName).lastName(lastName).build();
        GreetDeveloperResponse response = GreetDeveloperAssembler.toResourceFromEntity(developer);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<GreetDeveloperResponse> createGreeting(
            @RequestBody GreetDeveloperRequest request) {
        Developer developer = DeveloperAssembler.toEntityFromRequest(request);
        GreetDeveloperResponse response = GreetDeveloperAssembler.toResourceFromEntity(developer);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

}
