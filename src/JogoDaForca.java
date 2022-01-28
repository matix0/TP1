import java.util.Iterator;
import java.util.Random;
import java.util.Scanner;

public class JogoDaForca {
	static String[][] database = new String[50][50]; // Matriz para armazenar os temas.
	static int i = 0, j = 0, coluna = 1;
	static String nomeTema;
	static String nomePalavra;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int opMainMenu = 0, opMenuTemas = 0, opMenuPalavras = 0;
		do {
			opMainMenu = mainMenu(opMainMenu, sc);
			switch (opMainMenu) {
			case 1:
				do {
					opMenuTemas = menuTemas(opMenuTemas, sc);
					switch (opMenuTemas) {
					case 1:
						String nomeTema = "";
						cadastrarTema(database, sc, i, nomeTema);
						i++;
						break;
					case 2:
						String temaAlvo = "";
						deletaTema(database, i, temaAlvo, sc);
						break;
					case 3:
						String temaBuscado = "";
						buscarTema(database, temaBuscado, i, sc);
						break;
					default:
						break;
					}
				} while (opMenuTemas != 4);

				break;
			case 2:

				do {
					opMenuPalavras = menuPalavras(opMenuPalavras, sc);
					switch (opMenuPalavras) {
					case 1:
						cadastrarPalavra(database, sc, i, j, nomeTema, nomePalavra);
						j++;
						break;
					case 2:
						deletarPalavra(database, sc, i, j, nomeTema, nomePalavra);
						break;
					case 3:
						buscarPalavra(database, nomeTema, nomePalavra, sc);
						break;
					case 4:
						listagemPalavra(database, nomeTema, nomePalavra, sc);
						break;
					default:
						break;
					}
				} while (opMenuPalavras != 5);
				break;
			case 3:
				System.out.println("Jogar");
				break;
			case 4:
				preencherMatriz(database);
				System.out.println("MATRIZ PREENCHIDA COM SUCESSO!");
				break;
			case 5:
				System.out.println("OBRIGADO POR JOGAR :))");
				break;
			default:
				break;
			}
		} while (opMainMenu != 5);
	}

	// =================================================================================

	public static void espacamento() {
		for (int i = 0; i < 5; i++) {
			System.out.println("");
		}
	}

	public static String randomizaValor() {
		String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		StringBuilder sb = new StringBuilder();
		Random random = new Random();
		int length = 5;
		for (int i = 0; i < length; i++) {
			int index = random.nextInt(alphabet.length());
			char randomChar = alphabet.charAt(index);
			sb.append(randomChar);
		}
		String randomString = sb.toString();
		return randomString;
	}

	public static void preencherMatriz(String database[][]) {
		for (int linha = 0; linha < 5; linha++) {
			String temaRandom = randomizaValor();
			database[linha][0] = temaRandom;
			System.out.println("Tema: " + temaRandom);
			for (int coluna = 0; coluna < 10; coluna++) {
				String palavraRandom = randomizaValor();
				database[linha][coluna] = palavraRandom;
				System.out.println("Palavra: " + palavraRandom);
			}
		}

	}

	// =====================================================================================
	// MÓDULO 1

	public static int mainMenu(int opMainMenu, Scanner sc) {
		espacamento();
		System.out.println("=======MENU PRINCIPAL========");
		System.out.println("1. Temas");
		System.out.println("2. Palavras");
		System.out.println("3. Jogar");
		System.out.println("4. Preencher Matriz");
		System.out.println("5. Sair");
		System.out.print("Escolha uma opção: ");
		opMainMenu = sc.nextInt();
		return opMainMenu;
	}

	public static int menuTemas(int opMenuTemas, Scanner sc) {
		espacamento();
		System.out.println("=======MENU TEMAS========");
		System.out.println("1. Cadastro");
		System.out.println("2. Excluir");
		System.out.println("3. Buscar");
		System.out.println("4. Sair");
		System.out.print("Escolha uma opção: ");
		opMenuTemas = sc.nextInt();
		return opMenuTemas;
	}

	public static String cadastrarTema(String database[][], Scanner sc, int i, String nomeTema) {
		espacamento();
		System.out.println("***** CADASTRAR TEMA *****");
		System.out.print("Insira o nome do tema: ");
		nomeTema = sc.next();
		if (validaTema(database, i, nomeTema) == true) {
			System.out.println("Tema já existente");
		} else {
			database[i][0] = nomeTema;
			System.out.println("\nFoi armazenado o tema: " + nomeTema + " na posição: " + i);
		}
		return database[i][0];

	}

	public static Boolean validaTema(String database[][], int i, String compTema) {
		for (int c = 0; c < database.length; c++) {
			if (database[c][0] != null && database[c][0].equals(compTema)) {
				return true;
			} else if (database[c][0] == null) {
				return false;
			}
		}
		return false;
	}

	public static void deletaTema(String[][] database, int i, String temaAlvo, Scanner sc) {
		System.out.print("Escreva o tema que deseja deletar: ");
		temaAlvo = sc.next();
		if (validaTema(database, i, temaAlvo) == true) {
			int posicaoTema = buscarPosicaoTema(database, temaAlvo);
			System.out.println("O tema " + temaAlvo + " será deletado.");
			database[posicaoTema][0] = "";
			arrumaMatrizTemas();
		} else {
			System.out.println("Tema inválido");
		}
	}

	public static int buscarPosicaoTema(String database[][], String temaPesquisado) {
		int auxPosicaoTema = 0;
		for (int a = 0; a < database.length; a++) {
			if (database[a][0] != null && database[a][0].equals(temaPesquisado)) {
				auxPosicaoTema = a;
				break;
			}
		}
		return auxPosicaoTema;
	}

	public static void arrumaMatrizTemas() {
		String[][] tempTemas = new String[50][50];
		int count = 0;
		for (int i = 0; i < database.length; i++) {
			if (database[i][0] != null && !database[i][0].isEmpty() && !database[i][0].isBlank()) {
				tempTemas[count] = database[i];
				count++;
			}
		}
		i = count;
		database = tempTemas;
	}

	public static void buscarTema(String database[][], String temaAlvo, int i, Scanner sc) {
		System.out.print("Insira o tema que deseja pesquisar: ");
		temaAlvo = sc.next();
		if (validaTema(database, i, temaAlvo) == true) {
			i = buscarPosicaoTema(database, temaAlvo);
			System.out.println("TEMA: " + temaAlvo + "\nPOSIÇÃO:" + i + "\nJÁ CADASTRADO!!!");
		} else
			System.out.println("Esse tema não foi cadastrado!");

	}

	// ===============================================================================
	// AQUI COMEÇA A PARTE DO MÓDULO 2

	public static int menuPalavras(int opMenuPalavras, Scanner sc) {
		espacamento();
		System.out.println("=======MENU PALAVRAS========");
		System.out.println("1. Cadastro");
		System.out.println("2. Excluir");
		System.out.println("3. Buscar");
		System.out.println("4. Listar");
		System.out.println("5. Sair");
		opMenuPalavras = sc.nextInt();
		return opMenuPalavras;
	}

	public static String cadastrarPalavra(String database[][], Scanner sc, int i, int j, String nomeTema,
			String nomePalavra) {
		System.out.print("Insira o Tema: ");
		nomeTema = sc.next();
		if (validaTema(database, i, nomeTema) == true) {
			i = buscarPosicaoTema(database, nomeTema);
			System.out.print("Insira a palavra: ");
			nomePalavra = sc.next();
			if (validaPalavra(database, i, j, nomePalavra, nomeTema) == true) {
				for (int a = 1; a < database.length; a++) {
					if (database[i][a] == null) {
						j = a;
						break;
					}
				}
				System.out.println(
						"\nFoi armazenado a palavra: '" + nomePalavra + "' na posição: [" + i + "][" + j + "]");
				database[i][j] = nomePalavra;
			} else {
				System.out.println("palavra já existente!");
			}

		} else {
			System.out.println("Tema inexistente!");
		}
		return database[i][j];
	}

	public static int setarPosicaoPalavra(String database[][], String palavraPesquisada, String temaPesquisado,
			int coluna) {
		int linha = buscarPosicaoTema(database, temaPesquisado);
		for (int a = 1; a < database.length; a++) {
			if (database[linha][a] == null) {
				coluna = a;
				break;
			}
		}
		return coluna;
	}

	public static Boolean validaPalavra(String database[][], int i, int j, String compPalavra, String compTema) {
		i = buscarPosicaoTema(database, compTema);
		j = buscarPosicaoPalavra(database, compPalavra, compTema, coluna);
		for (int k = 0; k < database.length; k++) {
			if (database[i][j] != null && database[i][j].equals(compPalavra)) {
				System.out.println("PALAVRA JÁ EXISTENTE");
				return false;
			}
		}
		return true;
	}

	public static int buscarPosicaoPalavra(String database[][], String palavraPesquisada, String temaPesquisado,
			int coluna) {
		i = buscarPosicaoTema(database, temaPesquisado);
		for (int a = 1; a < database.length; a++) {
			if ((database[i][a] != null) && (database[i][a].equals(palavraPesquisada))) {
				coluna = a;
				break;
			} else if (database[i][a] == null || database[i][a].isBlank()) {
				if (database[i][a] == null) {
					coluna = a;
					break;
				}
			}
		}
		return coluna;
	}

	public static void arrumaMatrizPalavras() {
		String[] novasPalavras = new String[50];
		int count2 = 0;
		for (int j = 0; j < database.length; j++) {
			for (int b = 0; b < database[j].length; b++) {
				if (database[j][b] != null && !database[j][b].isEmpty() && !database[j][b].isBlank()) {
					novasPalavras[count2] = database[j][b];
					count2++;
				}
			}
			database[j] = novasPalavras;
			novasPalavras = new String[50];
		}
	}

	public static void deletarPalavra(String database[][], Scanner sc, int i, int j, String temaAlvo,
			String palavraAlvo) {
		espacamento();
		System.out.println("=========== deletar palavra ==========");
		System.out.print("Insira o tema: ");
		temaAlvo = sc.next();
		if (validaTema(database, i, temaAlvo) == true) {
			int posicaoTema = buscarPosicaoTema(database, temaAlvo);
			System.out.println("POSIÇÃO TEMA:" + posicaoTema);
			System.out.print("Insira a palavra: ");
			palavraAlvo = sc.next();
			if (validaPalavra(database, i, j, palavraAlvo, temaAlvo) == false) {
				int posicaoPalavra = buscarPosicaoPalavra(database, palavraAlvo, temaAlvo, coluna);
				System.out.println("VALOR DO I: " + posicaoTema + " VALOR DO J: " + posicaoPalavra);
				System.out.println("A palavra " + palavraAlvo + " será deletada.");
				database[posicaoTema][posicaoPalavra] = null;
				arrumaMatrizPalavras();
			} else {
				System.out.println(validaPalavra(database, i, j, palavraAlvo, temaAlvo));
				System.out.println("palavra inválida!");
			}
		} else {
			System.out.println("Tema inválido");
		}
	}

	public static void buscarPalavra(String database[][], String temaAlvo, String palavraAlvo, Scanner sc) {
		boolean validacao = false;
		System.out.print("Insira a palavra que deseja pesquisar: ");
		palavraAlvo = sc.next();
		for (int k = 0; k < database.length; k++) {// k = linha / l = coluna
			for (int l = 1; l < database.length; l++) {
				if (palavraAlvo.equals(database[k][l])) {
					temaAlvo = database[k][0];
					System.out.print("A PALAVRA: " + palavraAlvo + " ESTÁ CADASTRADA NO TEMA: " + temaAlvo);
					validacao = true;
					break;
				}
			}
			if (validacao == true) {
				break;
			}
		}
		if (validacao == false)
			System.out.println("PALAVRA NÃO ENCONTRADA");
	}

	// o usuário poderá selecionar um tema e ver todas as palavras cadastrada
	// naquele tema
	public static void listagemPalavra(String database[][], String temaAlvo, String palavraAlvo, Scanner sc) {
		System.out.print("Insira o tema que deseja pesquisar: ");
		temaAlvo = sc.next();
		System.out.println("Tema:" + temaAlvo);
		System.out.println("Palavras: ");
		int auxBuscarPosTema = buscarPosicaoTema(database, temaAlvo);
		for (int j = 1; j < database.length; j++) {
			if (database[auxBuscarPosTema][j] != null && !database[auxBuscarPosTema][j].isBlank()) {
				System.out.println("\t" + database[auxBuscarPosTema][j]);
			}
		}
	}

}
