package gov.goias.sfr.desktop.util;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.plaf.basic.BasicTextFieldUI;
import javax.swing.text.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;

public class CaixaTexto extends JTextField{

    public CaixaTexto(){
//
//        this.setDocument(new PlainDocument(){
//            public void insertString(final int offs, final String str, final AttributeSet a) throws BadLocationException {
//                try{
//                    Integer.parseInt(str);
//                    if (str != null && (getLength() + str.length() <= tamanho)) {
//                        super.insertString(offs, str, a);
////                        if(getLength() == tamanho){
////                            this.fireChangedUpdate(new DocumentEvent() {
////                                public int getOffset() {return 0;}
////                                public int getLength() {return tamanho;}
////                                public Document getDocument() {return null;}
////                                public EventType getType() {return EventType.CHANGE;}
////                                public ElementChange getChange(Element elem) {return null;}
////                            });
////                        }
//                    }
//                }catch(NumberFormatException e){}
//            }
//        });
        //f.install(this);
        this.setUI(new BasicTextFieldUI() {
            private final int round = 6;
            private final int shadeWidth = 2;
            private final int textSpacing = 3;
            public void installUI(final JComponent componente) {
                super.installUI(componente);
                componente.setOpaque(false);
                final int s = shadeWidth + 1 + textSpacing;
                componente.setBorder(BorderFactory.createEmptyBorder(s, s, s, s));
            }
            protected void paintSafely(final Graphics grafico) {
                final Graphics2D g2d = (Graphics2D) grafico;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                final Shape border = getBorderShape();
                final Stroke os = g2d.getStroke();
                g2d.setStroke(new BasicStroke(shadeWidth * 2));
                g2d.setPaint(Color.black);
                g2d.setStroke(os);
                g2d.setPaint(Color.WHITE);
                g2d.fill(border);
                g2d.setPaint(Color.BLACK);
                super.paintSafely(grafico);
            }
            private Shape getBorderShape() {
                final JTextComponent c = getComponent();
                if (round > 0) {
                    return new RoundRectangle2D.Double(shadeWidth, shadeWidth, c.getWidth() - shadeWidth * 2 - 1, c.getHeight() - shadeWidth * 2 - 1, round * 2, round * 2);
                } else {
                    return new Rectangle2D.Double(shadeWidth, shadeWidth, c.getWidth() - shadeWidth * 2 - 1, c.getHeight() - shadeWidth * 2 - 1);
                }
            }
        });
    }
}
