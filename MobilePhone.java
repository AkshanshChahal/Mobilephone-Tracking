public  class MobilePhone 
{
	

    //   Instance variables of Mobile Phone  //
	private int id;
	private boolean status;
	private Exchange location;


    // access methods
	public MobilePhone(int number)
	{
		id = number;
		///////    *******   status = true;   /////// 
	}
	public int number(){ return id; }
	public boolean status(){ return status; }
	public void switchOn(){ status = true; }
	public void switchOff(){ status = false; }
	public void setLocation(Exchange e){ location = e; }
	public Exchange location() 
	{
		
		if(!status)
			System.out.println("Phone is switched off");
		return location;



	}



	
}
