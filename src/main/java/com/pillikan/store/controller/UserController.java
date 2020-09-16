package com.pillikan.store.controller;

import com.pillikan.store.dto.UserDto;
import com.pillikan.store.model.User;
import com.pillikan.store.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

import static com.pillikan.store.util.ValidationUtil.checkNotFoundWithId;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final UserService service;

    @GetMapping("/{id}")
    public User get(@PathVariable int id) {
        return checkNotFoundWithId(service.getById(id), id);
    }

    @GetMapping
    @Secured("ROLE_ADMIN")
    public List<User> getAll() {
        return service.getAll();
    }

    @PostMapping
    public ResponseEntity<User> create(@Valid @RequestBody UserDto user) {
        User dbUser = service.create(user);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(dbUser.getId()).toUri();
        return ResponseEntity.created(location).body(dbUser);
    }

    @PutMapping("/{id}")
    public User update(@Valid @RequestBody UserDto user, @PathVariable int id) {
        return service.update(user, id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        service.delete(id);
    }
}
