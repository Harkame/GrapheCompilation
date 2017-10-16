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
		    String ls = System.getProperty("line.separator");
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
