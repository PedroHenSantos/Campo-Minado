package br.com.kash.cm.visao;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Scanner;

import br.com.kash.cm.excecao.ExplosaoException;
import br.com.kash.cm.excecao.SairException;
import br.com.kash.cm.modelo.Tabuleiro;

public class TabulConsole {
	
	private Tabuleiro tabuleiro;
	private Scanner entrada = new Scanner(System.in);
	
	public TabulConsole(Tabuleiro tabuleiro) {
		this.tabuleiro = tabuleiro;
		
		executarJogo();
	}

	private void executarJogo() {
		try {
			boolean continuar = true;
			
			while(continuar) {
				cicloDoJogo();
				
				System.out.println("Deseja iniciar outra partida? (S/n) ");
				String resposta = entrada.nextLine();
				
				if("n".equalsIgnoreCase(resposta)) {
					continuar = false;
				}else {
					tabuleiro.reiniciar();
				}
			}
		}catch(SairException e) {
			System.out.println("Byee!!!");
		}finally {
			entrada.close();
		}
	}

	private void cicloDoJogo() {
		try {
			
			while(!tabuleiro.objetivoAtingido() ) {
				System.out.println(tabuleiro);
				
				String digitado = capturarValorDigitado("Digite (x, y:)");
				
				
				Iterator<Integer> xy = Arrays.stream(digitado.split(","))
				.map(e -> Integer.parseInt(e.trim())).iterator();
				
				digitado = capturarValorDigitado("1 - Abrir ou 2 - (Des)Marcar: ");
				
				if("1".equalsIgnoreCase(digitado)) {
					tabuleiro.abrir(xy.next(), xy.next());
				}else if("2".equalsIgnoreCase(digitado)) {
					tabuleiro.alterarMarcacao(xy.next(), xy.next());
				}
			}
			
			System.out.println(tabuleiro);
			System.out.println("Você Ganhou!!!");
		}catch(ExplosaoException e) {
			System.out.println(tabuleiro);
			System.out.println("BOOM!!! /n Você Perdeu!!!");
		}
	}
	
	private String capturarValorDigitado(String texto) {
		System.out.print(texto);
		String digitado = entrada.nextLine();
		
		if("sair".equalsIgnoreCase(digitado)) {
			throw new SairException();
		}
		
		return digitado;
	}
}
