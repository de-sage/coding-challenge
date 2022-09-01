package com.dev.codingchallenge.userportal.service;

import com.dev.codingchallenge.userportal.entity.ApplicationUser;
import com.dev.codingchallenge.userportal.exception.UserPortalException;
import com.dev.codingchallenge.userportal.payload.request.AddUserDto;
import com.dev.codingchallenge.userportal.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;


    @Override
    public ApplicationUser addUser(AddUserDto addUserDto) throws UserPortalException {
        ApplicationUser user = ApplicationUser.builder().email(addUserDto.getEmail()).firstName(addUserDto.getFirstName()).lastName(addUserDto.getLastName()).dateOfBirth(addUserDto.getDateOfBirth()).build();
        if(user.getAge() < 18) {
             throw new UserPortalException("user must be 18 years or older");
        }
        return userRepository.save(user);
    }


    @Override
    public ApplicationUser updateUser(AddUserDto addUserDto, long id) {

        return userRepository.findById(id)
                .map(user -> {
                    user.setFirstName(addUserDto.getFirstName());
                    user.setLastName(addUserDto.getLastName());
                    user.setEmail(addUserDto.getEmail());
                    user.setDateOfBirth(addUserDto.getDateOfBirth());
                    return userRepository.save(user);
                })
                .orElseGet(() -> {
                    try {
                        return addUser(addUserDto);
                    } catch (UserPortalException e) {
                        throw new RuntimeException(e);
                    }
                });
    }

    @Override
    public void deleteUser(long id) throws UserPortalException {
        Optional<ApplicationUser> user =  userRepository.findById(id);

        if(user.isPresent() ) {
            userRepository.deleteById(id);
        } else {
            throw new UserPortalException("user with id "+ id +" not found" );
        }
    }
}
