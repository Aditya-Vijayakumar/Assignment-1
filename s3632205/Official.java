package s3632205;

import java.util.Random;

// Written by Aditya Vijayakumar(s3632205)
public class Official
{
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
