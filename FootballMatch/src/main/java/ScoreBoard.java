import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JFrame;
import javax.swing.WindowConstants;
import javax.swing.GroupLayout;
import javax.swing.SwingConstants;
import javax.swing.LayoutStyle;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ScoreBoard extends JFrame implements ActionListener{

    private static JButton btnAwayScoreUpdate;
    private static JButton btnHomeScoreUpdate;
    private static JButton btnStartGame;
    private static JButton btnSummaryGames;
    String[] countryCodes;
   String homeTeam, awayTeam;
    static int boardHomeScore=0;
    static int numberOfGamesAdded=0;
    static int boardAwayScore=0;
    boolean homeTeamSelected=false;
    boolean awayTeamSelected=true;
    static List <String> unorderedMatchData=  new ArrayList <>();
    static List <Integer> totalScoreList = new ArrayList <>();
    static ArrayList <String> orderedMatchData = new ArrayList<>();

    static List <Integer> testScoreList=  new ArrayList <>();
    static int currentBoardAwayScore=0, currentBoardHomeScore=0;
    static FootballMatch match = new FootballMatch();
    private static JComboBox cmbAwayTeam;
    private static JComboBox cmbHomeTeam;
    private static JLabel lblAway;
    private static JLabel lblAwayScoreUpdate;
    private static JLabel lblAwayTeamSelection;
    private static JLabel lblHome;
    private static JLabel lblHomeScoreUpdate;
    private static JLabel lblHomeTeamSelection;
    private static JLabel lblStatus;
    private JLabel lblTitleGameControl;
    private JLabel lblTitleMain;
    private static JLabel lblTitleTeamSelection;
    private JPanel pnlAwayScoreUpdate;
    private JPanel pnlGameControl;
    private JPanel pnlHomeScoreUpdate;
    private JPanel pnlMain;
    private static JPanel pnlScoreBoard;
    private static JPanel pnlTeamSelection;
    private JPanel pnlTitle;
    private static JTextField txtGameScore;
    private JPanel pnlGameSummary;
    private static JTextArea txtAreaSummary;

    public ScoreBoard() {
        drawGUI(); //A slight modification of the generated initComponents method of NetBeans
        this.setLocationRelativeTo(null);
    }

    public static void activateScoreBoard () // A method that activates the score board to receive scores of home and away team.
    {
        txtGameScore.setEnabled(true);
        lblStatus.setText("Please enter the score");
        lblHomeScoreUpdate.setForeground(Color.black);
        lblAwayScoreUpdate.setForeground(Color.black);
        btnHomeScoreUpdate.setForeground(new Color(0,102,0));
        btnAwayScoreUpdate.setForeground(new Color(0,102,0));
        pnlTeamSelection.setEnabled(false);
        lblHomeTeamSelection.setForeground(new Color(153, 153, 153));
        lblAwayTeamSelection.setForeground(new Color(153, 153, 153));
        lblTitleTeamSelection.setForeground(new Color(153, 153, 153));
        cmbHomeTeam.setEnabled(false);
        cmbAwayTeam.setEnabled(false);
    }
    public static void clearScoreBoard()  // A method that clears the scoreboard after the completion of a game
    {
        lblHomeScoreUpdate.setForeground(new Color(153, 153, 153));
        btnHomeScoreUpdate.setForeground(new Color(153, 153, 153));
        lblHome.setForeground(new Color(153,153,153));
        lblAway.setForeground(new Color(153,153,153));
        btnAwayScoreUpdate.setForeground(new Color(153, 153, 153));
        lblAwayScoreUpdate.setForeground(new Color(153, 153, 153));
        txtGameScore.setEnabled(false);
        lblTitleTeamSelection.setForeground(Color.black);
        lblHomeTeamSelection.setForeground(Color.black);
        lblAwayTeamSelection.setForeground(Color.black);
        cmbHomeTeam.setEnabled(true);
        cmbAwayTeam.setEnabled(true);
        lblHome.setText("Home");
        lblAway.setText("Away");
        txtGameScore.setText("0:0");
        btnSummaryGames.setEnabled(true);
        btnStartGame.setEnabled(false);
        btnHomeScoreUpdate.setEnabled(false);
        btnAwayScoreUpdate.setEnabled(false);
        boardHomeScore=0;
        boardAwayScore=0;

    }
    public static void activateStartGame() //A method that activates a start of a game on the score board UI.
    {
        btnStartGame.setEnabled(true);
        lblStatus.setText("Click 'Start' button to start the game");
        pnlScoreBoard.setEnabled(true);
        lblHome.setForeground(Color.black);
        lblAway.setForeground(Color.black);
        lblAway.setText(String.valueOf((cmbAwayTeam.getSelectedItem())));
        lblHome.setText(String.valueOf((cmbHomeTeam.getSelectedItem())));
    }
    public static void manageGameRules()
    {
        btnStartGame.setEnabled(false);
        JOptionPane.showMessageDialog(null, "Please Select two different teams", "Roseindia.net", 1);
        lblHome.setForeground(new Color(153, 153, 153));
        lblAway.setForeground(new Color(153, 153, 153));
        lblHome.setText("Home");
        lblAway.setText("Away");
    }

    public static void printGameSummary() //A method that manages ordering of matches by their total score and recent addition to the system.
    {

        unorderedMatchData=match.matchData;
        String singleMatchResultOfA="";
        String singleMatchResultOfB="";
        String stringFormOfTotalA="";
        String stringFormOfTotalB="";
        boolean foundSimilarTotalScore=false;
        int currentMaximum=0;
        for(int i=0; i< unorderedMatchData.size();i++) //looping from the last elements of the list as they are the recent matches added to the system.
        {
            for(int j=0; j<unorderedMatchData.size(); j++)
            {
                singleMatchResultOfA=unorderedMatchData.get(i); //returns a single match along with its result and total score from the list.
                int neihbourIndexOfA=singleMatchResultOfA.lastIndexOf(":"); //To retrieve a sub string which is after ":". Total Score
                stringFormOfTotalA=singleMatchResultOfA.substring(neihbourIndexOfA + 1); //Total Score returned.
                int integerFormatOfTotalA=Integer.parseInt(stringFormOfTotalA); //String form of total score converted to Integer for further comparison purpose.
                if(integerFormatOfTotalA > currentMaximum)
                {
                    currentMaximum=integerFormatOfTotalA;
                    orderedMatchData.add(j, unorderedMatchData.get(i));
                    break;
                }
                else if (integerFormatOfTotalA < currentMaximum )
                {
                    orderedMatchData.add(unorderedMatchData.get(i));
                    break;
                }
                else
                {
                    for(int k=orderedMatchData.size();foundSimilarTotalScore==false;k--)
                    {
                        singleMatchResultOfB=orderedMatchData.get(k-1);
                        int neihbourIndexOfB=singleMatchResultOfB.lastIndexOf(":");
                        stringFormOfTotalB=singleMatchResultOfB.substring(neihbourIndexOfB + 1);//SubString where total exists
                        int integerFormatOfTotalB=Integer.parseInt(stringFormOfTotalB);
                        if(integerFormatOfTotalB==integerFormatOfTotalA)
                        {
                            orderedMatchData.add(k-1, unorderedMatchData.get(i));
                            foundSimilarTotalScore=true;
                            break;
                        }
                        else
                        {
                            foundSimilarTotalScore=false;
                        }
                    }

                }
            }
        }

        txtAreaSummary.setText("MATCH SUMMARY"+ "\n");

        for(String match: orderedMatchData)
        {

            txtAreaSummary.append( match + "\n");
        }


    }

    private void drawGUI()  //this is to draw a GUI of the prototype. to show how the system works.
    {

        pnlMain = new JPanel();
        pnlGameControl = new JPanel();
        lblTitleGameControl = new JLabel();
        btnStartGame = new JButton();
        btnSummaryGames = new JButton();
        pnlTitle = new JPanel();
        lblTitleMain = new JLabel();
        pnlScoreBoard = new JPanel();
        lblHome = new JLabel();
        txtGameScore = new JTextField();
        lblAway = new JLabel();
        pnlTeamSelection = new JPanel();
        lblTitleTeamSelection = new JLabel();
        lblHomeTeamSelection = new JLabel();
        cmbHomeTeam = new JComboBox();
        lblAwayTeamSelection = new JLabel();
        countryCodes = Locale.getISOCountries();
        List<String> countriesNamesList = new ArrayList<>();
        for (String countryCode : countryCodes)  //retrieves list of countries
        {
            Locale locale = new Locale("", countryCode);
            countriesNamesList.add(locale.getDisplayCountry());
        }
        cmbAwayTeam = new JComboBox();
        pnlHomeScoreUpdate = new JPanel();
        btnHomeScoreUpdate = new JButton();
        lblHomeScoreUpdate = new JLabel();
        pnlAwayScoreUpdate = new JPanel();
        btnAwayScoreUpdate = new JButton();
        lblAwayScoreUpdate = new JLabel();
        lblStatus = new JLabel();
        pnlGameSummary = new JPanel();
        txtAreaSummary = new JTextArea("");


        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("World Cup Scoreboard");
        setAutoRequestFocus(false);
        setBackground(new Color(102, 102, 102));
        setForeground(new Color(153, 153, 153));

        pnlMain.setBackground(new Color(153, 153, 153));
        pnlMain.setName("pnlMain");

        pnlGameControl.setBackground(new Color(204, 204, 255));
        pnlGameControl.setName("pnlGameControl");

        lblTitleGameControl.setFont(new Font("Tahoma", 1, 12));
        lblTitleGameControl.setText("Game");

        btnStartGame.setBackground(new Color(0, 153, 153));
        btnStartGame.setText("Start");
        btnStartGame.setEnabled(false);
        btnStartGame.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                btnStartGameActionPerformed(evt);
            }
        });
        btnSummaryGames.setBackground(new Color(0, 153, 153));
        btnSummaryGames.setText("Summary");
        btnSummaryGames.setEnabled(false);
        btnSummaryGames.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                btnSummaryGamesActionPerformed(evt);
            }
        });
        GroupLayout pnlGameControlLayout = new GroupLayout(pnlGameControl);
        pnlGameControl.setLayout(pnlGameControlLayout);
        pnlGameControlLayout.setHorizontalGroup(
                pnlGameControlLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(pnlGameControlLayout.createSequentialGroup()
                                .addGap(30, 30, 30)
                                .addComponent(lblTitleGameControl)
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(pnlGameControlLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(pnlGameControlLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(btnStartGame, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(btnSummaryGames, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addContainerGap())
        );
        pnlGameControlLayout.setVerticalGroup(
                pnlGameControlLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(pnlGameControlLayout.createSequentialGroup()
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lblTitleGameControl)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnStartGame)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnSummaryGames)
                                .addGap(59, 59, 59))
        );

        pnlTitle.setBackground(new Color(204, 204, 255));
        pnlTitle.setName("pnlTitle");

        lblTitleMain.setFont(new Font("Tahoma", 1, 14));
        lblTitleMain.setText(" Football World Cup Scoreboard");

        GroupLayout pnlTitleLayout = new GroupLayout(pnlTitle);
        pnlTitle.setLayout(pnlTitleLayout);
        pnlTitleLayout.setHorizontalGroup(
                pnlTitleLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(pnlTitleLayout.createSequentialGroup()
                                .addGap(83, 83, 83)
                                .addComponent(lblTitleMain, GroupLayout.PREFERRED_SIZE, 230, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(87, Short.MAX_VALUE))
        );
        pnlTitleLayout.setVerticalGroup(
                pnlTitleLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(GroupLayout.Alignment.TRAILING, pnlTitleLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(lblTitleMain, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addContainerGap())
        );

        pnlScoreBoard.setBackground(new Color(204, 204, 255));
        pnlScoreBoard.setEnabled(false);
        pnlScoreBoard.setName("pnlScoreBoard");

        lblHome.setFont(new Font("Segoe UI Emoji", 1, 14));
        lblHome.setHorizontalAlignment(SwingConstants.RIGHT);
        lblHome.setText("Home");
        lblHome.setName("lblHome");
        lblHome.setForeground(new Color(153,153,153));

        txtGameScore.setFont(new Font("Tahoma", 1, 14));
        txtGameScore.setHorizontalAlignment(JTextField.CENTER);
        txtGameScore.setText("0:0");
        txtGameScore.setEnabled(false);

        lblAway.setFont(new Font("Segoe UI Emoji", 1, 14));
        lblAway.setHorizontalAlignment(SwingConstants.LEFT);
        lblAway.setText("Away");
        lblAway.setName("lblAway");
        lblAway.setForeground(new Color(153,153,153));

        GroupLayout pnlScoreBoardLayout = new GroupLayout(pnlScoreBoard);
        pnlScoreBoard.setLayout(pnlScoreBoardLayout);
        pnlScoreBoardLayout.setHorizontalGroup(
                pnlScoreBoardLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(pnlScoreBoardLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(lblHome, GroupLayout.PREFERRED_SIZE, 96, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtGameScore, GroupLayout.PREFERRED_SIZE, 52, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblAway, GroupLayout.PREFERRED_SIZE, 95, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlScoreBoardLayout.setVerticalGroup(
                pnlScoreBoardLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(pnlScoreBoardLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(pnlScoreBoardLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(lblHome, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtGameScore, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lblAway, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlTeamSelection.setBackground(new Color(204, 204, 255));
        pnlTeamSelection.setName("pnlTeamSelection");

        lblTitleTeamSelection.setFont(new Font("Tahoma", 1, 14));
        lblTitleTeamSelection.setText("Select Teams");

        lblHomeTeamSelection.setFont(new Font("Tahoma", 1, 12));
        lblHomeTeamSelection.setText("Home");

        cmbHomeTeam.setFont(new Font("Tahoma", 1, 12));
        cmbHomeTeam.setModel(new DefaultComboBoxModel<String>(countriesNamesList.toArray(new String[0]))); //This is to populate world countries using Java's Locale
        cmbHomeTeam.setName("cmbHomeTeam");
        cmbHomeTeam.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                cmbHomeTeamActionPerformed(evt);
            }
        });

        lblAwayTeamSelection.setFont(new Font("Tahoma", 1, 12));
        lblAwayTeamSelection.setText("Away");

        cmbAwayTeam.setFont(new Font("Tahoma", 1, 12));
        cmbAwayTeam.setModel(new DefaultComboBoxModel<String>(countriesNamesList.toArray(new String[0])));
        cmbHomeTeam.setName("cmbAwayTeam");
        cmbAwayTeam.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                cmbAwayTeamActionPerformed(evt);
            }
        });

        GroupLayout pnlTeamSelectionLayout = new GroupLayout(pnlTeamSelection);
        pnlTeamSelection.setLayout(pnlTeamSelectionLayout);
        pnlTeamSelectionLayout.setHorizontalGroup(
                pnlTeamSelectionLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(pnlTeamSelectionLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(pnlTeamSelectionLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(lblAwayTeamSelection)
                                        .addGroup(pnlTeamSelectionLayout.createSequentialGroup()
                                                .addComponent(lblHomeTeamSelection)
                                                .addGap(45, 45, 45)
                                                .addGroup(pnlTeamSelectionLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                                        .addComponent(cmbAwayTeam, 0, 1, Short.MAX_VALUE)
                                                        .addComponent(cmbHomeTeam, GroupLayout.PREFERRED_SIZE, 156, GroupLayout.PREFERRED_SIZE))))
                                .addContainerGap(39, Short.MAX_VALUE))
                        .addGroup(GroupLayout.Alignment.TRAILING, pnlTeamSelectionLayout.createSequentialGroup()
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lblTitleTeamSelection)
                                .addGap(97, 97, 97))
        );
        pnlTeamSelectionLayout.setVerticalGroup(
                pnlTeamSelectionLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(pnlTeamSelectionLayout.createSequentialGroup()
                                .addComponent(lblTitleTeamSelection, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
                                .addGap(13, 13, 13)
                                .addGroup(pnlTeamSelectionLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(lblHomeTeamSelection)
                                        .addComponent(cmbHomeTeam, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(pnlTeamSelectionLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(lblAwayTeamSelection)
                                        .addComponent(cmbAwayTeam, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlHomeScoreUpdate.setBackground(new Color(204, 204, 255));
        pnlHomeScoreUpdate.setName("pnlHomeScoreUpdate");

        btnHomeScoreUpdate.setFont(new Font("Tahoma", 1, 18));
        btnHomeScoreUpdate.setBackground(new Color(0, 153, 153));
        btnHomeScoreUpdate.setForeground(new Color(153, 153, 153));
        btnHomeScoreUpdate.setText("+1");
        btnHomeScoreUpdate.setEnabled(false);
        btnHomeScoreUpdate.setName("btnHomeScoreUpdate");
        btnHomeScoreUpdate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                btnHomeScoreUpdateActionPerformed(evt);
            }
        });

        lblHomeScoreUpdate.setFont(new Font("Tahoma", 1, 12));
        lblHomeScoreUpdate.setText("Home Score");
        lblHomeScoreUpdate.setForeground(new Color(153, 153, 153));

        GroupLayout pnlHomeScoreUpdateLayout = new GroupLayout(pnlHomeScoreUpdate);
        pnlHomeScoreUpdate.setLayout(pnlHomeScoreUpdateLayout);
        pnlHomeScoreUpdateLayout.setHorizontalGroup(
                pnlHomeScoreUpdateLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(pnlHomeScoreUpdateLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(btnHomeScoreUpdate, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addContainerGap())
                        .addGroup(pnlHomeScoreUpdateLayout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addComponent(lblHomeScoreUpdate)
                                .addContainerGap(22, Short.MAX_VALUE))
        );
        pnlHomeScoreUpdateLayout.setVerticalGroup(
                pnlHomeScoreUpdateLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(pnlHomeScoreUpdateLayout.createSequentialGroup()
                                .addComponent(lblHomeScoreUpdate)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnHomeScoreUpdate)
                                .addContainerGap())
        );

        pnlAwayScoreUpdate.setBackground(new Color(204, 204, 255));
        pnlAwayScoreUpdate.setName("pnlAwayScoreUpdate");


        btnAwayScoreUpdate.setFont(new java.awt.Font("Tahoma", 1, 18));
        btnAwayScoreUpdate.setForeground(new Color(153, 153, 153));
        btnAwayScoreUpdate.setBackground(new Color(0, 153, 153));
        btnAwayScoreUpdate.setText("+1");
        btnAwayScoreUpdate.setEnabled(false);
        btnAwayScoreUpdate.setName("btnAwayScoreUpdate");
        btnAwayScoreUpdate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                btnAwayScoreUpdateActionPerformed(evt);
            }
        });

        lblAwayScoreUpdate.setFont(new Font("Tahoma", 1, 12));
        lblAwayScoreUpdate.setText("Away Score");
        lblAwayScoreUpdate.setName("lblAwayScoreUpdate");
        lblAwayScoreUpdate.setForeground(new Color(153, 153, 153));

        GroupLayout pnlAwayScoreUpdateLayout = new GroupLayout(pnlAwayScoreUpdate);
        pnlAwayScoreUpdate.setLayout(pnlAwayScoreUpdateLayout);
        pnlAwayScoreUpdateLayout.setHorizontalGroup(
                pnlAwayScoreUpdateLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(pnlAwayScoreUpdateLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(pnlAwayScoreUpdateLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(btnAwayScoreUpdate, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(pnlAwayScoreUpdateLayout.createSequentialGroup()
                                                .addGap(9, 9, 9)
                                                .addComponent(lblAwayScoreUpdate)
                                                .addGap(0, 11, Short.MAX_VALUE)))
                                .addContainerGap())
        );
        pnlAwayScoreUpdateLayout.setVerticalGroup(
                pnlAwayScoreUpdateLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(GroupLayout.Alignment.TRAILING, pnlAwayScoreUpdateLayout.createSequentialGroup()
                                .addComponent(lblAwayScoreUpdate)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnAwayScoreUpdate, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addContainerGap())
        );

        lblStatus.setForeground(new Color(153, 0, 0));
        lblStatus.setText("First, you need to selcet teams that are playing.");

        pnlGameSummary.setBackground(new Color(204, 204, 255));
        pnlGameSummary.setName("pnlGameSummary");


        txtAreaSummary.setName("txtAreaSummary");

        GroupLayout pnlGameSummaryLayout = new GroupLayout(pnlGameSummary);
        pnlGameSummary.setLayout(pnlGameSummaryLayout);
        pnlGameSummaryLayout.setHorizontalGroup(
                pnlGameSummaryLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(pnlGameSummaryLayout.createSequentialGroup()
                                .addGap(45, 45, 45)
                                .addComponent(txtAreaSummary)
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlGameSummaryLayout.setVerticalGroup(
                pnlGameSummaryLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(pnlGameSummaryLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(txtAreaSummary)
                                .addContainerGap(126, Short.MAX_VALUE))
        );

        GroupLayout pnlMainLayout = new GroupLayout(pnlMain);
        pnlMain.setLayout(pnlMainLayout);
        pnlMainLayout.setHorizontalGroup(
                pnlMainLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(pnlMainLayout.createSequentialGroup()
                                .addGroup(pnlMainLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(GroupLayout.Alignment.TRAILING, pnlMainLayout.createSequentialGroup()
                                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addGroup(pnlMainLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                        .addComponent(pnlTitle, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                        .addGroup(pnlMainLayout.createSequentialGroup()
                                                                .addComponent(pnlGameControl, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(18, 18, 18)
                                                                .addGroup(pnlMainLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                                                        .addComponent(pnlScoreBoard, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                        .addComponent(pnlTeamSelection, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                                        .addGroup(pnlMainLayout.createSequentialGroup()
                                                                                .addComponent(pnlHomeScoreUpdate, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                                .addComponent(pnlAwayScoreUpdate, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))))
                                        .addGroup(pnlMainLayout.createSequentialGroup()
                                                .addGap(97, 97, 97)
                                                .addComponent(lblStatus)
                                                .addGap(0, 0, Short.MAX_VALUE))
                                        .addGroup(pnlMainLayout.createSequentialGroup()
                                                .addContainerGap()
                                                .addComponent(pnlGameSummary, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addContainerGap())
        );
        pnlMainLayout.setVerticalGroup(
                pnlMainLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(pnlMainLayout.createSequentialGroup()
                                .addComponent(pnlTitle, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(pnlMainLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                        .addGroup(pnlMainLayout.createSequentialGroup()
                                                .addComponent(pnlTeamSelection, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(pnlScoreBoard, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                        .addComponent(pnlGameControl, GroupLayout.PREFERRED_SIZE, 103, GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(pnlMainLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(pnlAwayScoreUpdate, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(pnlHomeScoreUpdate, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(pnlGameSummary, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lblStatus))
        );

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(pnlMain, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(pnlMain, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }

    private void btnHomeScoreUpdateActionPerformed(ActionEvent evt)  //This is to update the HomeScore by 1 whenever the data feeder clicks the button
    {
        boardHomeScore=boardHomeScore+1; //A football team can only score ONE goal at a time.
        currentBoardHomeScore= currentBoardHomeScore +1;
        txtGameScore.setText(boardHomeScore + ":" + boardAwayScore);
        match.setHomeScore(boardHomeScore);

    }
    private void btnAwayScoreUpdateActionPerformed(ActionEvent evt) {

        boardAwayScore=boardAwayScore+1;
        currentBoardAwayScore= currentBoardAwayScore + 1;
        txtGameScore.setText(boardHomeScore + ":" + boardAwayScore);
        match.setAwayScore(boardAwayScore);
    }
    private void btnStartGameActionPerformed(ActionEvent evt) {

        if(btnStartGame.getText().equals("Start"))
        {
            btnStartGame.setText("Finish");
        }
        else
        {
            btnStartGame.setText("Start");
        }
        if(evt.getActionCommand().equals("Start"))
        {
            boolean checkDataIntegrity=false;
            String txtAreaSummaryValue=txtAreaSummary.getText().trim();
            if(txtAreaSummaryValue.equals("")==true)
            {
                checkDataIntegrity=true; //The TextArea is empty
            }
            else //If it's already contains match results
            {
                if (txtAreaSummaryValue.contains(homeTeam) || txtAreaSummaryValue.contains(awayTeam))
                {
                    checkDataIntegrity=false;
                    JOptionPane.showMessageDialog(null, "A team (s) can't played twice within three days", "Roseindia.net", 1);
                    btnStartGame.setText("Start");
                }
                else
                {
                    checkDataIntegrity=true;
                }
            }
            if(checkDataIntegrity==true) //If the selected teams satisfies the rules
            {
                btnHomeScoreUpdate.setEnabled(true);
                btnAwayScoreUpdate.setEnabled(true);
                ScoreBoard.activateScoreBoard();
            }
        }
        else
        {
            homeTeam=String.valueOf(cmbHomeTeam.getSelectedItem());
            awayTeam=String.valueOf(cmbAwayTeam.getSelectedItem());
            match.setHomeTeam(homeTeam);
            match.setAwayTeam(awayTeam);
            numberOfGamesAdded=numberOfGamesAdded+1;
            totalScoreList.add(currentBoardHomeScore+currentBoardAwayScore);
            txtAreaSummary.append(match.getHomeTeam() + " " + match.getHomeScore() + " - " + match.getAwayScore() + " " + match.getAwayTeam()   + "\n");
            match.matchData.add(homeTeam + " " + currentBoardHomeScore + " - " + currentBoardAwayScore + " " + awayTeam + ":"+ (currentBoardHomeScore+currentBoardAwayScore));

            ScoreBoard.clearScoreBoard();

        }

    }
    private void cmbHomeTeamActionPerformed(ActionEvent evt) {
        homeTeamSelected=true;
        homeTeam=String.valueOf(cmbHomeTeam.getSelectedItem());
        currentBoardHomeScore=0;

    }
    private void cmbAwayTeamActionPerformed(ActionEvent evt) {
        awayTeamSelected=true;
        awayTeam=String.valueOf(cmbAwayTeam.getSelectedItem());
        currentBoardAwayScore=0;
        if(homeTeamSelected==true && awayTeamSelected==true) //Two countries needed for a world cup match.
        {
            if((homeTeam.equals(awayTeam)==false)) //Two different teams must be selected to start the game.
            {
                ScoreBoard.activateStartGame();

            }
            else
            {
                ScoreBoard.manageGameRules();
            }
        }
    }

    private void btnSummaryGamesActionPerformed(ActionEvent evt) {

        ScoreBoard.printGameSummary();
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


}



