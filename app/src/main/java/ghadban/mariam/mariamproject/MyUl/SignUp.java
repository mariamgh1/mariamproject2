package ghadban.mariam.mariamproject.MyUl;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCanceledListener;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import ghadban.mariam.mariamproject.MyUtils.Myvaildations;
import ghadban.mariam.mariamproject.R;

public class SignUp extends AppCompatActivity {
    private EditText etFirstName, etLastName, etPassword, etVerPassword, etEmail, etPhone;
    private Button Savebtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        etFirstName = findViewById(R.id.etFirstName);
        etLastName = findViewById(R.id.etLastName);
        etPassword = findViewById(R.id.etPassword);
        etVerPassword = findViewById(R.id.etVerPassword);
        etEmail = findViewById(R.id.etEmail);
        etPhone = findViewById(R.id.etPhone);
        Savebtn = findViewById(R.id.Savebtn);
    }

    private void validateForm()//hl 3bahn sa7(sign up)
    {
        String firstn = etFirstName.getText().toString();
        String lastn = etLastName.getText().toString();
        String pass = etPassword.getText().toString();
        String verPass = etVerPassword.getText().toString();
        String email = etEmail.getText().toString();
        String phone = etPhone.getText().toString();
        String save = Savebtn.getText().toString();

        boolean isOK = true;

        if (firstn.length() < 2) {
            isOK = false;
            etFirstName.setError("at least two letter");
        }

        if (email.length() < 5 || (email.indexOf('@') == 0) || email.indexOf('@') >= email.length() - 2 || email.indexOf('.') == 0
                || email.indexOf('.') >= email.length() - 1 || email.lastIndexOf('.') < email.indexOf('@')) {
            isOK = false;
            etEmail.setError("Wrong E-mail. Try again");
        }
        if(!etPassword.equals(etVerPassword)){
            isOK=false;
            etVerPassword.setError("Password must be the same");
        }
        else {
            Myvaildations myVaildations = new Myvaildations();
            if (myVaildations.validatePasword(pass) == false) {
                isOK = false;
                etPassword.setError("Invalid Password");
            }
        }

        if(isOK)// isok=true
        {
            //todo : create account  and return to sign in screen/ close this screen

            createNewAccount(firstn,lastn,pass,email,phone);
        }
    }

    /**
     *
     * @param firstn
     * @param lastn
     * @param pass
     * @param email
     * @param phone
     */

    private void createNewAccount(String firstn, String lastn, String pass, String email, String phone)

    {
        FirebaseAuth auth=FirebaseAuth.getInstance();
        OnCompleteListener listener = new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(SignUp.this, "Successfully Signin up", Toast.LENGTH_SHORT).show();
                    finish();
                }
                else
                    {
                        Toast.makeText(SignUp.this, "Signing up, failed" +task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        etEmail.setError("signing up, failed"+task.getException().getMessage());
                    }
            }
        };
       auth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener(listener);
    }
}
