package com.example.phasetwo.activities.ui.login;

import android.util.Log;
import android.util.Patterns;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.phasetwo.R;
import com.example.phasetwo.activities.data.login.FirebaseLoginRepository;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseUser;

import java.util.Objects;

public class LoginViewModel extends ViewModel {

    private static final String TAG = LoginViewModel.class.getSimpleName();

    private FirebaseLoginRepository repository;

    private static final int MINIMUM_PASSWORD_LENGTH = 5;
    private MutableLiveData<LoginFormState> loginFormState = new MutableLiveData<>();
    private MutableLiveData<LoginResult> loginResult = new MutableLiveData<>();

    LoginViewModel(FirebaseLoginRepository repository) {
        this.repository = repository;
    }

    LiveData<LoginFormState> getLoginFormState() {
        return loginFormState;
    }

    LiveData<LoginResult> getLoginResult() {
        return loginResult;
    }

    public void login(String email, String password) {
        // can be launched in a separate asynchronous job
        repository.login(email, password, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    FirebaseUser user = Objects.requireNonNull(task.getResult()).getUser();

                    loginResult.setValue(new LoginResult(new LoggedInUserView(
                            Objects.requireNonNull(user).getUid(), user.getEmail())));
                } else {
                    Exception e = task.getException();
                    Log.e(TAG, "onComplete: ", e);
                    loginResult.setValue(new LoginResult(R.string.login_failed));
                }
            }
        });
    }

    public void registerUser(String email, String password) {
        repository.createNewUser(email, password, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    FirebaseUser user = Objects.requireNonNull(task.getResult()).getUser();

                    loginResult.setValue(new LoginResult(new LoggedInUserView(
                            Objects.requireNonNull(user).getUid(), user.getEmail())));
                } else {
                    Exception e = task.getException();
                    Log.e(TAG, "onComplete: ", e);
                    loginResult.setValue(new LoginResult(R.string.login_failed));
                }
            }
        });
    }

    public void loginDataChanged(String username, String password) {
        if (!isUserNameValid(username)) {
            loginFormState.setValue(new LoginFormState(R.string.invalid_username, null));
        } else if (!isPasswordValid(password)) {
            loginFormState.setValue(new LoginFormState(null, R.string.invalid_password));
        } else {
            loginFormState.setValue(new LoginFormState(true));
        }
    }

    // A placeholder username validation check
    private boolean isUserNameValid(String username) {
        if (username == null) {
            return false;
        }
        if (username.contains("@")) {
            return Patterns.EMAIL_ADDRESS.matcher(username).matches();
        } else {
            return !username.trim().isEmpty();
        }
    }

    // A placeholder password validation check
    private boolean isPasswordValid(String password) {
        return password != null && password.trim().length() > MINIMUM_PASSWORD_LENGTH;
    }
}
