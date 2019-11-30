package rs.ac.uns.ftn.projekat.dialogs;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import rs.ac.uns.ftn.projekat.classes.Predmet;
import rs.ac.uns.ftn.projekat.data.BazaPredmet;
import rs.ac.uns.ftn.projekat.view.PredmetJTable;

public class ObrisiPredmet extends JDialog{

	private static final long serialVersionUID = -5857883443208022631L;
	
	public ObrisiPredmet(JFrame parent) {
		super(parent,"Brisanje predmeta",true);
		
		this.setSize(400,150);
		this.setLayout(new BorderLayout());
		
		JPanel panelS = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		JLabel lblTxt = new JLabel("Da li ste sigurni da zelite da izbrisete izabrani predmet?");
		lblTxt.setBorder(new EmptyBorder(0,10,0,0));
		
		add(lblTxt,BorderLayout.CENTER);
		
		Button bPotvrda = new Button("Potvrda");
		Button bOdustanak = new Button("Odustanak");
		
		bOdustanak.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				dispose();
			}
		});
		
		bPotvrda.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
			
				Predmet p= BazaPredmet.getInstance().getRow(PredmetJTable.selectedRow);
				BazaPredmet.getInstance().izbrisiPredmet(p.getSifra_predmeta());
				
				dispose();
				PredmetJTable.osvezi();
			}
		});
		
		panelS.add(bOdustanak);
		panelS.add(bPotvrda);
		//test
		add(panelS,BorderLayout.SOUTH);
		
		this.setResizable(false);
		this.setLocationRelativeTo(parent);
		this.setVisible(true);
	}

}