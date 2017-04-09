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

