
package o1
import constants._
class Bug(private var currentPos: Pos) {
  private var yVelocity = 0.0
  val radius = BugRadius
  def pos = this.currentPos
  def flap(strength: Double) = {
    this.yVelocity = -strength
  }
  def fall() = {
     if (this.currentPos.y < groundY) {
       this.yVelocity += 2.0
     } 
     this.move(yVelocity)
  }
  
  def move(howMuchY: Double) = {
    this.currentPos = this.pos.addY(howMuchY).clampY(0, 350)
  }
  
  def isInBounds: Boolean = {
    (this.pos.y > 0 && this.pos.y < groundY)
  }
  
  override def toString = "center at " + this.pos + ", radius " + this.radius
}
