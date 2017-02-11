package cs.dal.sudoku;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public GridView gridView;
    public String[] solvedSudokuGrid;
    public String[] dugSudokuGrid;
    public String[] referenceGrid;

    public GridView buttonGridView;
    public String[] buttons  = new String[] {"1","2","3","4","5","6","7","8","9","C"};
    public String number;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        solvedSudokuGrid = SolvedSudokuGenerator.generatePuzzle();
        dugSudokuGrid = SolvedSudokuDigger.digPuzzle(solvedSudokuGrid, 0);
        referenceGrid = dugSudokuGrid.clone();

        gridView = (GridView)this.findViewById(R.id.myGridView);
        SudokuGridAdapter gridAdapter = new SudokuGridAdapter(MainActivity.this, dugSudokuGrid);
        gridView.setAdapter(gridAdapter);

        buttonGridView = (GridView)this.findViewById(R.id.buttonGridView);
        ButtonGridAdapter buttonGridAdapter = new ButtonGridAdapter(MainActivity.this, buttons);
        buttonGridView.setAdapter(buttonGridAdapter);


        buttonGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 9) {
                    number = "";
                } else {
                    number = Integer.toString(position + 1);
                }
            }
        });

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                if (referenceGrid[position].equals(" ")) {
                    if (ValidateChoice.confirmNumber(dugSudokuGrid, number, position)) {
                        dugSudokuGrid[position] = number;
                        SudokuGridAdapter gridAdapter = new SudokuGridAdapter(MainActivity.this, dugSudokuGrid);
                        gridView.setAdapter(gridAdapter);
                    }
                    else {
                        Toast toast = Toast.makeText(getApplicationContext(), "Invalid Selection. Try another number.", Toast.LENGTH_SHORT);
                        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
                        toast.show();
                    }
                }

            }
        });

    }

}

