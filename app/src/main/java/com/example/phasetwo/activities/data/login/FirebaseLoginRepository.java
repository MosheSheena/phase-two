package com.example.phasetwo.activities.data.login;

import com.example.phasetwo.activities.data.model.LoggedInUser;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**
 * Class that requests authentication and user information from the remote data source and
 * maintains an in-memory cache of login status and user credentials information.
 */
public class FirebaseLoginRepository {

    private static volatile FirebaseLoginRepository instance;

    private FirebaseAuth mAuth;

    // private constructor : singleton access
    private FirebaseLoginRepository() {
        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();
    }

    public static FirebaseLoginRepository getInstance() {
        if (instance == null) {
            instance = new FirebaseLoginRepository();
        }
        return instance;
    }

    public void createNewUser(String email, String password, OnCompleteListener<AuthResult> listener) {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(listener);
    }

    public void login(String email, String password, OnCompleteListener<AuthResult> listener) {
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(listener);
    }

    public String getCurrentLoggedInUser() {
        FirebaseUser user = mAuth.getCurrentUser();
        if (user != null) {
            return user.getUid();
        } else {
            return null;
        }
    }

    public void logOutCurrentUser() {
        mAuth.signOut();
    }
}