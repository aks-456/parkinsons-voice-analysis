package com.pinnaclesoftwaresolutions.ews;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class loginActivity extends AppCompatActivity {
    Button btnSignIn;
    TextView tvCreateAccount;
    EditText etEmailLogin, etPasswordLogin;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnSignIn = findViewById(R.id.btnSignIn);
        tvCreateAccount = findViewById(R.id.tvCreateAccount);
        etEmailLogin = findViewById(R.id.etEmailLogin);
        etPasswordLogin = findViewById(R.id.etPasswordLogin);
        mAuth = FirebaseAuth.getInstance();


        //Check if User is already signed in
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            //FirebaseUser user = mAuth.getCurrentUser();
            updateUI(user);
        } else {
            updateUI(null);
        }

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = etEmailLogin.getText().toString().trim();
                String password = etPasswordLogin.getText().toString().trim();

                if(!(email.isEmpty()) && !(password.isEmpty()))
                {
                    //If Email and Password are not empty

                    //Authenticate Google Sign In
                    mAuth.signInWithEmailAndPassword(email, password)
                            .addOnCompleteListener(loginActivity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        // Sign in success, update UI with the signed-in user's information
                                        FirebaseUser user = mAuth.getCurrentUser();
                                        updateUI(user);
                                    } else {
                                        // If sign in fails, display a message to the user.
                                        Toast.makeText(loginActivity.this, "Sign In Unsuccessful, Please Try Again", Toast.LENGTH_SHORT).show();
                                        updateUI(null);
                                        // ...
                                    }

                                    // ...
                                }
                            });
                    
                } else
                {
                    //If Email or Password are empty
                    Toast.makeText(loginActivity.this, "Please Fill In Any Empty Fields", Toast.LENGTH_SHORT).show();
                }


            }
        });

    }

    private void updateUI(FirebaseUser user) {
        if (user != null) {

            //If user is logged in, go to main activity
            Intent userLoggedInIntent = new Intent(loginActivity.this, MainActivity.class);
            startActivity(userLoggedInIntent);

        }
    }


    public void createAccount(View view)
    {
        //Register Activity
        Intent createAccountIntent = new Intent(loginActivity.this, RegisterActivity.class);
        startActivity(createAccountIntent);
    }
}
