package com.example.phasetwo.logic;

import com.example.phasetwo.common.UserAlreadyExistsException;
import com.example.phasetwo.common.UserDoesNotExistException;
import com.example.phasetwo.common.UserType;

import java.util.List;

public interface UserManagementService {

    UserEntity createNewUser(String name, String email, String password, UserType type) throws UserAlreadyExistsException;

    boolean isUserExistByEmail(String email);

    UserEntity getUserByEmail(String email) throws UserDoesNotExistException;

    List<UserEntity> getAllUsers();

    List<UserEntity> getAllProducers();

    List<UserEntity> getAllConsumers();

    void deleteUser(String email) throws UserDoesNotExistException;
}
