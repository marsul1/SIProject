package utils;

public class PrintTestsMessages {

    public static void buildTestMessages(){
        StringBuilder stringBuilder = new StringBuilder();

//        stringBuilder.append("1 - Test the creation of a player \n");
//        stringBuilder.append("2 - Test the creation of a game \n");
//        stringBuilder.append("3 - Test the creation of a conversation \n");
//        stringBuilder.append("4 - Player Purschase  \n");
//        stringBuilder.append("5 - Test insert match and single match  \n");
//        stringBuilder.append("6 - Test insert match and multi match   \n");
        stringBuilder.append("1 - criarJogador   \n");
        stringBuilder.append("2 - banirJogador  \n");
        stringBuilder.append("3 - desativarJogador \n");
        stringBuilder.append("4 - totalPontosJogador \n");
        stringBuilder.append("5 - pontosJogoPorJogador \n");
        stringBuilder.append("6 - jogosJogador \n");
        stringBuilder.append("7 - associarCracha \n");
        stringBuilder.append("8 - iniciarConversa \n");
        stringBuilder.append("9 - juntarConversa \n");
        stringBuilder.append("10 - enviarMensagem \n");
        stringBuilder.append("11 - jogadorTotalInfo \n");
        stringBuilder.append("12 - associarCrachaJPA \n");
        stringBuilder.append("13 - optimisticLocking \n");
        stringBuilder.append("14 - pessimistLocking \n");



        System.out.println(stringBuilder);
    }
}
