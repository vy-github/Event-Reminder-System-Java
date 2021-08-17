/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eve_rem;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

/**
 *
 * @author Vaibhav
 */
public class set_Maxlimit extends PlainDocument
{
    private final int limit;
    
    public set_Maxlimit(int limitation)
    {
        this.limit=limitation;
    }
    @Override
    public void insertString(int offset,String str,AttributeSet set) throws BadLocationException
    {
        if(str==null)
        {
        }
        else if((getLength() + str.length()) <= limit)
        {
            str = str;
            super.insertString(offset, str, set);
        }
            
    }
}
