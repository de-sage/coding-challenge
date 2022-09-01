package com.dev.codingchallenge.userportal.assembler;


import com.dev.codingchallenge.userportal.controller.UserController;
import com.dev.codingchallenge.userportal.entity.ApplicationUser;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class UserRepresentationAssembler implements RepresentationModelAssembler<ApplicationUser, EntityModel<ApplicationUser>> {

    @Override
    public org.springframework.hateoas.@NotNull EntityModel<ApplicationUser> toModel(@NotNull ApplicationUser entity) {
        return EntityModel.of(entity,
                linkTo(methodOn(UserController.class).findById(entity.getId())).withSelfRel(),
                linkTo(methodOn(UserController.class).deleteById(entity.getId())).withRel("delete-user"),
                linkTo(UserController.class).withRel("users"));
    }
}
