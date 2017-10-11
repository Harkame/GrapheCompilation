import java.util.ArrayList;
import java.util.TreeMap;

public class Graphe
{
	private ArrayList<Summit> summits;
	
	public Graphe()
	{
		summits = new ArrayList<Summit>();
	}
	
	public void colorate(Graphe grapheToColorate)
	{
		
	}
	
	public void addSummit(Summit summitToAdd)
	{
		summits.add(summitToAdd);
	}
	
	@Override
	public String toString()
	{
		StringBuilder toString = new StringBuilder();
		
		return toString.toString();
	}
}
