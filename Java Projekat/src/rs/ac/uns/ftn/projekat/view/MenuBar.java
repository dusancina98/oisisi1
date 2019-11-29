package rs.ac.uns.ftn.projekat.view;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

import rs.ac.uns.ftn.projekat.actions.CreateEditAction;
import rs.ac.uns.ftn.projekat.actions.CreateEntityAction;
import rs.ac.uns.ftn.projekat.additionalclass.ScaleIcon;

public class MenuBar extends JMenuBar{
	
	/**
	 *  JOS AKCIJE DA SE DODAJU , DIZAJN ZAVRSEN
	 */
	private static final long serialVersionUID = 1L;

	public MenuBar() {
		
		JMenu file = new JMenu("File");
		file.setMnemonic(KeyEvent.VK_F);
		
		JMenu edit = new JMenu("Edit");
		edit.setMnemonic(KeyEvent.VK_E);
		
		JMenu help = new JMenu("Help");
		help.setMnemonic(KeyEvent.VK_H);
		CreateEntityAction ac = new CreateEntityAction();
		JMenuItem miNew = new JMenuItem();
		miNew.setAction(ac);
		miNew.setText("New");
		miNew.setIcon(ScaleIcon.ScaleIconSize("icon/addperson.png"));
		miNew.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, ActionEvent.CTRL_MASK));
		JMenuItem miClose = new JMenuItem("Close",ScaleIcon.ScaleIconSize("icon/exit.png"));
		miClose.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, ActionEvent.CTRL_MASK));
		
		CreateEditAction ed= new CreateEditAction();
		JMenuItem miEdit = new JMenuItem();
		miEdit.setAction(ed);
		miEdit.setText("Edit");
		miEdit.setIcon(ScaleIcon.ScaleIconSize("icon/penicon.png"));
		miEdit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, ActionEvent.CTRL_MASK));
		
		JMenuItem miDelete = new JMenuItem("Delete",new ImageIcon("icon/deleteicon.png"));
		miDelete.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, ActionEvent.CTRL_MASK));

		
		JMenuItem miHelp = new JMenuItem("Help",ScaleIcon.ScaleIconSize("icon/helpicon.jpg"));
		miHelp.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H, ActionEvent.CTRL_MASK));
		JMenuItem miAbout = new JMenuItem("About",ScaleIcon.ScaleIconSize("icon/abouticon.jpg"));	
		miAbout.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_U, ActionEvent.CTRL_MASK));


		
		file.add(miNew);
		file.add(miClose);
		
		edit.add(miEdit);
		edit.add(miDelete);
		
		help.add(miHelp);
		help.add(miAbout);
		
		this.add(file);
		this.add(edit);
		this.add(help);
	}
}