package view;

import javax.swing.JOptionPane;

import controller.RedesController;

public class Main {

	public static void main(String[] args) {
		RedesController rd = new RedesController();
		String os = rd.si();
		
		
		int opc = 22;
		
		while(opc!=0) {
			opc = Integer.parseInt(JOptionPane.showInputDialog("1 - Mostrar IP \n"
				+ "2 - Mostrar PING \n"
				+ "0 - Sair"));
			
			switch (opc) {
			case 1:
				JOptionPane.showMessageDialog(null, rd.ip(os));
				break;
			case 2:
				JOptionPane.showMessageDialog(null, rd.ping(os));
				break;
			default: 
				break;
			}
		}
	}

}
