package com.ss.tictactoe;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.VectorDrawable;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends Activity {

    @BindView(R.id.squareA1)
    ImageView mSquareA1;
    @BindView(R.id.squareB1)
    ImageView mSquareB1;
    @BindView(R.id.squareC1)
    ImageView mSquareC1;
    @BindView(R.id.squareA2)
    ImageView mSquareA2;
    @BindView(R.id.squareB2)
    ImageView mSquareB2;
    @BindView(R.id.squareC2)
    ImageView mSquareC2;
    @BindView(R.id.squareA3)
    ImageView mSquareA3;
    @BindView(R.id.squareB3)
    ImageView mSquareB3;
    @BindView(R.id.squareC3)
    ImageView mSquareC3;
    @BindView(R.id.layout1)
    LinearLayout mLayout1;
    @BindView(R.id.layout2)
    LinearLayout mLayout2;

    private TicTacToe mTicTacToe;

    private TicTacToe.SquareState mTurn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();

        int dpHeight = (int) (displayMetrics.heightPixels / displayMetrics.density);
        int dpWidth = (int) (displayMetrics.widthPixels / displayMetrics.density);
        Log.d("DEBUG", "Width->" + dpWidth + ",Height=>" + dpHeight);
        int width = dpWidth / 3;
        int height = width;

        ViewGroup.LayoutParams params;
        params = mSquareA1.getLayoutParams();
        params.width = width;
        params.height = height;
        mSquareA1.setLayoutParams(params);

        params = mSquareB1.getLayoutParams();
        params.width = width;
        params.height = height;
        mSquareB1.setLayoutParams(params);

        params = mSquareC1.getLayoutParams();
        params.width = width;
        params.height = height;
        mSquareC1.setLayoutParams(params);

        params = mSquareA2.getLayoutParams();
        params.width = width;
        params.height = height;
        mSquareA2.setLayoutParams(params);

        params = mSquareB2.getLayoutParams();
        params.width = width;
        params.height = height;
        mSquareB2.setLayoutParams(params);

        params = mSquareC2.getLayoutParams();
        params.width = width;
        params.height = height;
        mSquareC2.setLayoutParams(params);

        params = mSquareA3.getLayoutParams();
        params.width = width;
        params.height = height;
        mSquareA3.setLayoutParams(params);

        params = mSquareB3.getLayoutParams();
        params.width = width;
        params.height = height;
        mSquareB3.setLayoutParams(params);

        params = mSquareC3.getLayoutParams();
        params.width = width;
        params.height = height;
        mSquareC3.setLayoutParams(params);
    }

    @Override
    protected void onResume() {
        super.onResume();
        // 盤面の初期化
        mTicTacToe = new TicTacToe();
        mTurn = TicTacToe.SquareState.Cross;
        mLayout1.setVisibility(View.VISIBLE);
        mLayout2.setVisibility(View.INVISIBLE);
        mSquareA1.setImageDrawable(null);
        mSquareB1.setImageDrawable(null);
        mSquareC1.setImageDrawable(null);
        mSquareA2.setImageDrawable(null);
        mSquareB2.setImageDrawable(null);
        mSquareC2.setImageDrawable(null);
        mSquareA3.setImageDrawable(null);
        mSquareB3.setImageDrawable(null);
        mSquareC3.setImageDrawable(null);
    }

    @OnClick(R.id.squareA1)
    void onClickSquareA1() {
        if (mTicTacToe.setSquareState("A1", mTurn)) {
            if (mTurn == TicTacToe.SquareState.Cross) {
                mSquareA1.setImageResource(R.drawable.ic_clear_black_48px);
            } else {
                mSquareA1.setImageResource(R.drawable.ic_panorama_fish_eye_white_48px);
            }
            if (mTicTacToe.checkIssue() != TicTacToe.SquareState.None) {
                mLayout1.setVisibility(View.VISIBLE);
                mLayout2.setVisibility(View.VISIBLE);
            } else {
                changeTurn();
            }
        }
    }

    @OnClick(R.id.squareB1)
    void onClickSquareB1() {
        if (mTicTacToe.setSquareState("B1", mTurn)) {
            if (mTurn == TicTacToe.SquareState.Cross) {
                mSquareB1.setImageResource(R.drawable.ic_clear_black_48px);
            } else {
                mSquareB1.setImageResource(R.drawable.ic_panorama_fish_eye_white_48px);
            }
            if (mTicTacToe.checkIssue() != TicTacToe.SquareState.None) {
                mLayout1.setVisibility(View.VISIBLE);
                mLayout2.setVisibility(View.VISIBLE);
            } else {
                changeTurn();
            }
        }
    }

    @OnClick(R.id.squareC1)
    void onClickSquareC1() {
        if (mTicTacToe.setSquareState("C1", mTurn)) {
            if (mTurn == TicTacToe.SquareState.Cross) {
                mSquareC1.setImageResource(R.drawable.ic_clear_black_48px);
            } else {
                mSquareC1.setImageResource(R.drawable.ic_panorama_fish_eye_white_48px);
            }
            if (mTicTacToe.checkIssue() != TicTacToe.SquareState.None) {
                mLayout1.setVisibility(View.VISIBLE);
                mLayout2.setVisibility(View.VISIBLE);
            } else {
                changeTurn();
            }
        }
    }

    @OnClick(R.id.squareA2)
    void onClickSquareA2() {
        if (mTicTacToe.setSquareState("A2", mTurn)) {
            if (mTurn == TicTacToe.SquareState.Cross) {
                mSquareA2.setImageResource(R.drawable.ic_clear_black_48px);
            } else {
                mSquareA2.setImageResource(R.drawable.ic_panorama_fish_eye_white_48px);
            }
            if (mTicTacToe.checkIssue() != TicTacToe.SquareState.None) {
                mLayout1.setVisibility(View.VISIBLE);
                mLayout2.setVisibility(View.VISIBLE);
            } else {
                changeTurn();
            }
        }
    }

    @OnClick(R.id.squareB2)
    void onClickSquareB2() {
        if (mTicTacToe.setSquareState("B2", mTurn)) {
            if (mTurn == TicTacToe.SquareState.Cross) {
                mSquareB2.setImageResource(R.drawable.ic_clear_black_48px);
            } else {
                mSquareB2.setImageResource(R.drawable.ic_panorama_fish_eye_white_48px);
            }
            if (mTicTacToe.checkIssue() != TicTacToe.SquareState.None) {
                mLayout1.setVisibility(View.VISIBLE);
                mLayout2.setVisibility(View.VISIBLE);
            } else {
                changeTurn();
            }
        }
    }

    @OnClick(R.id.squareC2)
    void onClickSquareC2() {
        if (mTicTacToe.setSquareState("C2", mTurn)) {
            if (mTurn == TicTacToe.SquareState.Cross) {
                mSquareC2.setImageResource(R.drawable.ic_clear_black_48px);
            } else {
                mSquareC2.setImageResource(R.drawable.ic_panorama_fish_eye_white_48px);
            }
            if (mTicTacToe.checkIssue() != TicTacToe.SquareState.None) {
                mLayout1.setVisibility(View.VISIBLE);
                mLayout2.setVisibility(View.VISIBLE);
            } else {
                changeTurn();
            }
        }
    }

    @OnClick(R.id.squareA3)
    void onClickSquareA3() {
        if (mTicTacToe.setSquareState("A3", mTurn)) {
            if (mTurn == TicTacToe.SquareState.Cross) {
                mSquareA3.setImageResource(R.drawable.ic_clear_black_48px);
            } else {
                mSquareA3.setImageResource(R.drawable.ic_panorama_fish_eye_white_48px);
            }
            if (mTicTacToe.checkIssue() != TicTacToe.SquareState.None) {
                mLayout1.setVisibility(View.VISIBLE);
                mLayout2.setVisibility(View.VISIBLE);
            } else {
                changeTurn();
            }
        }
    }

    @OnClick(R.id.squareB3)
    void onClickSquareB3() {
        if (mTicTacToe.setSquareState("B3", mTurn)) {
            if (mTurn == TicTacToe.SquareState.Cross) {
                mSquareB3.setImageResource(R.drawable.ic_clear_black_48px);
            } else {
                mSquareB3.setImageResource(R.drawable.ic_panorama_fish_eye_white_48px);
            }
            if (mTicTacToe.checkIssue() != TicTacToe.SquareState.None) {
                mLayout1.setVisibility(View.VISIBLE);
                mLayout2.setVisibility(View.VISIBLE);
            } else {
                changeTurn();
            }
        }
    }

    @OnClick(R.id.squareC3)
    void onClickSquareC3() {
        if (mTicTacToe.setSquareState("C3", mTurn)) {
            if (mTurn == TicTacToe.SquareState.Cross) {
                mSquareC3.setImageResource(R.drawable.ic_clear_black_48px);
            } else {
                mSquareC3.setImageResource(R.drawable.ic_panorama_fish_eye_white_48px);
            }
            if (mTicTacToe.checkIssue() != TicTacToe.SquareState.None) {
                mLayout1.setVisibility(View.VISIBLE);
                mLayout2.setVisibility(View.VISIBLE);
            } else {
                changeTurn();
            }
        }
    }

    private void changeTurn() {
        if (mTurn == TicTacToe.SquareState.Cross) {
            mTurn = TicTacToe.SquareState.Circle;
            mLayout1.setVisibility(View.INVISIBLE);
            mLayout2.setVisibility(View.VISIBLE);
        } else {
            mTurn = TicTacToe.SquareState.Cross;
            mLayout1.setVisibility(View.VISIBLE);
            mLayout2.setVisibility(View.INVISIBLE);
        }
    }
}
