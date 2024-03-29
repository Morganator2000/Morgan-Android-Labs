package algonquin.cst2335.bake0374;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * The MainLauncher for the app.
 * @author bake0374
 * @version 1.0
 */
public class MainActivity extends AppCompatActivity {
    /** This is the text that will display*/
    TextView tv = null;
    /** This is the bar where the password will be entered*/
    EditText et = null;
    /** this is the button for checking the password*/
    Button btn = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView tv = findViewById(R.id.textView);
        EditText et = findViewById(R.id.editText);
        Button btn = findViewById(R.id.button);

        btn.setOnClickListener( clk ->{
            String password = et.getText().toString();
            if (checkPasswordComplexity(password)){
                tv.setText("Your password meets the requirements");
            } else {
                tv.setText("You shall not pass!");
            }
        });
    }

    /**
     * This function makes sure that the password is suitable complex enough.
     * @param pw The string we are checking
     * @return True if is is complex enough, false if not.
     */
    private boolean checkPasswordComplexity(String pw) {
        boolean foundUpper, foundLower, foundNumber, foundSpecial;
        foundUpper = foundLower = foundNumber = foundSpecial = false;

        for (int i = 0; i < pw.length(); i++) {
            char c = pw.charAt(i);
            if (Character.isLowerCase(c)) {
                foundLower = true;
            } else if (Character.isUpperCase(c)) {
                foundUpper = true;
            } else if (Character.isDigit(c)) {
                foundNumber = true;
            } else {
                foundSpecial = true;
            }
        }
        if(!foundUpper) {
            Toast.makeText(this, "No uppercase character found", Toast.LENGTH_SHORT).show();// Say that they are missing an upper case letter;
            return false;
        }

        else if(!foundLower) {
            Toast.makeText(this, "No lowercase character found", Toast.LENGTH_SHORT).show();// Say that they are missing a lower case letter;
            return false;
        }
        else if(!foundNumber) {
            Toast.makeText(this, "No number found", Toast.LENGTH_SHORT).show(); // Say that they are missing a lower case letter;
            return false;
        }
        else if(!foundSpecial) {
            Toast.makeText(this, "No special character found", Toast.LENGTH_SHORT).show(); // Say that they are missing a lower case letter;
            return false;
        } else {
            return true;
        }
    }
}