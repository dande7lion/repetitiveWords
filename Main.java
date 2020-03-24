import java.io.*;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {

        Scanner in1 = null;
        Scanner in2 = null;
        String tab1 [] = new String [20];
        String tab2 [] = new String [20];
        String powtorzenia [] = new String [20];
        int powt1 = 0, powt2 = 0;

        String [] znak = {".", ",", "!", "?", ";", ":", "(", ")"};

        int i = 0, j, k, l, m = 0, n, s = 0;

        try {
            in1 = new Scanner(new BufferedReader(new FileReader(args[0])));

            while (in1.hasNext()) {
                tab1[i] = in1.next();
                for (j = 0; j < znak.length; j++) {
                    while (tab1[i].endsWith(znak[j])) {
                        tab1[i] = tab1[i].substring(0, (tab1[i].length()) - 1);
                    }
                }
                i++;
            }
            in2 = new Scanner(new BufferedReader(new FileReader(args[1])));

            while (in2.hasNext()) {
                tab2[m] = in2.next();
                for (l = 0; l < znak.length; l++) {
                    while (tab2[m].endsWith(znak[l])) {
                        tab2[m] = tab2[m].substring(0, (tab2[m].length()) - 1);
                    }
                }
                m++;
            }

            for(k = 0; k < i; k++)
                for(n = 0; n < m; n++){
                    if(tab1[k].equals(tab2[n])){
                        powtorzenia[s] = tab1[k];
                        s++;
                        break;
                    }
                }

            for(k = 0; k < i; k++)
                for(n = 0; n < s; n++){
                    if(tab1[k].equals(powtorzenia[n])){
                        powt1++;
                    }
                }

            for(k = 0; k < m; k++)
                for(n = 0; n < s; n++){
                    if(tab2[k].equals(powtorzenia[n])){
                        powt2++;
                    }
                }

            double powt11 = (double)powt1;
            double powt22 = (double)powt2;
            double ii = (double)i;
            double mm = (double)m;
            double pierwszy = (powt11/ii)*100;
            double drugi = (powt22/mm)*100;
            System.out.println(args[0] + ": " + pierwszy + "% powtarzajacych sie slow");
            System.out.println(args[1] + ": " + drugi + "% powtarzajacych sie slow");

            for(k = 0; k < i; k++)
                for(n = 0; n < s; n++){
                    if(tab1[k].equals(powtorzenia[n])){
                        tab1[k] += "]";
                    }
                }

            for(k = 0; k < m; k++)
                for(n = 0; n < s; n++){
                    if(tab2[k].equals(powtorzenia[n])){
                        tab2[k] += "]";
                    }
                }

        }
        catch (FileNotFoundException ex) {
            System.out.println("Nie ma takiego pliku");
        }
        finally{
            if (in1 != null)
                in1.close();

            if (in2 != null)
                in2.close();

        }

    }
}
