/*
 Walter Vieira (2022-04-22)
 Sistemas de Informação - projeto JPAAulas_ex3
 Código desenvolvido para iulustração dos conceitos sobre acesso a dados, concretizados com base na especificação JPA.
 Todos os exemplos foram desenvolvidos com EclipseLinlk (3.1.0-M1), usando o ambientre Eclipse IDE versão 2022-03 (4.23.0).
 
Não existe a pretensão de que o código estaja completo.

Embora tenha sido colocado um esforço significativo na correção do código, não há garantias de que ele não contenha erros que possam 
acarretar problemas vários, em particular, no que respeita à consistência dos dados.  
 
*/

package presentation;

import java.util.Scanner;


import businessLogic.*;
import utils.PrintTestsMessages;


/**
 * Hello world!
 *
 */

public class App
{
	protected interface ITest {
		void test();
	}

   @SuppressWarnings("unchecked")
	public static void main( String[] args ) throws Exception
   {   BLService srv = new BLService();
   	ITest[] tests = new ITest[] {
//			() -> {try { srv.testT1(); } catch(Exception e) {
//				System.out.println(e.getMessage());
//			}},
//			() -> {try { srv.testT2(); } catch(Exception e) {
//				System.out.println(e.getMessage());
//			}},
//			() -> {try { srv.testT3(); } catch(Exception e) {
//				System.out.println(e.getMessage());
//			}},
//			() -> {try { srv.testT4(); } catch(Exception e) {
//				System.out.println(e.getMessage());
//			}},
//			() -> {try { srv.testT5(); } catch(Exception e) {
//				System.out.println(e.getMessage());
//			}},
//			() -> {try { srv.testT6(); } catch(Exception e) {
//				System.out.println(e.getMessage());
//			}},
			() -> {try { srv.criarJogador("asds22wawdw@isel.pt", "jawdddwaha", "Europe"); } catch(Exception e) {
				System.out.println(e.getMessage()); // 8
			}},
			() -> {try { srv.banirJogador(3); } catch(Exception e) {
				System.out.println(e.getMessage());// 9
			}},
			() -> {try { srv.desativarJogador(2); } catch(Exception e) {
				System.out.println(e.getMessage());// 10
			}},
			() -> {try { srv.totalPontosJogador(1); } catch(Exception e) {
				System.out.println(e.getMessage()); // 11
			}},
			() -> {try { srv.jogosJogador(1); } catch(Exception e) {
				System.out.println(e.getMessage()); // 12
			}},
			() -> {try { srv.pontosJogoPorJogador("G1"); } catch(Exception e) {
				System.out.println(e.getMessage());// 13
			}},
			() -> {try { srv.associarCracha(1,"G1", "strong"); } catch(Exception e) {
				System.out.println(e.getMessage());// 14
			}},
			() -> {try { srv.iniciarConversa(1, "First Conversation"); } catch(Exception e) {
				System.out.println(e.getMessage());// 15
			}},
			() -> {try { srv.juntarConversa(2,1); } catch(Exception e) {
				System.out.println(e.getMessage());// 16
			}},
			() -> {try { srv.enviarMensagem(1,1,
					"Minha primeira mensagem"); } catch(Exception e) {   // 17
				System.out.println(e.getMessage());
			}},
			() -> {try { srv.jogadorTotalInfo(); } catch(Exception e) {   // 18
				System.out.println(e.getMessage());
			}},
			() -> {try { srv.associarCrachaJPA(1,"G1","strong"); } catch(Exception e) {   // 18
				System.out.println(e.getMessage()); //19
			}},
			() -> {try { srv.optimisticLocking("G1","strong",1); } catch(Exception e) {   // 18
				System.out.println(e.getMessage()); //19
			}},
			() -> {try { srv.pessimistLocking("G1","strong"); } catch(Exception e) {   // 18
				System.out.println(e.getMessage()); //19
			}}
      };

	   Scanner imp = new Scanner(System.in);
	   PrintTestsMessages.buildTestMessages();
	   System.out.print("Escolha um teste (1-" + tests.length + ")? ");
	   int option = imp.nextInt();
	   if (option >= 1 && option <= tests.length)
		   tests[--option].test();
   }
}

