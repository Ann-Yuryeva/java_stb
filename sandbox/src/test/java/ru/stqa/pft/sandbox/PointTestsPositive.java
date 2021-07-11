package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PointTestsPositive {

  @Test
  public void testDistance(){

    Point p1 = new Point(10,11);
    Point p2 = new Point(42,49);
    p2.distance(p1);
    Assert.assertEquals((int)p2.distance(p1),  49);
  }
}
