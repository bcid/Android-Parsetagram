package com.example.parsetagram;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class SignUpActivity extends AppCompatActivity {

    public static final String TAG = "SignUpActivity";
    private EditText etSUUsername;
    private EditText etEmail;
    private EditText etSUPassword;
    private Button btnSignUp;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        if (ParseUser.getCurrentUser() != null){
            goMainActivity();
        }

        etSUUsername = findViewById(R.id.etSUUsername);
        etEmail = findViewById(R.id.etEmail);
        etSUPassword = findViewById(R.id.etSUPassword);
        btnSignUp = findViewById(R.id.btnSignUp);
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "onClick SignUp button");
                String username = etSUUsername.getText().toString();
                String password = etSUPassword.getText().toString();
                String email = etEmail.getText().toString();
                signUpUser(username, password, email);
            }

            private void signUpUser(String username, String password, String email) {
                Log.i(TAG, "attempting to signup new user"+ username);
                // Create the ParseUser
                ParseUser user = new ParseUser();
                // Set core properties
                user.setUsername(username);
                user.setPassword(password);
                user.setEmail(email);
                // Set custom properties
                //user.put("phone", "650-253-0000");
                // Invoke signUpInBackground
                user.signUpInBackground(new SignUpCallback() {
                    public void done(ParseException e) {
                        if (e == null) {
                            // Hooray! Let them use the app now.
                            goMainActivity();
                            Toast.makeText(SignUpActivity.this, "Success!", Toast.LENGTH_SHORT).show();
                        } else {
                            // Sign up didn't succeed. Look at the ParseException
                            // to figure out what went wrong
                            Log.e(TAG, "Issue with SignUp", e);
                            Toast.makeText(SignUpActivity.this, "Issue with SignUp", Toast.LENGTH_SHORT).show();
                            return;

                        }
                    }
                });

            }
        });

    }

    private void goMainActivity() {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
        finish();
    }





}
