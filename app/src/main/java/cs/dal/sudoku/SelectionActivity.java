package cs.dal.sudoku;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SelectionActivity extends AppCompatActivity {

    Button easy;
    Button medium;
    Button hard;
    Button evil;

@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selection);

        final MediaPlayer mediaPlayer = MediaPlayer.create(SelectionActivity.this, R.raw.switchsound);

        easy = (Button) findViewById(R.id.buttonEasy);
        medium = (Button) findViewById(R.id.buttonMedium);
        hard = (Button) findViewById(R.id.buttonHard);
        evil = (Button) findViewById(R.id.buttonEvil);

        easy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.start();

                Intent i;
                i = new Intent(SelectionActivity.this, MainActivity.class);
                i.putExtra("DIFF_LEVEL", 0);
                startActivity(i);
            }
        });

        medium.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.start();

                Intent i;
                i = new Intent(SelectionActivity.this, MainActivity.class);
                i.putExtra("DIFF_LEVEL", 1);
                startActivity(i);
            }
        });

        hard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.start();

                Intent i;
                i = new Intent(SelectionActivity.this, MainActivity.class);
                i.putExtra("DIFF_LEVEL", 2);
                startActivity(i);
            }
        });

        evil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.start();

                Intent i;
                i = new Intent(SelectionActivity.this, MainActivity.class);
                i.putExtra("DIFF_LEVEL", 3);
                startActivity(i);
            }
        });
    }
}
