I used 8 threads total.  My solution involves checking all rows
and columns for duplicates.  If a duplicate is found in a row, 
I check the corresponding column and bx and vice versa.  The correct
number will only have an invalid row/column/box.  The incorrect
number will have an invalid row, column, and box.  I am using
threads to check the corresponding rows, columns, and boxes
simultaneously.

Console output saved in "output.txt".

If you would prefer to just check out my github repo, here
is the link:
https://github.com/MatthewParnham/SudokuValidator.git