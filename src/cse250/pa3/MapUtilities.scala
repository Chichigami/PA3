/**
 * GroupByStore.scala
 *
 * Copyright 2019 Andrew Hughes (ahughes6@buffalo.edu)
 *
 * This work is licensed under the Creative Commons
 * Attribution-NonCommercial-ShareAlike 4.0 International License.
 * To view a copy of this license, visit
 * http://creativecommons.org/licenses/by-nc-sa/4.0/.
 *
 * Submission author
 * UBIT: garyfeng
 * Person#: 50242102
 *
 * Collaborators (include UBIT name of each, comma separated):
 * UBIT:
 */
package cse250.pa3

import cse250.objects.{StreetGraph, TaxEntry}

import scala.collection.mutable
import scala.xml.XML

object MapUtilities {
  def loadIntersectionIDs(filename: String): mutable.Set[String] = {
    val file = XML.loadFile(filename)
    val nodeSet: mutable.Set[String] = mutable.Set()

    for {
      nodeString <- file \\ "node" \@ "id"
    } yield {
      println(nodeString)
      nodeSet += nodeString
    }
    nodeSet
  }


  def loadMapInfo(filename: String): mutable.Map[String, mutable.Set[String]] = {
//    val file = XML.loadFile(filename)
//    val nodeSet: mutable.Set[String] = mutable.Set()
//    val nodeMap: mutable.Map[String, mutable.Set[String]] = mutable.Map()
//
//    for {
//      wayID <- file \\ "way" \@ "id"
//    } yield {
//
//    }
    ???
  }

  def buildIntersectionGraph(intersectionIDs: mutable.Set[String],
                             nodeToStreetMapping: mutable.Map[String, mutable.Set[String]]): StreetGraph = {
    val streetGraph = new StreetGraph
    streetGraph
  }

  def computeFewestTurns(streetGraph: StreetGraph, start: TaxEntry, end: TaxEntry): Int = {
    -1
  }

  def computeFewestTurnsList(streetGraph: StreetGraph, start: TaxEntry, end: TaxEntry): Seq[String] = {
    List()
  }
}
