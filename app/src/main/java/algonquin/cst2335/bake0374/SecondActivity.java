package algonquin.cst2335.bake0374;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        Intent fromPrevious = getIntent();
        String emailAddress = fromPrevious.getStringExtra("EmailAddress");
        TextView textView2 = findViewById(R.id.textView2);
        textView2.setText("Welcome back " + emailAddress);
        ImageView profileImage = findViewById(R.id.profileImage);
        TextView phoneNumber = findViewById(R.id.editTextPhone);
        Button callNumber = findViewById(R.id.callNumber);
        Button changePicture = findViewById(R.id.button2);

        callNumber.setOnClickListener( clk-> {
            Intent call = new Intent(Intent.ACTION_DIAL);
            call.setData(Uri.parse("tel:" + phoneNumber));
        });

        changePicture.setOnClickListener( clk-> {
            Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

            if(checkSelfPermission(android.Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED)
                startActivity(cameraIntent);
            else
                requestPermissions(new String[] {Manifest.permission.CAMERA}, 20);

            ActivityResultLauncher<Intent> cameraResult = registerForActivityResult(
                    new ActivityResultContracts.StartActivityForResult(),
                    new ActivityResultCallback<ActivityResult>() {
                        @Override
                        public void onActivityResult(ActivityResult result) {
                            if (result.getResultCode() == Activity.RESULT_OK) {
                                Intent data = result.getData();
                                Bitmap thumbnail = data.getParcelableExtra("data");
                                profileImage.setImageBitmap(thumbnail);
                            }
                        }
                    });
            cameraResult.launch(cameraIntent);
        });

    }
}