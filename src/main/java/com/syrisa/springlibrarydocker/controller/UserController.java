package com.syrisa.springlibrarydocker.controller;


import com.syrisa.springlibrarydocker.dto.UserDto;
import com.syrisa.springlibrarydocker.model.impl.User;
import com.syrisa.springlibrarydocker.service.UserService;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.constraints.Min;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<URI> create(@RequestBody UserDto userDto) {
        try {
            UserDto editedUser = userService.create(userDto.toUser()).toUserDto();
            URI location = ServletUriComponentsBuilder.fromHttpUrl("http://localhost:8080/api/v1/user")
                    .path("/{userID}")
                    .buildAndExpand(editedUser.toUser().getUserID())
                    .toUri();
            return ResponseEntity.created(location).build();
        } catch (Exception exception) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage());
        }
    }


    @PutMapping("/update")
    public UserDto update(@RequestBody UserDto userDto) {
        try {
            return userService.update(userDto.toUser()).toUserDto();
        } catch (Exception exception) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage());
        }
    }

    @GetMapping("/{userID}")
    public ResponseEntity<UserDto> getByUserID(@PathVariable("userID") long userID) {
        try {
            return ResponseEntity.ok(userService.getById(userID).toUserDto());
        } catch (Exception exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, exception.getMessage());
        }
    }

    @GetMapping(value = "/users", params = {"page", "size"})
    public List<UserDto> getAll(@Min(0) int page, @Min(1) int size) {
        return userService.getAll(PageRequest.of(page, size))
                .stream()
                .map(User::toUserDto)
                .collect(Collectors.toList());
    }

    @DeleteMapping("/undo/{userID}")
    public String delete(@PathVariable("userID") long userID) {
        try {
            return userService.delete(userID);
        } catch (Exception exception) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage());
        }
    }
}
