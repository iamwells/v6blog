package io.github.iamwells.v6blog.server.controller;

import io.github.iamwells.v6blog.server.dto.AuthUserDTO;
import io.github.iamwells.v6blog.server.service.AuthUserService;
import io.github.iamwells.v6blog.server.vo.AuthUserQueryVO;
import io.github.iamwells.v6blog.server.vo.AuthUserUpdateVO;
import io.github.iamwells.v6blog.server.vo.AuthUserVO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@Validated
@RestController
@RequestMapping("/auth/user")
public class AuthUserController {

    @Autowired
    private AuthUserService authUserService;

    @PostMapping
    public String save(@Valid @RequestBody AuthUserVO vO) {
        return authUserService.save(vO).toString();
    }

    @DeleteMapping("/{id}")
    public void delete(@Valid @NotNull @PathVariable("id") Long id) {
        authUserService.delete(id);
    }

    @PutMapping("/{id}")
    public void update(@Valid @NotNull @PathVariable("id") Long id,
                       @NotNull @RequestBody AuthUserUpdateVO vO) {
        authUserService.update(id, vO);
    }

    @GetMapping("/{id}")
    public AuthUserDTO getById(@Valid @NotNull @PathVariable("id") Long id) {
        return authUserService.getById(id);
    }

    @GetMapping
    public Page<AuthUserDTO> query(@Valid AuthUserQueryVO vO) {
        return authUserService.query(vO);
    }
}
