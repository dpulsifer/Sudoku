package cs.dal.sudoku;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;

import cs.dal.gridLogic.CheckForWin;
import cs.dal.gridLogic.SolvedSudokuDigger;
import cs.dal.gridLogic.SolvedSudokuGenerator;
import cs.dal.gridLogic.ValidateChoice;

public class MainActivity extends AppCompatActivity {

    GridView gridView;
    String[] solvedSudokuGrid;
    String[] dugSudokuGrid;
    String[] referenceGrid;

    GridView buttonGridView;
    String[] buttons  = new String[] {"1","2","3","4","5","6","7","8","9","⌫"};
    String number;

    int difficultyLevel;

    Button home;
    Button reset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        home = (Button) findViewById(R.id.main_home);

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(MainActivity.this, SelectionActivity.class);
                startActivity(i);
                finish();
            }
        });

        reset = (Button) findViewById(R.id.main_reset);

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dugSudokuGrid = referenceGrid.clone();
                SudokuGridAdapter gridAdapter = new SudokuGridAdapter(MainActivity.this, dugSudokuGrid);
                gridView.setAdapter(gridAdapter);
            }
        });

        difficultyLevel = getIntent().getExtras().getInt("DIFF_LEVEL");

        //generate sudoku puzzle
        solvedSudokuGrid = SolvedSudokuGenerator.generatePuzzle();
        dugSudokuGrid = SolvedSudokuDigger.digPuzzle(solvedSudokuGrid, difficultyLevel);
        referenceGrid = dugSudokuGrid.clone();

        //generate sudoku grid
        gridView = (GridView)this.findViewById(R.id.myGridView);
        SudokuGridAdapter gridAdapter = new SudokuGridAdapter(MainActivity.this, dugSudokuGrid);
        gridView.setAdapter(gridAdapter);

        //generate button grid
        buttonGridView = (GridView)this.findViewById(R.id.buttonGridView);
        ButtonGridAdapter buttonGridAdapter = new ButtonGridAdapter(MainActivity.this, buttons);
        buttonGridView.setAdapter(buttonGridAdapter);

        //handle number selection from button grid
        buttonGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 9) {
                    number = " ";
                } else {
                    number = Integer.toString(position + 1);
                }
            }
        });

        //handle numbers being placed on puzzle grid
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                if (referenceGrid[position].equals(" ") && (number != null)) {
                    if (number.equals(" ")) {
                        dugSudokuGrid[position] = number;
                        SudokuGridAdapter gridAdapter = new SudokuGridAdapter(MainActivity.this, dugSudokuGrid);
                        gridView.setAdapter(gridAdapter);
                    }
                    else if (ValidateChoice.confirmNumber(dugSudokuGrid, number, position) && !number.equals(" ")) {
                        dugSudokuGrid[position] = number;
                        SudokuGridAdapter gridAdapter = new SudokuGridAdapter(MainActivity.this, dugSudokuGrid);
                        gridView.setAdapter(gridAdapter);
                        if (CheckForWin.confirmWin(solvedSudokuGrid, dugSudokuGrid)) {
                            Toast toast = Toast.makeText(getApplicationContext(), "CONGRATULATIONS! YOU WIN!", Toast.LENGTH_LONG);
                            toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
                            toast.show();

                            Intent i = new Intent(MainActivity.this, SelectionActivity.class);
                            startActivity(i);
                            finish();
                        }
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

