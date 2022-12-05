//> using lib "com.lihaoyi::pprint:0.8.1"

import pprint.pprintln
import scala.collection.mutable.Stack
import scala.util.chaining._
import scala.language.implicitConversions

def loadInput(file: String) =
  val Array(state, moves) = scala.io.Source.fromFile(s"day05-$file.txt").mkString.split("\n\n")
  (state.split("\n").reverse.tail.map(a => {
    a.split("    ").flatMap(_.split(" "))
  }).foldLeft[Map[Int, List[String]]](Map.empty)((stacks, row) => {
    row.zipWithIndex.foldLeft(stacks)((acc, next) => {
      if next._1.nonEmpty then
        val index = next._2 + 1
        acc + (index -> (next._1 :: acc.getOrElse(index, List.empty)))
      else
        acc
    })
  }), moves.split("\n").map(line => line.split(" ") match {
    case Array(_, count, _, from, _, to) => (count.toInt, from.toInt, to.toInt)
  }))

val example = loadInput("example")

pprintln(example)

val (exampleBefore, exampleMoves) = example

val stateAfter = exampleMoves.foldLeft(exampleBefore)((stacks, move) => {
  val (count, fromIndex, toIndex) = move

  Range.inclusive(1, count).foldLeft(stacks)((acc, next) => {
    val fromStack = acc.getOrElse(fromIndex, List.empty) 
    acc + (
      fromIndex -> fromStack.tail,
      toIndex   -> (fromStack.head :: acc.getOrElse(toIndex, List.empty))
    )
  })
})

pprintln(stateAfter)

println("Part1:")
println(s"- example: ${stateAfter.keySet.map(index => stateAfter(index).head).mkString}")

val (part1Before, part1Moves) = loadInput("myInput")

val part1After = part1Moves.foldLeft(part1Before)((stacks, move) => {
  val (count, fromIndex, toIndex) = move

  Range.inclusive(1, count).foldLeft(stacks)((acc, next) => {
    val fromStack = acc.getOrElse(fromIndex, List.empty)
    acc + (
      fromIndex -> fromStack.tail,
      toIndex   -> (fromStack.head :: acc.getOrElse(toIndex, List.empty))
    )
  })
})

println(s"- myInput: ${part1After.keySet.toList.sorted.map(index => part1After(index).head).mkString.replaceAll("[^A-Z]", "")}")

val part2Example = example._2.foldLeft(example._1)((stacks, move) => {
  val (count, fromIndex, toIndex) = move
  val (moving, remainder) = stacks.getOrElse(fromIndex, List.empty).splitAt(count)
  stacks + (
    fromIndex -> remainder,
    toIndex   -> (moving ::: stacks.getOrElse(toIndex, List.empty))
  )
})

val part2MyInput = part1Moves.foldLeft(part1Before)((stacks, move) => {
  val (count, fromIndex, toIndex) = move
  val (moving, remainder) = stacks.getOrElse(fromIndex, List.empty).splitAt(count)
  stacks + (
    fromIndex -> remainder,
    toIndex   -> (moving ::: stacks.getOrElse(toIndex, List.empty))
  )
})

println("Part2:")
println(s"- example: ${part2Example.keySet.toList.sorted.map(i => part2Example(i).head).mkString.replaceAll("[^A-Z]", "")}")
println(s"- myInput: ${part2MyInput.keySet.toList.sorted.map(i => part2MyInput(i).head).mkString.replaceAll("[^A-Z]", "")}")
