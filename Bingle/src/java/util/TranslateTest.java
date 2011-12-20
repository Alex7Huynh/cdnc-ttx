/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import com.memetix.mst.language.Language;
import com.memetix.mst.translate.Translate;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author win7
 */
public class TranslateTest {
       public static void main(String[] args){
        String englishString = "cat and dog";
        Translate.setKey("FE383F9A948802A6D19102654EE563456120DDC6");
        String viString = "";
        try {
             viString = Translate.execute(englishString, Language.ENGLISH, Language.VIETNAMESE);
        } catch (Exception ex) {
            Logger.getLogger(test.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(viString);
        
        
    }
}
