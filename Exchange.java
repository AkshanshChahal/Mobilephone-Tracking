public class Exchange  
{


	// Instance variables of Exchange class //
	private int id;

	private int numChildren; 

	public  Exchange parent;

	public  ExchangeList leftMostChild = new ExchangeList();  // it will be the head of linked list of the children of any node in the tree

	//public  Exchange next;

    public  boolean  isroot = false;

    MobilePhoneSet mobset = new MobilePhoneSet();



    //constructors
    public Exchange(int number)
	{
		id = number;
		numChildren = 0;
	}

	public Exchange()
	{ 
		
		numChildren = 0;
	}

	public void setID(int i)
	{
		id = i;
	}

	public int getID(){ return id; }





	// access methods


	
				
	public Exchange parent(){ return parent; }

	public Boolean  isRoot(){ return isroot; }
	
	public int numChildren()
	{
		
		return  numChildren;
		 
	}

	public Exchange child(int i) 
	{
		if(i>numChildren) System.out.println("No. of Children are less than the asked #child ");
		ExchangeList dummy = new ExchangeList();
		dummy = leftMostChild;
		for(int j=0; j<i; j++)
		{
			dummy = dummy.next;
		}
		return dummy.data;
	}


	public void addChild(Exchange e)
	{

		if(numChildren==0)
		{
			numChildren++;
			Exchange ex = new Exchange(e.getID());
			ex.parent = this;
			leftMostChild.data = ex;
			leftMostChild.next = null;
			return;
		}

		ExchangeList dummy = new ExchangeList();
		dummy.data = e;
		dummy.data.parent = this;
		ExchangeList temp = leftMostChild;
		while(temp.next!=null)
		{
			temp = temp.next;
		}
		temp.next = dummy;
		dummy.next = null;

		numChildren++;
	}





	public MobilePhoneSet residentSet(){ return mobset; }

	public RoutingMapTree subtree(int i)
	{
		Exchange dummy = new Exchange();
		dummy = child(i);
		
		RoutingMapTree subtree = new RoutingMapTree(dummy);
		return subtree;


	}









	

	
}
