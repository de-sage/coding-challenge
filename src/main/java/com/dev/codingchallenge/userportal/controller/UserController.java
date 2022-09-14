package com.dev.codingchallenge.userportal.controller;

import com.dev.codingchallenge.userportal.assembler.UserRepresentationAssembler;
import com.dev.codingchallenge.userportal.entity.ApplicationUser;
import com.dev.codingchallenge.userportal.exception.UserPortalException;
import com.dev.codingchallenge.userportal.payload.request.AddUserDto;
import com.dev.codingchallenge.userportal.repository.UserRepository;
import com.dev.codingchallenge.userportal.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserService userService;

    @Autowired
    private UserRepresentationAssembler recordAssembler;

    @Autowired
    private PagedResourcesAssembler<ApplicationUser> pagedResourcesAssembler;


    @GetMapping({""})
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> getUsers(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "10") int size
    ) {
        Page<ApplicationUser> users;

        Pageable pageable = PageRequest.of(page, size);
        users = userRepository.findAll(pageable);

        PagedModel<EntityModel<ApplicationUser>> collModel = pagedResourcesAssembler.toModel(users, recordAssembler);
        return ResponseEntity.ok().body(collModel);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable("id") Long id) {
        Optional<ApplicationUser> user = userRepository.findById(id);

        if (user.isPresent()) {
            return ResponseEntity.ok().body(recordAssembler.toModel(user.get()));
        }
        return ResponseEntity.notFound().build();
    }


    @PostMapping("")
//    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> addContact(@Valid @RequestBody AddUserDto addUserDto) throws UserPortalException {
        ApplicationUser user = userService.addUser(addUserDto);
        return ResponseEntity.ok().body(user);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateById(@Valid @RequestBody AddUserDto addUserDto, @PathVariable("id") Long id) {
        ApplicationUser user = userService.updateUser(addUserDto, id);
        return ResponseEntity.ok().body(user);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id") Long id) throws UserPortalException {
        userService.deleteUser(id);
        return ResponseEntity.ok().build();
    }
}
