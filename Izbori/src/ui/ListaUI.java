package ui;


import java.util.List;

import utils.PomocnaKlasa;
import model.Lista;
import model.Stranka;
import dao.ListaDAO;


public class ListaUI {
	
	public static void ispisiSveListeSaStrankama() {
		List<Lista> sveListe = ListaDAO.getAll(IzboriUI.getConn());
		
		System.out.println();
		System.out.printf("%-11s %-37s %-10s %-10s %-30s", "ID", "Izborni Slogan", "ID Stranke", "Skracenica", "Naziv") ;
		System.out.println();
		System.out.println("==========  ====================================  ========== ========== ====================================");
		for (Lista itVal: sveListe) {
			System.out.printf("%-11s %-38s  ", 
					itVal.getId(), 
					itVal.getSlogan()); 
				System.out.println();
			for(Stranka itStr: itVal.getStranke()){
				System.out.printf("%-11s %-37s %-10s %-10s %-40s", " "," ",
						itStr.getId(), 
						itStr.getSkracenica(),
						itStr.getNaziv()); 
					System.out.println();
			}
			System.out.println("----------  ------------------------------------  ---------- ---------- ------------------------------------");
		}
	}
	
	public static void glasanje() {
		Lista ls = pronadjiListu();
		if (ls != null) {
			
			int trenutniBroj= ls.getBrojGlasova();
			ls.setBrojGlasova(trenutniBroj+1);
			ListaDAO.update(IzboriUI.getConn(), ls);
			System.out.println("Uspesno ste glasali za listu: " +ls.getSlogan());
		}
	}

	private static Lista pronadjiListu() {
		
		Lista retVal = null;
		System.out.print("Unesi ID Liste:");
		int id = utils.PomocnaKlasa.ocitajCeoBroj();
		retVal = ListaDAO.getListaByID(IzboriUI.getConn(), id);
		if (retVal == null)
			System.out.println("Lista sa ID " + id
					+ " ne postoji u evidenciji");
		return retVal;
		
	}
	
	public static void ispisiStatistikuGlasanja() {
		System.out.print("Unesi Broj clanova unije:");
		double broj = PomocnaKlasa.ocitajRealanBroj();
		List<Lista> sveListe = ListaDAO.getAll(IzboriUI.getConn());
		double ukupanBroj = ListaDAO.ukupanBrojGlasova(IzboriUI.getConn());
		System.out.println();
		System.out.printf("broj clanova koji je izasao na glasanj: %.0f %n", ukupanBroj);
		System.out.printf("broj nevazecih listica: %.0f %n " , (broj*0.8-ukupanBroj));
		System.out.println();
		System.out.printf("%-11s %-37s %-10s %-18s %-27s  %-15s", "R.br", "Izborni Slogan", "Broj gls.", "Nosilac Liste", "Procenat glasova u odnosu na" , "Procenat glasova u odnosu na") ;
		System.out.println();
		System.out.printf("%-11s %-37s %-10s %-18s %-27s   %-15s", "", "", "", "", "ukupan broj clanova" , "broj osvojenih glasova") ;
		System.out.println();
		System.out.println("==========  ====================================  ========== ================== ============================= =============================");
		for (Lista itVal: sveListe) {
			String lice =itVal.getsLice();
			if(itVal.getsLice()==null){
				lice = " ";}
			System.out.printf("%-11s %-37s %-10s %-18s %-28.2f  %-15.2f ", 
					itVal.getId(), 
					itVal.getSlogan(),
					itVal.getBrojGlasova(),
					lice,
					(itVal.getBrojGlasova()/broj)* 100,
					(itVal.getBrojGlasova()/ukupanBroj)* 100);
				System.out.println();
			
		System.out.println("----------  ------------------------------------  ---------- ------------------ ----------------------------- -----------------------------");
		}
	}
	
	
}
