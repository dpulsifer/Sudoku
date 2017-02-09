package cs.dal.sudoku;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.GridView;

public class MainActivity extends AppCompatActivity {

    public GridView gridView;
    public String[] items = new String[]{"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28",
            "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50",
            "51", "52", "53", "54", "55", "56", "57", "58", "59", "60", "61", "62", "63", "64", "65", "66", "67", "68", "69", "70", "71", "72", "73", "74", "75",
            "76", "77", "78", "79", "80", "81"};

    public GridView buttonGridView;
    public String[] buttons  = new String[] {"1","2","3","4","5","6","7","8","9","C"};



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gridView = (GridView)this.findViewById(R.id.myGridView);
        SudokuGridAdapter gridAdapter = new SudokuGridAdapter(MainActivity.this, items);
        gridView.setAdapter(gridAdapter);

        buttonGridView = (GridView)this.findViewById(R.id.buttonGridView);
        ButtonGridAdapter buttonGridAdapter = new ButtonGridAdapter(MainActivity.this, buttons);
        buttonGridView.setAdapter(buttonGridAdapter);

    }

}
