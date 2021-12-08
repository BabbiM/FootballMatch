import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FootballMatch {
    String homeTeam;
    String awayTeam;
    int homeTeamScore=0;
    int awayTeamScore=0;
    int totalScore=0;
    ArrayList <String> matchData = new ArrayList<String>();

    FootballMatch(String homeTeam, int homeTeamScore, String awayTeam, int awayTeamScore, int totalScore){
        this.homeTeam=homeTeam;
        this.homeTeamScore=homeTeamScore;
        this.awayTeam=awayTeam;
        this.awayTeamScore=awayTeamScore;
        this.totalScore=homeTeamScore + awayTeamScore;

    }

    public FootballMatch() {

    }

    public String getHomeTeam()
    {
        return homeTeam;
    }
    public String getAwayTeam()
    {
        return awayTeam;
    }
    public int getHomeScore()
    {
        return homeTeamScore;
    }
    public int getAwayScore()
    {
        return awayTeamScore;
    }
    public int getTotalScore()
    {
        return homeTeamScore + awayTeamScore;
    }
    public void setHomeTeam(String homeTeam)
    {
        this.homeTeam=homeTeam;
    }
    public void setAwayTeam(String awayTeam)
    {
        this.awayTeam=awayTeam;
    }
    public void setHomeScore(int homeTeamScore)
    {
        this.homeTeamScore=homeTeamScore;
    }
    public void setAwayScore(int awayTeamScore)
    {
        this.awayTeamScore=awayTeamScore;
    }
    public ArrayList getMatchData ()
    {
        return matchData;
    }
    public void setMatchData(ArrayList matchData)
    {
        this.matchData=matchData;
    }

}
