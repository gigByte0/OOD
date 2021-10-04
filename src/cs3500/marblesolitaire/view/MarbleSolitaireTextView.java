package cs3500.marblesolitaire.view;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState;

/**
 * Class to implement Marble Solitaire View Interface.
 */
public class MarbleSolitaireTextView implements MarbleSolitaireView {
  MarbleSolitaireModelState model;

  /**
   * Constructor for text view.
   *
   * @param model represents marble solitaire model to be printed.
   * @throws IllegalArgumentException if the model is null.
   */
  public MarbleSolitaireTextView(MarbleSolitaireModelState model) throws IllegalArgumentException {
    if (model == null) {
      throw new IllegalArgumentException("Model is null and cannot be printed.");
    }
    this.model = model;
  }

  @Override
  public String toString() {
    String output = "";
    int armThick = ((model.getBoardSize() + 2) / 3);
    int endSmallMarbRow = (model.getBoardSize() - (armThick - 1));
    int lastMarbRow = (model.getBoardSize() - armThick);
    for (int i = 0; i < model.getBoardSize(); i++) {
      for (int j = 0; j < model.getBoardSize(); j++) {
        if ((j == endSmallMarbRow - 1) && ((i <= armThick - 2) || (i >= endSmallMarbRow)
                && (i != model.getBoardSize() - 1)) &&
                (model.getSlotAt(i, j) == MarbleSolitaireModelState.SlotState.Marble)) {
          output += "O\n";
        } else if ((i == model.getBoardSize() - 1) && (j == endSmallMarbRow - 1) &&
                (model.getSlotAt(i, j) == MarbleSolitaireModelState.SlotState.Marble)) {
          output += "O";
        } else if ((i == model.getBoardSize() - 1) && (j == endSmallMarbRow - 1) &&
                (model.getSlotAt(i, j) == MarbleSolitaireModelState.SlotState.Empty)) {
          output += "_";
        } else if ((j == (endSmallMarbRow - 1)) && ((i <= (armThick - 2)) ||
                (i >= endSmallMarbRow)) &&
                (model.getSlotAt(i, j) == MarbleSolitaireModelState.SlotState.Empty)) {
          output += "_\n";
        } else if ((j == model.getBoardSize() - 1) &&
                (i > armThick - 2) && (i < endSmallMarbRow)
                && (model.getSlotAt(i, j) == MarbleSolitaireModelState.SlotState.Marble)) {
          output += "O\n";
        } else if ((j == model.getBoardSize() - 1) &&
                (i > armThick - 2) && (i < endSmallMarbRow)
                && (model.getSlotAt(i, j) == MarbleSolitaireModelState.SlotState.Empty)) {
          output += "_\n";
        } else if ((j >= endSmallMarbRow) &&
                (model.getSlotAt(i, j) == MarbleSolitaireModelState.SlotState.Invalid)) {
          output += "";
        } else if (model.getSlotAt(i, j) == MarbleSolitaireModelState.SlotState.Invalid) {
          output += "  ";
        } else if ((model.getSlotAt(i, j)) == MarbleSolitaireModelState.SlotState.Empty) {
          output += "_ ";
        } else if ((model.getSlotAt(i, j)) == MarbleSolitaireModelState.SlotState.Marble) {
          output += "O ";
        }
      }
    }
    return output;
  }
}

