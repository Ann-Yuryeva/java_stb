package ru.stqa.pft.sandbox;

public class PointRun {


  public static void main(String[] args) {

    Point p1 = new Point(10, 11);
    Point p2 = new Point(42, 49);
    System.out.println("Результат вычисления расстояния между точками " + p1 + " и " + p2 + "  = " + p1.distance(p2));
  }
}

