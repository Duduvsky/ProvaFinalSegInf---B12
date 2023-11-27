package app;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import javax.crypto.BadPaddingException;

import controller.CryptoUtils;
import dao.B12Dao;
import dao.PacienteDao;
import dao.SenhaDao;
import dao.UsuarioDao;
import dao.ValorPadraoDao;
import model.B12;
import model.Paciente;
import model.Senha;
import model.Usuario;
import model.ValorPadrao;

public class Application {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        
        // Código começa aqui, você será apresentado a uma janela de opções para poder navegar no programa.
        
        // Para a usar o programa corretamente será necessário seguir alguns passos:
        // 1- Primeiro será necessário criar um Cadastro, para isso você irá informar o Nome e CPF
        // Sendo que o CPF que escolher será usado no "Login", irá ser pedido que informe a senha que deseja usar.
        // 2- Após o cadastro você será jogado na tela inicial novamente, onde agora, poderá fazer login pois já fez o cadastro.
        // 3- Após feito o Cadastro e Login, você irá para a tela "Logado".
        // 4- Nesse novo menu é bem simples, se você deseja ir aos exames ou sair, futuramente essa parte será dividida com os medicos
        // onde poderam criando exames.
        // 5- Selecionando "Exames", você será jogado para outra tela onde poderá Gerar os Resultados dos seus exames e ver os mesmos.
        // 6- Primeiro será necessário você " Gerar os Resultado " onde, será disponibilizado uma chave secreta, para poder prosseguir.
        // 7- Após adquirir a chave secreta, você poderá ver os resultados dos exames, indo na opção "Ver Exames".
        // 8- Irá ser pedido a chave secreta, basta copiar e colar a chave que lhe foi dada.
        // 9- Após isso será mostrado o resultado do seu exame com os valores de referencia.
        // 10- Fim
        
        int opcao;
        do {
            exibirMenu();
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    realizarLogin();
                    break;
                case 2:
                    realizarCadastro();
                    break;
                case 3:
                    System.out.println("Saindo... Até logo!");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }

        } while (opcao != 3);

        scanner.close();
    }

    // Aqui é o Menu principal, onde você irá começar sua jornada
    private static void exibirMenu() {
        System.out.println("\n===== Menu do Usuário =====");
        System.out.println("1 - Login");
        System.out.println("2 - Cadastro");
        System.out.println("3 - Sair");
        System.out.println("===========================\n");
    }
    
    // Aqui é o Menu depois de logado, onde você poderá acessar seu(s) exames
    private static void exibirMenuLogado() {
        System.out.println("\n===== Menu Logado =====");
        System.out.println("1 - Exames");       
        System.out.println("2 - Sair");
        System.out.println("===========================\n");
    }
    
    // Aqui é o Menu apos ter escolhido a opções "Exames".
    private static void exibirMenuExame() {
        System.out.println("\n===== Menu Exame =====");
        System.out.println("1 - Gerar Resultados do Exame");       
        System.out.println("2 - Ver Exame");
        System.out.println("3 - Sair");
        System.out.println("===========================\n");
    }
    
    // Método quando é escolhido a opção "Login" na primeira janela do Menu
    private static void realizarLogin() {
        Scanner scanner = new Scanner(System.in);

        try {
        	// Aqui pegamos o Login e Senha fornecido pelo usuario e criptografamos, usando SHA 128.
            System.out.println("===== Login =====");
            System.out.print("Login: ");
            String login = scanner.nextLine();

            System.out.print("Senha: ");
            String senha = scanner.nextLine();
            
          // Temos a inserção no banco de dados
            String loginHash = CryptoUtils.sha128(login);
            UsuarioDao usuarioDao = new UsuarioDao();
            Usuario usuario = usuarioDao.obterUsuarioPorLogin(loginHash);
            
            // Aqui há a verificação se existe um hash no banco de dados e se o mesmo tem uma senha criptografaga.
            
            if (usuario != null && CryptoUtils.validarSenha(senha, usuario.getSenha())) {
                System.out.println("Login e senha conferem!");
                
                // Login e senha conferidos, vamos entrar nas opções para ir ao exame
                int opcao2;            
                do {
                    exibirMenuLogado();
                    System.out.print("Escolha uma opção: ");
                    opcao2 = scanner.nextInt();
                    scanner.nextLine();

                    switch (opcao2) {
                        case 1:
                            exibirExame();
                            break;
                        case 2:
                            System.out.println("Saindo... Até logo!");
                            break;
                        default:
                            System.out.println("Opção inválida. Tente novamente.");
                    }

                } while (opcao2 != 2);

            } else {
                System.out.println("Usuário ou senha incorretos.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void realizarCadastro() {
        Scanner scanner = new Scanner(System.in);
        try {
            // Informações do usuário para verificar
            System.out.println("===== Cadastro =====");
            
            // Aqui pegamos o Nome e CPF fornecido pelo usuario e criptografamos, usando SHA 128.
            System.out.print("Nome: ");
            String nome = scanner.nextLine();
            
            System.out.print("CPF: ");
            String cpf = scanner.nextLine();
          
            System.out.println("Seu login sera o seu CPF!");
            String cpfCripto = CryptoUtils.sha128(cpf);

            System.out.println("Senha: ");
            String senha = scanner.nextLine();
            String senhaCript = CryptoUtils.sha128(senha);
            
            // Temos a inserção no banco de dados
            Usuario usuarioNovo = new Usuario(cpfCripto, senhaCript);
            UsuarioDao usuarioDao = new UsuarioDao();
            usuarioDao.adicionarUsuario(usuarioNovo);
            Paciente pacienteNovo = new Paciente(nome,cpf);
            PacienteDao pacienteDao = new PacienteDao();
            pacienteDao.adicionarPaciente(pacienteNovo);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    
    private static void exibirExame() {
    	Scanner scanner = new Scanner(System.in);
    	
    	// Aqui exibiremos a ultima janela do programa, onde o usuario poderá Gerar Seus Resultados e Vê-los
    	int opcao3;            
        do {
            exibirMenuExame();
            System.out.print("Escolha uma opção: ");
            opcao3 = scanner.nextInt();
            scanner.nextLine();
                  
            switch (opcao3) {
                case 1:
                	// Caso escolha a opção 1, será gerado o Exame e sua Chave Secrete.
                    gerarExame();
                    break;
                case 2:
                	// Com a Chave Secreta que irá ser dado a você, poderá entrar e verificar os resultados do exame.
                    verExame();
                    break;
                case 3:
                    System.out.println("Saindo... Até logo!");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }

        } while (opcao3 != 3);
    	
    }
    
    private static void gerarExame() {
    	try {
    		// Aqui é feito a Chave Secreta juntamente com a criaçao do Exame, ambos estando vinculados
    		// Somente a Chave Secreta gerada por esse exame poderá ser usada para abrir ele depois.
            String userSecretKey = CryptoUtils.gerarChaveSecreta();
            System.out.println("Chave Secreta na Main: " + userSecretKey);
            SenhaDao senhadao = new SenhaDao();
            Senha senha1 = new Senha(userSecretKey);
            senhadao.adicionarSenha(senha1);
            
           // Dando o resultado o exame, hardcode
            double resultadoDoExame5 = 450;

            String resultadoCriptografado1 = CryptoUtils.encrypt(Double.toString(resultadoDoExame5), userSecretKey);

            adicionarResultadoNoBanco(resultadoCriptografado1);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    // Método para salvar o resultado no Banco de Dados.
    private static void adicionarResultadoNoBanco(String resultadoCriptografado) {
        try {
            B12Dao b12Dao = new B12Dao();
            B12 b12 = new B12(resultadoCriptografado);
            b12Dao.adicionarB12(b12);
        } catch (Exception e) {
			
		}
    }
    
    // Método para a verificação da Chave Secreta que for fornecida pelo usuario.
    private static void verExame() {
    	Scanner scanner = new Scanner(System.in);
    	try {
    		System.out.println("===== Exame =====");
            System.out.print("Informe a Chave Secreta: ");
            String userSecretKey1 = scanner.nextLine();
            obterEImprimirResultado(1, userSecretKey1);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    // Método para verificar os resultados do Exame e dos Valores Padrões.
    private static void obterEImprimirResultado(int id, String userSecretKey) {
        try {
            B12Dao b12Dao = new B12Dao();
            B12 b12 = b12Dao.obterB12PorId(id);
            
            // Visualização dos Resultados
            if (b12 != null) {
            	System.out.println("\n====== Resultado ======");
                System.out.println("ID: " + b12.getId());
                
                String resultadoDescripto = CryptoUtils.decrypt(b12.getResultado(), userSecretKey);
                System.out.println("Resultado B12: " + resultadoDescripto + " pg/mL ");
                System.out.println("\n===== Padrão de Referência =====");
                try {
                    ValorPadraoDao vpDao = new ValorPadraoDao();
                    List<ValorPadrao> vps = vpDao.obterTodosValoresPadroes();

                    for (ValorPadrao vp : vps) {
                        System.out.println("id: "+vp.getId());
                        System.out.println("Unidade: "+ vp.getUnidade());
                        System.out.println("Limite: "+vp.getLimite());
                        System.out.println("Referencia: "+vp.getReferencia());                      
                        System.out.println("----------------------------");
                    }

                } catch (SQLException e) {
                    e.printStackTrace();
                }

            } else {
                System.out.println("hemoglobina não encontrada para o ID " + id);
            }
        } catch (BadPaddingException bpe) {
            System.out.println("Erro de descriptografia: Dados incorretos ou chave inválida.");
            bpe.printStackTrace();
        } catch (Exception e) {
            System.out.println("Erro desconhecido durante a descriptografia.");
            e.printStackTrace();
        }


    }

    	   	 

}
