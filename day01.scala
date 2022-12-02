//> using lib "com.lihaoyi::pprint:0.7.0"

def loadInput(file: String) = 
  scala.io.Source.fromFile(s"day01-$file.txt").mkString.split("\n\n").map(_.split("\n").map(_.toInt).toList).toList

val example = loadInput("example")
pprint.pprintln(example)

println("Part1:")
val myInput = loadInput("part1")
println(s"- carrying most from example ${example.map(_.sum).max}")
println(s"- carrying most from my input ${myInput.map(_.sum).max}")

println("Part2:")
val biggestFirst = Ordering[Int].reverse
println(s"- total carried by three elves carrying the most from example: ${
  example.map(_.sum).sorted(biggestFirst).take(3).sum
}")
println(s"- total carried by three elves carrying the most from my input: ${
  myInput.map(_.sum).sorted(biggestFirst).take(3).sum
}")

