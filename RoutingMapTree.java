public class RoutingMapTree
{
	
	private Exchange root;

	public  RoutingMapTree()
	{
		root = new Exchange(0);
		root.parent = null;

	}

	public  RoutingMapTree(Exchange e)
	{
		root = e ;
		root.setID(0);
		root.parent = null;

	}	

	public  Exchange root()
	{
		return root;
	}


	boolean task1 = false;
	boolean task2 = false;
	boolean task3 = false;
	boolean task4 = false;
	boolean task5 ;
	Exchange xyz;
	Exchange zzz;

	boolean task6 = false;
	boolean task7 = false;

	public int depth(Exchange e)
	{
		if(e.getID()==0)
			{return 0;}
		else
			return 1+depth(e.parent);
	}







	private void  containsNode(Exchange m, Exchange n)
	{
		
		if(n==null)
		{
			return;
		}

		

		if(m.getID()==n.getID())
			{	
				task5 = true;  
				return;
			} 

        int numChild = m.numChildren();
		for(int i=0;i<numChild;i++)
		{
			if(task5)
			{
				return;
			} 
			
			containsNode( m.child(i), n);
			if(task5)
			{
				return;
			}
			 
				
		}	
		

	}


	

	private void searchNode(Exchange m, Exchange n)
	{
		if(m.getID()==n.getID())
			{ 
				
				xyz = m;

				return;
			}
        
        int numChild = m.numChildren();
		for(int i=0;i<numChild;i++)
		{
		 	
			searchNode(m.child(i), n);
				
		}
		
	}

	
/////////////////////////////////////////////////////////////////////////////////////////////

	public void addExchange(int a, int b)
	{
		
		Exchange x = new Exchange(a);
		task5 = false;
		containsNode(root, x);
		if(!task5)
			{System.out.println("NO SUCH EXCHANGE PRESENT WITH THE GIVEN ID"); return;}
		
		searchNode(root, x);
		Exchange dumm = xyz;

		Exchange d = new Exchange(b);
		dumm.addChild(d);
		d.parent = dumm;
		
		task1 = true;
		return;
	
	}





	public void searchMobile(int n, Exchange e)
	{
		if(e.mobset.mset.IsMember(n))
		{
			
			task5 = true;
			zzz =  e;
			return;

		}

		else
		{
			int k = e.numChildren();
			for(int i=0; i<k;i++)
			{
				 searchMobile(n, e.child(i));
			}
		}	
	
	}


	public void switchON(int a, int b)
	{
		Exchange x = new Exchange(b);
		task5 = false;
		containsNode(root, x);
		if(!task5)
			return;
			
		 searchNode(root, x);
		 Exchange dummy = xyz;
		 if(dummy.numChildren()!=0)
		 {
		 	System.out.println("GIVEN EXCHANGE IS NOT A BASE STATION");
		 	task2 = true;
		 	return;
		 }

		 MobilePhone my = new MobilePhone(a);

		 task5 = false;           /////////////////   checking if the mobile phone is present anywhere else or not
		 searchMobile(a,root);
		 if(!task5){}
		 else 
		 {
		 	if(zzz.getID()!=dummy.getID())
		 	{	
		 		zzz.mobset.mset.Delete(my);
		 	}		
		 }	



		MobilePhone mp = new MobilePhone(a);
		if(dummy.mobset.isMember(mp))
		{
			Node temp = dummy.mobset.mset.head;
			while(temp!=null)
			{
				if(temp.mphone.number()==a)
				{
					temp.mphone.switchOn();
					task2 = true;
					return;
				}
				temp = temp.next;
			}
		}

		else
		{
			MobilePhone newphone = new MobilePhone(a);
			dummy.mobset.register(newphone);
			task2 = true;
			return;

		}

		return;

	}



	public void switchOFF(int a, Exchange m)
	{
		if(m.mobset.mset.IsMember(a))
		{
			Node temp = m.mobset.mset.head;
			while(temp!=null)
			{
				if(temp.mphone.number()==a)
				{
					temp.mphone.switchOff();
					task3=true;
					return;
				}
				temp = temp.next;
			}
		}

		else
		{
			int n = m.numChildren();   
			for(int i=0; i<n;i++)
			{
				switchOFF(a, m.child(i));
			}
		}
	} 


	public  void queryNthChild(int a, int b)
	{
		Exchange x = new Exchange(a);
		task5 = false;
		containsNode(root, x);
		if(!task5)
			{System.out.println("NO SUCH EXCHANGE EXISTS WITH THE GIVEN ID");return;}
		
		searchNode(root, x);
		Exchange dummy = xyz;
		Exchange baby = dummy.child(b);
		System.out.println(baby.getID());
		task4=true;
		return;

	}



	public void queryMobilePhoneSet(int a)
	{
		Exchange x = new Exchange(a);
		task5 = false;
		containsNode(root, x);
		if(!task5)
			{System.out.print("NO SUCH EXCHANGE EXISTS WITH THE GIVEN ID " + a );return;}
		task5 = true;
		
		searchNode(root, x);
		Exchange dummy = xyz;
		printmobs(dummy);
		return;


	}



	public void printmobs(Exchange e)
	{	
		

		Node temp = e.mobset.mset.head;
			while(temp!=null)
			{
				if(!temp.mphone.status())
				{
					temp = temp.next;
					continue;
				}
				
				System.out.print(temp.mphone.number() + ",  ");
				temp = temp.next;
			}

		int n = e.numChildren();

		for(int i=0;i<n;i++)
		{
			printmobs(e.child(i));
		} 
		return;
	}


 
////////////////////////////  ASSIGNMENT 3  /////////////////////////////////


	public Exchange findPhone(MobilePhone m)
	{
		task6 = false;
		task7 = false;
		findPhone(m, root);
		if(task6) return null;
		if(task7) return xyz;
		else
		{
			System.out.println("Error - No mobile phone with identifier " + m.number() + " found in the network");
			return null;
		}
		
	}




	private void findPhone(MobilePhone m, Exchange e)
	{
		if(e.mobset.mset.IsMember(m.number()))
		{
			Node temp = e.mobset.mset.head;
			while(temp!=null)
			{
				if(temp.mphone.number()==m.number())
				{
					if(!temp.mphone.status())
					{
						System.out.println("Error - Mobile phone with identifier " + temp.mphone.number() + " is currently switched off");
						task6 = true;
						return ;
					}
					else
					{
						task7 = true;
						xyz = e;
						return;
					}
					
				}
				temp = temp.next;
            }}


		else
			{
				int n = e.numChildren();
				for(int i=0; i<n;i++)
				{
					findPhone(m, e.child(i));
				}
			}

		return;	

	}





	public Exchange lowestRouter(Exchange a, Exchange b)
	{
		
		task5 = false;

		
		containsNode(root, a);
		if(!task5)
			{System.out.println("NO SUCH EXCHANGE EXISTS WITH ID "+ a.getID());return null;}

		task5 = false;
		containsNode(root, b);
		if(!task5)
			{System.out.println("NO SUCH EXCHANGE EXISTS WITH ID "+ b.getID());return null;}
		
		searchNode(root, a);
		Exchange a1 = xyz;
		
		searchNode(root, b);
		Exchange b1 = xyz;

		int d1 = depth(a1);
		int d2 = depth(b1);

		if(d1>d2)
		{
			for(int i=0;i<d1-d2;i++)
			{
				a1 = a1.parent;
			}
		}
		else if(d2>d1)
		{
			for(int i=0;i<d2-d1;i++)
			{
				b1 = b1.parent;
			}
		}

		if(a1.getID()==b1.getID())
		{
			return a1;
		}

		else
		{
			return lowestRouter(a1.parent, b1.parent);
		}
		
	}




	public ExchangeList routeCall(MobilePhone a, MobilePhone b)
	{
		Exchange A = findPhone(a);
		Exchange B = findPhone(b);
		if(A==null || B==null)
		{
			return null;
		}
		////////   First going upwards from A 

		ExchangeList head = new ExchangeList();
		ExchangeList tail = new ExchangeList();
		
		tail.data = A;
		tail.next = null;
		head = tail;

		Exchange e = A;
		Exchange l = lowestRouter(A,B);
		if(l.getID()==A.getID())
		{
			
			return head;
		}
		while(e.getID()!=l.getID())
		{
			e = e.parent;
			ExchangeList temp = new ExchangeList();
			temp.data = e;
			temp.next = null;
			tail.next = temp;
			tail = temp;
			
		}

		

		////////     Now going upwards from B

		ExchangeList headx = new ExchangeList();
		headx.data = B;
		headx.next = null;

		Exchange f = B.parent;

		while(f.getID()!=l.getID())
		{
			
			ExchangeList tempx = new ExchangeList();
			tempx.data = f;
			tempx.next = headx;
			headx = tempx;
			f = f.parent;
		}

		tail.next = headx;
		return head;
	}





public void movePhone(MobilePhone a, Exchange b)
{
		Exchange e = findPhone(a);
		if(e==null)
		{
			return;
		}
		task5 = false;
		containsNode(root, b);
		if(!task5)
		{
			System.out.println("NO SUCH EXCHANGE EXISTS WITH ID "+ b.getID());
			return;
		}	
		searchNode(root, b);
		Exchange dummy = xyz;	
		if(dummy.numChildren()!=0)
		{
			System.out.println("GIVEN EXCHANGE IS NOT A BASE STATION");
			return;
		}
		
		
		int num = a.number();

		Node temp = e.mobset.mset.head;
			while(temp!=null)
			{
				if(temp.mphone.number()==a.number())
				{
					if(!temp.mphone.status())
						{System.out.println("As the mob phone is swtched off, returning from the fn");
						return;}
					else
						{
							e.mobset.mset.Delete(a);
							break;
						}
				}
				temp = temp.next;

			}	

		
		dummy.mobset.register(a);
		
		return;	


	    
}


public void performAction(String actionMessage)
{  

	if(actionMessage.length() < 11)
	{
		System.out.println("FATAL ERROR : INVALID INPUT STRING");
		return;
	}



    switch(actionMessage.substring(0,10))

   	{  case "addExchang":
	                     
	                    String str="";
						  
						int n = 12;
						
						while((int)actionMessage.charAt(n)!=32 && n<actionMessage.length())
						{
						    
							str = str + actionMessage.charAt(n);
							n++;
						}

						n++;
						
						 if(n>=actionMessage.length() || (int)actionMessage.charAt(n)==32)
						 {
						 	System.out.println("FATAL ERROR : INVALID INPUT STRING");
						 	break;
						 }

						 int x;
						 int y;

						try
						{
							 x = Integer.parseInt(str);

						}
						catch(Exception e)
						{
							System.out.println(e.getMessage()  + " INVALID INPUT : ENTER AN INTEGER");
							break;
						}	

						str = actionMessage.substring(n,actionMessage.length());
						 
                        try
						{
							 y = Integer.parseInt(str);

						}
						catch(Exception e)
						{
							System.out.println(e.getMessage() + " INVALID INPUT : ENTER AN INTEGER");
							break;
						}	

		               
						addExchange(x, y);
		               
		                break;
						
		 case "switchOnMo":
		               
		                str = "";
						  
						n = 15;



						while( n<actionMessage.length() && ((int)actionMessage.charAt(n)!=32))
						  {
						    
							str +=actionMessage.charAt(n);
							n++;
						  }
						 n++;
						 
						 if(n>=actionMessage.length() || (int)actionMessage.charAt(n)==32)
						 {
						 	System.out.println("FATAL ERROR : INVALID INPUT STRING");
						 	break;
						 }


						 try
						{
							 x = Integer.parseInt(str);

						}
						catch(Exception e)
						{
							System.out.println(e.getMessage() + " INVALID INPUT : ENTER AN INTEGER");
							break;
						}	

						 str = actionMessage.substring(n,actionMessage.length());
						 
                        
		                 try
						{
							 y = Integer.parseInt(str);

						}
						catch(Exception e)
						{
							System.out.println(e.getMessage() + " INVALID INPUT : ENTER AN INTEGER");
							break;
						}	
						switchON(x,y);

						if(!task2){System.out.println("Exchange NOT FOUND WITH THE GIVEN ID");}

						break;


		 case "switchOffM":
                        
		 				if(actionMessage.length() < 17)
						{
							System.out.println("FATAL ERROR : INVALID INPUT STRING");
						 	break;
						}

                        try
						{
							x = Integer.parseInt(actionMessage.substring(16,actionMessage.length()));
						
						}
						catch(Exception e)
						{
							System.out.println(e.getMessage() + " INVALID INPUT : ENTER AN INTEGER");
							break;
						}	


						switchOFF(x,root);
						
						if(!task3)
						{System.out.println("NO SUCH MOBILE FOUND @@@@@@@@@@");}
						break;
						 
		 case "queryNthCh":
		                 
						System.out.print(actionMessage+": ");
		                str = "";
						  
						 n = 14;
						  while( n<actionMessage.length() && ((int)actionMessage.charAt(n)!=32))
						  {
						    
							str +=actionMessage.charAt(n);
							n++;
						  }
						  n++; 

						   if(n>=actionMessage.length() || (int)actionMessage.charAt(n)==32)
						 {
						 	System.out.println("FATAL ERROR : INVALID INPUT STRING");
						 	break;
						 }

						  
		                 try
						{
							 x = Integer.parseInt(str);

						}
						catch(Exception e)
						{
							System.out.println(e.getMessage() + " INVALID INPUT : ENTER AN INTEGER");
							break;
						}

						str =actionMessage.substring(n,actionMessage.length());
						  
						 
                        
		                  try
						{
							 y = Integer.parseInt(str);

						}
						catch(Exception e)
						{
							System.out.println(e.getMessage() + " INVALID INPUT : ENTER AN INTEGER");
							break;
						}


						queryNthChild( x, y);
					    
					    if(!task4)
					    	{System.out.println("NO SUCH EXCHANGE");}
						break;




         case "queryMobil":
		                System.out.print(actionMessage+": ");

		                if(actionMessage.length() < 21)
						{
							System.out.println("FATAL ERROR : INVALID INPUT STRING");
						 	break;
						}

		                str=actionMessage.substring(20,actionMessage.length());
		                
		                 try
						{
							 x = Integer.parseInt(str);

						}
						catch(Exception e)
						{
							System.out.println(e.getMessage() + " INVALID INPUT : ENTER AN INTEGER");
							break;
						}	

						queryMobilePhoneSet(x);
						
						System.out.println("");
						if(!task5)
						{}

						break;







		case "queryFindP":
						System.out.print(actionMessage+": ");

						if(actionMessage.length() < 16)
						{
							System.out.println("FATAL ERROR : INVALID INPUT STRING");
						 	break;
						}

						str=actionMessage.substring(15,actionMessage.length());

						 try
						{
							 x = Integer.parseInt(str);

						}
						catch(Exception e)
						{
							System.out.println(e.getMessage() + " INVALID INPUT : ENTER AN INTEGER");
							break;
						}	

						MobilePhone ak = new MobilePhone(x);
						Exchange call = findPhone(ak);

						if(call == null)
						{
						}
						else
						{
							System.out.println( call.getID());
						}	

						break;




		case "queryLowes":
						System.out.print(actionMessage+": ");

						str="";

						n =18;
						
						while( n<actionMessage.length() && ((int)actionMessage.charAt(n)!=32))
						{
						    
							str = str + actionMessage.charAt(n);
							n++;
						}

						n++;
						
						 if(n>=actionMessage.length() || (int)actionMessage.charAt(n)==32)
						 {
						 	System.out.println("FATAL ERROR : INVALID INPUT STRING");
						 	break;
						 }


						 try
						{
							 x = Integer.parseInt(str);

						}
						catch(Exception e)
						{
							System.out.println(e.getMessage() + " INVALID INPUT : ENTER AN INTEGER");
							break;
						}	
						str = actionMessage.substring(n,actionMessage.length());
						 
                         try
						{
							 y = Integer.parseInt(str);

						}
						catch(Exception e)
						{
							System.out.println(e.getMessage() + " INVALID INPUT : ENTER AN INTEGER");
							break;
						}	

                        Exchange p = new Exchange(x);
                        Exchange q = new Exchange(y);

                        call = lowestRouter(p,q);

                        if(call==null)
                        {
                        	//System.out.println("LOWESTROUTER FUNCTION RETURNED NULL");
                        }

                        else
                        {
                        	System.out.println(call.getID());
                        }	

                        break;


        case "queryFindC":

        				System.out.print(actionMessage+": ");

						str="";

						n =18;
						
						while( n<actionMessage.length() && ((int)actionMessage.charAt(n)!=32))
						{
						    
							str = str + actionMessage.charAt(n);
							n++;
						}

						n++;

						 if(n>=actionMessage.length() || (int)actionMessage.charAt(n)==32)
						 {
						 	System.out.println("FATAL ERROR : INVALID INPUT STRING");
						 	break;
						 }

						
						 try
						{
							 x = Integer.parseInt(str);

						}
						catch(Exception e)
						{
							System.out.println(e.getMessage() + " INVALID INPUT : ENTER AN INTEGER");
							break;
						}	

						str = actionMessage.substring(n,actionMessage.length());
						 
                         try
						{
							 y = Integer.parseInt(str);

						}
						catch(Exception e)
						{
							System.out.println(e.getMessage() + " INVALID INPUT : ENTER AN INTEGER");
							break;
						}	

                        MobilePhone m1 = new MobilePhone(x);
                        MobilePhone m2 = new MobilePhone(y);

                        ExchangeList callist = routeCall(m1,m2);

                        if(callist ==null)
                        {
                        	//System.out.println("Route call returned null #########");
                        }

                        else
                        {
                        	ExchangeList listx = callist;
                        	while(listx!=null)
                        	{
                        		if(listx.next==null)
                        		{
                        			System.out.print(listx.data.getID());
                        			break;
                        			
                        		}
                        		System.out.print(listx.data.getID() + ", ");
                        		listx = listx.next;
                        	}
                        	System.out.println();

                        }	

                        break;



        case "movePhone ":
                        
						str="";

						n =10;
						
						while( n<actionMessage.length() && ((int)actionMessage.charAt(n)!=32))
						{
						    
							str = str + actionMessage.charAt(n);
							n++;
						}

						n++;
						

						 if(n>=actionMessage.length() || (int)actionMessage.charAt(n)==32)
						 {
						 	System.out.println("FATAL ERROR : INVALID INPUT STRING");
						 	break;
						 }

						 try
						{
							 x = Integer.parseInt(str);

						}
						catch(Exception e)
						{
							System.out.println(e.getMessage() + " INVALID INPUT : ENTER AN INTEGER");
							break;
						}	
						str = actionMessage.substring(n,actionMessage.length());
						 
                         try
						{
							 y = Integer.parseInt(str);

						}
						catch(Exception e)
						{
							System.out.println(e.getMessage() + " INVALID INPUT : ENTER AN INTEGER");
							break;
						}	

                        MobilePhone r = new MobilePhone(x);
                        Exchange s = new Exchange(y);

                        movePhone(r,s);
                        break;

		  default:				
						System.out.println("FATAL ERROR : INVALID INPUT STRING");
						break;
    	}
	
	}
}
