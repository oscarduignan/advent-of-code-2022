//> using lib "com.lihaoyi::pprint:0.8.1"

def loadInput(file: String) = 
  scala.io.Source.fromFile(s"day01-$file.txt").mkString.split("\n\n").map(_.split("\n").map(_.toInt).toList).toList

val example = loadInput("example")
pprint.pprintln(example)
val part1 = loadInput("part1")

println("Part1:")
val biggestFirst = Ordering[Int].reverse
println(s"- carrying most from example ${example.map(_.sum).sorted(biggestFirst).head}")
println(s"- carrying most from my input ${part1.map(_.sum).sorted(biggestFirst).head}")

println("Part2:")
println(s"- total carried by three elves carrying the most from example: ${
  example.map(_.sum).sorted(biggestFirst).take(3).sum
}")
println(s"- total carried by three elves carrying the most from my input: ${
  part1.map(_.sum).sorted(biggestFirst).take(3).sum
}")

