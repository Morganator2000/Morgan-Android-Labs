package algonquin.cst2335.bake0374.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.widget.Toast;

import algonquin.cst2335.bake0374.data.MainViewModel;
import algonquin.cst2335.bake0374.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding variableBinding;
    private MainViewModel model;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        model = new ViewModelProvider(this).get(MainViewModel.class);

        variableBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(variableBinding.getRoot());



        model.isSelected.observe (this, selected -> {
            variableBinding.checkBox.setChecked(selected);
            variableBinding.radioButton.setChecked(selected);
            variableBinding.switch1.setChecked(selected);
        });

        variableBinding.radioButton.setOnCheckedChangeListener((btn, isChecked) -> {
            model.isSelected.postValue(isChecked);
           // textview.setText("Do you drink coffee?" + isChecked);
            CharSequence text = "The value is now:" + isChecked;
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(this, text, duration);
            toast.show();
        } );
        variableBinding.checkBox.setOnCheckedChangeListener((btn, isChecked) -> {
            model.isSelected.postValue(isChecked);
           // textview.setText("Do you drink coffee?" + isChecked);
            CharSequence text = "The value is now:" + isChecked;
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(this, text, duration);
            toast.show();
        });
        variableBinding.switch1.setOnCheckedChangeListener((btn, isChecked) -> {
            model.isSelected.postValue(isChecked);
           // textview.setText("Do you drink coffee?" + isChecked);
            CharSequence text = "The value is now:" + isChecked;
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(this, text, duration);
            toast.show();
        } );

        variableBinding.myimagebutton.setOnClickListener(click -> {
            Toast stats = Toast.makeText(
                    this,
                    "The width = " + variableBinding.image.getMeasuredWidth() +
                            " and height = " + variableBinding.image.getMeasuredHeight(),
                    Toast.LENGTH_SHORT);
            stats.show();
        });

        variableBinding.textview.setText(model.editString);
        variableBinding.mybutton.setOnClickListener(click -> {
            model.editString = variableBinding.myEditText.getText().toString();
            variableBinding.textview.setText("Your edit text has: " + model.editString);
        });
    }
}