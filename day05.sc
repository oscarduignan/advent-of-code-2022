//> using lib "com.lihaoyi::pprint:0.8.1"

import pprint.pprintln
import scala.collection.mutable.Stack
import scala.util.chaining._
import scala.language.implicitConversions

def loadInput(file: String) =
  val Array(state, moves) = scala.io.Source.fromFile(s"day05-$file.txt").mkString.split("\n\n")
  (state.split("\n").reverse.tail.map(a => {
    a.split("    ").flatMap(_.split(" "))
  }).foldLeft[Map[Int, Stack[String]]](Map.empty)((stacks, row) => {
    row.zipWithIndex.foldLeft(stacks)((acc, next) => {
      val index = next._2 + 1
      var stack = acc.getOrElse(index, Stack.empty)
      if (next._1 != "") stack.push(next._1)
      acc + (index -> stack)
    })
  }), moves.split("\n").map(line => line.split(" ") match {
    case Array(_, count, _, from, _, to) => (count.toInt, from.toInt, to.toInt)
  }))

val example = loadInput("example")

pprintln(example)

val (stateBefore, moves) = example

val stateAfter = moves.foldLeft(stateBefore)((stacks, move) => {
  val (count, from, to) = move
  Range.inclusive(1, count).foldLeft(stacks)((stacks, next) => {
    var fromStack = stacks.getOrElse(from, Stack.empty)
    var toStack   = stacks.getOrElse(to, Stack.empty)
    toStack.push(fromStack.pop())
    stacks + (from -> fromStack, to -> toStack)
  })
})

println("Part1 :")
println(s"- example: ${stateAfter.keySet.map(index => stateAfter(index).pop()).mkString}")

val (part1Before, part1Moves) = loadInput("myInput")

val part1After = part1Moves.foldLeft(part1Before)((stacks, move) => {
  val (count, from, to) = move
  Range.inclusive(1, count).foldLeft(stacks)((stacks, next) => {
    var fromStack = stacks.getOrElse(from, Stack.empty)
    var toStack   = stacks.getOrElse(to, Stack.empty)
    toStack.push(fromStack.pop())
    stacks + (from -> fromStack, to -> toStack)
  })
})

println(s"- myInput: ${part1After.keySet.toList.sorted.map(index => part1After(index).pop()).mkString.replaceAll("[^A-Z]", "")}")

