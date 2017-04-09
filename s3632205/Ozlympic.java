

import java.util.*;
import java.util.Scanner;
import java.util.Random;
 
 
 
 class Ozlympic {
	 
	 //Written by Subhashini Naresh
	//Student ID: s3632208


	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Driver3632208 driver2=new Driver3632208();
		Game game=null;
		Game[] games=new Game[100];
		int i=1;
		games[0]=null;
		int count=0;
		System.out.println("Welcome to Ozlympic Sports!");
		
		while(i==1)
		{
			System.out.println("\nPlease choose the options 1-6 from the menu below:\n1)Select a game to run\n2)Predict the winner of the game\n3)Start the game\n4)Display the final result of all games\n5)Display the points of all Athletes\n6)Exit\n");
			Scanner s=new Scanner(System.in);
			String option=s.next();
		
			if(option.equals("1"))
			{
				game=driver2.selectAGame();
				if(game==null)
				{
					System.out.println("if you wish to exit,Please press 6\n");
					String option1=s.next();
					if(option1.equals("6"))
						System.exit(0);
				}
				games[count]=game;
				count++;
			}
		
			else if(option.equals("2"))
			{
				if(game==null)
					System.out.println("Please select a game to run");
				else
				{
					game=driver2.prediction(game);
					if(game==null)
						System.out.println("if you wish to exit,Please press 6\n");
				}
			}
			else if(option.equals("3"))
			{
				if(game==null)
					System.out.println("Please select a game to run");
				else
				{
					game=driver2.startGame(game);
					if(game==null)
						System.out.println("if you wish to exit,Please press 6\n");
					System.out.println("The game "+game.gameId+" has succesfully been played by all participants!\nChoose 4 to view results");
				}
			}
			else if(option.equals("4"))
			{
				if(games[0]==null)
					System.out.println("Please select a game to run.There are no games to display");
				else
					driver2.displayResults(games,count);	
			}
			else if(option.equals("5"))
			{
				driver2.displayPoints();
			}
			else if(option.equals("6"))
			{
				System.exit(0);
			}
			else
			{
				System.out.println("please choose the right option or press \"6\" to exit");
			}
		}
	}
}

 
 
 class Driver3632208
{
	
	//Written by Subhashini Naresh
	//Student ID: s3632208


	public Game selectAGame()
	{
		Scanner s = new Scanner(System.in);
		String option=null;
	  
		do{
			   System.out.println("\nChoose 1-3 options for: \n 1)Swimming\n 2)Running\n 3)Cycling\n");
			    option=s.next();
			    if(option.equals("1") || option.equals("2") || option.equals("3"))
			    	break;
			    else  {
			    System.out.println("Please choose the right option or choose 5 to exit");
			    option=s.next();
			    }
			    if (option.equals("5"))
			    	return null;
	    }while(!(option.equals("1")) || !(option.equals("2"))|| !(option.equals("3")));
		
		
	    String choose="";
	    Game game=new Game();
	    game=game.createGameId(game, option);
		if(option.equals("1"))
			choose="SW";
		else if(option.equals("2"))
			choose="RU";
		else
			choose="CY";
		game=game.chooseAthletes(game,choose);
		game.referee= Official.chooseReferee();
		System.out.println("Participants of "+game.gameId+" are:");
		for(int i=0;i<game.participants.length;i++)
			System.out.println((i+1)+")"+game.participants[i].name+" "+game.participants[i].athleteId);	
		return game;
		}

	public Game prediction(Game game)
	{
		
		System.out.println("Participants of "+game.gameId+" are:");
		for(int i=0;i<game.participants.length;i++)
		{
			System.out.println((i+1)+")"+game.participants[i].name+" "+game.participants[i].athleteId);	
		}
		
		System.out.println("\nChoose the ID of the athlete you wish to predict as the winner\n");
		int j=1;
		while(j==1)
		{
			Scanner scan=new Scanner(System.in);
			game.userPrediction=scan.next();
			if(game.userPrediction.equals("*"))
			{
				game.userPrediction=null;
				return game;
			}
			int flag=0;
			game.userPrediction=game.userPrediction.toUpperCase();
			for(int i=0;i<game.participants.length;i++)
			{
				if(game.userPrediction.equals(game.participants[i].athleteId))
					flag=1;
			
			}
			if(flag==0)
				System.out.println("\nPlease enter a valid ID\n else press * to return to main menu");
			else if(flag==1)
				return game;
		}
		return game;
	}
	
	public Game startGame(Game game)
	{
		int minTime1=999,minTime2=999,minTime3=999;
		int firstWinner=0,secondWinner=0,thirdWinner=0;
		game.winners=new Athlete[3];
		for(int i=0;i<game.participants.length;i++)
		{
			game.participants[i].compete(game.gameId);
		}
		for(int i=0;i<game.participants.length;i++)
		{
			if(game.participants[i].time<minTime1)
			{
				minTime1=game.participants[i].time;
				firstWinner=i;	
			}
			
		}
		game.participants[firstWinner].points+=5;
		
		for(int i=0;i<game.participants.length;i++)
		{
			if(minTime1 <=game.participants[i].time && !(game.participants[i].athleteId.equals(game.participants[firstWinner].athleteId)) && game.participants[i].time<minTime2)
			{
				minTime2=game.participants[i].time;
				secondWinner=i;	
			}
			
		}
		game.participants[secondWinner].points+=2;
		for(int i=0;i<game.participants.length;i++)
		{
			if(game.participants[i].time>minTime2 && !(game.participants[i].athleteId.equals(game.participants[secondWinner].athleteId)) && game.participants[i].time<minTime3)
			{	minTime3=game.participants[i].time;
				thirdWinner=i;}
		}
		game.participants[thirdWinner].points+=1;
		game.winners[0]=game.participants[firstWinner];
		game.winners[1]=game.participants[secondWinner];
		game.winners[2]=game.participants[thirdWinner];
		int flag=0;
		if(game.userPrediction!=null)
		{
			for(int i=0;i<3;i++){
			if(game.userPrediction.equals(game.winners[i].athleteId))
			{
				flag=1;
				System.out.println("Congratulations!! Your prediction turned out to be right");
			}
		}
			if(flag!=1)
				System.out.println("Sorry.Hope you predict correctly next time.Enjoy!");
		}
		game.userPrediction=null;
		return game;
	}
	
	public void displayResults(Game[] games,int count)
	{
		String[] titles=new String[3];
		titles[0]="First";
		titles[1]="Second";
		titles[2]="Third";
		
		for(int i=0;i<count;i++){
			if(games[i].winners==null)
				System.out.println("\nThe game "+games[i].gameId+" was aborted due to various reasons!\n");
			else
			{
				System.out.println("\nResults of the Game "+games[i].gameId+" with Referee "+games[i].referee.name+","+games[i].referee.age+" ID: "+games[i].referee.officialId+" from "+games[i].referee.state);
				System.out.println("\nWinners:\n========================================================================\nTITLE\t\tATLHLETE\tID\tAGE\tPOINTS\tSTATE");
				for(int j=0;j<games[i].winners.length;j++)
        		System.out.println(titles[j]+"\t\t"+games[i].winners[j].name+"\t\t"+games[i].winners[j].athleteId+"\t"+games[i].winners[j].age+"\t"+games[i].winners[j].points+"\t"+games[i].winners[j].state+"\n");
				
			}
		} 
	}
	public void displayPoints()
	{
		System.out.println("\nPoints of all Athletes are displayed as below:\n");
		System.out.println("\nID      ATHLETE NAME\t\tPOINTS\n");
		if(AthleteDetails.listofAthletes==null)
			AthleteDetails.storeAthletes();
		for(int i=0;i<32;i++)
			System.out.println(AthleteDetails.listofAthletes[i].athleteId+"\t  "+AthleteDetails.listofAthletes[i].name+"   \t\t"+AthleteDetails.listofAthletes[i].points);
	}
	}
	

 class Official
{
	//Written by Aditya Vijayakumar
	//Student ID: s3632205


	public int officialId=0;
	public String name=null;
	public int age=0;
	public String state=null;
	
	public Official(int officialId,String name, int age, String state)
	{
		this.officialId=officialId;
		this.name=name;
		this.age=age;
		this.state=state;
	}
	public Official(){}

public static Official chooseReferee(){
	Official[] referee=new Official[3];
	referee[0]=new Official(120,"Johns",35,"Vic");
	referee[1]=new Official(220,"Stephane",29,"Wa");
	referee[2]=new Official(230,"Barbara",35,"Sa");
	
	Random r= new Random();
    int index= r.nextInt((2-0)+1)+0;
    return referee[index];
}
}






 class Game 
{
		
	//Written by Aditya Vijayakumar
	//Student ID: s3632205


	public String gameId;
	public Official referee;
	public Athlete[] participants=new Athlete[8];
	public String userPrediction=null;
	public Athlete[] winners=null;
	
	public Game chooseAthletes(Game game,String choose){
		
		this.participants=AthleteDetails.chooseAthletes(choose);
		return game;
	}
	public Game createGameId(Game game,String option)
	{
		Random r= new Random();
	    int randomId= r.nextInt((99-10)+1)+10;
	    if(option.equals("1")){
			game.gameId="S"+randomId;
			
		}
		else if(option.equals("2"))
		{
			game.gameId="R"+randomId;
			
		}
		else {
			game.gameId="C"+randomId;
			
		}
	    return game;
	}
	}




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



class Athlete 
{
	//Written by Aditya Vijayakumar
	//Student ID: s3632205


	public String athleteId;
	public String name;
	public int age;
	public String state;
	public int points=0;
	public int time=0;
	
	public Athlete()
	{
		
	}
	public Athlete(String athleteId,String name,int age,String state){
		this.age=age;
		this.state=state;
		this.name=name;
		this.athleteId=athleteId;
		this.points=0;
	}

	public void compete(String gameId)
	{
		gameId=gameId.toUpperCase();
		if(gameId.startsWith("S"))
		{
			//RANDOM function for swimming 100 - 200 seconds
			Random r= new Random();
	    this.time= r.nextInt((200-100)+1)+100;
		}
		else if(gameId.startsWith("C"))
		{
		//RANDOM function for cycling 500 - 800 seconds
		Random r= new Random();
	    this.time= r.nextInt((800-500)+1)+500;
		
		}
		else if(gameId.startsWith("R"))
		
		{
			//RANDOM function for running 10 - 20 seconds
			Random r= new Random();
	    this.time= r.nextInt((20-10)+1)+10;
		}

}
}



