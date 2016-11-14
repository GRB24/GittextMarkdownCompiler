/**
  * Created by BatesG on 10/11/2016.
  */
package edu.towson.cosc.cosc455.gbates1.project1

import scala.io.Source._

object Compiler{
 //Variable declarations and class
  var fileContents : String = ""
  var currentToken : String = ""

  val Scanner = new MyLexicalAnalyzer
  val Parser = new MySyntaxAnalyzer
  val SemanticAnalyzer = new MySemanticAnalyzer

  def main(args : Array[String]): Unit = {
    //check usage
    checkFile(args)
    readFile(args(0))

    print(fileContents)

    //Scanner.begin(fileContents)
    //gets first token
    Scanner.getNextToken()

    //calls start state of BNF in SyntaxAnalyzer
    Parser.gittex()

    // on the return, there is a parse tree
    // SemanticAnalyzer
    SemanticAnalyzer.gittex()
  }

  def readFile(file: String) = {
    val source = scala.io.Source.fromFile(file)
    fileContents = try source.mkString finally source.close()
  }

  def checkFile(args : Array[String]) ={
    if (args.length != 1){
      println("usage Error: wrong number of args!")
      System.exit(1)
    }
    else if (! args(0).endWith(".mkd")){
      println("Usage Error: wrong extension!")
      System.exit(1)

    }
  }



}
