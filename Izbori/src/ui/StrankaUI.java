package ui;

import java.util.List;
import dao.StrankaDAO;
import model.Stranka;

public class StrankaUI {
	
	public static void ispisiSveStranke() {
		List<Stranka> sveStranke = StrankaDAO.getAll(IzboriUI.getConn());

		System.out.println();
		System.out.printf("%-10s %-10s %-20s", "ID", "Skracenica", "Naziv") ;
		System.out.println();
		System.out.println("========== ========= =================================== ");
		for (Stranka itVal: sveStranke) {
			System.out.printf("%-10s %-10s %-20s ", 
					itVal.getId(), 
					itVal.getSkracenica(), 
					itVal.getNaziv())
					; System.out.println();
			
			System.out.println("---------- ---------- ------------------------------------ ");
		}
	}

		
		
	
}
