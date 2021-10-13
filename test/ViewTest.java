import org.junit.Test;

import java.io.IOException;
import cs3500.marblesolitaire.model.hw02.EnglishSolitaireModel;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.view.MarbleSolitaireTextView;
import cs3500.marblesolitaire.view.MarbleSolitaireView;
import static org.junit.Assert.assertEquals;

/**
 * Class to test methods of the game's view.
 */
public class ViewTest {


  @Test
  public void testContructView() {
    MarbleSolitaireModel model = new EnglishSolitaireModel();
    MarbleSolitaireView view = new MarbleSolitaireTextView(model);
    Appendable out = new StringBuilder();
    MarbleSolitaireView view2 = new MarbleSolitaireTextView(model, out);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructViewException() {
    Appendable out = new StringBuilder();
    EnglishSolitaireModel model = new EnglishSolitaireModel();
    MarbleSolitaireView test1 = new MarbleSolitaireTextView(null, out);
    MarbleSolitaireView test2 = new MarbleSolitaireTextView(model, null);
    MarbleSolitaireView test3 = new MarbleSolitaireTextView(null, null);
  }

  @Test
  public void printView() {
    EnglishSolitaireModel printTest = new EnglishSolitaireModel();
    MarbleSolitaireTextView textView = new MarbleSolitaireTextView(printTest);
    EnglishSolitaireModel printTest2 = new EnglishSolitaireModel(5);
    MarbleSolitaireTextView textView2 = new MarbleSolitaireTextView(printTest2);
    assertEquals("    O O O\n    O O O"
            + "\nO O O O O O O\nO O O _ O O O\nO O O O O O O\n"
            + "    O O O\n    O O O", textView.toString());
    assertEquals("        O O O O O\n        " +
            "O O O O O\n        " +
            "O O O O O\n        " +
            "O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O _ O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O", textView2.toString());
  }
  @Test
  public void testRenderBoard() {
    MarbleSolitaireModel model = new EnglishSolitaireModel();
    MarbleSolitaireView view = new MarbleSolitaireTextView(model);
    MarbleSolitaireModel model1 = new EnglishSolitaireModel(5);
    MarbleSolitaireView view1 = new MarbleSolitaireTextView(model1);
    MarbleSolitaireModel model2 = new EnglishSolitaireModel();
    model2.move(3,5,3,3);
    model2.move(5,4,3,4);
    MarbleSolitaireView view2 = new MarbleSolitaireTextView(model2);
    java.io.ByteArrayOutputStream out = new java.io.ByteArrayOutputStream();
    System.setOut(new java.io.PrintStream(out));
    try {
      view.renderBoard();
      view1.renderBoard();
      view2.renderBoard();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Test
  public void testRenderMessage() {
    MarbleSolitaireModel model = new EnglishSolitaireModel();
    MarbleSolitaireView view = new MarbleSolitaireTextView(model);
    MarbleSolitaireModel model1 = new EnglishSolitaireModel(5);
    MarbleSolitaireView view1 = new MarbleSolitaireTextView(model1);
    model.move(3,5,3,3);
    model.move(5,4,3,4);
    MarbleSolitaireView view2 = new MarbleSolitaireTextView(model);
    MarbleSolitaireModel model3 = new EnglishSolitaireModel();
    model3.move(3,1,3,3);
    model3.move(5,2,3,2);
    model3.move(4,0,4,2);
    model3.move(4,3,4,1);
    model3.move(4,5,4,3);
    model3.move(6,4,4,4);
    model3.move(3,4,5,4);
    model3.move(6,2,6,4);
    model3.move(6,4,4,4);
    model3.move(2,2,4,2);
    model3.move(0,2,2,2);
    model3.move(1,4,3,4);
    model3.move(3,4,5,4);
    model3.move(5,4,5,2);
    model3.move(5,2,3,2);
    model3.move(3,2,1,2);
    model3.move(2,0,4,0);
    model3.move(4,0,4,2);
    model3.move(4,2,4,4);
    model3.move(2,6,2,4);
    model3.move(2,3,2,5);
    model3.move(4,6,2,6);
    model3.move(2,6,2,4);
    model3.move(0,4,0,2);
    model3.move(0,2,2,2);
    model3.move(2,1,2,3);
    model3.move(2,3,2,5);
    model3.move(2,5,4,5);
    model3.move(4,5,4,3);
    model3.move(4,3,2,3);
    model3.move(1,3,3,3);
    MarbleSolitaireView view3 = new MarbleSolitaireTextView(model3);
    java.io.ByteArrayOutputStream out = new java.io.ByteArrayOutputStream();
    System.setOut(new java.io.PrintStream(out));
    try {
      view.renderMessage("\nThis is a normal marble solitaire model with arm thickness 3.\n");
      view.renderBoard();
      view1.renderMessage("\n\nThis is a normal marble solitaire model with arm thickness 5.\n");
      view1.renderBoard();
      view2.renderMessage("\n\nThis is a normal marble solitaire model after 2 moves are made.\n");
      view2.renderBoard();
      view3.renderMessage("\n\nThis is a winning board.\n");
      view3.renderBoard();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Test
  public void testRenderException() throws IOException {
    EnglishSolitaireModel model = new EnglishSolitaireModel();
    Appendable fakeOut = new FakeAppendable();
    MarbleSolitaireView view = new MarbleSolitaireTextView(model, fakeOut);
    view.renderBoard();
    view.renderMessage("This throws IO exception.");
  }
}
