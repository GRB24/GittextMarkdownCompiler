/**
  * Created by BatesG on 10/11/2016.
  */
package edu.towson.cosc.cosc455.gbates1.project1

//checks syntax of input
class MySyntaxAnalyzer extends SyntaxAnalyzer{



  val parse = new scala.collection.mutable.Stack[String]
  var error : Boolean = false
  def setError() = error = true
  def resetError() = error = false
  def getError : Boolean = error


  override def gittex(): Unit = {
    if (Compiler.currentToken.equalsIgnoreCase(CONSTANTS.DOCB)){
      //add to parse tree
      Compiler.Scanner.getNextToken()
      variableDefine()
      title()
      body()
     if (Compiler.currentToken.equalsIgnoreCase(CONSTANTS.DOCE)){

      }
    }
    else{
      println("Syntax error")
      System.exit(1)
    }
  }
//implementing BNF rules
//
  override def title(): Unit = {
    titleB()
    text()
    bracketE()


  }

  def titleB() = {
    if (Compiler.currentToken.equalsIgnoreCase(CONSTANTS.TITLEB)) {
      parse.push(CONSTANTS.TITLEB)
      Compiler.Scanner.getNextToken()
    }
      else{
        println("Syntax error with Title begin")
      }
  }

  override def body(): Unit = {
    if (Compiler.currentToken.equalsIgnoreCase(CONSTANTS.DEFB)){
      variableDefine()
      body()
    }
    else if (Compiler.currentToken.equalsIgnoreCase(CONSTANTS.PARAB)){
      paragraph()
      body()

    }
    else if (Compiler.currentToken.equalsIgnoreCase(CONSTANTS.NEWLINE)){
      newline()
      body()
    }
    else if (Compiler.currentToken.equalsIgnoreCase(CONSTANTS.DOCE))
      {

      }
    else{
      innerText()
      body()

    }
  }
  def text() : Boolean = {
    val badText = List('~', '!', '@', '#', '$', '%', '^', '&', '*')
    if (badText.contains(Compiler.currentToken)){
      return false

    }
    else{
      return true
    }
  }

  def bracketE() = {
    if (Compiler.currentToken.equalsIgnoreCase(CONSTANTS.BRACKETE)){
      parse.push(Compiler.currentToken)
      Compiler.Scanner.getNextToken()
    }
    else{
      println("Syntax error with [")
      System.exit(1)
    }

  }


  override def paragraph(): Unit = {
    if(!error) paraB()
    while(Compiler.currentToken.equalsIgnoreCase(CONSTANTS.DEFB)){
      if(!error) variableDefine()
    }
    if(!error) paraE()
  }

  def paraB() ={
    if(Compiler.currentToken.equalsIgnoreCase(CONSTANTS.PARAB)){
      parse.push(Compiler.currentToken)
      Compiler.Scanner.getNextToken()
    }
    else{
      println("Syntax error with \\PARAB")
      System.exit(1)
    }
  }
  def paraE() = {
    if (Compiler.currentToken.equalsIgnoreCase(CONSTANTS.PARAE)){
      parse.push(Compiler.currentToken)
      Compiler.Scanner.getNextToken()
    }
    else{
      println("Syntax error with \\PARAE")
      System.exit(1)
    }
  }

  override def innerText(): Unit = {
    while(!error){
      if (Compiler.currentToken.equalsIgnoreCase(CONSTANTS.USEB)){
        variableUse()
        innerText()
      }
      if (Compiler.currentToken.equalsIgnoreCase(CONSTANTS.HEADING)){
        heading()
        innerText()
      }
      if (Compiler.currentToken.equalsIgnoreCase(CONSTANTS.BOLD)){
        bold()
        innerText()
      }
      if (Compiler.currentToken.equalsIgnoreCase(CONSTANTS.ITALICS)){
        italics()
        innerText()
      }
      if(Compiler.currentToken.equalsIgnoreCase(CONSTANTS.LISTITEM)){
        listItem()
        innerText()
      }
      if (Compiler.currentToken.equalsIgnoreCase(CONSTANTS.IMAGEB)){
        image()
        innerText()
      }
      if (Compiler.currentToken.equalsIgnoreCase(CONSTANTS.TEXT)){
        text()
        innerText()
      }
      if (Compiler.currentToken.equalsIgnoreCase(CONSTANTS.LINKB)){
        link()
        innerText()
      }
      else if (Compiler.currentToken.equalsIgnoreCase(CONSTANTS.NEWLINE)){
        if(!error) {
          newline()
          body()
        }
        else {
          if(!error){
            innerText()
            body()
          }
        }
      }
    }
  }

  override def heading(): Unit = {
    headingB()
    text()
  }

  def headingB() = {
    if (Compiler.currentToken.equalsIgnoreCase(CONSTANTS.HEADING)){
      parse.push(Compiler.currentToken)
      Compiler.Scanner.getNextToken()
    }
    else{
      println("Syntax error with HEADING")
      System.exit(1)
    }

  }


  override def variableDefine(): Unit = {
    defB()
    text()
    equalSign()
    text()
    bracketE()
  }

  def defB() = {
    if (Compiler.currentToken.equalsIgnoreCase(CONSTANTS.DEFB)){
      parse.push(Compiler.currentToken)
      Compiler.Scanner.getNextToken()
    }
    else{
      println("Syntax error with Define begin")
    }
  }


  def equalSign() ={
    if (Compiler.currentToken.equalsIgnoreCase(CONSTANTS.EQSIGN)){
      parse.push(Compiler.currentToken)
      Compiler.Scanner.getNextToken()
    }
    else{
      println("Syntax error with =")
      System.exit(1)
    }

  }


  override def variableUse(): Unit = {
    varUseB()
    text()
    bracketE()
  }


  def varUseB() = {
    if (Compiler.currentToken.equalsIgnoreCase(CONSTANTS.USEB)){
      parse.push(Compiler.currentToken)
      Compiler.Scanner.getNextToken()
    }
    else{
      println("Syntax error with Var")
      System.exit(1)
    }
  }


  override def bold(): Unit = {
    if(!error) bold()
    if(!error) text()
    if(!error) bold()

  }

  def boldB() = {
    if (Compiler.currentToken.equalsIgnoreCase(CONSTANTS.BOLD)){
      parse.push(Compiler.currentToken)
      Compiler.Scanner.getNextToken()
    }
    else{
      println("Syntax error with BOLD")
      System.exit(1)
    }

  }


  override def italics(): Unit = {
    if(!error) italics()
    if(!error) text()
    if(!error) italics()


  }

  def italicsB() = {
    if (Compiler.currentToken.equalsIgnoreCase(CONSTANTS.ITALICS)){
      parse.push(Compiler.currentToken)
      Compiler.Scanner.getNextToken()
    }
    else{
      println("Syntax error with Italics")
      System.exit(1)
    }
  }

  override def listItem(): Unit = {
    listItemB()
    innerItem()
  }

  def listItemB() = {
    if (Compiler.currentToken.equalsIgnoreCase(CONSTANTS.LISTITEM)){
      parse.push(Compiler.currentToken)
      Compiler.Scanner.getNextToken()
    }
    else {
      println("Syntax error with List")
      System.exit(1)
    }
  }

  override def innerItem(): Unit = {
    while(!error){
      if(Compiler.currentToken.equalsIgnoreCase(CONSTANTS.USEB)){
        variableUse()
        innerText()
      }
      if (Compiler.currentToken.equalsIgnoreCase(CONSTANTS.BOLD)){
        bold()
        innerItem()
      }
      if (Compiler.currentToken.equalsIgnoreCase(CONSTANTS.ITALICS)){
        italics()
        innerItem()
      }
      if (Compiler.currentToken.equalsIgnoreCase(CONSTANTS.LINKB)){
        link()
        innerText()
      }
      if (Compiler.currentToken.equalsIgnoreCase(CONSTANTS.TEXT)){
        text()
        innerText()
      }
      else{
        setError()
      }
    }
  }


  override def link(): Unit = {
    linkB()
    text()
    leftBracket()
    addressB()
    text()
    addressE()
  }
  def linkB() = {
    if (Compiler.currentToken.equalsIgnoreCase(CONSTANTS.LINKB)){
      parse.push(Compiler.currentToken)
      Compiler.Scanner.getNextToken()
    }
    else{
      println("Syntax error with Link begin")
    }
  }

  override def image(): Unit = {
    imageB()
    text()
    leftBracket()
    addressB()
    text()
    addressE()
  }

  def leftBracket() = {
    if(Compiler.currentToken.equalsIgnoreCase("[")){
      parse.push(Compiler.currentToken)
      Compiler.Scanner.getNextToken()
    }
    else{
      println("Syntax error with [")
      System.exit(1)
    }
  }

  def imageB() = {
    if (Compiler.currentToken.equalsIgnoreCase(CONSTANTS.IMAGEB)){
      parse.push(Compiler.currentToken)
      Compiler.Scanner.getNextToken()
    }
    else{
      println("Syntax error with Image begin")
    }

  }
  def addressB() = {
    if (Compiler.currentToken.equalsIgnoreCase(CONSTANTS.ADDRESSB)){
      parse.push(Compiler.currentToken)
      Compiler.Scanner.getNextToken()
    }
    else{
      println("Syntax error with Address begin")
    }

  }

  def addressE() = {
    if (Compiler.currentToken.equalsIgnoreCase(CONSTANTS.ADDRESSE)){
      parse.push(Compiler.currentToken)
      Compiler.Scanner.getNextToken()
    }
    else{
      println("Syntax error with Address end")
    }

  }

  override def newline(): Unit = {
    if (Compiler.currentToken.equalsIgnoreCase((CONSTANTS.NEWLINE))) {
      parse.push(CONSTANTS.NEWLINE)
      Compiler.Scanner.getNextToken()
    }

    else {
      println("Syntax error with \\")
      System.exit(1)
    }
  }


}
