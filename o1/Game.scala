package o1

import constants._

class Game {

  val bug       = new Bug(new Pos(ViewWidth / 10, ViewHeight / 10))
  val obstacles = Vector(new Obstacle(70), new Obstacle(30), new Obstacle(20))

  def activateBug() = {
    this.bug.flap(flapStrength)
  }

  def timePasses() = {
    for (obstacle <- this.obstacles) {
      obstacle.approach()
    }
    this.bug.fall()
  }

  def isLost = {
    var isDeadAlready = !this.bug.isInBounds
    for (obstacle <- this.obstacles) {
      if (obstacle.touches(bug)) {
        isDeadAlready = true
      }
    }
    isDeadAlready
  }

}