//> using lib "com.lihaoyi::pprint:0.8.1"

import pprint.pprintln

def loadInput(file: String): List[((Int, Int), (Int, Int))] =
  scala.io.Source.fromFile(s"day04-$file.txt").getLines.map(_.split(",").map(_.split("-").map(_.toInt) match {
    case Array(start, end) => start -> end
  }) match {
    case Array(elf1, elf2) => (elf1, elf2)
  }).toList

val example = loadInput("example")

pprintln(example)

println("Part1:")

def oneIsASubset(pairs: ((Int, Int), (Int, Int))): Boolean =
  val (
    elf1_start -> elf1_end,
    elf2_start -> elf2_end
  ) = pairs

  (elf1_start >= elf2_start && elf1_end <= elf2_end) || // elf 1 contained by elf 2
  (elf1_start <= elf2_start && elf1_end >= elf2_end)    // elf 2 contained by elf 1

pprintln(example.filter(oneIsASubset))
pprintln(example.filter(oneIsASubset).length)

val myInput = loadInput("myInput")

pprintln(myInput.filter(oneIsASubset).length)

println("Part2:")

def theyOverlap(pairs: ((Int, Int), (Int, Int))): Boolean =
  val (
    elf1_start -> elf1_end,
    elf2_start -> elf2_end
  ) = pairs

  (elf1_start >= elf2_start && elf1_start <= elf2_end) || // elf 1 starts in elf 2
  (elf2_start >= elf1_start && elf2_start <= elf1_end)    // elf 2 starts in elf 1

pprintln(example.filter(theyOverlap).length)

pprintln(myInput.filter(theyOverlap).length)
