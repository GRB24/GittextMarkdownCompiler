/**
  * Created by BatesG on 10/11/2016.
  */
package edu.towson.cosc.cosc455.gbates1.project1

import com.sun.org.apache.xalan.internal.xsltc.compiler.CompilerException

//Checks tokens for the input tokens exist
class MyLexicalAnalyzer extends LexicalAnalyzer{


  private val lexical: Array[Char] = new Array[Char](100)
  private var next: Char = _
  var current: Int = 0

  //deal with spaces
  def isSpace(a: String): Boolean ={
    if (a==" ") return true
    false
  }

//adds char
  override def addChar(): Unit = {
    lexical(length) = next
    length +=1
    current +=1
  }

//gets char
  override def getChar(): Char = {
    next = source.charAt(current)
    val a = ""+next
    if (isSpace(a)){
      current += 1
      getChar

    }
  }
//gets next token
  override def getNextToken(): Unit = {
    try {
      if (current >= source.length) Compiler.currentToken = "" else {
        if (text.legal(String.valueOf(next))) {
          addChar()
          //addText()
        } else if (next == "") {
          addChar()
          getChar
          //addHashTag()
        } else if (next == '#') {
          addChar()
          getChar
          //addVariable()
        } else if (next == '*') {
          addChar()
          getChar
          if (next == '*') {
            addChar()
            getChar
          }
        } else if (lookup()) {
          addChar()
        } else {
          throw new CompilerException("Lexical Error: Not a valid token")
        }
      }
    } catch {
      case e: CompilerException => {
        System.exit(0)
      }
    }

    getChar
  }


  override def lookup(): Boolean = {
    val lookup = "" + getNextToken()
    val tokens = Array("*","**","\\BEGIN","\\END","\\TITLE[","]","[","\\\\","+","\\USE[","\\DEF[","\\PARAB","\\PARAE","#","(",")","=","![","]",
      (('a' to 'z') ++ ('A' to 'Z') ++ ('0' to '9')).toString())
    for(a<- tokens if lookup == a) return true
    false
  }


}
