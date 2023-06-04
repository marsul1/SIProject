/*
 Walter Vieira (2022-04-22)
 Sistemas de Informa��o - projeto JPAAulas_ex6
 C�digo desenvolvido para iulustra��o dos conceitos sobre acesso a dados, concretizados com base na especifica��o JPA.
 Todos os exemplos foram desenvolvidos com EclipseLinlk (3.1.0-M1), usando o ambientre Eclipse IDE vers�o 2022-03 (4.23.0).
 
N�o existe a pretens�o de que o c�digo estaja completo.

Embora tenha sido colocado um esfor�o significativo na corre��o do c�digo, n�o h� garantias de que ele n�o contenha erros que possam 
acarretar problemas v�rios, em particular, no que respeita � consist�ncia dos dados.  
 
*/

package genericInterfaces;

import java.util.List;

public interface IRepository<Tentity,Tkey> {
	
    List<Tentity> getAll() throws Exception;;
    Tentity find(Tkey k) throws Exception;;
    //List<Tentity> Find(String criteria); // find by criteria
    void add(Tentity entity) throws Exception;;
    void delete(Tentity entity) throws Exception;;
    void save(Tentity e) throws Exception;;

}
