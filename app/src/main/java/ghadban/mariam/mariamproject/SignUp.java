package ghadban.mariam.mariamproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class SignUp extends AppCompatActivity {
    private EditText etFirstName,etLastName,etPassword,etVrPassword,etEmail,etPhone;
    private Button Savebtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        etFirstName=findViewById(R.id.etFirstName);
        etLastName=findViewById(R.id.etLastName);
        etPassword=findViewById(R.id.etPassword);
        etVrPassword=findViewById(R.id.etVrPassword);
        etEmail=findViewById(R.id.etEmail);
        etPhone=findViewById(R.id.etPhone);
        Savebtn=findViewById(R.id.Savebtn);
    }

    private void checkForm()//hl 3bahn sa7(sign up)
    {
        String firstn=etFirstName.getText().toString();
        String lastn=etLastName.getText().toString();
        String pass=etPassword.getText().toString();
        String verPass=etVrPassword.getText().toString();
        String email=etEmail.getText().toString();
        String phone=etPhone.getText().toString();
        String save=Savebtn.getText().toString();

        boolean isOK=true;

        if(firstn.length()<2)
        {
            isOK=false;
            etFirstName.setError("at least two letter");
        }

        if (email.length()<5 || (email.indexOf('@')==0 ) || email.indexOf('@')>=email.length()-2 || email.indexOf('.')==0
                ||email.indexOf('.')>=email.length()-1 ||email.lastIndexOf('.')<email.indexOf('@'))
        {
            isOK=false;
            etEmail.setError("Wrong E-mail. Try again");
        }

    }