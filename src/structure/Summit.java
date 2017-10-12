package structure;
import java.util.ArrayList;
import java.util.List;

public class Summit
	{
		private String name;
		private int color;
		
		private ArrayList<Summit> neighbors;
		
		public Summit(String name)
		{
			this.name = name;
			color = 0;
			
			neighbors = new ArrayList<Summit>();
		}

        public String getName() {
            return name;
        }

        public static Summit getColorableSummit(List<Summit> summits, int k)
		{
			for(Summit summit : summits)
				if(summit.color != 0)
					return summit;
			
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
			
			w:while(!colorAvailable)
				for(Summit neighbor : neighbors)
					if(neighbor.color == color)
					{
						color++;
						break w;
					}
					else
						colorAvailable = true;
			
			return color;
		}
		
		public int getDegree()
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
			StringBuilder toString = new StringBuilder();
			toString.append(name).append(" : ").append(color).append(" --> ");
			for (Summit n : neighbors){
				toString.append(System.getProperty("line.separator"));
			    toString.append("\t\t").append(n.color).append(" : ").append(n.name);
            }
			
			toString.append(System.getProperty("line.separator"));
			
			return toString.toString();
		}
	}