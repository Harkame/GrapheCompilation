import java.util.ArrayList;

class Summit
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
		
		public static Summit getColorableSummit(ArrayList<Summit> summits, int k)
		{
			return null;
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
			
			return toString.toString();
		}
	}