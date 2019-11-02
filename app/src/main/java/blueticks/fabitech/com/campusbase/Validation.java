package blueticks.fabitech.com.campusbase;


import android.support.annotation.NonNull;

import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validation {

    public static boolean emailValidation(String email){

        Pattern pattern;
        Matcher matcher;
        final String EMAIL_PATTERN = "^[_a-zA-Z0-9-]"
                + "+(\\.[_a-zA-Z0-9-]+)" +
                "*@[a-zA-Z0-9]+(\\.[a-zA-Z0-9]+)*(\\.[a-zA-Z]{2,})$";
        pattern = Pattern.compile(EMAIL_PATTERN);
        matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public static boolean userValidation(String username){

        Pattern pattern;
        Matcher matcher;

        final String USERNAME_PATTERN = "^([\\w]{5,})*$";
        pattern = Pattern.compile(USERNAME_PATTERN);
        matcher = pattern.matcher(username);
        return matcher.matches();
    }

    public static boolean passwordValidation(String pass){

        Pattern pattern;
        Matcher matcher;

        final String PASSWORD_PATTERN = "^(?=.*[A-Z])(?=.*[a-z])(?=.*[!#@$&*_()%])(?=.*[\\d]).{8,}$";
        pattern = Pattern.compile(PASSWORD_PATTERN);
        matcher = pattern.matcher(pass);
        return matcher.matches();
    }

    public static String verifyMobileNumber(String number) {

        if (number.length() < 9 || number.length() > 10) {
            return null;
        }

        PhoneNumberUtil phoneUtil = PhoneNumberUtil.getInstance();
        Phonenumber.PhoneNumber phoneNumber = null;
        try {
            phoneNumber = phoneUtil.parse(number, "GH");
        } catch (NumberParseException e) {
            e.printStackTrace();
        }
        String interFormat = null;

        if (phoneUtil.isValidNumber(phoneNumber)) {
            if (phoneNumber != null) {
                interFormat = phoneUtil.format(phoneNumber, PhoneNumberUtil.PhoneNumberFormat.INTERNATIONAL);
            }
            return interFormat;
        } else {
            return null;
        }
    }


    public static boolean indexValidation(String index){

        Pattern pattern;
        Matcher matcher;
        final String INDEX_PATTERN = "^[_a-zA-Z0-9-]"
                + "+(\\.[_a-zA-Z0-9-]+)"
                //+//"*@[a-zA-Z0-9]+(\\.[a-zA-Z0-9]+)*(\\.[a-zA-Z]{2,})$"
        ;
        if (index.length() < 8 || index.length() > 9) {
            return Boolean.parseBoolean( null );
        }
        pattern = Pattern.compile(INDEX_PATTERN);
        matcher = pattern.matcher(index);
        return matcher.matches();
    }

}
