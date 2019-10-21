package o1

import constants._
import scala.util.Random

// This class is introduced in Chapter 2.6.

class Obstacle(val radius: Int) {
  private var currentPos = this.randomLaunchPosition()
  def pos = this.currentPos
  def isActive: Boolean = (this.currentPos.x >= -radius) 
  def approach() = {
    if (this.isActive) {
       this.currentPos = this.pos.addX(-ObstacleSpeed)
    } else {
       this.currentPos = new Pos(ViewWidth + 250, 0.5 * ViewHeight)
    }
  }

  private def randomLaunchPosition() = {
    val launchX = ViewWidth + this.radius + Random.nextInt(500)
	  val launchY = Random.nextInt(ViewHeight)
    new Pos(launchX, launchY)
  }
  override def toString = "center at " + this.pos + ", radius " + this.radius
  def touches(bug: Bug): Boolean = {
    (currentPos.distance(bug.pos) < (this.radius + bug.radius)) 
  }
  
}
