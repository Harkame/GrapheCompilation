package structure;
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
	
	public void colorate(int k)
	{  
		if(summits.size() == 0)
			return;
		
		Summit summit = Summit.getColorableSummit(summits, k);
		
		Graphe newGraphe = new Graphe();
		newGraphe.summits.addAll(summits);
		newGraphe.summitsSpilled.addAll(summitsSpilled);
		
		if(summit != null)		
		{
			newGraphe.summits.remove(summit);
			
			newGraphe.colorate(k);
			
			summit.setColor(summit.getFirstAvailableColor());
			
			summits.add(summit);
		}
		else
		{

			summit = Summit.getHighestDegreeSummit(newGraphe.summits);
			
			newGraphe.summits.remove(summit);
			
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
		
		toString.append("Sommets :" + System.getProperty("line.separator"));
		
		for(Summit summit : summits)
			toString.append("\t" + summit.toString());
		
		toString.append(System.getProperty("line.separator"));
		toString.append("Pile :" + System.getProperty("line.separator"));
		
		for(Summit summit : summits)
			toString.append("\t" + summit.getName() + System.getProperty("line.separator"));
		
		return toString.toString();
	}
}
