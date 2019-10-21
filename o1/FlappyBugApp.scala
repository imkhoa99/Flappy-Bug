package o1

import constants._

object FlappyBugApp extends App {

  val scenery = {
    val sky    = rectangle(ViewWidth, ViewHeight,  LightBlue)
    val ground = rectangle(ViewWidth, GroundDepth, SandyBrown)
    val tree = {
      val trunk   = rectangle(30, 250, SaddleBrown)
      val foliage = circle(200, ForestGreen)
      trunk.onto(foliage, TopCenter, Center)
    }
    val rootedTree = tree.onto(ground, BottomCenter, new Pos(ViewWidth / 2, 30))
    sky.place(rootedTree, BottomLeft, BottomLeft)
  }

  val bugPic = Pic("ladybug.png")
  val enemyPic = Pic("obstacle.png")

  val game = new Game

  val gui = new View(game, "FlappyBug") {

    var background = scenery

    def makePic = {
      var compositePic = this.background
      for (obstacle <- game.obstacles) {
        val obstaclePic = enemyPic.scaleTo(obstacle.radius * 2)
        compositePic = compositePic.place(obstaclePic, obstacle.pos)
      }
      compositePic.place(bugPic, game.bug.pos)
    }

    override def onKeyDown(key: Key) = {
      if (key == Key.Space) {
        game.activateBug()
      }
    }

    override def onTick() = {
      game.timePasses()
      this.background = this.background.shiftLeft(2)
    }

    override def isDone = game.isLost

  }

  gui.start()

}