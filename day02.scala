//> using lib "com.lihaoyi::pprint:0.8.1"

def loadInput(file: String) = 
  scala.io.Source.fromFile(s"day02-$file.txt").getLines.toList.map(line => line.split(" ")).map {
    case Array(a,b) => (a, b)
  }

def scoreByMoveWePlayed(moves: (String, String)): Int = {
  val wePlayRock     = "X"
  val wePlayPaper    = "Y"
  val wePlayScissors = "Z"
  val lose = 0
  val draw = 3
  val win  = 6
  val opponentPlayedRock = "A"
  val opponentPlayedPaper = "B"
  val opponentPlayedScissors = "C"
  moves.match {
    case (opponentMove, `wePlayRock`) => 1 + (opponentMove.match {
      case `opponentPlayedRock`     => draw
      case `opponentPlayedPaper`    => lose
      case `opponentPlayedScissors` => win
    })
    case (opponentMove, `wePlayPaper`) => 2 + (opponentMove.match {
      case `opponentPlayedRock`     => win
      case `opponentPlayedPaper`    => draw
      case `opponentPlayedScissors` => lose
    })
    case (opponentMove, `wePlayScissors`) => 3 + (opponentMove.match {
      case `opponentPlayedRock`     => lose
      case `opponentPlayedPaper`    => win
      case `opponentPlayedScissors` => draw
    })
  }
}

def scoreByOutcomeWeNeeded(moves: (String, String)): Int = {
  val weNeedToWin  = "Z"
  val weNeedToDraw = "Y"
  val weNeedToLose = "X"
  val wePlayRock = 1
  val wePlayPaper = 2
  val wePlayScissors  = 3
  val opponentPlayedRock = "A"
  val opponentPlayedPaper = "B"
  val opponentPlayedScissors = "C"
  moves.match {
    case (opponentMove, `weNeedToWin`) => 6 + (opponentMove.match {
      case `opponentPlayedRock`     => wePlayPaper
      case `opponentPlayedPaper`    => wePlayScissors
      case `opponentPlayedScissors` => wePlayRock
    })
    case (opponentMove, `weNeedToDraw`) => 3 + (opponentMove.match {
      case `opponentPlayedRock`     => wePlayRock
      case `opponentPlayedPaper`    => wePlayPaper
      case `opponentPlayedScissors` => wePlayScissors
    })
    case (opponentMove, `weNeedToLose`) => 0 + (opponentMove.match {
      case `opponentPlayedRock`     => wePlayScissors
      case `opponentPlayedPaper`    => wePlayRock
      case `opponentPlayedScissors` => wePlayPaper
    })
  }
}

@main def day02() =
  val example = loadInput("example")
  pprint.pprintln(example)
  println(example.map(scoreByMoveWePlayed(_)))
  val part1 = loadInput("part1")
  println(part1.map(scoreByMoveWePlayed(_)).sum)
  println(example.map(scoreByOutcomeWeNeeded(_)))
  println(part1.map(scoreByOutcomeWeNeeded(_)).sum)
