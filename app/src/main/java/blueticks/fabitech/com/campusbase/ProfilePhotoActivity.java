package blueticks.fabitech.com.campusbase;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Toast;

import com.theartofdev.edmodo.cropper.CropImage;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class ProfilePhotoActivity extends AppCompatActivity {

    public static Bitmap bitmap;
    ImageView ivPhoto;
    Uri imageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_photo);

//        Toolbar toolbar = findViewById(R.id.photo_action_bar);
//        setSupportActionBar(toolbar);
//        if (getSupportActionBar() != null) {
//            getSupportActionBar().setTitle(R.string.edit_profile);
//            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//            getSupportActionBar().setDisplayShowHomeEnabled(true);
//        }

        ivPhoto = findViewById(R.id.iv_profile_photo);
        try {
            bitmap = BitmapFactory.decodeStream(ProfilePhotoActivity.this.
                    openFileInput("profile_image"));
            ivPhoto.setImageBitmap(bitmap);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);

            if (resultCode == RESULT_OK) {
                imageUri = result.getUri();

                try {
                    bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageUri);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                ivPhoto.setImageBitmap(bitmap);
            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                Exception error = result.getError();
                Toast.makeText(ProfilePhotoActivity.this, (CharSequence) error, Toast.LENGTH_SHORT).show();
            }
        }
        createImageFromBitmap();
    }

    public String getStringImage(Bitmap bitmap) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        byte[] imageByteArray = bytes.toByteArray();
        return Base64.encodeToString(imageByteArray, Base64.DEFAULT);
    }

    public void createImageFromBitmap() {
        String fileName = "profile_image";
        try {
            ByteArrayOutputStream bytes = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
            FileOutputStream fo = openFileOutput(fileName, Context.MODE_PRIVATE);
            fo.write(bytes.toByteArray());
            fo.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater mMenuInflater = getMenuInflater();
        mMenuInflater.inflate(R.menu.edit_image, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.edit_profile) {
            CropImage.activity().setAspectRatio(1, 1)
                    .setActivityTitle("Edit Photo").start(ProfilePhotoActivity.this );
        }
        return true;
    }

}
