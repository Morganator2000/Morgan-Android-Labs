public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding variableBinding;
    private MainViewModel model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        model = new ViewModelProvider(this).get(MainViewModel.class);

        variableBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(variableBinding.getRoot());

        variableBinding.textview.setText(model.editString);
        variableBinding.mybutton.setOnClickListener(click -> {
            model.editString = variableBinding.my_edit_text.getText().toString();
            variableBinding.textview.setText("Your edit text has: " + model.editString);
        });
    }
}
