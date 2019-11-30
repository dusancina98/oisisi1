package rs.ac.uns.ftn.projekat.data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import rs.ac.uns.ftn.projekat.classes.Predmet;
import rs.ac.uns.ftn.projekat.classes.Profesor;

public class BazaProfesor {

	private static BazaProfesor instance = null;
	//singleton?
	public static BazaProfesor getInstance() {
		if (instance == null) {
			instance = new BazaProfesor();
		}
		return instance;
	}
	
	private List<Profesor> profesori;
	private List<String> kolone;
	
	private BazaProfesor() {
	
		initPredmet();

		this.kolone = new ArrayList<String>();
		this.kolone.add("Ime");
		this.kolone.add("Prezime");
		this.kolone.add("Broj telefona");
		this.kolone.add("Mail");
		this.kolone.add("Adresa kancelarije");
		this.kolone.add("Broj licne karte");

	}
	@SuppressWarnings("deprecation")
	private void initPredmet() {
		this.profesori = new ArrayList<Profesor>();
		profesori.add(new Profesor("Petar", "Peric","Novosadskog Sajma 4","+38164568745","profa1@gmail.com","Jugodrvo 1","0056984","docent","doktor",new Date(1998,04,03) ));
		profesori.add(new Profesor("Stefan", "Maric","Maksima Gorkog 4","+38164568745","profa2@gmail.com","Jugodrvo 2","0056921","docent","doktor",new Date(1988,04,03)));
		profesori.add(new Profesor("Petar", "Peric","Novosadskog Sajma 4","+38164568745","profa3@gmail.com","Jugodrvo 3","1003546","docent","doktor",new Date(1978,8,8)));
	}
	
	public List<Profesor> getProfesori() {
		return profesori;
	}

	public void setProfesori(List<Profesor> profesori) {
		this.profesori = profesori;
	}

	public int getColumnCount() {
		return 6;
	}

	public String getColumnName(int index) {
		return this.kolone.get(index);
	}

	public Profesor getRow(int rowIndex) {
		return this.profesori.get(rowIndex);
	}

	public String getValueAt(int row, int column) {
		Profesor profesor = this.profesori.get(row);
		switch (column) {
		case 0:
			return profesor.getIme();
		case 1:
			return profesor.getPrezime();
		case 2:
			return profesor.getBr_telefona();
		case 3:
			return profesor.getMail();
		case 4:
			return profesor.getAdresa_kancelarije();
		case 5:
			return profesor.getBr_licne();		
		default:
			return null;
		}
	}

	public void dodajProfesora(String ime, String prezime, String adresa_stanovanja, String br_telefona, String mail,
			String adresa_kancelarije, String br_licne, String titula, String zvanje, Date datum_rodjenja) {
		this.profesori.add(new Profesor(ime, prezime, adresa_stanovanja, br_telefona,mail,adresa_kancelarije,br_licne,titula,zvanje,datum_rodjenja));
	}

	public void izbrisiProfesora(String br_licne) {
		for (Profesor p : profesori) {
			if (p.getBr_licne().equals(br_licne)) {
				profesori.remove(p);
				break;
			}
		}
	}

	public void izmeniPredmet(String ime, String prezime, String adresa_stanovanja, String br_telefona, String mail,
			String adresa_kancelarije, String br_licne, String titula, String zvanje, Date datum_rodjenja) {
		for (Profesor p : profesori) {
			if (p.getBr_licne().equals(br_licne)) {
				p.setIme(ime);
				p.setPrezime(prezime);
				p.setAdresa_stanovanja(adresa_stanovanja);
				p.setBr_telefona(br_telefona);
				p.setMail(mail);
				p.setAdresa_kancelarije(adresa_kancelarije);
				p.setBr_licne(br_licne);//jel sme da se menja?
				p.setTitula(titula);
				p.setZvanje(zvanje);
				p.setDatum_rodjenja(datum_rodjenja);
			}
		}
	}
}