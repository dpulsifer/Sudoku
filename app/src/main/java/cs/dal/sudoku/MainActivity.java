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

import cs.dal.gridLogic.SolvedSudokuDigger;
import cs.dal.gridLogic.SolvedSudokuGenerator;
import cs.dal.gridLogic.Validation;

public class MainActivity extends AppCompatActivity {

    GridView gridView;
    String[] solvedSudokuGrid;
    String[] dugSudokuGrid;
    String[] referenceGrid;

    String numberSelection;

    int difficultyLevel;

    Button home;
    Button reset;

    Button button1;
    Button button2;
    Button button3;
    Button button4;
    Button button5;
    Button button6;
    Button button7;
    Button button8;
    Button button9;
    Button buttonC;

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

        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);
        button4 = (Button) findViewById(R.id.button4);
        button5 = (Button) findViewById(R.id.button5);
        button6 = (Button) findViewById(R.id.button6);
        button7 = (Button) findViewById(R.id.button7);
        button8 = (Button) findViewById(R.id.button8);
        button9 = (Button) findViewById(R.id.button9);
        buttonC = (Button) findViewById(R.id.buttonC);

        //handle numbers being placed on puzzle grid
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                if (referenceGrid[position].equals(" ") && (numberSelection != null)) {

                    if (numberSelection.equals(" ")) {

                        dugSudokuGrid[position] = numberSelection;
                        SudokuGridAdapter gridAdapter = new SudokuGridAdapter(MainActivity.this, dugSudokuGrid);
                        gridView.setAdapter(gridAdapter);

                    }
                    else if (Validation.confirmNumber(dugSudokuGrid, numberSelection, position) && !numberSelection.equals(" ")) {

                        dugSudokuGrid[position] = numberSelection;
                        SudokuGridAdapter gridAdapter = new SudokuGridAdapter(MainActivity.this, dugSudokuGrid);
                        gridView.setAdapter(gridAdapter);

                        if (Validation.confirmWin(solvedSudokuGrid, dugSudokuGrid)) {
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

    public void getNumberSelected(View view) {

        switch (view.getId()) {
            case R.id.button1:
                numberSelection = "1";
                break;
            case R.id.button2:
                numberSelection = "2";
                break;
            case R.id.button3:
                numberSelection = "3";
                break;
            case R.id.button4:
                numberSelection = "4";
                break;
            case R.id.button5:
                numberSelection = "5";
                break;
            case R.id.button6:
                numberSelection = "6";
                break;
            case R.id.button7:
                numberSelection = "7";
                break;
            case R.id.button8:
                numberSelection = "8";
                break;
            case R.id.button9:
                numberSelection = "9";
                break;
            case R.id.buttonC:
                numberSelection = " ";
                break;
        }
    }

}

