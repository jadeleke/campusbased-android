package blueticks.fabitech.com.campusbase;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.EditText;
import android.widget.MultiAutoCompleteTextView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.List;


/**
 */
public class RegisterActivity extends AppCompatActivity {

    private static final String[] Places = new String[]{"Telecommunication Engineering, FoE",
            "Computer Engineering, FoE", "Marketing, FoITB",
            "Human Resource Management, FoITB", "Banking and Finance, FoITB",
            "Accounting, FoITB", "Management, FoITB",
            " Accounting with Computing", "Economics, FoITB", "Procurement and Logistics, FoITB",
            "Human Resource, FoITB",
            " Entrepreneurship, FoITB"};
    MultiAutoCompleteTextView editText;
    List<String> list;
    Spinner spinner;
    EditText passwordEt, usernameEt, numberEt, emailEt, indexEt;
    ConnectionDetector connectionDetector;
    Button bt_register;
    CheckedTextView checkedTextView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );

        setContentView( R.layout.activity_register );
        editText = findViewById( R.id.multiautocompletextview );

//        Toolbar toolbar = findViewById( R.id.register_action_bar );
//        setSupportActionBar( toolbar );
//        if (getSupportActionBar() != null) {
//            getSupportActionBar().setTitle( "Register" );
//        }
//        getSupportActionBar().setDisplayHomeAsUpEnabled( true );
//        getSupportActionBar().setDisplayShowHomeEnabled( true );
        ArrayAdapter<String> adapter = new ArrayAdapter<>( this, android.R.layout.simple_list_item_1, Places );
        passwordEt = findViewById( R.id.et_password );
        usernameEt = findViewById( R.id.et_username );
        numberEt = findViewById( R.id.et_number );
        emailEt = findViewById( R.id.et_email );
        indexEt = findViewById( R.id.et_index );

        connectionDetector = new ConnectionDetector( this );
        editText.setAdapter( adapter );
        editText.setThreshold( 1 );
        editText.setTokenizer( new MultiAutoCompleteTextView.CommaTokenizer() );
        spinner = findViewById( R.id.spinner );
        bt_register  = findViewById( R.id.register_btn );

        String [] service = {"Morning", "Evening", "Weekend"};
        ArrayAdapter<String> adapters = new ArrayAdapter<>( this,
                android.R.layout.simple_spinner_dropdown_item, service );
        spinner.setAdapter(adapters);
        final int m=0;
//        bt_register.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View v) {
//                if(dualcamera1.isChecked())
//                    m++; // you can save this as checked somewhere
//                if(dualcamera2.isChecked())
//                    m++; // you can save this as checked somewhere
//                if(dualcamera3.isChecked())
//                    m++; // you can save this as checked somewhere
//                if(dualcamera4.isChecked())
//                    m++; // you can save this as checked somewhere
//            }
//        });
//        if(true){
//
//            // show error message
//        }
//        else{
//            // code here
//        }
    }

    public void SignUp(View view) {
        final String indexText = indexEt.getText().toString();
        final String usernameText = usernameEt.getText().toString();
        final String emailText = emailEt.getText().toString().trim();
        final String passwordText = passwordEt.getText().toString().trim();
        final String telephoneText = numberEt.getText().toString();

        if (!usernameText.isEmpty() && !emailText.isEmpty() && !passwordText.isEmpty() && !telephoneText.isEmpty()
                && !indexText.isEmpty()) {
            if (!Validation.userValidation( usernameText )) {
                usernameEt.setError( getString( R.string.name_error ) );
                return;
            }
            if (!Validation.emailValidation( (emailText) )) {
                emailEt.setError( getString( R.string.email_error ) );
                return;
            }
            if (Validation.verifyMobileNumber( telephoneText ) == null) {
                numberEt.setError( getString( R.string.number_error ) );
                return;
            }
            if (Validation.indexValidation( indexText )) {
                indexEt.setError( getString( R.string.index_error ) );
                return;
            }
            if (!Validation.passwordValidation( passwordText )) {
                passwordEt.setError( getString( R.string.password_error ) );
                return;
            }
            if (connectionDetector.isConnected()) {
                MyQueue( usernameText, passwordText, emailText, Validation.verifyMobileNumber( telephoneText ), indexText );
            } else {
                connectionDetector.alertDialog( RegisterActivity.this, getString( R.string.error_connection_title ),
                        getString( R.string.connection_error_message ) );
            }
        } else {
            if (usernameText.isEmpty() && indexText.isEmpty() && passwordText.isEmpty() && telephoneText.isEmpty()) {
                Toast.makeText( RegisterActivity.this, R.string.all_fields_error, Toast.LENGTH_SHORT ).show();
            } else if (usernameText.isEmpty())
                usernameEt.setError( getResources().getString( R.string.error_fill_form ) );
            else if (emailText.isEmpty())
                emailEt.setError( getResources().getString( R.string.error_fill_form ) );
            else if (telephoneText.isEmpty())
                numberEt.setError( getResources().getString( R.string.error_fill_form ) );
            else if (indexText.isEmpty())
                numberEt.setError( getResources().getString( R.string.error_fill_form ) );
            else passwordEt.setError( getResources().getString( R.string.error_fill_form ) );
        }


    }

    private void MyQueue(final String username, String password, final String email, final String telephone, final  String index) {
//            @Override
//            public void onResponse(String response) {
//                try {
//                    JSONObject responseObj = new JSONObject(response);
//                    boolean success = responseObj.getBoolean("success");
//
//                    if (success) {
//                        String responseMessage = responseObj.getString("message");
//                        Toast.makeText(RegisterActivity.this, Base64Encrypt.decrypt(responseMessage),
//                                Toast.LENGTH_LONG).show();

        SharedPreferences mPreferences = getSharedPreferences( "status", MODE_PRIVATE );
        SharedPreferences.Editor mEditor = mPreferences.edit();
        mEditor.putBoolean( "login", true );
        mEditor.apply();

        Intent sendIntent = new Intent( RegisterActivity.this, MainActivity.class );
        sendIntent.putExtra( "username", username );
        sendIntent.putExtra( "number", telephone );
        sendIntent.putExtra( "email", email );
        sendIntent.putExtra( "telephone", telephone );
//        sendIntent.putExtra( "inde", telephone );
//        sendIntent.putExtra( "telephone", telephone );


        startActivity( sendIntent );
        finish();

        // Write a message to the database
//        FirebaseDatabase database = FirebaseDatabase.getInstance();
//        DatabaseReference myRef = database.getReference( "message" );
//
//        myRef.setValue( "Hello, World!" );
//                    } else {
//                        String responseMessage = responseObj.getString("message");
//                        Toast.makeText(RegisterActivity.this, Base64Encrypt.decrypt(responseMessage),
//                                Toast.LENGTH_LONG).show();
//                    }
//                } catch (JSONException e) {

    }
}
