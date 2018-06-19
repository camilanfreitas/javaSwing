package br.go.senac.casabancaria.swing;

import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;


public class CampoLista extends JComboBox{
	private static final long serialVersionUID = -7866771598150045558L;
	
	public CampoLista(){
		addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				
			}
		});
		addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent evt) {
				if (evt.getKeyCode() == KeyEvent.VK_ENTER) transferFocus();
			}
		});
	}
	
	@Override
	public void setModel(ComboBoxModel modelo) {
		if(modelo != null){
			((DefaultComboBoxModel)modelo).insertElementAt(null, 0);
			modelo.setSelectedItem(modelo.getElementAt(0));
		}
		super.setModel(modelo);
	}
	
}