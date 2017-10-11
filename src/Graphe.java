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
		if(summits.size() == 0)
			return;
		
		Summit summit = Summit.getColorableSummit(summits, k);
		
		Graphe newGraphe = (Graphe) this.clone();
		newGraphe.removeSummit(summit);
		
		newGraphe.colorate(k);
		
		if(summit != null)		
			summit.setColor(summit.getFirstAvailableColor());
		else
		{
			summit = Summit.getHighestDegreeSummit(newGraphe.summits);
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
