import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ScoreBoardTest {
    ScoreBoard scoreBoard = new ScoreBoard();

    @Test
    void clearScoreBoard() {

        ScoreBoard.currentBoardHomeScore = 10;
        ScoreBoard.currentBoardAwayScore = 2;
        assertEquals(0, ScoreBoard.boardHomeScore);
        assertEquals(0, ScoreBoard.boardAwayScore);
    }
    @Test
    void printGameSummary() {
        FootballMatch footballMatch = new FootballMatch();
        ArrayList<String> unorderedMatchData = footballMatch.matchData;
        for (String matches : unorderedMatchData) {
            assertNotNull(matches);
        }
        int unorderedMatchDataSize = 5;
        assertEquals(footballMatch.matchData.size(), 0);
        String firstMatch = "";
        String secondMatch = "";
        String stringFormOfFirstMatchTotal = "";
        String stringFormOSecondMatchTotal = "";
        ArrayList<String> orderedData = new ArrayList<>();
        boolean isSimilarTotalScore = false;
        int currentMax = 0;
        unorderedMatchData.add("Norway 2 - 2 Ethiopia:4");
        unorderedMatchData.add("Germany 5 - 2 Brazil:7");
        unorderedMatchData.add("Sweden 5 - 2 Denmark:7");

        for (int i = 0; i < unorderedMatchData.size(); i++) //looping from the last elements of the list as they are the recent matches added to the system.
        {
            for (int j = 0; j < unorderedMatchData.size(); j++) {
                firstMatch = unorderedMatchData.get(i); //returns a single match along with its result and total score from the list.
                int sidingIndexOfA = firstMatch.lastIndexOf(":"); //To retrieve a sub string which is after ":". Total Score
                stringFormOfFirstMatchTotal = firstMatch.substring(sidingIndexOfA + 1); //Total Score returned.
                int integerFormatOfTotalA = Integer.parseInt(stringFormOfFirstMatchTotal); //String form of total score converted to Integer for further comparison purpose.
                if (integerFormatOfTotalA > currentMax) {
                    currentMax = integerFormatOfTotalA;
                    orderedData.add(j, unorderedMatchData.get(i));
                    break;
                } else if (integerFormatOfTotalA < currentMax) {
                    orderedData.add(unorderedMatchData.get(i));
                    break;
                } else {
                    for (int k = orderedData.size(); isSimilarTotalScore == false; k--) {
                        secondMatch = orderedData.get(k - 1);
                        int neihbourIndexOfB = secondMatch.lastIndexOf(":");
                        stringFormOSecondMatchTotal = secondMatch.substring(neihbourIndexOfB + 1);//SubString where total exists
                        int integerFormatOfTotalB = Integer.parseInt(stringFormOSecondMatchTotal);
                        if (integerFormatOfTotalB == integerFormatOfTotalA) {
                            orderedData.add(k - 1, unorderedMatchData.get(i));
                            isSimilarTotalScore = true;
                            break;
                        } else {
                            isSimilarTotalScore = false;
                        }
                    }

                }
            }
        }
        for (String matches: orderedData) {
            assertEquals("Sweden 5 - 2 Denmark:7", "Sweden 5 - 2 Denmark:7", orderedData.get(0));
        }
    }
}
