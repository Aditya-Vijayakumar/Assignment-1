 class AthleteDetails extends Athlete {
	
	//Written by Aditya Vijayakumar
	//Student ID: s3632205


	public AthleteDetails(String athleteId, String name, int age, String state) {
		super(athleteId, name, age, state);
		// TODO Auto-generated constructor stub
	}
	public AthleteDetails()
	{
		super();
	}

	public static Athlete[] listofAthletes=null;
	
	public static void storeAthletes()
	{
		listofAthletes=new Athlete[32];
		listofAthletes[0]=new Athlete("SW120","John andy",26,"Vic");
		listofAthletes[1]=new Athlete("SW145","Vivien",28,"Qld");
		listofAthletes[2]=new Athlete("SW123","Williams",23,"Vic");
		listofAthletes[3]=new Athlete("SW134","Simon",27,"Vic");
		listofAthletes[4]=new Athlete("SW165","Woods",19,"Tsm");
		listofAthletes[5]=new Athlete("SW172","Michael",30,"Nsw");
		listofAthletes[6]=new Athlete("SW132","Jazz",32,"Qld");
		listofAthletes[7]=new Athlete("SW112","Caroline",26,"Vic");
		
		listofAthletes[8]=new Athlete("RU111","Michelle",20,"Wa");
		listofAthletes[9]=new Athlete("RU110","Jimmy",24,"Vic");
		listofAthletes[10]=new Athlete("RU190","Zac",26,"Qld");
		listofAthletes[11]=new Athlete("RU180","Johny",24,"NSW");
		listofAthletes[12]=new Athlete("RU170","Joana",25,"Tsm");
		listofAthletes[13]=new Athlete("RU160","Rose",26,"Vic");
		listofAthletes[14]=new Athlete("RU150","Carmen",29,"Wa");
		listofAthletes[15]=new Athlete("RU140","Andrea",30,"Sa");
		
		
		
		listofAthletes[16]=new Athlete("CY131","Julia",22,"Sa");
		listofAthletes[17]=new Athlete("CY132","Jerry",23,"Wa");
		listofAthletes[18]=new Athlete("CY133","Steve",24,"Qld");
		listofAthletes[19]=new Athlete("CY134","Bella",26,"Tsm");
		listofAthletes[20]=new Athlete("CY135","Lily",28,"Sa");
		listofAthletes[21]=new Athlete("CY136","Jacob",29,"Vic");
		listofAthletes[22]=new Athlete("CY137","Andrew",21,"Nsw");
		listofAthletes[23]=new Athlete("CY138","Huan",21,"Vic");
		
		listofAthletes[24]=new Athlete("SA148","Barbera",19,"Vic");
		listofAthletes[25]=new Athlete("SA149","Joseph",20,"Nsw");
		listofAthletes[26]=new Athlete("SA200","Hanna",24,"Wa");
		listofAthletes[27]=new Athlete("SA210","David",23,"Wa");
		listofAthletes[28]=new Athlete("SA220","Richard",22,"Sa");
		listofAthletes[29]=new Athlete("SA230","Stephie",22,"Sa");
		listofAthletes[30]=new Athlete("SA240","Serena",21,"Sa");
		listofAthletes[31]=new Athlete("SA250","Federer",20,"Tsm");
		
	}
	
	public static Athlete[] chooseAthletes(String choose)
	{
		Athlete[] chosenAthletes=new Athlete[16];
		
		int index=0;
		if(listofAthletes==null){
		storeAthletes();
		
		}
		if(choose.startsWith("SW"))
		{	for(int i=0;i<listofAthletes.length;i++)
			{
				if(listofAthletes[i].athleteId.startsWith("SW") || listofAthletes[i].athleteId.startsWith("SA"))
						{
					chosenAthletes[index]=listofAthletes[i];
					index++;
						}
			}
		}
		if(choose.startsWith("RU"))
		{	for(int i=0;i<listofAthletes.length;i++)
			{
				if(listofAthletes[i].athleteId.startsWith("RU") || listofAthletes[i].athleteId.startsWith("SA"))
						{
					chosenAthletes[index]=listofAthletes[i];
					index++;
						}
			}
		}
		if(choose.startsWith("CY"))
		{	for(int i=0;i<listofAthletes.length;i++)
			{
				if(listofAthletes[i].athleteId.startsWith("CY") || listofAthletes[i].athleteId.startsWith("SA"))
						{
					chosenAthletes[index]=listofAthletes[i];
					index++;
						}
			}
		}
		index=0;
		int[] randArr=new int[8];
		int flag=0;
		Random r = new Random();
		int randomNumber=r.nextInt((8-4)+1)+4;
		Athlete[] finalAthletes1=new Athlete[randomNumber];
		while(index<randomNumber)
		{
			flag=0;
	     int randomId= r.nextInt((15-0)+1);
	     for(int j=0;j<index+1;j++)
	     {
	     if(randArr[j]==randomId)
	     {
	    	 flag=1;
	     }
	     }
	     if(flag==0)
	     {
	    	randArr[index]=randomId;
	        index++;
	     }
	     }
		for(int i=0;i<randomNumber;i++)
		{
	     finalAthletes1[i]=chosenAthletes[randArr[i]];
		}
	return finalAthletes1;
	}
	
}
