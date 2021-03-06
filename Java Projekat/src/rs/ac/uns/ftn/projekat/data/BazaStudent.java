package rs.ac.uns.ftn.projekat.data;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import rs.ac.uns.ftn.projekat.classes.Predmet;
import rs.ac.uns.ftn.projekat.classes.Student;
import rs.ac.uns.ftn.projekat.classes.Student.Status;


public class BazaStudent {
	
	private static BazaStudent instance = null; // kreiramo referencu koja ce pokazivati na bazu
	
	public static BazaStudent getInstance() {  // kreiramo metodu koja ce vracati instancu baze(referencu)
		if (instance == null) {
			instance = new BazaStudent();
		}
		return instance;
	}
	 
	private List<Student> studenti;  // lista studenata
	private List<String> kolone; // kolone tabele
	private List<Student> pretrazeni_studenti; // lista pretrazenih studenata
	public static int indikator = 0;  // da li je u toku lista pretrazenih 1 -DA 0 -NE
	
	private BazaStudent() {  // konstruktor klase Baza Studenata
		
		initStudent();

		this.kolone = new ArrayList<String>(); // instanciramo listu kolona i dodajemo elemente
		this.kolone.add("Indeks");
		this.kolone.add("Ime");
		this.kolone.add("Prezime");
		this.kolone.add("Datum rodjenja");
		this.kolone.add("Status");
		this.kolone.add("Datum upisa");
		this.kolone.add("Prosek");
		this.kolone.add("Detalji");
		this.kolone.add("Predmeti");
	}
	
	private void initStudent() { // inicijalizovace studente u listu
		this.studenti = new ArrayList<Student>();
		this.pretrazeni_studenti = new ArrayList<Student>();
	}
	
	public Student getStudentInd(String idx) {
		Student s= null;
		for(Student p : studenti) {
			if(p.getIndeks().equals(idx)) {
				s=p;
				break;
			}
		}
		return s;
	}
	
	public List<Student> getPretrazeni() {
		return this.pretrazeni_studenti;
	}
	
	public void setPretrazeni(List<Student> pretrazeni) {
		this.pretrazeni_studenti = pretrazeni;
	}
	
	public List<Student> getStudenti() { // vraca listu studenata
		return studenti;
	}
	
	public void setStudenti(List<Student> studenti) { // setuje studente
		this.studenti = studenti;
	}
	
	public int getColumnCount() {
		return 9;
	}
	
	public String getColumnName(int index) { // vraca naziv kolone na indeksu
		return this.kolone.get(index);
	}
	
	public Student getRow(int rowIndex) { // vraca studenta u odredjenoj vrsti
		return this.studenti.get(rowIndex);
	}
	
	
	public Object getValueAt(int row, int column) {
		Student stud = new Student();
		if(indikator == 0)
			stud = this.studenti.get(row);
		else
			stud = this.pretrazeni_studenti.get(row);
		
		switch (column) {
		case 0:
			return stud.getIndeks();
		case 1:
			return stud.getIme();
		case 2:
			return stud.getPrezime();
		case 3:
			return stud.getDatum_rodjenja();
		case 4:
			return stud.getStatus().toString();
		case 5:
			return stud.getDatum_upisa();
		case 6:
			return stud.getProsecna_ocena();
		default:
			return null;
		}
	}
	
	public void dodajStudenta(String i,String p,String a_s,String k_t,
			String e_a,String ind,Date dr,Date du,int g_s,
			double p_o,Status ss) {
			this.studenti.add(new Student(i, p, a_s, k_t,e_a,ind,dr,du,g_s,p_o,ss));
		
			
	}
	// metoda koja proverava da li postoji student sa prosledjenim indeksom u bazi
	public boolean postoji(String indeks) {
		boolean pronadjen=false;
		for(Student s : studenti) {
			if(s.getIndeks().equals(indeks))
				pronadjen=true;
		}
		return pronadjen;
	}
	//brise studenta po indeksu
	public void izbrisiStudenta(String indeks_studenta) {
		for (Student s : studenti) {
			if (s.getIndeks().equals(indeks_studenta)) {
				studenti.remove(s);
				// metoda brise u listi predmeta studenta kojeg smo izbrisali
				BazaPredmet.getInstance().obrisiStudentaSaSvihPredmeta(indeks_studenta);
				break;
			}
		}
	}
	
	// u slucaju da obrisemo predmet metoda brise predmet studentima koji slusaju taj predmet
	public void izbrisiPredmetStudentima(String sifra_predmeta) {
		for (Student s : studenti) {
			for(Predmet pred : s.getPredmeti()) {
				if(pred.getSifra_predmeta().equals(sifra_predmeta)) {
					s.getPredmeti().remove(pred);
					break;
				}
			}
		}
	}
	// u slucaju izmene predmeta vrsi se izmena i u listi predmeta kod studenta
	public void izmenaPredmetaStudentima(Predmet predmet) {
		for(Student s : studenti) {
			for(Predmet pred : s.getPredmeti()) {
				if(pred.getSifra_predmeta().equals(predmet.getSifra_predmeta())) {
					pred.setGodina_studija(predmet.getGodina_studija());
					pred.setNaziv(predmet.getNaziv());
					pred.setSemestar(predmet.getSemestar());
					break;
				}
			}
		}
	}
	
	public void izmeniStudenta(String indeks_studenta, String ime,String prz,String a_s,String k_t,
			String e_a,Date dr,Date du,int g_s,
			double p_o,Status ss) {
		for (Student s : studenti) {
			if (s.getIndeks().equals(indeks_studenta)) {
				s.setIme(ime);
				s.setPrezime(prz);
				s.setAdresa_stanovanja(a_s);
				s.setKontakt_telefon(k_t);
				s.setEmail_adresa(e_a);
				s.setDatum_rodjenja(dr);
				s.setDatum_upisa(du);
				s.setGod_studija(g_s);
				s.setProsecna_ocena(p_o);
				s.setStatus(ss);
			}
		}
	}
	
	public void dodajPredmet(String indeks_studenta, Predmet p) {
		for (Student s : studenti) {
			if (s.getIndeks().equals(indeks_studenta)) {
				s.getPredmeti().add(p);
			}
		}
	}
	// vraca listu indeksa svih studenata
	public ArrayList<String> getIndekse(){
		ArrayList<String> lista = new ArrayList<String>();
		
		for(Student s : studenti) {
			lista.add(s.getIndeks());
		}	
		return lista;
	}
	
	public void serialize() {
		try { ObjectOutputStream out = new ObjectOutputStream(
		          new FileOutputStream("./datafiles/studentiFile.txt"));
		      out.writeObject(this.getStudenti());
		      out.close();
		}catch(IOException e) {
			e.printStackTrace();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("unchecked")
	public void deserialize() {
		try {  ObjectInputStream in = new ObjectInputStream(
		          new FileInputStream("./datafiles/studentiFile.txt"));
			this.setStudenti((ArrayList<Student>)in.readObject());
			in.close();
		}catch(FileNotFoundException e) {
		}
		catch(IOException e) {
			e.printStackTrace();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	// vraca stvarni indeks iz pretrazene liste
	public int getRealRowForFilter(int selectedrow) {
		Student stud = pretrazeni_studenti.get(selectedrow);
		int i = 0;
		for(Student s : getStudenti()) {
			if(stud.getIndeks().equals(s.getIndeks())) {
				break;
			}
			i++;
		}
		return i;
	}
	//	brise predmet studentu kada se iz JList izbrise student
	public void brisanjeStudentaSaPredmeta(String indexStudenta,String sifraPredmeta) {
		for(Student s : studenti) {
			if(s.getIndeks().equals(indexStudenta)) {
				for(Predmet p : s.getPredmeti()) {
					if(p.getSifra_predmeta().equals(sifraPredmeta)) {
						s.getPredmeti().remove(p);
						break;
					}
				}
				break;
			}
		}
	}
	
}
