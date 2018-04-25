package com.example.nidhi.connect3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

//active player for having alternate zero kaata
    //gamestate for who which col has what and keep track if somebody has won
    //winning postions for  arrangement for which game is won
    //tags for identifying where the player has placed the zero kaata
int activePlayer=0;
    int[]gamestate={2,2,2,2,2,2,2,2,2};
    int[][]winningpositions={{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
    boolean gameactive=true;

    public  void dropIn(View view)
    {
        ImageView counter=(ImageView)view;
        int tappedcounter=Integer.parseInt(counter.getTag().toString());
        if(gamestate[tappedcounter]==2&&gameactive) {
            counter.setTranslationY(-1500);

            gamestate[tappedcounter] = activePlayer;


            if (activePlayer == 0) {

                counter.setImageResource(R.drawable.zero);
                activePlayer = 1;

            } else {

                counter.setImageResource(R.drawable.kaata);
                activePlayer = 0;
            }
            counter.animate().translationYBy(1500).setDuration(300).rotation(360000);
            String winner="";
            for (int[] wp : winningpositions) {
                if (gamestate[wp[0]] == gamestate[wp[1]] && gamestate[wp[1]] == gamestate[wp[2]] && gamestate[wp[0]] != 2) {
                    //won
                    gameactive = false;
                    if (activePlayer == 1) {
                        winner="0";
                    } else {
                        winner ="1";
                    }


                    Button playagainbutton=(Button) findViewById(R.id.buttonplayagain);
                    TextView tv=(TextView) findViewById(R.id.textView);
                    tv.setText(winner+" has won");
                    playagainbutton.setVisibility(View.VISIBLE);
                    tv.setVisibility(View.VISIBLE);
                }



            }
        }




    }
    public void playAgain(View view)
    {
        Button playagainbutton=(Button) findViewById(R.id.buttonplayagain);
        TextView tv=(TextView) findViewById(R.id.textView);
        playagainbutton.setVisibility(View.INVISIBLE);
        tv.setVisibility(View.INVISIBLE);
        GridLayout gridLayout=(GridLayout) findViewById(R.id.board);
        for (int i=0;i<gridLayout.getChildCount();i++)
        {
            ImageView counter=(ImageView)gridLayout.getChildAt(i);
            counter.setImageDrawable(null);

        }
        gameactive=true;
        activePlayer=0;
        for (int gs=0;gs<gamestate.length;gs++)
        {
            gamestate[gs]=2;
        }


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
