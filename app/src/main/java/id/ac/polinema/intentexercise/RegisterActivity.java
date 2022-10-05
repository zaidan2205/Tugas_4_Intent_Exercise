package id.ac.polinema.intentexercise;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;

import de.hdodenhof.circleimageview.CircleImageView;

public class RegisterActivity extends AppCompatActivity {

    private ImageView input;
    private Button tombol;
    private CircleImageView avatar;
    private EditText nama;
    private EditText email;
    private EditText password;
    private EditText confirm;
    private EditText homepage;
    private EditText about;
    private Uri imageUri = null;
    private boolean isiavatar = false;

    private static final String TAG = RegisterActivity.class.getCanonicalName();
    private static final int GALLERY_REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        avatar = findViewById(R.id.image_profile);
        nama = findViewById(R.id.text_fullname);
        email = findViewById(R.id.text_email);
        password = findViewById(R.id.text_password);
        confirm = findViewById(R.id.text_confirm_password);
        homepage = findViewById(R.id.text_homepage);
        about = findViewById(R.id.text_about);
        input = findViewById(R.id.imageView);
        tombol = findViewById(R.id.button_ok);
        tombol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String pass = password.getText().toString();
                String conf = confirm.getText().toString();
                boolean check = validateinfo(pass, conf);

                if (!isiavatar){
                    Toast.makeText(RegisterActivity.this, "Upload Foto Anda", Toast.LENGTH_SHORT).show();
                }else if (nama.getText().toString().isEmpty()){
                    nama.setError("Nama Tidak Boleh Kosong");
                    Toast.makeText(RegisterActivity.this, "Masukkan Nama Anda", Toast.LENGTH_SHORT).show();
                }else if (email.getText().toString().isEmpty()){
                    email.setError("Email Tidak Boleh Kosong");
                    Toast.makeText(RegisterActivity.this, "Masukkan Email Anda", Toast.LENGTH_SHORT).show();
                }else if (password.getText().toString().isEmpty()){
                    password.setError("Password Tidak Boleh Kosong");
                    Toast.makeText(RegisterActivity.this, "Masukkan Password Anda", Toast.LENGTH_SHORT).show();
                }else if (confirm.getText().toString().isEmpty()){
                    confirm.setError("Konfirmasi Password Tidak Boleh Kosong");
                    Toast.makeText(RegisterActivity.this, "Konfirmasi Password Anda", Toast.LENGTH_SHORT).show();
                }else if (check == false) {
                    confirm.setError("Password Anda Tidak Cocok");
                    Toast.makeText(RegisterActivity.this, "Password Tidak Cocok", Toast.LENGTH_SHORT).show();
                }else if (homepage.getText().toString().isEmpty()){
                    homepage.setError("Konfirmasi Password Tidak Boleh Kosong");
                    Toast.makeText(RegisterActivity.this, "Masukkan Homepage Anda", Toast.LENGTH_SHORT).show();
                }else if (about.getText().toString().isEmpty()){
                    about.setError("Konfirmasi Password Tidak Boleh Kosong");
                    Toast.makeText(RegisterActivity.this, "Masukkan Tentang Anda", Toast.LENGTH_SHORT).show();
                }else{
                    Intent pindah = new Intent(RegisterActivity.this, ProfileActivity.class);
                    pindah.putExtra("nama", nama.getText().toString());
                    pindah.putExtra("email", email.getText().toString());
                    pindah.putExtra("password", password.getText().toString());
                    pindah.putExtra("confirm", confirm.getText().toString());
                    pindah.putExtra("homepage", homepage.getText().toString());
                    pindah.putExtra("about", about.getText().toString());
                    pindah.putExtra("avatar", imageUri.toString());
                    startActivity(pindah);
                }
            }
        });
        input.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI), GALLERY_REQUEST_CODE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_CANCELED){
            Toast.makeText(this, "Cancel Input Image", Toast.LENGTH_SHORT).show();
            return;
        }else if(requestCode == GALLERY_REQUEST_CODE){
            if (data != null){
                try {
                    isiavatar = true;
                    imageUri = data.getData();
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageUri);
                    avatar.setImageBitmap(bitmap);
                }
                catch (IOException e){
                    Toast.makeText(this, "", Toast.LENGTH_SHORT).show();
                    Log.e(TAG, e.getMessage());
                }
            }

        }
    }
    private Boolean validateinfo(String password, String confpass) {
        if(!password.equals(confpass)){
            confirm.requestFocus();
            return false;
        }else{
            return true;
        }
    }
}
