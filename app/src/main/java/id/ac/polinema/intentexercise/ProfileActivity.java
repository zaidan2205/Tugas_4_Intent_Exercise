package id.ac.polinema.intentexercise;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileActivity extends AppCompatActivity {
    private CircleImageView foto;
    private TextView about;
    private TextView nama;
    private TextView email;
    private TextView homepage;
    private Button tombol;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        String name = getIntent().getExtras().getString("nama");
        String mail = getIntent().getExtras().getString("email");
        final String home = getIntent().getExtras().getString("homepage");
        String aboutme = getIntent().getExtras().getString("about");
        String avatar = getIntent().getExtras().getString("avatar");

        foto = findViewById(R.id.image_profile);
        nama = findViewById(R.id.label_fullname);
        homepage = findViewById(R.id.label_homepage);
        email = findViewById(R.id.label_email);
        about = findViewById(R.id.label_about);
        tombol = findViewById(R.id.button_homepage);


        foto.setImageURI(Uri.parse(avatar));
        nama.setText(name);
        email.setText(mail);
        homepage.setText(home);
        about.setText(aboutme);

        tombol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pergi(home);
            }
        });

    }
    private void pergi (String go){
        Uri uri = Uri.parse(go);
        Intent pindah = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(pindah);
    }
}
