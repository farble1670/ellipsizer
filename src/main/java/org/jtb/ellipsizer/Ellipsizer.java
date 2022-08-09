package org.jtb.ellipsizer;


import org.jetbrains.annotations.NotNull;

public class Ellipsizer {
  public static final String ELLIPSIS = "\u2026";

  public enum At { START, END, MIDDLE }

  @NotNull
  private final CharSequence string;
  private final int length;
  @NotNull private final At at;

  public Ellipsizer(@NotNull CharSequence string, int length, @NotNull At at) {
    this.string = string;
    this.length = length;
    this.at = at;
  }

  @NotNull
  @Override
  public String toString() {
    if (string.length() <= length) {
      return string.toString();
    }
    if (length <= 0) {
      return ELLIPSIS;
    }
    switch (at) {
      case END:
        return string.subSequence(0, length - 1) + ELLIPSIS;
      case START:
        return ELLIPSIS + string.subSequence(string.length() - length + 1, string.length());
      case MIDDLE:
        final CharSequence left = string.subSequence(0, (length / 2));
        final CharSequence right = string.subSequence(string.length() - (length / 2) + ((length % 2 == 0) ? 1 : 0), string.length());
        return left + ELLIPSIS + right;
      default:
        return string.toString();
    }
  }
}
