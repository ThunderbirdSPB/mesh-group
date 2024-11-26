package com.group.mesh.web;

import com.group.mesh.config.log.IntegrationLog;
import com.group.mesh.db.entity.User;
import com.group.mesh.service.UserService;
import com.group.mesh.web.dto.request.GetUserByEmailReq;
import com.group.mesh.web.dto.request.GetUserByNameReq;
import com.group.mesh.web.dto.request.GetUserByPhoneReq;
import com.group.mesh.web.dto.request.GetUsersByBirthDateReq;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

/**
 * In a normal application I'd use dto and mapstruct, but it was not specified in the task and
 * I don't have so much time
 */
@Validated
@RestController
@RequestMapping(value = "api/v1/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/byBirthDate")
    @IntegrationLog
    public List<User> getUsersByDateOfBirth(@Valid @RequestBody GetUsersByBirthDateReq dto) {
        return userService.getUsersByDateOfBirth(dto.getPage(), dto.getSize(), dto.getBirthDate());
    }

    @GetMapping("/byPhone")
    @IntegrationLog
    public User getUserByPhone(@Valid @RequestBody GetUserByPhoneReq dto) {
        return userService.getUserByPhone(dto.getPhone());
    }

    @GetMapping("/byEmail")
    @IntegrationLog
    public User getUserByEmail(@Valid @RequestBody GetUserByEmailReq dto) {
        return userService.getUserByEmail(dto.getEmail());
    }

    @GetMapping("/byName")
    @IntegrationLog
    public List<User> getUserByName(@Valid @RequestBody GetUserByNameReq dto) {
        return userService.getUsersByName(dto.getPage(), dto.getSize(), dto.getName());
    }
}
