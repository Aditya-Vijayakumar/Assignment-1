package s3632205;
//Aditya Vijayakumar
public class Game
{

	public String gameId;
	public Official referee;
	public int score;
	public Athlete[] participants=new Athlete[8];
	public String userPrediction=null;
	public Athlete[] winners=null;

	public Game chooseAthletes(Game game,String choose){

		this.participants=AthleteDetails.chooseAthletes(choose);
		return game;
	}

}

