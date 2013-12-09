/* 
 * ENSICAEN
 * 6 Boulevard Marechal Juin 
 * F-14050 Caen Cedex 
 *
 * This file is owned by ENSICAEN students.
 * No portion of this document may be reproduced, copied
 * or revised without written permission of the authors.
 */
/**
 * @author Gaëtan Le Barbé gaetan.lebarbe@ecole.ensicaen.fr
 * @version 0.0.1
 * @date 9 déc. 2013
 */
package robotTetris.tetrisGame.view;

import java.io.IOException;
import java.io.OutputStream;

import javax.swing.JTextArea;
import javax.swing.text.BadLocationException;

/**
 * @author lebarbe
 * 
 */
public class TextAreaOutputStream extends OutputStream
{
	private JTextArea	textArea;

	public TextAreaOutputStream(JTextArea textArea)
	{
		this.textArea = textArea;
	}

	@Override
	public void write(int b) throws IOException
	{
		// redirects data to the text area
		textArea.append(String.valueOf((char) b));
		// scrolls the text area to the end of data
		textArea.setCaretPosition(textArea.getDocument().getLength());
		if (textArea.getLineCount() > 23)
		{
			try
			{
				textArea.replaceRange("", 0, textArea.getLineEndOffset(0));
			} catch (BadLocationException e)
			{
				e.printStackTrace();
			}
		}
	}

}
