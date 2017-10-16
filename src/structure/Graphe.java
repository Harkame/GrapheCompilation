package structure;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class Graphe
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

	    String ls = System.getProperty("line.separator");
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
