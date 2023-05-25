public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding variableBinding;
    private MainViewModel model;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        model = new ViewModelProvider(this).get(MainViewModel.class);

        variableBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(variableBinding.getRoot());

        CharSequence text = "The value is now:" + isChecked;
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(this, text, duration);

        model.isSelected.observe (this, selected -> {
            variableBinding.checkBox.setChecked(selected);
            variableBinding.radioButton.setChecked(selected);
            variableBinding.switch1.setChecked(selected);
        });

        radioButton.setOnCheckedChangeListener((btn, isChecked) -> {
            model.isSelected.postValue(isChecked);
            textview.setText("Do you drink coffee?" + isChecked);
            toast.show();
        } );
        checkBox.setOnCheckedChangeListener((btn, isChecked) -> {
            model.isSelected.postValue(isChecked);
            textview.setText("Do you drink coffee?" + isChecked);
            toast.show();
        });
        switch1.setOnCheckedChangeListener((btn, isChecked) -> {
            model.isSelected.postValue(isChecked);
            textview.setText("Do you drink coffee?" + isChecked);
            toast.show();
        } );

        variableBinding.myimagebutton.setOnClickListener(click -> {
            Toast stats = Toast.makeText(
                    this,
                    "The width = " + getMeasuredWidth() +
                    " and height = " + getMeasuredHeight(),
                    Toast.LENGTH_SHORT);
            stats.show();
        })

        variableBinding.textview.setText(model.editString);
        variableBinding.mybutton.setOnClickListener(click -> {
            model.editString = variableBinding.my_edit_text.getText().toString();
            variableBinding.textview.setText("Your edit text has: " + model.editString);
        });
    }
}
