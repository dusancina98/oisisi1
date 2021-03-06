package rs.ac.uns.ftn.projekat.view.dialogs;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.Document;

import rs.ac.uns.ftn.projekat.classes.Profesor;
import rs.ac.uns.ftn.projekat.classes.Profesor.Titula;
import rs.ac.uns.ftn.projekat.classes.Profesor.Zvanje;
import rs.ac.uns.ftn.projekat.controllers.ProfesorController;
import rs.ac.uns.ftn.projekat.data.BazaProfesor;
import rs.ac.uns.ftn.projekat.view.MainFrame;
import rs.ac.uns.ftn.projekat.view.ProfesorJTable;

public class IzmeniProfesora extends JDialog{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -9097209350598743027L;
	public static JTextField txtIme;
	public static JTextField txtPrezime ;
	public static JTextField txtDatumRodj ;
	public static JTextField txtAdresa;
	public static JTextField txtBrojTel ;
	public static JTextField txteadresa ;
	public static JTextField txtAdresaKanc ;
	public static JTextField txtBrLicne ;
	@SuppressWarnings("rawtypes")
	public static JComboBox  cbTitula ;
	@SuppressWarnings("rawtypes")
	public static JComboBox cbZvanje ;
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public IzmeniProfesora(JFrame parent) {
		super(parent,"Izmena profesora",true);
			if(BazaProfesor.getInstance().getProfesori().size()==0) {
				JOptionPane.showMessageDialog(null, "Ne postoji ili nije selektovan ni jedan student", "Error", JOptionPane.ERROR_MESSAGE );
		        dispose();
			}else {
				this.setSize(MainFrame.sirina*3/7,MainFrame.visina*6/8);
				this.setLayout(new BorderLayout());
				
				JPanel panelC = new JPanel(new GridBagLayout());  // panel za unos 
				JPanel panelS = new JPanel(new FlowLayout(FlowLayout.RIGHT));  // panel za button-e
				
				Profesor p= new Profesor();
				p= BazaProfesor.getInstance().getRow(ProfesorJTable.selectedRow);
				
				JLabel lblIme = new JLabel("Ime*");
				JLabel lblPrezime = new JLabel("Prezime*");
				JLabel lblDatumRodj= new JLabel("Datum Rodjenja*");
				JLabel lblAdresa = new JLabel("Adresa stanovanja*");
				JLabel lblBrojTel = new JLabel("Broj telefona*");
				JLabel lbleadresa = new JLabel("Email adresa*");
				JLabel lblAdresaKanc = new JLabel("Adresa kancelarije*");
				JLabel lblBrLicne = new JLabel("Broj licne*");
				JLabel lblTitula = new JLabel("Titula*");
				JLabel lblZvanje = new JLabel("Zvanje*");
				
				String[] sTitule = { "Prof.Dr","Doktor","Magistar","Master"};
				 String[] sZvanje = { "Redovni profesor","Vandredni profesor","Asistent","Saradnik u nastavi","Docent" };
				 txtIme = new JTextField();
				 txtPrezime = new JTextField();
				 txtDatumRodj = new JTextField();
				 txtAdresa = new JTextField();
				 txtBrojTel = new JTextField();
				 txteadresa = new JTextField();
				 txtAdresaKanc = new JTextField();
				 txtBrLicne = new JTextField();
				 cbTitula = new JComboBox(sTitule);
				 cbZvanje = new JComboBox(sZvanje);
				
				
				txtIme.setText(p.getIme());
				txtPrezime.setText(p.getPrezime());
				//datum rodjenja
				Date date=p.getDatum_rodjenja();
				DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy.", Locale.ENGLISH);
				txtDatumRodj.setText(dateFormat.format(date));
				//
				txtAdresa.setText(p.getAdresa_stanovanja());
				txtBrojTel.setText(p.getBr_telefona());
				txteadresa.setText(p.getMail());
				txtAdresaKanc.setText(p.getAdresa_kancelarije());
				txtBrLicne.setText(p.getBr_licne());
				
				txtBrLicne.setEditable(false);
				
				if(p.getTitula() == Titula.doktor)
					cbTitula.setSelectedItem("Doktor");
				else if(p.getTitula() == Titula.magistar)
					cbTitula.setSelectedItem("Magistar");
				else if(p.getTitula() == Titula.prof_dr)
					cbTitula.setSelectedItem("Prof.Dr");
				else
					cbTitula.setSelectedItem("Master");

				if(p.getZvanje() == Zvanje.asistent)
					cbZvanje.setSelectedItem("Asistent");
				else if(p.getZvanje() == Zvanje.docent)
					cbZvanje.setSelectedItem("Docent");
				else if(p.getZvanje() == Zvanje.red_profesor)
					cbZvanje.setSelectedItem("Redovni profesor");
				else if(p.getZvanje() == Zvanje.van_profesor)
					cbZvanje.setSelectedItem("Vandredni profesor");
				else
					cbZvanje.setSelectedItem("Saradnik u nastavi");

				
				panelC.add(lblIme, gbclbl(0,0));
				panelC.add(txtIme, gbctxt(1,0));
				panelC.add(lblPrezime,gbclbl(0,1));
				panelC.add(txtPrezime,gbctxt(1,1));
				panelC.add(lblDatumRodj,gbclbl(0,2));
				panelC.add(txtDatumRodj,gbctxt(1,2));
				panelC.add(lblAdresa,gbclbl(0,3));
				panelC.add(txtAdresa,gbctxt(1,3));
				panelC.add(lblBrojTel,gbclbl(0,4));
				panelC.add(txtBrojTel,gbctxt(1,4));
				panelC.add(lblAdresaKanc,gbclbl(0,5));
				panelC.add(txtAdresaKanc,gbctxt(1,5));
				panelC.add(lbleadresa,gbclbl(0,6));
				panelC.add(txteadresa,gbctxt(1,6));
				panelC.add(lblBrLicne,gbclbl(0,7));
				panelC.add(txtBrLicne,gbctxt(1,7));
				panelC.add(lblTitula,gbclbl(0,8));
				panelC.add(cbTitula,gbctxt(1,8));
				panelC.add(lblZvanje,gbclbl(0,9));
				panelC.add(cbZvanje,gbctxt(1,9));
	
				JButton bPotvrda = new JButton("Potvrda");
				JButton bOdustanak = new JButton("Odustanak");
				
				//disable dugmeta 
				JButtonStateControllerIzmeniProfesora jbsc = new JButtonStateControllerIzmeniProfesora(bPotvrda);
				Document textFieldDocIme = txtIme.getDocument();
				textFieldDocIme.addDocumentListener(jbsc);
				Document textFieldDocPrezime = txtPrezime.getDocument();
				textFieldDocPrezime.addDocumentListener(jbsc);
				Document textFieldDocDatumRodj = txtDatumRodj.getDocument();
				textFieldDocDatumRodj.addDocumentListener(jbsc);
				Document textFieldDocAdresa = txtAdresa.getDocument();
				textFieldDocAdresa.addDocumentListener(jbsc);
				Document textFieldDocBrojTel = txtBrojTel.getDocument();
				textFieldDocBrojTel.addDocumentListener(jbsc);
				Document textFieldDocEmailAdresa = txteadresa.getDocument();
				textFieldDocEmailAdresa.addDocumentListener(jbsc);
				Document textFieldDocAdresaKanc= txtAdresaKanc.getDocument();
				textFieldDocAdresaKanc.addDocumentListener(jbsc);
				Document textFieldDocBrojLicneKarte = txtBrLicne.getDocument();
				textFieldDocBrojLicneKarte.addDocumentListener(jbsc);
			
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
						
						if(ProfesorController.getInstance().izmeniProfesora()==1)
							dispose();
					}
				});
				
				panelS.add(bOdustanak);
				panelS.add(bPotvrda);
				
				add(panelC,BorderLayout.NORTH);
				add(panelS,BorderLayout.SOUTH);
				
				//this.setResizable(false);
				this.setLocationRelativeTo(parent);
				this.setVisible(true);
			}
}
	
	private GridBagConstraints gbclbl(int x,int y) {
		GridBagConstraints gbc= new GridBagConstraints();
		gbc.gridx = x;
		gbc.gridy = y;
		gbc.gridwidth = 1;
		gbc.anchor = GridBagConstraints.WEST;
		gbc.insets = new Insets(10, 20, 0, 0);
		return gbc;
	}
	private GridBagConstraints gbctxt(int x,int y) {
		GridBagConstraints gbc= new GridBagConstraints();
		gbc.gridx = x;
		gbc.gridy = y;
		gbc.gridwidth = 3;
		gbc.weightx = 100;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.insets = new Insets(10, 20, 0, 20);
		return gbc;
	}
}

class JButtonStateControllerIzmeniProfesora implements DocumentListener {
    private JButton button;

    JButtonStateControllerIzmeniProfesora(JButton bPotvrda) {
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
    	if(IzmeniProfesora.txtIme.getText().isEmpty() || IzmeniProfesora.txtPrezime.getText().isEmpty() || IzmeniProfesora.txtDatumRodj.getText().isEmpty()
    			|| IzmeniProfesora.txtAdresa.getText().isEmpty() || IzmeniProfesora.txtBrojTel.getText().isEmpty() || IzmeniProfesora.txteadresa.getText().isEmpty()
    			|| IzmeniProfesora.txtAdresaKanc.getText().isEmpty() || IzmeniProfesora.txtBrLicne.getText().isEmpty())
    		button.setEnabled(false);
    	else
    		button.setEnabled(true);

    } 
}
