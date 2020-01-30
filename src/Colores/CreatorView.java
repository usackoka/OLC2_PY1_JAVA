package Colores;

import javax.swing.text.Element;
import javax.swing.text.View;
import javax.swing.text.ViewFactory;


/**
 * @author kees
 * @date 13-jan-2006
 *
 */
public class CreatorView extends Object implements ViewFactory {

    /**
     * @param element
     * @return 
     * @see javax.swing.text.ViewFactory#create(javax.swing.text.Element)
     */
    @Override
    public View create(Element element) {
        return new XmlTokens(element);
    }

}
