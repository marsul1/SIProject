package utils;

public class PrintTestsMessages {

    public static void buildTestMessages(){
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("1 - Test the creation of a player \n");
        stringBuilder.append("2 - Test the creation of a game \n");
        stringBuilder.append("3 - Test the creation of a conversation \n");
        stringBuilder.append("4 - Test the creation of a match \n");

        System.out.println(stringBuilder);
    }
}
