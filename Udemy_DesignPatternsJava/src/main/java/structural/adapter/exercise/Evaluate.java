package structural.adapter.exercise;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class Evaluate
{
  @Test
  public void test()
  {
    Square sq = new Square(11);
    SquareToRectangleAdapter adapter = new SquareToRectangleAdapter(sq);
    assertEquals(121, adapter.getArea());
  }
}
