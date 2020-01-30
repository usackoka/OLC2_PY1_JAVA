/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Colores;

import javax.swing.text.Element;
import javax.swing.text.View;
import javax.swing.text.ViewFactory;

/**
 *
 * @author Koka
 */
public class CreatorViewFS extends Object implements ViewFactory {
    @Override
    public View create(Element element) {
        return new FSTokens(element);
    }
}
