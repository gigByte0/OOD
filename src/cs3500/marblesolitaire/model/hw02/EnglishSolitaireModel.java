package cs3500.marblesolitaire.model.hw02;

/**
 * Class to represent a game object, where each instantiation creates a new game.
 * sRow field represents row of empty slot on board.
 * sCol field represents column of empty slot on board.
 */
public class EnglishSolitaireModel implements MarbleSolitaireModel {
  public final int sRow;
  public final int sCol;
  private final int armThickness;
  private SlotState[][] board;

  /**
   * Constructor that takes in no parameters.
   * Initializes the game board with an arm thickness 3 and empty slot at the center
   */
  public EnglishSolitaireModel() {

    this(3, 3, 3);
  }

  /**
   * Constructor that takes in args sRow and sCol.
   * Initializes the gameboard with arm thickness 3 and empty slot at (sRow, sCol).
   * @param sRow represents the row of empty slot.
   * @param sCol represents the column of empty slot.
   * @throws IllegalArgumentException if given position is invalid, returns
   *                                  "Invalid empty cell position (r,c)", with 𝑟 and
   *                                  𝑐 replaced with the row
   *                                  and column passed to it.
   */
  public EnglishSolitaireModel(int sRow, int sCol) throws IllegalArgumentException {
    this(3, sRow, sCol);
    if (isInitInvalidSlot(sRow, sCol, 3)) {
      throw new IllegalArgumentException(String.format("Invalid empty" +
              " cell position (%d,%d)", sRow, sCol));
    }
  }

  /**
   * Constructor that takes in armThickness arg.
   * Initializes board with empty slot at the center.
   * @param armThickness represents arm thickness of board, the number of marbles in the top row.
   * @throws IllegalArgumentException if given arm thickness is not a positive, odd number.
   */
  public EnglishSolitaireModel(int armThickness) throws IllegalArgumentException {
    this(armThickness, (Math.round(((3 * armThickness) - 2) / 2)),
            (Math.round(((3 * armThickness) - 2) / 2)));
    if ((armThickness % 2 == 0) || (armThickness < 0)) {
      throw new IllegalArgumentException("Arm Thickness must be a positive and odd number.");
    }
  }

  /**
   * Constructor that takes in armThickness, sRow, and sCol as args.
   * @param armThickness represents number of marbles in top row of board.
   * @param sRow         represents row number of empty cell.
   * @param sCol         represents column number of empty cell.
   * @throws IllegalArgumentException if arm thickness is not a positive odd number or
   *                                  the empty cell position is invalid.
   */
  public EnglishSolitaireModel(int armThickness, int sRow, int sCol)
          throws IllegalArgumentException {
    if ((armThickness % 2 == 0) || (armThickness < 0)) {
      throw new IllegalArgumentException("Arm Thickness must be a positive and odd number.");
    }
    if (isInitInvalidSlot(sRow, sCol, armThickness)) {
      throw new IllegalArgumentException(String.format("Invalid empty"
              + " cell position (%d,%d)", sRow, sCol));
    }
    this.armThickness = armThickness;
    this.sRow = sRow;
    this.sCol = sCol;
    this.board = inItBoard();
  }

  /**
   * Method that initializes and assigns slot states of board.
   * Constructs 2d array of slots. Each slot is indexed and assigned a slot state.
   * @return slot state representing the board.
   */
  private SlotState[][] inItBoard() {
    int boardSize = ((this.armThickness * 3) - 2);
    SlotState[][] board = new SlotState[boardSize][boardSize];
    for (int i = 0; i < boardSize; i++) {
      for (int j = 0; j < boardSize; j++) {
        if (isInitInvalidSlot(i, j, this.armThickness)) {
          board[i][j] = SlotState.Invalid;
        } else if ((i == this.sRow) && (j == this.sCol)) {
          board[i][j] = SlotState.Empty;
        } else {
          board[i][j] = SlotState.Marble;
        }
      }
    }
    return board;
  }

  /**
   * Method that gets armthickness of model.
   * @return int value of armthickness.
   */
  public int getArmThickness() {
    return this.armThickness;
  }

  /**
   * Method to determine if a slot state is invalid.
   * @param row          represents the row of a slot.
   * @param column       represents the column of a slot.
   * @param armThickness represent the arm thickness of board.
   * @return boolean value, returns true if slot is invalid and false if it is valid slot position.
   */
  private static boolean isInitInvalidSlot(int row, int column, int armThickness) {
    return (((row <= (armThickness - 2)) && (column <= (armThickness - 2))) ||
            ((row >= (2 * armThickness - 1)) && (column <= (armThickness - 2))) ||
            ((row <= (armThickness - 2)) && (column >= (2 * armThickness - 1))) ||
            ((row >= (2 * armThickness - 1)) && (column >= (2 * armThickness - 1))) ||
            ((row < 0) || (column < 0)) || ((row >= ((armThickness * 3) - 2)) ||
            (column >= ((armThickness * 3) - 2))));
  }

  @Override
  public void move(int fromRow, int fromCol, int toRow, int toCol) throws
          IllegalArgumentException {
    if (isInitInvalidSlot(fromRow, fromCol, armThickness)) {
      throw new IllegalArgumentException("Invalid from position.");
    }
    if (isInitInvalidSlot(toRow, toCol, armThickness)) {
      throw new IllegalArgumentException("Invalid to position.");
    }
    if (! (isValidMove(fromRow, fromCol, toRow, toCol))) {
      throw new IllegalArgumentException("Invalid Move.");
    }
    if (isValidMove(fromRow, fromCol, toRow, toCol)) {
      this.board[fromRow][fromCol] = SlotState.Empty;
      this.board[toRow][toCol] = SlotState.Marble;
      if (rowOrColMove(fromRow, fromCol, toRow, toCol).equals("RowMove")) {
        this.board[fromRow][(fromCol + toCol) / 2] = SlotState.Empty;
      } else {
        this.board[(fromRow + toRow) / 2][fromCol] = SlotState.Empty;
      }
    }
  }

  /**
   * Determines whether a move is valid.
   * @param fromRow row of from position.
   * @param fromCol column of from position.
   * @param toRow   row of to position.
   * @param toCol   column of to position.
   * @return boolean, return true if the move is valid and false if not.
   */
  private boolean isValidMove(int fromRow, int fromCol, int toRow, int toCol) {
    return (((getSlotAt(fromRow, fromCol) == SlotState.Marble)
            && (getSlotAt(toRow, toCol) == SlotState.Empty))
            && ((this.board[(fromRow + toRow) / 2][fromCol] == SlotState.Marble)
            || (this.board[fromRow][(fromCol + toCol) / 2] == SlotState.Marble))
            && (((fromRow + 2 == toRow) || (fromRow - 2 == toRow) || (fromCol + 2 == toCol)
            || (fromCol - 2 == toCol))));
  }

  /**
   * Returns whether a move is a row move or a column move.
   * A horizontal move is a "row move" and a vertical move is a "column move".
   * @param fromRow row of from position.
   * @param fromCol column of from position.
   * @param toRow   row of to position.
   * @param toCol   column of to position.
   * @return String form of what type of move is made.
   */
  private static String rowOrColMove(int fromRow, int fromCol, int toRow, int toCol) {
    if (fromRow == toRow) {
      return "RowMove";
    } else if (fromCol == toCol) {
      return "ColumnMove";
    } else {
      return "Neither";
    }
  }

  /**
   * Determines if a slot on the board has a valid slot 2 slots to its right.
   * @param row row of slot.
   * @param col column of slot.
   * @return boolean value of true if slot has slot 2 slots to its right.
   */
  private boolean hasRight(int row, int col) {
    return (!(isInitInvalidSlot(row, (col + 2), armThickness)));
  }

  /**
   * Determines if a slot on the board has a valid slot 2 slots to its left.
   * @param row row of slot.
   * @param col column of slot.
   * @return boolean value of true if slot has slot 2 slots to its left.
   */
  private boolean hasLeft(int row, int col) {
    return (!(isInitInvalidSlot(row, (col - 2), armThickness)));
  }

  /**
   * Determines if a slot on the board has a valid slot 2 slots in upward direction.
   * @param row row of slot.
   * @param col column of slot.
   * @return boolean value of true if slot has slot 2 slots in upward direction.
   */
  private boolean hasUp(int row, int col) {
    return (!(isInitInvalidSlot((row + 2), col, armThickness)));
  }

  /**
   * Determines if a slot on the board has a valid slot 2 slots in downward direction.
   * @param row row of slot.
   * @param col column of slot.
   * @return boolean value of true if slot has slot 2 slots in downward direction.
   */
  private boolean hasDown(int row, int col) {
    return (!(isInitInvalidSlot((row - 2), col, armThickness)));
  }

  /**
   * Determines if a position on the board can make a valid right move.
   * @param row represents row of slot.
   * @param col represents column of slot.
   * @return boolean value of whether valid right move can be made.
   */
  private boolean moveRight(int row, int col) {
    if (hasRight(row, col)) {
      return ((this.board[row][(col + 1)] == SlotState.Marble) &&
              (this.board[row][(col + 2)] == SlotState.Empty));
    } else {
      return false;
    }
  }

  /**
   * Determines if a position on the board can make a valid left move.
   * @param row represents row of slot.
   * @param col represents column of slot.
   * @return boolean value of whether valid left move can be made.
   */
  public boolean moveLeft(int row, int col) {
    if (hasLeft(row, col)) {
      return ((this.board[row][(col - 1)] == SlotState.Marble) &&
              (this.board[row][(col - 2)] == SlotState.Empty));
    } else {
      return false;
    }
  }

  /**
   * Determines if a position on the board can make a valid up move.
   * @param row represents row of slot.
   * @param col represents column of slot.
   * @return boolean value of whether valid up move can be made.
   */
  private boolean moveUp(int row, int col) {
    if (hasUp(row, col)) {
      return ((this.board[(row + 1)][col] == SlotState.Marble) &&
              (this.board[(row + 2)][col] == SlotState.Empty));
    } else {
      return false;
    }
  }

  /**
   * Determines if a position on the board can make a valid down move.
   * @param row represents row of slot.
   * @param col represents column of slot.
   * @return boolean value of whether valid down move can be made.
   */
  private boolean moveDown(int row, int col) {
    if (hasDown(row, col)) {
      return ((this.board[(row - 1)][col] == SlotState.Marble) &&
              (this.board[(row - 2)][col] == SlotState.Empty));
    } else {
      return false;
    }
  }

  /**
   * Determines whether slot at given position can make any valid moves.
   * @param row represents row of slot.
   * @param col represent column of slot.
   * @return boolean value of true if the slot has any valid moves, false if not.
   */
  public boolean canMove(int row, int col) {
    return moveRight(row, col) || moveLeft(row, col) ||
            moveUp(row, col) || moveDown(row, col);
  }

  /**
   * Determines whether a marble in a slot can make any valid moves.
   * @param row    row of current marble.
   * @param column column of current marble.
   * @return boolean, returns true if marble can make valid move and false if not.
   */
  private boolean anyValidMoves(int row, int column) {
    if (isInitInvalidSlot(row, column, armThickness)) {
      return false;
    } else {
      return canMove(row, column);
    }
  }

  @Override
  public boolean isGameOver() {
    for (int i = 0; i < this.getBoardSize(); i++) {
      for (int j = 0; j < this.getBoardSize(); j++) {
        if ((getSlotAt(i,j) == SlotState.Marble) &&
                anyValidMoves(i, j)) {
          return false;
        }
      }
    }
    return true;
  }

  @Override
  public int getBoardSize() {
    return ((this.armThickness * 3) - 2);
  }

  @Override
  public SlotState getSlotAt(int row, int col) throws IllegalArgumentException {
    if (notOnBoard(row, col)) {
      throw new IllegalArgumentException("Row and Column not on board.");
    }
    return this.board[row][col];
  }

  /**
   * Determines whether a slot is on board or not.
   * @param row row of given slot.
   * @param col column of given slot.
   * @return boolean of whether a position is on the board or not.
   */
  private boolean notOnBoard(int row, int col) {
    return (row < 0) || (col < 0) || (row >= this.getBoardSize()) || (col >= this.getBoardSize());

  }

  @Override
  public int getScore() {
    int score = 0;
    int size = this.getBoardSize();
    for (int i = 0; i < size; i++) {
      for (int j = 0; j < size; j++) {
        if (this.board[i][j] == SlotState.Marble) {
          score += 1;
        }
      }
    }
    return score;
  }
}
