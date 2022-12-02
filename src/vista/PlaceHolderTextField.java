/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;


import java.awt.*;

import javax.swing.*;
import javax.swing.text.Document;

@SuppressWarnings("serial")
public class PlaceHolderTextField extends JTextField {

       public static void main(final String[] args) {
           final PlaceHolderTextField tf = new PlaceHolderTextField("");
           tf.setColumns(20);
           tf.setPlaceholder("All your base are belong to us!");
           final Font f = tf.getFont();
           tf.setFont(new Font(f.getName(), f.getStyle(), 30));
           JOptionPane.showMessageDialog(null, tf);
       }

       private String placeholder;

       public PlaceHolderTextField() {
       }

       public PlaceHolderTextField(
           final Document pDoc,
           final String pText,
           final int pColumns)
       {
           super(pDoc, pText, pColumns);
       }

       public PlaceHolderTextField(final int pColumns) {
           super(pColumns);
       }

       public PlaceHolderTextField(final String pText) {
           super(pText);
       }

       public PlaceHolderTextField(final String pText, final int pColumns) {
           super(pText, pColumns);
       }

       public String getPlaceholder() {
           return placeholder;
       }

       @Override
       protected void paintComponent(final Graphics pG) {
           super.paintComponent(pG);

           if (placeholder.length() == 0 || getText().length() > 0) {
               return;
           }

           final Graphics2D g = (Graphics2D) pG;
           g.setRenderingHint(
               RenderingHints.KEY_ANTIALIASING,
               RenderingHints.VALUE_ANTIALIAS_ON);
           g.setColor(new Color(227,228,217));
           g.drawString(placeholder, getInsets().left+5, pG.getFontMetrics()
               .getMaxAscent() + getInsets().top+7);
       }

       public void setPlaceholder(final String s) {
           placeholder = s;
       }

}