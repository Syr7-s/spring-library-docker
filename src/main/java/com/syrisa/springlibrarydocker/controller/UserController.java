package com.syrisa.springlibrarydocker.controller;


import com.syrisa.springlibrarydocker.dto.UserDto;
import com.syrisa.springlibrarydocker.model.impl.User;
import com.syrisa.springlibrarydocker.service.UserService;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.constraints.Min;
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
    public UserDto create(@RequestBody UserDto userDto) {
        try {
            return userService.create(userDto.toUser()).toUserDto();
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
    public UserDto getByUserID(@PathVariable("userID") long userID) {
        try {
            return userService.getById(userID).toUserDto();
        } catch (Exception exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, exception.getMessage());
        }
    }

    @GetMapping(value = "/users", params = {"page", "size"})
    public List<UserDto> getAll(@Min(0) int page, @Min(0) int size) {
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
