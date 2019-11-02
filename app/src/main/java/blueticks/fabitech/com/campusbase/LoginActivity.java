package blueticks.fabitech.com.campusbase;


import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class LoginActivity extends AppCompatActivity {
    ConnectionDetector connectionDetector;
    EditText etUsername, etPassword;
    TextView signUpTv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_login );
        setRequestedOrientation( ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

//        Toolbar toolbar = findViewById(R.id.login_action_bar);
//        setSupportActionBar(toolbar);
//        if(getSupportActionBar()!= null){
//            getSupportActionBar().setTitle("Register");
//        }
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        getSupportActionBar().setDisplayShowHomeEnabled(true);

        etUsername = findViewById(R.id.index_et);
        etPassword = findViewById(R.id.password_et);
        signUpTv = findViewById(R.id.sign_up_page);


        signUpTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });
    }
    public void Join(View view) {
        final String textUsername = etUsername.getText().toString().trim();
        final String textPassword = etPassword.getText().toString().trim();


        if (!textUsername.isEmpty() && !textPassword.isEmpty()) {

            if (!Validation.indexValidation(textUsername)) {
                etUsername.setError(getResources().getString(R.string.index_error));
                return;
            }
            if (!Validation.passwordValidation(textPassword)) {
                etPassword.setError(getResources().getString(R.string.password_error));
                return;
            }
            if (connectionDetector.isConnected()) {
                MyLoginQueue(textUsername, textPassword);
            } else {
                connectionDetector.alertDialog(LoginActivity.this,
                        getString(R.string.error_connection_title),
                        getString(R.string.connection_error_message));
            }
        } else {
            if (textUsername.isEmpty() && textPassword.isEmpty())
                Toast.makeText(LoginActivity.this, R.string.all_fields_error, Toast.LENGTH_SHORT).show();
            else if(textUsername.isEmpty())
                etUsername.setError(getResources().getString(R.string.error_fill_form));
            else
                etPassword.setError(getResources().getString(R.string.error_fill_form));
        }
    }

    private void MyLoginQueue(String textUsername, String textPassword) {
        // Read from the database
//        myRef.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                // This method is called once with the initial value and again
//                // whenever data at this location is updated.
//                String value = dataSnapshot.getValue(String.class);
//                Log.d(TAG, "Value is: " + value);
//            }
//
//            @Override
//            public void onCancelled(DatabaseError error) {
//                // Failed to read value
//                Log.w(TAG, "Failed to read value.", error.toException());
//            }
//        });
    }

}

