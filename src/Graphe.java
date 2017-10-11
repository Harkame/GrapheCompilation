import java.util.ArrayList;
import java.util.List;

public class Graphe
{
	private List<Summit> summits;
	private List<Summit> summitsSpilled;
	
	public Graphe()
	{
		summits = new ArrayList<Summit>();
		summitsSpilled = new ArrayList<Summit>();
	}
	
	public void colorate(int k) throws CloneNotSupportedException
	{
		Summit summit = Summit.getColorableSummit(summits, k);
		
		if(summit == null)
			return;
		
		if(true) //il existe un sommet s trivialement colorable
		{
			Graphe newGraphe = (Graphe) this.clone();
			newGraphe.removeSummit(summit);
			
			newGraphe.colorate(k);
			
			summit.setColor(summit.getFirstAvailableColor());
		}
		{
			Graphe newGraphe = (Graphe) this.clone();
			newGraphe.removeSummit(summit);
			
			newGraphe.colorate(k);
			
			summitsSpilled.add(summit);
		}
	}
	
	public void addSummits(Summit... summitsToAdd)
	{
		for(Summit summit : summitsToAdd)
			summits.add(summit);
	}
	
	public void addSummit(Summit summitToAdd)
	{
		summits.add(summitToAdd);
	}
	
	public void removeSummit(Summit summitToRemove)
	{
		summits.remove(summitToRemove);
	}
	
	@Override
	public String toString()
	{
		StringBuilder toString = new StringBuilder();
		
		return toString.toString();
	}
}
