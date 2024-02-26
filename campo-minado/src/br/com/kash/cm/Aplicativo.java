package br.com.kash.cm;

import br.com.kash.cm.modelo.Tabuleiro;
import br.com.kash.cm.visao.TabulConsole;

public class Aplicativo {

	public static void main(String[] args) {
		
		Tabuleiro tabuleiro = new Tabuleiro(6, 6, 6);
		
		new TabulConsole(tabuleiro);
		
		
	}
}
