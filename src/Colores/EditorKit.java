package Colores;

import javax.swing.text.StyledEditorKit;
import javax.swing.text.ViewFactory;

public class EditorKit extends StyledEditorKit {

    private static final long serialVersionUID = 2969169649596107757L;
    private ViewFactory creatorView;

    public EditorKit(SyntaxTextPane.TipoSintaxis tipo) {
        switch(tipo){
            case FS:
                creatorView = new CreatorViewFS();
                break;
            case XML:
                creatorView = new CreatorViewXML();
                break;
        }
    }
    
    @Override
    public ViewFactory getViewFactory() {
        return creatorView;
    }

    @Override
    public String getContentType() {
        return "text/xml";
    }

    
}
