/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import com.memetix.mst.language.Language;
import com.memetix.mst.translate.Translate;

/**
 *
 * @author Fate
 */
public class TranslateEngine {
    public static String Translate(String content) {        
        Translate.setKey("FE383F9A948802A6D19102654EE563456120DDC6");
        String result = "";
        try {
             result = Translate.execute(content, Language.ENGLISH, Language.VIETNAMESE);
        } catch (Exception ex) {
            System.out.println(ex.toString());
            //Logger.getLogger(test.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
}
