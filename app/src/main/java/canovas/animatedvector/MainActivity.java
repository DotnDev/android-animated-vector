package canovas.animatedvector;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.Animatable;
import android.os.Bundle;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView robot_imgview = findViewById(R.id.main_robot_imgview);

        Animatable animatable = (Animatable) robot_imgview.getDrawable();
        animatable.start();


    }
}
