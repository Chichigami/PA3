/**
 * cse250.objects.TaxEntry.scala
 *
 * Copyright 2019 Andrew Hughes (ahughes6@buffalo.edu)
 *
 * This work is licensed under the Creative Commons
 * Attribution-NonCommercial-ShareAlike 4.0 International License.
 * To view a copy of this license, visit
 * http://creativecommons.org/licenses/by-nc-sa/4.0/.
 *
 * DO NOT MODIFY THIS FILE
 */
package cse250.objects

import scala.collection.mutable.{ArrayBuffer, HashMap, Map}

class TaxEntry {
  val infoMap: Map[String, String] = new HashMap[String, String]

  override def toString: String = {
    TaxEntry.HEADERS.map(h => infoMap.getOrElse(h, "")).addString(new StringBuilder(), ",").result()
  }

  // Scala Cookbook: Equals
  // https://www.oreilly.com/library/view/scala-cookbook/9781449340292/ch04s16.html
  def canEqual(a: Any) = a.isInstanceOf[TaxEntry]

  override def equals(that: Any): Boolean =
    that match {
      case that: TaxEntry => that.canEqual(this) && this.hashCode == that.hashCode
      case _ => false
    }

  override def hashCode: Int = {
    infoMap.hashCode
  }
}

object TaxEntry {
  val HEADERS = Array("PRINT KEY", "FRONT", "DEPTH", "PROPERTY CLASS", "PROP CLASS DESCRIPTION", "HOUSE NUMBER", "STREET", "ADDRESS", "CITY", "STATE", "ZIP CODE (5-DIGIT)", "DEED DATE", "LAND VALUE", "TOTAL VALUE", "SALE PRICE", "YEAR BUILT", "TOTAL LIVING AREA", "OVERALL CONDITION", "# OF FIREPLACES", "# OF BEDS", "# OF BATHS", "COUNCIL DISTRICT", "POLICE DISTRICT", "NEIGHBORHOOD", "LATITUDE", "LONGITUDE", "LOCATION")

  def loadEntries(filename: String, numToLoad: Int): ArrayBuffer[TaxEntry] = {
    // Scala Cookbook reading CSV:
    // https://www.oreilly.com/library/view/scala-cookbook/9781449340292/ch12s06.html
    val bufferedSource = io.Source.fromFile(filename)
    val lines = bufferedSource.getLines
    val buffer = new ArrayBuffer[TaxEntry]()
    lines.next
    for (_ <- 1 to numToLoad) {
      val line = lines.next
      val cols = line.split(',')
      var colsIndex = 0
      val taxEntry = new TaxEntry
      for (headerIndex <- TaxEntry.HEADERS.indices) {
        val entry = {
          if (colsIndex < cols.length && cols(colsIndex).length != 0 && cols(colsIndex)(0) == '"') {
            val sb = new StringBuilder
            sb ++= cols(colsIndex)
            sb += ','
            while (cols(colsIndex).length == 0 || cols(colsIndex).last != '"') {
              colsIndex += 1
              sb ++= cols(colsIndex)
              sb += ','
            }
            (TaxEntry.HEADERS(headerIndex), sb.result.dropRight(1))
          }
          else if (colsIndex < cols.length) {
            (TaxEntry.HEADERS(headerIndex), cols(colsIndex))
          }
          else {
            (TaxEntry.HEADERS(headerIndex), "")
          }
        }
        colsIndex += 1
        taxEntry.infoMap.addOne(entry)
      }
      buffer.append(taxEntry)
    }
    bufferedSource.close
    buffer
  }
}