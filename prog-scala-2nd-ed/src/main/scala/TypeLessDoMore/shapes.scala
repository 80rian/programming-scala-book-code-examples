// src/main/scala/TypeLessDoMore/shapes.scala

package typeless.shapes {  
  // BEGIN POINT_DEF
  case class Point(x: Double = 0.0, y: Double = 0.0) {

    def shift(deltax: Double = 0.0, deltay: Double = 0.0) = 
      copy (x + deltax, y + deltay)
  }
  // END POINT_DEF

  // BEGIN SHAPE_DEF
  abstract class Shape() { 
    /** 
     * Draw takes two arguments, one list with an offset for drawing,
     * and the other list that is the function argument we used previously.
     */
    def draw(offset: Point = Point(0.0, 0.0))(f: String => Unit): Unit = 
      f(s"draw(offset = $offset), ${this.toString}")

    /**
     * Return a new shape scaled in both x and y dimensions by factor.
     */
    def scale(factor: Double): Shape = {
      assert (factor > 0.0)
      doscale(factor)
    }

    protected def doscale(factor: Double): Shape
  }
  // END SHAPE_DEF

  case class Circle(center: Point, radius: Double) extends Shape {

    /** Only the radius changes. */
    def doscale(factor: Double) = copy (radius = radius * factor)
  }
  
  case class Rectangle(lowerLeft: Point, height: Double, width: Double)
        extends Shape {

    /** Keep the lowerleft point fixed. */
    def doscale(factor: Double) = 
      copy (height = height * factor, width = width * factor)
  }
}