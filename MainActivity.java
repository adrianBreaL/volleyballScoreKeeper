package com.example.android.volleyballscore;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
/*
Volleyball Score
This app tracks the score in a volleyball match.
One team wins the match when it wins 3 out 5 sets
One team wins one set when it scores 25 points (always with a difference of 2 points)
this applies to the 4 first sets.
to win set 5, the team needs to score 15 points (always with a difference of 2 points)
 */
public class MainActivity extends AppCompatActivity {
    private int teamA, teamB, iA, iB, numA, numB = 0;
    private int[] setA = {
            R.id.setA1,
            R.id.setA2,
            R.id.setA3,
            R.id.setA4,
            R.id.setA5,
    };
    private int[] setB = {
            R.id.setB1,
            R.id.setB2,
            R.id.setB3,
            R.id.setB4,
            R.id.setB5,
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
//    @pointScoreA: add points to the correct set for Team A
    public void pointScoreA(View view){
        if ((teamA >= 14 && teamB <15 && iA == 4) ||(teamA >= 15 && (teamA - teamB) >= 1 && iA == 4)){
            display(teamA+1,setA[iA]);
            Toast.makeText(this, "Team A wins!", Toast.LENGTH_SHORT).show();
            reset(view);
        } else if((teamA >= 24 && teamB <25)|| (teamA >= 24 && (teamA - teamB) >= 1)){
            display(teamA+1,setA[iA]);
            teamA = 1; numA = numA + 1;
            teamB = 0;
            iB = iB + 1;
            iA = iA + 1;
            display(teamA,setA[iA]);
            display(teamB, setB[iB]);
        }else{
            teamA = teamA + 1;
            display(teamA,setA[iA]);
            display(teamB, setB[iB]);
        }
        if(numA >= 3 && (numA - numB)>= 2){
            Toast.makeText(this, "Team A wins!", Toast.LENGTH_SHORT).show();
            reset(view);
        }
    }
//    @pointScoreB: add points to the correct set for Team B
    public void pointScoreB(View view){
        if (teamB >= 15 && teamA <15 && iB == 4){
            Toast.makeText(this, "Team B wins", Toast.LENGTH_SHORT).show();
            reset(view);
        }else if(teamB >= 15 && iB == 4 && (teamB - teamA) >= 1 ){
            display(teamB+1,setB[iB]);
            Toast.makeText(this, "Team B wins!", Toast.LENGTH_SHORT).show();
            reset(view);
        }else if(teamB >= 25 && teamA <25){
            teamA = 0;
            teamB = 1; numB = numB + 1;
            iB = iB + 1;
            iA = iA + 1;
            display(teamA,setA[iA]);
            display(teamB, setB[iB]);
        }else if(teamB >= 25 && (teamB - teamA) >= 1){
            display(teamB+1,setB[iB]);
            teamA = 0;
            teamB = 1; numB = numB + 1;
            iB = iB + 1;
            iA = iA +1;
            display(teamA,setA[iA]);
            display(teamB, setB[iB]);
        }else{
            teamB = teamB + 1;
            display(teamA,setA[iA]);
            display(teamB, setB[iB]);
        }
        if(numB >= 3 && (numB - numA)>= 2){
            Toast.makeText(this, "Team B wins!", Toast.LENGTH_SHORT).show();
            reset(view);
        }

    }
    //  @reset: restarts the game
    public void reset(View view) {
        teamA = 0; iA = 0; numA = 0;
        teamB = 0; iB = 0; numB = 0;
        for(int i = 0; i < setA.length; i++){
            TextView tA = (TextView) findViewById(setA[i]);
            TextView tB = (TextView) findViewById(setB[i]);
            tA.setText(getString(R.string.tset));
            tB.setText(getString(R.string.tset));
        }
    }
    //    @display: displays score on screen
    private void display(int team, int idTeam) {
        TextView score = (TextView) findViewById(idTeam);
        score.setText("" + team);
    }
}
