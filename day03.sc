//> using lib "com.lihaoyi::pprint:0.8.1"

import pprint.pprintln

def loadInput(file: String) =
  scala.io.Source.fromFile(s"day03-$file.txt").getLines.map(line => line.grouped(line.length/2).map(_.toSet).toList).toList

val example = loadInput("example");

pprintln(example)

pprintln(example.map(bag => bag(0).intersect(bag(1))))

pprintln(example.map(bag => bag(0).intersect(bag(1)).map(char => {
  char.toInt - (if char.isUpper then 38 else 96)
})))

pprintln(example.map(bag => bag(0).intersect(bag(1)).map(char => {
  char.toInt - (if char.isUpper then 38 else 96)
}).sum).sum)

val myInput = loadInput("myInput")

pprintln(myInput.map(bag => bag(0).intersect(bag(1)).map(char => {
  char.toInt - (if char.isUpper then 38 else 96)
}).sum).sum)

pprintln(example.grouped(3).map(group => {
  group.map(_.reduce(_ ++ _)).reduce(_.intersect(_))
}).toList)

pprintln(example.grouped(3).map(group => {
  group.map(_.reduce(_ ++ _)).reduce(_.intersect(_)).map(char => {
    char.toInt - (if char.isUpper then 38 else 96)
  }).sum
}).sum)

pprintln(myInput.grouped(3).map(group => {
  group.map(_.reduce(_ ++ _)).reduce(_.intersect(_)).map(char => {
    char.toInt - (if char.isUpper then 38 else 96)
  }).sum
}).sum)
