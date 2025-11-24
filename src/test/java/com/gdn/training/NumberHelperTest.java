package com.gdn.training;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Number Helper Test")
class NumberHelperTest {
  @Test
  @DisplayName("is odd test")
  public void isOddTest() {
    assertFalse(NumberHelper.isOdd(2));
    assertTrue(NumberHelper.isOdd(3));
  }

  @Test
  @DisplayName("is even test")
  public void isEvenTest() {
    assertFalse(NumberHelper.isEven(3));
    assertTrue(NumberHelper.isEven(2));
  }
}