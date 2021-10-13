package cs3500.marblesolitaire.view;

import java.io.IOException;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState;

/**
 * Class to implement Marble Solitaire View Interface.
 */
public class MarbleSolitaireTextView implements MarbleSolitaireView {
  protected final MarbleSolitaireModelState model;
  protected final Appendable viewDestination;


  /**
   * Constructor for text view.
   * @param model represents marble solitaire model to be printed.
   * @throws IllegalArgumentException if the model is null.
   */
  public MarbleSolitaireTextView(MarbleSolitaireModelState model) throws IllegalArgumentException {
    if (model == null) {
      throw new IllegalArgumentException("Model is null and cannot be printed.");
    }
    this.model = model;
    this.viewDestination = System.out;
  }

  /**
   * Constructor for text view.
   * @param model represents marble solitaire model to be printed.
   * @param viewDestination represents file that view is transmitted to.
   * @throws IllegalArgumentException if any arguments provided to the constructor are null.
   */
  public MarbleSolitaireTextView
          (MarbleSolitaireModelState model, Appendable viewDestination)
          throws IllegalArgumentException {
    if (model == null) {
      throw new IllegalArgumentException("Model cannot be null");
    }
    if (viewDestination == null) {
      throw new IllegalArgumentException("View Destination cannot be null");
    }
    this.model = model;
    this.viewDestination = viewDestination;
  }

  @Override
  public String toString() {
    StringBuilder output = new StringBuilder();
    int armThick = ((model.getBoardSize() + 2) / 3);
    int endSmallMarbRow = (model.getBoardSize() - (armThick - 1));
    int lastMarbRow = (model.getBoardSize() - armThick);
    for (int i = 0; i < model.getBoardSize(); i++) {
      for (int j = 0; j < model.getBoardSize(); j++) {
        if ((j == endSmallMarbRow - 1) && ((i <= armThick - 2) || (i >= endSmallMarbRow)
                && (i != model.getBoardSize() - 1)) &&
                (model.getSlotAt(i, j) == MarbleSolitaireModelState.SlotState.Marble)) {
          output.append("O\n");
        } else if ((i == model.getBoardSize() - 1) && (j == endSmallMarbRow - 1) &&
                (model.getSlotAt(i, j) == MarbleSolitaireModelState.SlotState.Marble)) {
          output.append("O");
        } else if ((i == model.getBoardSize() - 1) && (j == endSmallMarbRow - 1) &&
                (model.getSlotAt(i, j) == MarbleSolitaireModelState.SlotState.Empty)) {
          output.append("_");
        } else if ((j == (endSmallMarbRow - 1)) && ((i <= (armThick - 2)) ||
                (i >= endSmallMarbRow)) &&
                (model.getSlotAt(i, j) == MarbleSolitaireModelState.SlotState.Empty)) {
          output.append("_\n");
        } else if ((j == model.getBoardSize() - 1) &&
                (i > armThick - 2) && (i < endSmallMarbRow)
                && (model.getSlotAt(i, j) == MarbleSolitaireModelState.SlotState.Marble)) {
          output.append("O\n");
        } else if ((j == model.getBoardSize() - 1) &&
                (i > armThick - 2) && (i < endSmallMarbRow)
                && (model.getSlotAt(i, j) == MarbleSolitaireModelState.SlotState.Empty)) {
          output.append("_\n");
        } else if ((j >= endSmallMarbRow) &&
                (model.getSlotAt(i, j) == MarbleSolitaireModelState.SlotState.Invalid)) {
          output.append("");
        } else if (model.getSlotAt(i, j) == MarbleSolitaireModelState.SlotState.Invalid) {
          output.append("  ");
        } else if ((model.getSlotAt(i, j)) == MarbleSolitaireModelState.SlotState.Empty) {
          output.append("_ ");
        } else if ((model.getSlotAt(i, j)) == MarbleSolitaireModelState.SlotState.Marble) {
          output.append("O ");
        }
      }
    }
    return output.toString();
  }

  @Override
  public void renderBoard() {
    try {
      this.viewDestination.append(this.toString());
    }
    catch (IOException e){
      e.printStackTrace();
    }
  }

  @Override
  public void renderMessage(String message) {
    try {
      this.viewDestination.append(message);
    }
    catch (IOException e) {
      e.printStackTrace();
    }
  }
}

