import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

class Main {

    private static void printHelp(){
        System.out.println("Usage : java Main <k : coloration> <n : numero_de_graphe>");
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

class Summit
	{
		private String name;
		private int color;

		private ArrayList<Summit> neighbors;

		public Summit(String name)
		{
			this.name = name;
			color = 0;

			neighbors = new ArrayList<>();
		}

        public String getName() {
            return name;
        }

        public static Summit getColorableSummit(List<Summit> summits, int k)
		{

			for(Summit summit : summits){
                if (summit.getDegree() < k && summit.color == 0){
                    return summit;
                }
            }
			return null;
		}

		public static Summit getHighestDegreeSummit(List<Summit> summits){
			Summit summit = summits.get(0);

			for (Summit s : summits){
				if (summit.getDegree() < s.getDegree())
					summit = s;
			}
			return summit;
		}

		public int getFirstAvailableColor()
		{
            int color = 1;
			boolean colorAvailable = false;

			if (neighbors.size() == 0) {
			    colorAvailable = true;
            }

			while(!colorAvailable)
			{
				for(int index = 0; index < neighbors.size(); index++)
				{
					if(neighbors.get(index).color == color)
					{
						color++;
						break;
					}

					if(index == neighbors.size() - 1)
						colorAvailable = true;
				}
			}

            return color;
		}

		private int getDegree()
		{
			return neighbors.size();
		}

		public void link(Summit summitToLink)
		{
			this.neighbors.add(summitToLink);

			summitToLink.neighbors.add(this);
		}

		public void setColor(int newColor)
		{
			color = newColor;
		}

		@Override
		public String toString()
		{
		    String ls = System.lineSeparator();
			StringBuilder toString = new StringBuilder();
			toString.append(name).append(" : (couleur ").append(color).append(") -->");
			for (Summit n : neighbors){
				toString.append(ls).append("\t\t");
                toString.append(n.name).append("(couleur ").append(n.color).append(")");
            }

			toString.append(ls);

			return toString.toString();
		}

		public void removeNeighbor(Summit summitToRemove) {
			neighbors.remove( summitToRemove );
		}

		public Summit copy() {
            return new Summit(this.name);
		}

        public ArrayList<Summit> getNeighbors() {
            return neighbors;
        }

        @Override
        public boolean equals(Object o){
		    return o instanceof Summit && ((Summit) o).getName().equals(this.getName());
        }

        public int getColor() {
            return color;
        }
    }

class Graphe
{
	private List<Summit> summits;
	private List<Summit> summitsSpilled;

	public Graphe()
	{
		summits = new ArrayList<>();
		summitsSpilled = new ArrayList<>();
    }

	public void colorate(int k)
	{
		if(summits.size() == 0) {
            return;
        }


        Summit summit = Summit.getColorableSummit(summits, k);

        Graphe newGraphe = this.copy();

        if(summit != null) // un sommet est trivialement colorable
		{
            newGraphe.removeSummit(summit);
            newGraphe.colorate(k);
            raiseModifications(newGraphe);

            summit.setColor(summit.getFirstAvailableColor());
            System.out.println("Coloration du sommet " + summit.getName() + " en couleur " + summit.getColor());


		}
		else
		{
            summit = Summit.getHighestDegreeSummit(newGraphe.summits);
            System.out.println("Spilling du sommet " + summit.getName());

            newGraphe.removeSummit(summit);
			newGraphe.colorate(k);
			raiseModifications(newGraphe);
			summitsSpilled.add(summit);
        }

	}

    /**
     * Remonte au graphe courant les modifications effectuées par la coloration récursive (car copie en profondeur, non par référence)
     * @param newGraphe le graphe coloré par récursion
     */
    private void raiseModifications(Graphe newGraphe) {
	    for (Summit n : newGraphe.summits){
	        for (Summit s : summits){
	            if (n.equals(s)){
	                s.setColor(n.getColor());
                }
            }
        }

        for (Summit s : newGraphe.summitsSpilled){
	        summitsSpilled.add(s);
        }
    }

    private Graphe copy(){
	    Graphe graphe = new Graphe();
        HashMap<Summit, List<String>> links = new HashMap<>(); // stocker les liens de chaque sommet copié par noms

	    for (Summit s : summits){
	        Summit newSummit = s.copy();
            graphe.summits.add(newSummit);

            List<String> neighborsNames = new ArrayList<>();
            for (Summit n : s.getNeighbors()){
                neighborsNames.add(n.getName());
            }
            links.put(newSummit, neighborsNames);
        }


	    for (Summit s : summitsSpilled)
	        graphe.summitsSpilled.add(s.copy());

	    //Après ajout des sommets, réappliquer les liens sur les nouveaux sommets
	    for (Summit s : graphe.summits)
            for (String name : links.get(s))
                for (Summit n : graphe.summits)
                    if (n.getName().equals(name))
                        s.getNeighbors().add(n);

        return graphe;
    }

	public void addSummits(Summit... summitsToAdd)
	{
        Collections.addAll(summits, summitsToAdd);
	}

	private void removeSummit(Summit summitToRemove)
	{
        for (Summit neighbor : summits){
            neighbor.removeNeighbor(summitToRemove);
        }
		summits.remove(summitToRemove);
	}

	@Override
	public String toString()
	{

	    String ls = System.lineSeparator();
		StringBuilder toString = new StringBuilder();

		toString.append("Sommets :");
		toString.append(ls);

		for(Summit summit : summits)
			toString.append("\t").append(summit.toString());

		toString.append(ls);
		toString.append("Pile :");
		toString.append(ls);

		for(Summit summit : summitsSpilled) {
            toString.append("\t");
            toString.append(summit.getName());
            toString.append(ls);
        }

		return toString.toString();
	}
}
