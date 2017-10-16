package main;

import structure.Graphe;
import structure.Summit;

public class Main {

    private static void printHelp(){
        System.out.println("Usage : java -jar monExe.jar <k : coloration> <n : numero_de_graphe>");
        System.out.println("Avec n = : ");
        System.out.println("\t Graphe 1 : Losange");
        System.out.println("\t Graphe 2 : Exemple du cours");
        System.out.println("\t Graphe 3 : Binôme");
        System.out.println("\t Graphe 4 : Monôme");
    }

    public static void main (String [] args)
    {

        if (args.length < 2){
            printHelp();
            return;
        }


        int k = Integer.parseInt(args[0]);
        int n = Integer.parseInt(args[1]);

        Graphe graphe = new Graphe();

        switch (n){
            case 1:
                Summit a = new Summit("a");
                Summit b = new Summit("b");
                Summit c = new Summit("c");
                Summit d = new Summit("d");

                a.link(b);
                b.link(c);
                c.link(d);
                d.link(a);

                graphe.addSummits(a, b, c, d);

                break;
            case 2:
                Summit t = new Summit("t");
                Summit u = new Summit("u");
                Summit v = new Summit("v");
                Summit x = new Summit("x");
                Summit y = new Summit("y");
                Summit z = new Summit("z");

                t.link(v);
                t.link(y);
                z.link(v);
                v.link(x);
                u.link(x);
                y.link(x);
                y.link(u);

                graphe.addSummits(t, u, v, x, y, z);

                break;
            case 3:
                Summit s = new Summit("a");
                Summit r = new Summit("b");
                s.link(r);
                graphe.addSummits(r, s);

                break;
            case 4:
                graphe.addSummits(new Summit("a"));
                break;
            default:
                System.err.println("Le graphe demandé n'existe pas");
                return;
        }

        graphe.colorate(k);
        System.out.println("\n-----------------------\nGraphe final : \n" + graphe);

    }

}
