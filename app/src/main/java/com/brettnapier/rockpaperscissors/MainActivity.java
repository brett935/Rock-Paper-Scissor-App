package com.brettnapier.rockpaperscissors;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    int computerScore = 0; //score the computer
    int playerScore = 0; //score of the player
    int ties = 0; //amount of ties
    Random r = new Random(); //seed for random numbers
    int computerChoice; //the computers choice, 0=rock 1=paper 2=scissors
    int playerChoice; //the players choice, 0=rock 1=paper 2=scissors
    TextView scoreView; //declare score view area

    //when called changes the value of computerChoice
    public void computerChoice() {
        computerChoice = r.nextInt(2+1); //pick a number between 0 and 2, 0=rock 1=paper 2=scissors
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //fill scoreView with default value on app start
        scoreUpdate();

        final Button rockButton = (Button) findViewById(R.id.rockButton); //associate rockButton with the button with id rockButton
        //set action for rockButton when clicked
        rockButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //rockButton.setText("clicked");
                playerChoice = 0; //set player choice to 0 for rock
                play(); //play when choice is made
            }
        });

        final Button paperButton = (Button) findViewById(R.id.paperButton); //associate paperButton with the button with id paperButton
        //set action for paperButton when clicked
        paperButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                playerChoice = 1; //set player choice to 1 for paper
                play(); //play when choice is made
            }
        });

        final Button scissorButton = (Button) findViewById(R.id.scissorButton); //associate scissorButton with the button with id scissorButton
        //set action for scissorButton when clicked
        scissorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                playerChoice = 2; //set player choice to 2 for scissors
                play(); //play when choice is made
            }
        });

        /*final Button playButton = (Button) findViewById(R.id.playButton); //associate playButton with the button with id playButton
        playButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                play(); //play when choice is made

                //update the button to say play again after first game
                final Button playButton = (Button) findViewById(R.id.playButton);
                playButton.setText( "Play Again" );
            }
        });
       */
    }

    //main game loop
    public void play(){
        computerChoice(); //call function to get computers choice
        //computer chooses rock
        if(computerChoice==0){
            //player chooses rock
            if(playerChoice==0){
                //tie
                scored("none");
            }
            //player chooses paper
            else if(playerChoice==1){
                //player wins
                scored("player");
            }
            //player chooses scissors
            else if(playerChoice==2){
                //computer wins
                scored("computer");
            }
        }
        //computer chooses paper
        else if(computerChoice==1){
            //player chooses rock
            if(playerChoice==0){
                //computer wins
                scored("computer");
            }
            //player chooses paper
            else if(playerChoice==1){
                //tie
                scored("none");
            }
            //player chooses scissors
            else if(playerChoice==2) {
                //player wins
                scored("player");
            }
        }
        //computer chooses scissors
        else if(computerChoice==2){
            //player chooses rock
            if(playerChoice==0){
                //player wins
                scored("player");
            }
            //player chooses paper
            else if(playerChoice==1){
                //computer wins
                scored("computer");
            }
            //player chooses scissors
            else if(playerChoice==2){
                //tie
                scored("none");
            }
        }

        //update the score
        scoreUpdate();
    }

    public void scored(String winner){
        if(winner=="computer"){
            //increment computer score by 1
            computerScore++;

            //alert user of winner with a toast
            Toast toast = Toast.makeText(this , "Computer won this round!", Toast.LENGTH_SHORT);
            toast.show();
        }
        else if(winner=="player") {
            //increment player score by 1
            playerScore++;

            //alert user of winner with a toast
            Toast toast = Toast.makeText(this, "You won this round!!", Toast.LENGTH_SHORT);
            toast.show();
        }
        else if(winner=="none"){
            //increment tie by 1
            ties++;
            Toast toast = Toast.makeText(this, "This round was a tie!", Toast.LENGTH_SHORT);
            toast.show();
        }
    }
    //update the textView score
    public void scoreUpdate(){
        //update score textView
        scoreView = (TextView) findViewById( R.id.scores); //associate scores field with variable scoreView
        String message = "Score is: \nPlayer: " + playerScore + "\nComputer: " + computerScore + "\nTies: " + ties;
        scoreView.setText(message);
    }
    //save variables on screen orientation change
    protected void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
        outState.putInt("computerScore", computerScore); //save computerScore with the key "computerScore"
        outState.putInt("playerScore", playerScore); //save playerScore with the key "playerScore"
        outState.putInt("ties", ties); //save ties with the key "ties"
    }

    //retrieve variables on screen orientation change
    public void onRestoreInstanceState(Bundle instate){
        super.onRestoreInstanceState(instate);
        computerScore = instate.getInt("computerScore"); //retrieve the value associated with the key "computerScore" and restore the variable computerScore value
        playerScore = instate.getInt("playerScore"); //retrieve the value associated with the key "playerScore" and restore the variable playerScore value
        ties = instate.getInt("ties");

        //update the score after restoring instance
        scoreUpdate();
    }
}
