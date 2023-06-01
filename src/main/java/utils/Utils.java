package utils;



public class Utils {

    public static void commitSuccess(Object obj, String message) {
        System.out.println("Test was committed successfully");
        System.out.println(message + " -> " + obj);
    }

    /*public static Query getMaxId(String table, String column) {
        return (Integer) this.createQuery("SELECT MAX(p.id) FROM Player p").getSingleResult();
    }*/
}
