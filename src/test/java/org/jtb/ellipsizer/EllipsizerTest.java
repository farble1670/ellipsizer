package org.jtb.ellipsizer;

import org.junit.Assert;
import org.junit.Test;

import static org.jtb.ellipsizer.Ellipsizer.At.END;
import static org.jtb.ellipsizer.Ellipsizer.At.MIDDLE;
import static org.jtb.ellipsizer.Ellipsizer.At.START;
import static org.jtb.ellipsizer.Ellipsizer.ELLIPSIS;
import static org.junit.Assert.*;

public class EllipsizerTest {
  @Test
  public void testEnd() {
    final String test = "Hello, ellipsis.";
    final int len = test.length();

    assertEquals(test, new Ellipsizer(test, 100, END).toString());
    assertEquals("Hello, el" + ELLIPSIS, new Ellipsizer(test, 10, END).toString());
    assertEquals("Hello, ellipsis." , new Ellipsizer(test, len, END).toString());
    assertEquals("Hello, ellipsi" + ELLIPSIS , new Ellipsizer(test, len - 1, END).toString());
    assertEquals(ELLIPSIS, new Ellipsizer("Hello, ellipsis.", 0, END).toString());
    assertEquals(ELLIPSIS, new Ellipsizer("Hello, ellipsis.", -1, END).toString());
  }

  @Test
  public void testStart() {
    final String test = "Hello, ellipsis.";
    final int len = test.length();

    assertEquals(test, new Ellipsizer(test, 100, START).toString());
    assertEquals(ELLIPSIS + "ellipsis.", new Ellipsizer(test, 10, START).toString());
    assertEquals("Hello, ellipsis." , new Ellipsizer(test, len, START).toString());
    assertEquals(ELLIPSIS + "llo, ellipsis." , new Ellipsizer(test, len - 1, START).toString());
    assertEquals(ELLIPSIS, new Ellipsizer("Hello, ellipsis.", 0, START).toString());
    Assert.assertEquals(ELLIPSIS, new Ellipsizer("Hello, ellipsis.", -1, START).toString());
  }

  @Test
  public void testMiddleEven() {
    final String test = "Hello, ellipsis.";
    final int len = test.length();

    String ellipsized;

    ellipsized = new Ellipsizer(test, 100, MIDDLE).toString();
    assertEquals(len, ellipsized.length());
    assertEquals(test, ellipsized);

    ellipsized = new Ellipsizer(test, 10, MIDDLE).toString();
    assertEquals(10, ellipsized.length());
    assertEquals("Hello" + ELLIPSIS + "sis.", ellipsized);

    ellipsized = new Ellipsizer(test, len, MIDDLE).toString();
    assertEquals(len, ellipsized.length());
    assertEquals(test, ellipsized);

    ellipsized = new Ellipsizer(test, len - 1, MIDDLE).toString();
    assertEquals(len - 1, ellipsized.length());
    assertEquals("Hello, " + ELLIPSIS + "lipsis." , ellipsized);

    ellipsized = new Ellipsizer(test, len - 2, MIDDLE).toString();
    assertEquals(ellipsized, len - 2, ellipsized.length());
    assertEquals("Hello, " + ELLIPSIS + "ipsis." , ellipsized);

    ellipsized = new Ellipsizer("Hello, ellipsis.", 0, MIDDLE).toString();
    assertEquals(1, ellipsized.length());
    assertEquals(ELLIPSIS, ellipsized);

    ellipsized =  new Ellipsizer("Hello, ellipsis.", -1, MIDDLE).toString();
    assertEquals(1, ellipsized.length());
    Assert.assertEquals(ELLIPSIS,ellipsized);
  }

  @Test
  public void testMiddleOdd() {
    final String test = "Hello, ellipsis!!!!";
    final int len = test.length();

    String ellipsized;

    ellipsized = new Ellipsizer(test, 100, MIDDLE).toString();
    assertEquals(ellipsized, len, ellipsized.length());
    assertEquals(test, ellipsized);

    ellipsized = new Ellipsizer(test, 10, MIDDLE).toString();
    assertEquals(ellipsized, 10, ellipsized.length());
    assertEquals("Hello" + ELLIPSIS + "!!!!", ellipsized);

    ellipsized = new Ellipsizer(test, len, MIDDLE).toString();
    assertEquals(len, ellipsized.length());
    assertEquals(test, ellipsized);

    ellipsized = new Ellipsizer(test, len - 1, MIDDLE).toString();
    assertEquals(len - 1, ellipsized.length());
    assertEquals("Hello, el" + ELLIPSIS + "psis!!!!" , ellipsized);

    ellipsized = new Ellipsizer(test, len - 2, MIDDLE).toString();
    assertEquals(len - 2, ellipsized.length());
    assertEquals("Hello, e" + ELLIPSIS + "psis!!!!" , ellipsized);

    ellipsized = new Ellipsizer("Hello, ellipsis.", 0, MIDDLE).toString();
    assertEquals(1, ellipsized.length());
    assertEquals(ELLIPSIS, ellipsized);
  }

}
