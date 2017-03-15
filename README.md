## Sudoku

This application provides users the ability to play (and reset) 4 different levels of Sudoku puzzle: Easy, Medium, Hard, and Evil. The Play activity interface displays the Sudoku grid and 10 buttons (1-9 and a clear button). Validation is done on each attempt to place a number to ensure that the placement is valid. Users are informed when they have solved the puzzle.

Apart from the provided basic assignment instructions, the application also does or has the following features:
* Generate a (somewhat) unique random puzzle at difficulty selection.
* App contains a background.
* App plays sound effects on some button clicks and has background music
  during play.

### Puzzle generation:

A solved sudoku grid (based on the manipulation of a hard-coded solved sudoku grid) is generated as a result of between 100 and 10,000 manipulations to the original grid.

Manipulations are as follows:
* flipHorizontal: flips the grid along the axis of the 5th row.
* flipVertical: flips the grid along the axis of the 5th column.
* flipMinorDiagonal: flips the grid along the minor diagonal.
* flipMajorDiagonal: flips the grid along the major diagonal.
* exchangeDigits: exchanges 2 random digits within the grid.
* interchangeRows: interchanges rows within 3 row sections of the grid, or interchanges the 3 sections.
* interchangeColumns: interchanges columns within 3 column sections of the grid, or interchanges the 3 sections.

Based on the level of difficulty selected the generated solved Sudoku grid is "dug" down on to remove entries.

Entries of the grid are randomly removed based
on the following levels of difficulty:
* Easy: 45 given entries on grid with a minimum of 4 entries in each row, column, and 3x3 square.
* Medium: 35 given entries on a grid with a minimum of 3 entries in each row, column, and 3x3 square.
* Hard: 30 given entries on a grid with a minimum of 2 entries in each row, column, and 3x3 square.
* Evil: 25 given entries on a grid with a minimum of 0 entries in each row, column, and 3x3 square.

This process results in a playable Sudoku grid.
