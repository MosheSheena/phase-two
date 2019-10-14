package com.example.phasetwo.logic.stubs;

import com.example.phasetwo.common.UserAlreadyExistsException;
import com.example.phasetwo.common.UserDoesNotExistException;
import com.example.phasetwo.common.UserType;
import com.example.phasetwo.logic.UserEntity;
import com.example.phasetwo.logic.UserManagementService;

import java.util.ArrayList;
import java.util.List;

public class StubUsersService implements UserManagementService {

    private static volatile StubUsersService sSoleInstance;

    private List<UserEntity> userEntities;

    private StubUsersService() {
        if (sSoleInstance != null) {
            throw new RuntimeException("Use getInstance() method to get the single instance of this class");
        }

        userEntities = new ArrayList<>();
    }

    public static StubUsersService getInstance() {
        if (sSoleInstance == null) {
            synchronized (StubUsersService.class) {
                if (sSoleInstance == null) {
                    sSoleInstance = new StubUsersService();
                }
            }
        }

        return sSoleInstance;
    }

    @Override
    public UserEntity createNewUser(String name, String email, String password, UserType type) throws UserAlreadyExistsException {
        if (isUserExistByEmail(email))
            throw new UserAlreadyExistsException("user with email: " + email + " already exists");

        UserEntity newUser = new UserEntity(name, email, password, type);
        userEntities.add(newUser);
        return newUser;
    }

    @Override
    public boolean isUserExistByEmail(String email) {
        for (UserEntity user:userEntities
             ) {
            if (user.getEmail().equals(email))
                return true;
        }
        return false;
    }

    @Override
    public UserEntity getUserByEmail(String email) throws UserDoesNotExistException {
        if (!isUserExistByEmail(email))
            throw new UserDoesNotExistException("no user with email: " + email);
        for (UserEntity userEntity:userEntities
             ) {
            if (userEntity.getEmail().equals(email))
                return userEntity;
        }
        return null;
    }

    @Override
    public List<UserEntity> getAllUsers() {
        return userEntities;
    }

    @Override
    public List<UserEntity> getAllProducers() {
        List<UserEntity> results = new ArrayList<>();

        for (UserEntity user: userEntities
             ) {
            if (user.getUserType().equals(UserType.PRODUCER))
                results.add(user);
        }
        return results;
    }

    @Override
    public List<UserEntity> getAllConsumers() {
        List<UserEntity> results = new ArrayList<>();

        for (UserEntity user: userEntities
        ) {
            if (user.getUserType().equals(UserType.CONSUMER))
                results.add(user);
        }
        return results;
    }

    @Override
    public void deleteUser(String email) throws UserDoesNotExistException {
        if (!isUserExistByEmail(email))
            throw new UserDoesNotExistException("no user with email: " + email);
        userEntities.remove(getUserByEmail(email));
    }
}
