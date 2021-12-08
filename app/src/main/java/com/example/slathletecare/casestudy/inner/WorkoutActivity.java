package com.example.slathletecare.casestudy.inner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.os.Bundle;
import android.transition.AutoTransition;
import android.transition.TransitionManager;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.example.slathletecare.R;

public class WorkoutActivity extends AppCompatActivity {
    ImageButton arrow,a2;
    LinearLayout hiddenView,l2;
    CardView cardView,c2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout);
        cardView = findViewById(R.id.cccx);
        arrow = findViewById(R.id.arrow_button);
        hiddenView = findViewById(R.id.l1);
        c2 = findViewById(R.id.cccx2);
        a2 = findViewById(R.id.arrow_button2);
        l2 = findViewById(R.id.l2);
        getSupportActionBar().hide();
        arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // If the CardView is already expanded, set its visibility
                //  to gone and change the expand less icon to expand more.
                if (hiddenView.getVisibility() == View.VISIBLE) {
                    TransitionManager.beginDelayedTransition(cardView,
                            new AutoTransition());
                    hiddenView.setVisibility(View.GONE);
                    arrow.setImageResource(R.drawable.ic_baseline_expand_more_24);
                }

                // If the CardView is not expanded, set its visibility
                // to visible and change the expand more icon to expand less.
                else {

                    TransitionManager.beginDelayedTransition(cardView,
                            new AutoTransition());
                    hiddenView.setVisibility(View.VISIBLE);
                    arrow.setImageResource(R.drawable.ic_baseline_expand_less_24);
                }
            }
        });
        a2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // If the CardView is already expanded, set its visibility
                //  to gone and change the expand less icon to expand more.
                if (l2.getVisibility() == View.VISIBLE) {
                    TransitionManager.beginDelayedTransition(c2,
                            new AutoTransition());
                    l2.setVisibility(View.GONE);
                    a2.setImageResource(R.drawable.ic_baseline_expand_more_24);
                }

                // If the CardView is not expanded, set its visibility
                // to visible and change the expand more icon to expand less.
                else {

                    TransitionManager.beginDelayedTransition(c2,
                            new AutoTransition());
                    l2.setVisibility(View.VISIBLE);
                    a2.setImageResource(R.drawable.ic_baseline_expand_less_24);
                }
            }
        });
    }
}