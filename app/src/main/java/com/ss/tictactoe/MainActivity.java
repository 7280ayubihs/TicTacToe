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
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
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
    @BindView(R.id.crossLayout)
    LinearLayout mCrossLayout;
    @BindView(R.id.circleLayout)
    LinearLayout mCircleLayout;
    @BindView(R.id.restart)
    Button mRestart;
    @BindView(R.id.crossIssueTextView)
    TextView mCrossIssueTextView;
    @BindView(R.id.circleIssueTextView)
    TextView mCircleIssueTextView;

    private TicTacToe mTicTacToe;

    private TicTacToe.SquareState mTurn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setLayoutParams();
    }

    /**
     * 画面サイズ（dp）からマス目のサイズを計算
     */
    private void setLayoutParams() {
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        int dpWidth = (int) (displayMetrics.widthPixels / displayMetrics.density);
        int dpHeight = (int) (displayMetrics.heightPixels / displayMetrics.density);
        Log.d(getClass().getSimpleName(), "Width : " + dpWidth);
        Log.d(getClass().getSimpleName(), "Height: " + dpHeight);

        int size = dpWidth / 3;

        ViewGroup.LayoutParams params;
        params = mSquareA1.getLayoutParams();
        params.width = size;
        params.height = size;
        mSquareA1.setLayoutParams(params);

        params = mSquareB1.getLayoutParams();
        params.width = size;
        params.height = size;
        mSquareB1.setLayoutParams(params);

        params = mSquareC1.getLayoutParams();
        params.width = size;
        params.height = size;
        mSquareC1.setLayoutParams(params);

        params = mSquareA2.getLayoutParams();
        params.width = size;
        params.height = size;
        mSquareA2.setLayoutParams(params);

        params = mSquareB2.getLayoutParams();
        params.width = size;
        params.height = size;
        mSquareB2.setLayoutParams(params);

        params = mSquareC2.getLayoutParams();
        params.width = size;
        params.height = size;
        mSquareC2.setLayoutParams(params);

        params = mSquareA3.getLayoutParams();
        params.width = size;
        params.height = size;
        mSquareA3.setLayoutParams(params);

        params = mSquareB3.getLayoutParams();
        params.width = size;
        params.height = size;
        mSquareB3.setLayoutParams(params);

        params = mSquareC3.getLayoutParams();
        params.width = size;
        params.height = size;
        mSquareC3.setLayoutParams(params);
    }

    @Override
    protected void onResume() {
        super.onResume();
        initBoard();
    }

    /**
     * 盤面の初期化を行う。
     */
    private void initBoard() {
        mTicTacToe = new TicTacToe();
        mTurn = TicTacToe.SquareState.Cross;
        mCrossLayout.setVisibility(View.VISIBLE);
        mCircleLayout.setVisibility(View.INVISIBLE);
        mRestart.setVisibility(View.INVISIBLE);
        mCrossIssueTextView.setVisibility(View.INVISIBLE);
        mCircleIssueTextView.setVisibility(View.INVISIBLE);
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

    /**
     * {@link #mTicTacToe} の状態を参照し、盤面を更新します。
     */
    private void updateBoard() {
        if (mTicTacToe.isFinished()) {
            if (mTicTacToe.checkIssue() != TicTacToe.SquareState.None) {
                mCrossIssueTextView.setText(mTicTacToe.checkIssue() == TicTacToe.SquareState.Cross ? getString(R.string.issue_winner) : getString(R.string.issue_loser));
                mCircleIssueTextView.setText(mTicTacToe.checkIssue() == TicTacToe.SquareState.Circle ? getString(R.string.issue_winner) : getString(R.string.issue_loser));
            } else {
                // 引き分け
                mCrossIssueTextView.setText(R.string.issue_draw);
                mCircleIssueTextView.setText(R.string.issue_draw);
            }
            mCrossIssueTextView.setVisibility(View.VISIBLE);
            mCircleIssueTextView.setVisibility(View.VISIBLE);
            mCrossLayout.setVisibility(View.VISIBLE);
            mCircleLayout.setVisibility(View.VISIBLE);
            mRestart.setVisibility(View.VISIBLE);
        } else {
            if (mTurn == TicTacToe.SquareState.Cross) {
                mTurn = TicTacToe.SquareState.Circle;
                mCrossLayout.setVisibility(View.INVISIBLE);
                mCircleLayout.setVisibility(View.VISIBLE);
            } else {
                mTurn = TicTacToe.SquareState.Cross;
                mCrossLayout.setVisibility(View.VISIBLE);
                mCircleLayout.setVisibility(View.INVISIBLE);
            }
        }
    }

    @OnClick(R.id.squareA1)
    void onClickSquareA1() {
        if (mTicTacToe.setSquareState("A1", mTurn)) {
            if (mTurn == TicTacToe.SquareState.Cross) {
                mSquareA1.setImageResource(R.drawable.ic_clear_black_48px);
            } else {
                mSquareA1.setImageResource(R.drawable.ic_panorama_fish_eye_white_48px);
            }
            updateBoard();
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
            updateBoard();
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
            updateBoard();
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
            updateBoard();
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
            updateBoard();
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
            updateBoard();
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
            updateBoard();
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
            updateBoard();
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
            updateBoard();
        }
    }

    @OnClick(R.id.restart)
    void onClickRestart() {
        initBoard();
    }
}
