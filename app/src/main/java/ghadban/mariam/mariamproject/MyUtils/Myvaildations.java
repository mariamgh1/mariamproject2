package ghadban.mariam.mariamproject.MyUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 * מחלקה שבודקת תקינות שדות כמו אימיל וסיסמא ועוד
 */
public class Myvaildations
{
    private Pattern pattern;
    private Matcher matcher;
    private static final String PASSWORD_PATTERN = "((?=.*[a-z])(?=.*\\d)(?=.*[A-Z])(?=.*[@#$%!]).{8,40})";
    public Myvaildations() {
        pattern = Pattern.compile(PASSWORD_PATTERN);
    }

    public boolean validatePasword(final String password) {

        matcher = pattern.matcher(password);
        return matcher.matches();

    }

}
