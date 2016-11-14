/**
  * Created by BatesG on 10/11/2016.
  */
package edu.towson.cosc.cosc455.gbates1.project1

trait LexicalAnalyzer {
    def addChar(): Unit

    def getChar(): Char

    def getNextToken(): Unit

    def lookup(): Boolean
}