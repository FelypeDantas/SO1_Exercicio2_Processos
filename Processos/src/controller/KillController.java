package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.swing.JOptionPane;

public class KillController {

		public KillController() {
			super();
		}
		
		//operação irá mostrar o SO em execução
		private String os() {
			String os = System.getProperty("os.name");
			String arch = System.getProperty("os.arch");
			String version = System.getProperty("os.version");
			return os + " - v. " + version + " - arch. " + arch;
		}
		
		public void escolha() {
			String process;
			if(os().contains("Windows")) {
				
				String caminho = "cmd /c";
				process = "TASKLIST /FO TABLE";
				String mataNome = "TASKKILL /IM";
				listaProcessos(process, caminho);
				casos(caminho, "TASKKILL /PID", mataNome);
				
			}else {
				
				String caminho = "cd /home";
				process = "ps -ef";
				String mataNome = "pkill -f";
				listaProcessos(process, caminho);
				casos(caminho, "kill -9", mataNome);
			}
		}
		
		public void listaProcessos(String process, String caminho) {
				try {
					
					Process p = Runtime.getRuntime().exec(process);
					InputStream fluxo = p.getInputStream();
					InputStreamReader leitor = new InputStreamReader(fluxo);
					BufferedReader buffer = new BufferedReader(leitor);
					String linha = buffer.readLine();
					while(linha != null) {
						System.out.println(linha);
						linha = buffer.readLine();
					}
					
					buffer.close();
					leitor.close();
					fluxo.close();
					
				}catch(IOException e) {
					
					String msgError = e.getMessage();
					System.err.println(msgError);
					if(msgError.contains("740")) {
						StringBuffer buffer = new StringBuffer();
						buffer.append(caminho);
						buffer.append(" ");
						buffer.append(process);
						try {
							
							Runtime.getRuntime().exec(buffer.toString());
							
						} catch (IOException e1) {

							e1.printStackTrace();
							
						}
					} else {
						System.err.println(msgError);
					}
				}
		}
		
		public void casos(String acao, String caminho, String mataNome) {
			int opcao = 0;
			String pid = null;
			
			while(opcao != 9) {
				
				opcao = Integer.parseInt(JOptionPane.showInputDialog("Informe a opção: \n 1 - Mata por processo \n 2 - Mata por nome \n 9 - fim"));
				
				switch(opcao) {
					
				case 1: mataPid(pid, caminho, acao);
				      break;
				case 2: mataPorNome(caminho, mataNome);
				      break;
				case 9: JOptionPane.showMessageDialog(null, "Fim de busca");
				     break;
				default: JOptionPane.showMessageDialog(null, "Opção Inválida, digite novamente!");
				}
			}
		}

		public void mataPorNome(String caminho, String mataNome) {
			String nome;
			
			nome = JOptionPane.showInputDialog("Insira o Processo: ");
			String opcao = "y";
			
			try {
//				Process p = Runtime.getRuntime().exec(mataNome);
//				InputStream fluxo = p.getInputStream();
//				InputStreamReader leitor = new InputStreamReader(fluxo);
//				BufferedReader buffer = new BufferedReader(leitor);
//				String linha = buffer.readLine();
//				while(linha != null) {
//					System.out.println(linha);
//					linha = buffer.readLine();
//			     }
				
				StringBuffer buffer = new StringBuffer(mataNome);
				buffer.append(mataNome);
				buffer.append(" ");
				buffer.append(nome);
				
				while(opcao.contains("y") || opcao.contains("Y")) {
					opcao = JOptionPane.showInputDialog("Processo encontrado, deseja matar outro? (y/n)");
					
					if(opcao.contains("y") || opcao.contains("Y")) {
						mataNome = JOptionPane.showInputDialog("Insira o Processo: ");
					}
				}
			
//			buffer.close();
//			leitor.close();
//			fluxo.close();
			
		}catch(IOException e) {
			
			String msgError = e.getMessage();
			System.err.println(msgError);
			if(msgError.contains("740")) {
				StringBuffer buffer = new StringBuffer();
				buffer.append(caminho);
				buffer.append(" ");
				buffer.append(mataNome);
				buffer.append(" ");
				buffer.append(nome);
				try {
					
					Runtime.getRuntime().exec(buffer.toString());
					
				} catch (IOException e1) {

					e1.printStackTrace();
					
				}
			} else {
				System.err.println(msgError);
			}
			
		  }
			
		}

		public void mataPid(String pid, String caminho, String acao) {
			
			pid = JOptionPane.showInputDialog("Insira o Processo: ");
			String opcao = "y";
			
			try {
				Process p = Runtime.getRuntime().exec(acao);
				InputStream fluxo = p.getInputStream();
				InputStreamReader leitor = new InputStreamReader(fluxo);
				BufferedReader buffer = new BufferedReader(leitor);
				String linha = buffer.readLine();
				while(linha != null) {
					System.out.println(linha);
					linha = buffer.readLine();
			     }
				
				while(opcao.contains("y") || opcao.contains("Y")) {
					opcao = JOptionPane.showInputDialog("Processo encontrado, deseja matar outro? (y/n)");
					
					if(opcao.contains("y") || opcao.contains("Y")) {
						pid = JOptionPane.showInputDialog("Insira o Processo: ");
					}
				}
			
     		buffer.close();
			leitor.close();
			fluxo.close();
			
		}catch(IOException e) {
			
			String msgError = e.getMessage();
			System.err.println(msgError);
			if(msgError.contains("740")) {
				StringBuffer buffer = new StringBuffer();
				buffer.append(caminho);
				buffer.append(" ");
				buffer.append(acao);
				buffer.append(" ");
				buffer.append(pid);
				try {
					
					Runtime.getRuntime().exec(buffer.toString());
					
				} catch (IOException e1) {

					e1.printStackTrace();
					
				}
			} else {
				System.err.println(msgError);
			}
			
		  }
		}
		
			
}
