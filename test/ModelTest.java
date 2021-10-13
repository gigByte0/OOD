import org.junit.Test;

import cs3500.marblesolitaire.model.hw02.EnglishSolitaireModel;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState;
import cs3500.marblesolitaire.view.MarbleSolitaireTextView;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertEquals;

/**
 * Class that tests methods of English Solitaire Model class.
 */
public class ModelTest {

  @Test
  public void testConstruct() {
    EnglishSolitaireModel test = new EnglishSolitaireModel();
    EnglishSolitaireModel test1 = new EnglishSolitaireModel(4, 1);
    EnglishSolitaireModel test2 = new EnglishSolitaireModel(7);
    EnglishSolitaireModel test3 = new EnglishSolitaireModel(5, 6, 9);
    EnglishSolitaireModel test4 = new EnglishSolitaireModel(5);
    EnglishSolitaireModel test5 = new EnglishSolitaireModel(3);
    EnglishSolitaireModel test6 = new EnglishSolitaireModel(7);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructException() {
    EnglishSolitaireModel test = new EnglishSolitaireModel(1, 5);
    EnglishSolitaireModel test1 = new EnglishSolitaireModel(2);
    EnglishSolitaireModel test2 = new EnglishSolitaireModel(4);
    EnglishSolitaireModel test3 = new EnglishSolitaireModel(-1);
    EnglishSolitaireModel test4 = new EnglishSolitaireModel(5, 1, 2);
    EnglishSolitaireModel test5 = new EnglishSolitaireModel(2, 6, 9);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMoveException() {
    EnglishSolitaireModel moveTest = new EnglishSolitaireModel();
    moveTest.move(0, 4, 1, 4);
    moveTest.move(4, 6, 5, 6);
  }

  @Test
  public void testMove() {
    EnglishSolitaireModel moveTest = new EnglishSolitaireModel();
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, moveTest.getSlotAt(3, 5));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, moveTest.getSlotAt(3, 4));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, moveTest.getSlotAt(3, 3));
    moveTest.move(3, 5, 3, 3);
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, moveTest.getSlotAt(3, 3));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, moveTest.getSlotAt(3, 4));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, moveTest.getSlotAt(3, 5));
    moveTest.move(5, 4, 3, 4);
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, moveTest.getSlotAt(3, 4));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, moveTest.getSlotAt(4, 4));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, moveTest.getSlotAt(5, 4));
  }


  @Test
  public void testIsGameOver() {
    EnglishSolitaireModel test = new EnglishSolitaireModel();
    assertFalse(test.isGameOver());
    test.move(3, 5, 3, 3);
    test.move(3, 2, 3, 4);
    test.move(5, 3, 3, 3);
    test.move(3, 0, 3, 2);
    test.move(3, 3, 3, 1);
    test.move(5, 2, 3, 2);
    test.move(3, 1, 3, 3);
    test.move(3, 3, 3, 5);
    test.move(3, 6, 3, 4);
    test.move(4, 0, 4, 2);
    test.move(1, 3, 3, 3);
    test.move(3, 3, 3, 5);
    test.move(1, 4, 3, 4);
    test.move(3, 4, 3, 6);
    test.move(1, 2, 3, 2);
    test.move(3, 2, 5, 2);
    test.move(6, 2, 4, 2);
    test.move(2, 6, 2, 4);
    test.move(2, 0, 2, 2);
    test.move(5, 4, 3, 4);
    test.move(2, 4, 4, 4);
    test.move(4, 5, 4, 3);
    test.move(4, 2, 4, 4);
    test.move(4, 6, 2, 6);
    test.move(6, 4, 6, 2);
    assert (test.isGameOver());
    EnglishSolitaireModel test2 = new EnglishSolitaireModel();
    assertFalse(test2.isGameOver());
    test2.move(3,1,3,3);
    test2.move(5,2,3,2);
    test2.move(4,0,4,2);
    test2.move(4,3,4,1);
    test2.move(4,5,4,3);
    test2.move(6,4,4,4);
    test2.move(3,4,5,4);
    test2.move(6,2,6,4);
    test2.move(6,4,4,4);
    test2.move(2,2,4,2);
    test2.move(0,2,2,2);
    test2.move(1,4,3,4);
    test2.move(3,4,5,4);
    test2.move(5,4,5,2);
    test2.move(5,2,3,2);
    test2.move(3,2,1,2);
    test2.move(2,0,4,0);
    test2.move(4,0,4,2);
    test2.move(4,2,4,4);
    test2.move(2,6,2,4);
    test2.move(2,3,2,5);
    test2.move(4,6,2,6);
    test2.move(2,6,2,4);
    test2.move(0,4,0,2);
    test2.move(0,2,2,2);
    test2.move(2,1,2,3);
    test2.move(2,3,2,5);
    test2.move(2,5,4,5);
    test2.move(4,5,4,3);
    test2.move(4,3,2,3);
    test2.move(1,3,3,3);
    assert(test2.isGameOver());
    assertEquals(1, test2.getScore());
  }

  @Test
  public void testGetBoardSize() {
    EnglishSolitaireModel arm3 = new EnglishSolitaireModel();
    EnglishSolitaireModel arm5 = new EnglishSolitaireModel(5, 6, 6);
    EnglishSolitaireModel arm7 = new EnglishSolitaireModel(7, 9, 9);
    EnglishSolitaireModel arm11 = new EnglishSolitaireModel(11, 14, 14);
    assertEquals(7, arm3.getBoardSize());
    assertEquals(13, arm5.getBoardSize());
    assertEquals(19, arm7.getBoardSize());
    assertEquals(31, arm11.getBoardSize());
  }

  @Test
  public void testGetSlotAt() {
    EnglishSolitaireModel test = new EnglishSolitaireModel();
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, test.getSlotAt(3, 4));
    assertEquals(MarbleSolitaireModelState.SlotState.Invalid, test.getSlotAt(1, 5));
    assertEquals(MarbleSolitaireModelState.SlotState.Invalid, test.getSlotAt(0, 0));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, test.getSlotAt(3, 3));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, test.getSlotAt(6, 2));
    test.move(3, 5, 3, 3);
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, test.getSlotAt(3, 4));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, test.getSlotAt(3, 5));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, test.getSlotAt(3, 3));
    test.move(5, 4, 3, 4);
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, test.getSlotAt(5, 4));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, test.getSlotAt(3, 4));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, test.getSlotAt(4, 4));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, test.getSlotAt(3, 6));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, test.getSlotAt(4, 6));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGetSlotException() {
    EnglishSolitaireModel test = new EnglishSolitaireModel();
    test.getSlotAt(-1, -1);
    test.getSlotAt(1, 8);
  }

  @Test
  public void testGetScore() {
    EnglishSolitaireModel test = new EnglishSolitaireModel();
    assertEquals(32, test.getScore());
    test.move(3, 5, 3, 3);
    assertEquals(31, test.getScore());
    test.move(5, 4, 3, 4);
    assertEquals(30, test.getScore());
  }
}
