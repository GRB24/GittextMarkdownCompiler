/**
 * Created by BatesG on 10/11/2016.
 */
package edu.towson.cosc.cosc455.gbates1.project1

import java.awt.Desktop;
import java.io.{File, IOException}


public class MySemanticAnalyzer {

    //Checks if meaning of the input language through evaluating the syntactically correct strings that are legal

    //prints to HTML file and opens in default browser
    openHTMLFileInBrowser()


    // Hack Scala/Java function to take a String filename and open in default web browswer
    def openHTMLFileInBrowser(htmlFileStr : String) = {
        val file : File = new File(htmlFileStr.trim)
        println(file.getAbsolutePath)
        if (!file.exists())
            sys.error("File " + htmlFileStr + " does not exist.")

        try {
            Desktop.getDesktop.browse(file.toURI)
        }
        catch {
            case ioe: IOException => sys.error("Failed to open file:  " + htmlFileStr)
            case e: Exception => sys.error("Something went wrong...")
        }
    }

}
