package ooperg002;

public class OOPErg002 {

    public static void main(String[] args) {
        System.out.println("----------Ask1----------");
        
        //Ask1 a)
        String s = "Περιφερεια Κεντρικης Μακεδονιας";
        System.out.println(s.length());
        System.out.println("");

        //Ask1 b)
        for (int i = 0; i < s.length(); i++) {
            System.out.print(s.charAt(i) + " ");
        }
        System.out.println("");

        //Ask1 c)
        for (int i = 0; i < s.length(); i++) {
            System.out.println(s.charAt(i));
        }
        System.out.println("");

        //Ask1 e)
        char[] c = s.toCharArray();
        for (int i = 0; i < c.length; i++) {
            System.out.print(c[i] + "|");
        }
        System.out.println("");

        //Ask1 f)
        String[] sarr = s.split(" ");
        for (String b : sarr) {
            for (int i = 0; i < b.length(); i++) {
                System.out.println(b.charAt(i));
            }
        }
        for (String b : sarr) {
            for (int i = 0; i < b.length(); i++) {
                System.out.print(b.charAt(i) + " ");
            }
        }
        System.out.println("");

        //Ask1 g)
        String arktikolekso = "";
        for (String b : sarr) {
            arktikolekso += b.substring(0, 3);
        }
        System.out.println(arktikolekso);
        System.out.println("");

        //Ask1 h)
        sarr[1] = "Δυτικης";
        for (String b : sarr) {
            System.out.print(b + " ");
        }
        System.out.println("");

        //Ask1 i)
        int count = 1;
        for (String word : sarr) {
            if (word.contains("ικης")) {
                System.out.println("βρηκα 'ικης' " + count + " φορες");
            }
        }
        System.out.println("");
        
        System.out.println("----------Ask2----------");
        
        //Ask2 a)
        

    }

}
