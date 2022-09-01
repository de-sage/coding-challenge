package com.dev.codingchallenge.userportal.service;

import com.dev.codingchallenge.userportal.entity.ApplicationUser;
import com.dev.codingchallenge.userportal.exception.UserPortalException;
import com.dev.codingchallenge.userportal.payload.request.AddUserDto;

public interface UserService {

    public ApplicationUser addUser(AddUserDto addUserDto) throws UserPortalException;

    public ApplicationUser updateUser(AddUserDto addUserDto, long id);

    public void deleteUser(long id) throws UserPortalException;
}
