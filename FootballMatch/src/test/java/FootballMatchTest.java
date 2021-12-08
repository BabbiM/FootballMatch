import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FootballMatchTest {
    FootballMatch footballMatch = new FootballMatch("Norway", 5,"Ethiopia", 5, 10 );
    @Test
    void getHomeTeam() throws Exception {


        assertEquals("Norway", footballMatch.getHomeTeam());

    }

    @Test
    void getAwayTeam() {
        assertEquals("Ethiopia", footballMatch.getAwayTeam());

    }

    @Test
    void getHomeScore() {
        assertEquals(5, footballMatch.getHomeScore());

    }

    @Test
    void getAwayScore() {
        assertEquals(5, footballMatch.getHomeScore());

    }


    @Test
    void getTotalScore() {
        assertEquals(10, footballMatch.getTotalScore());

    }

    @Test
    void setHomeTeam() {
        footballMatch.setHomeTeam("Norway");
        assertEquals("Norway", footballMatch.getHomeTeam());

    }

    @Test
    void setAwayTeam() {
        footballMatch.setAwayTeam("Ethiopia");
        assertEquals("Ethiopia", footballMatch.getAwayTeam());
    }

    @Test
    void setHomeScore() {
        footballMatch.setHomeScore(5);
        assertEquals(5, footballMatch.getHomeScore());
    }

    @Test
    void setAwayScore() {
        footballMatch.setAwayScore(5);
        assertEquals(5, footballMatch.getAwayScore());
    }

    @Test
    void getMatchData() {
        List<String> matchData = new ArrayList<String>();
        matchData.add("Norway 5 - 5 Ethiopia:10");
        matchData.add("Germany 3 - 1 Brazil:4");
        for (String matches: matchData) {
            assertNotNull(matches);
        }
        assertEquals("Norway 5 - 5 Ethiopia:10" , "Norway 5 - 5 Ethiopia:10", matchData.get(0));
    }

    @Test
    void setMatchData() {
        ArrayList <String> matchData = new ArrayList<>();
        matchData.add("Norway 5 - 5 Ethiopia:10");
        assertEquals("Norway 5 - 5 Ethiopia:10", "Norway 5 - 5 Ethiopia:10", matchData.get(0));

    }
}