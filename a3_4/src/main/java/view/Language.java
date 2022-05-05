package view;

/**
 * More language can be added but a flag is needed for the languages
 * that invert the "value/color" (like Swedish does).
 */
public enum Language {
  ENGLISH("En"), SWEDISH("Sv");

  public final String str;

  Language(String str) {
    this.str = str;
  }
}
