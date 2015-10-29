public  class MobilePhoneSet 
{

	
	Myset mset = new Myset();

	public MobilePhoneSet()
	{	
		Myset mset = new Myset();
	}

	public  boolean  isEmpty()
	{
		return mset.IsEmpty();
	}

	public boolean isMember(MobilePhone m)
	{	
		return mset.IsMember(m.number());
	}

	public  void register(MobilePhone m)
	{
		
		mset.Insert(m);
	}

	public  void deregister(MobilePhone m)
	{
		
		mset.Delete(m);
	}

	





	
}
