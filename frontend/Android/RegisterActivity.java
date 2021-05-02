package com.pinnaclesoftwaresolutions.ews;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseUser;

public class RegisterActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    Button btnSignUp;
    EditText etEmailRegister, etPasswordRegister, etConfirmPasswordRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAuth = FirebaseAuth.getInstance();
        btnSignUp = findViewById(R.id.btnSignUp);
        etEmailRegister = findViewById(R.id.etEmailRegister);
        etPasswordRegister = findViewById(R.id.etPasswordRegister);
        etConfirmPasswordRegister = findViewById(R.id.etConfirmPasswordRegister);



        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = etEmailRegister.getText().toString().trim();
                String password = etPasswordRegister.getText().toString().trim();
                String confirmPassword = etConfirmPasswordRegister.getText().toString().trim();

                if( !(email.isEmpty()) && !(password.isEmpty()))
                {

                    if(confirmPassword.equals(password))
                    {

                        //Add Email Verification
                        mAuth.createUserWithEmailAndPassword(email, password)
                                .addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        if (task.isSuccessful()) {

                                            //Go to loginActivity
                                            Intent loginActivityIntent = new Intent(RegisterActivity.this, loginActivity.class);
                                            startActivity(loginActivityIntent);


                                        }
                                        if(!task.isSuccessful()){
                                            FirebaseAuthException e = (FirebaseAuthException )task.getException();
                                            Toast.makeText(RegisterActivity.this, "Failed Registration: "+e.getMessage(), Toast.LENGTH_SHORT).show();
                                            //message.hide();
                                            return;
                                        }

                                        // ...
                                    }
                                });
                    } else
                    {
                        Toast.makeText(RegisterActivity.this, "Passwords Do Not Match", Toast.LENGTH_SHORT).show();
                    }


                } else
                {
                    //If Fields are Empty
                    Toast.makeText(RegisterActivity.this, "Please Fill In Any Empty Fields", Toast.LENGTH_SHORT).show();
                }




            }
        });




    }

}
