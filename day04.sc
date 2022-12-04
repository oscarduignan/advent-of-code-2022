//> using lib "com.lihaoyi::pprint:0.8.1"

import pprint.pprintln

def loadInput(file: String): List[((Int, Int),(Int, Int))] =
  scala.io.Source.fromFile(s"day04-$file.txt").getLines.map(_.split(",").map(_.split("-").map(_.toInt) match {
    case Array(start, end) => start -> end
  }) match {
    case Array(elf1, elf2) => (elf1, elf2)
  }).toList

val example = loadInput("example")

println("Part1:")

def oneIsASubset(pairs: ((Int, Int),(Int, Int))): Boolean =
  val (elf1, elf2) = pairs
  (elf1._1 >= elf2._1 && elf1._2 <= elf2._2) ||
  (elf1._1 <= elf2._1 && elf1._2 >= elf2._2)

pprintln(example.filter(oneIsASubset).length)

val myInput = loadInput("myInput")

pprintln(myInput.filter(oneIsASubset).length)

println("Part2:")

def theyOverlap(pairs: ((Int,Int), (Int,Int))): Boolean =
  val (elf1, elf2) = pairs
  (elf1._1 >= elf2._1 && elf1._1 <= elf2._2) ||
  (elf1._2 >= elf2._1 && elf1._2 <= elf2._2) ||
  oneIsASubset(elf1, elf2)

pprintln(example.filter(theyOverlap).length)

pprintln(myInput.filter(theyOverlap).length)
