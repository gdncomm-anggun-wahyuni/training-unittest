package com.gdn.training;

import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DateFormatterTest {

  @Test
  void getFormattedYearMonth() {
    Date date = new Date(2025 - 1900, 0, 1); // 2025-01-01
    assertEquals("2025-01", DateFormatter.getFormattedYearMonth(date));
  }

  @Test
  void getFormattedYearMonthNull() {
    Exception ex = assertThrows(IllegalArgumentException.class, () -> DateFormatter.getFormattedYearMonth(null));
    assertEquals("date is null", ex.getMessage());
  }
}