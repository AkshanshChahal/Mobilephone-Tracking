public class Myset
{
	
	Node head = new Node();

	private int size;

	public Myset(){ head = null; size = 0; }
	
	public boolean IsEmpty() { return size==0;}
	
	public boolean IsMember(int n)
	{
		Node dummy = head;
		while(dummy!=null)
		{
			if(dummy.mphone.number()==n)
				return  true;
			dummy = dummy.next;
		}
		return false;
		
	}



	public void Insert(MobilePhone m)
	{
		Node newnode = new Node();
		MobilePhone mp = new MobilePhone(m.number());
		newnode.mphone = mp;
		newnode.mphone.switchOn();
		newnode.next = head;
		head = newnode; 
		size++;
		
	
	}




	public void Delete(MobilePhone m) 
	{
		if(size==0)
			System.out.println("This set is empty");
		Node dummy = head;
		if(dummy.mphone.number()==m.number())
		{
			dummy = dummy.next;
			head = dummy;
			return;
		}
		while(dummy.next!=null)
		{
			if(dummy.next.mphone.number()==m.number())
			{
				dummy.next = dummy.next.next;
				System.out.println("deleted phone with no. " +  m.number());
				return;
			}
			dummy = dummy.next;
		}
		System.out.println("no such item to delete");


	}




	public Myset Union(Myset a)
	{
		Myset union = new Myset();
		Node dummy = head;
		while(dummy!=null)
		{
			union.Insert(dummy.mphone);
			dummy = dummy.next;
		}		

		dummy = a.head;
		while(dummy!=null)
		{
			union.Insert(dummy.mphone);
			dummy = dummy.next;
		}
		return union;

	}



	public Myset Intersection(Myset a)
	{
		Myset inter = new Myset();
		Node dummy = head;
		while(dummy!=null)
		{
			if(a.IsMember(dummy.mphone.number()))
			{
				inter.Insert(dummy.mphone);	
			}
			dummy = dummy.next;
		}
		return inter;


	}















	
}
