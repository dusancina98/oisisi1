package rs.ac.uns.ftn.projekat.view.dialogs;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.Document;

import rs.ac.uns.ftn.projekat.classes.Predmet;
import rs.ac.uns.ftn.projekat.controllers.StudentController;
import rs.ac.uns.ftn.projekat.data.BazaPredmet;
import rs.ac.uns.ftn.projekat.view.PredmetJTable;

public class DodajStudentaNaPredmet extends JDialog{

	/**
	 * 
	 */
	public static JTextField txtIndeks;
	private static final long serialVersionUID = 1L;

	public DodajStudentaNaPredmet(JFrame parent) {
		super(parent,"Dodavanje studenta na predmet",true);
			//super(parent,"Dodavanje studenta na predmet",true);
			
			this.setSize(400,120);
			this.setLayout(new BorderLayout());
			Predmet pr = BazaPredmet.getInstance().getRow(PredmetJTable.selectedRow);
		
			JPanel panelC = new JPanel(new GridBagLayout());  // panel za unos 
			JPanel panelS = new JPanel(new FlowLayout(FlowLayout.RIGHT));  // panel za button-e
			
			JLabel lblIndeks = new JLabel("Indeks studenta*");
			 txtIndeks = new JTextField();
			
			GridBagConstraints gbcL= new GridBagConstraints();
			gbcL.gridx = 0;
			gbcL.gridy = 0;
			gbcL.gridwidth = 1;
			gbcL.anchor = GridBagConstraints.WEST;
			gbcL.insets = new Insets(10, 20, 0, 0);
			panelC.add(lblIndeks, gbcL);
			
			GridBagConstraints gbcT= new GridBagConstraints();
			gbcT.gridx = 1;
			gbcT.gridy = 0;
			gbcT.gridwidth = 3;
			gbcT.weightx = 100;
			gbcT.fill = GridBagConstraints.HORIZONTAL;
			gbcT.insets = new Insets(10, 20, 0, 20);
			panelC.add(txtIndeks, gbcT);
			
			JButton bPotvrda = new JButton("Potvrda");
			JButton bOdustanak = new JButton("Odustanak");
			bPotvrda.setEnabled(false);
			
			JButtonStateControllerDodajStudentaNaPredmet jbsc = new JButtonStateControllerDodajStudentaNaPredmet(bPotvrda);
			Document textFieldDocIndeks = txtIndeks.getDocument();
			textFieldDocIndeks.addDocumentListener(jbsc);
			
			bOdustanak.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent e) {
					dispose();
				}
			});
			
			bPotvrda.addActionListener(new ActionListener() {
	
				@Override
				public void actionPerformed(ActionEvent e) {
					
					if(StudentController.getInstance().dodajStudentaNaPredmet(pr)==1)
						dispose();	
				}
			});
			
			
			
			panelS.add(bOdustanak);
			panelS.add(bPotvrda);
			
			this.add(panelC,BorderLayout.CENTER);
			this.add(panelS,BorderLayout.SOUTH);
			
			this.setResizable(false);
			this.setLocationRelativeTo(parent);
			this.setVisible(true);
		}
}

class JButtonStateControllerDodajStudentaNaPredmet implements DocumentListener {
    private JButton button;

    JButtonStateControllerDodajStudentaNaPredmet(JButton bPotvrda) {
        button = bPotvrda;
    }

    public void changedUpdate(DocumentEvent e) {
        disableIfEmpty();
    }

    public void insertUpdate(DocumentEvent e){
        disableIfEmpty();
    }

    public void removeUpdate(DocumentEvent e){
        disableIfEmpty();
    }

    public void disableIfEmpty() {
    	if(DodajStudentaNaPredmet.txtIndeks.getText().isEmpty())
    		button.setEnabled(false);
    	else
    		button.setEnabled(true);

    } 
}
