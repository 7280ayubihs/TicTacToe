package com.ss.tictactoe;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.VectorDrawable;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.SparseArray;
import android.util.SparseIntArray;
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

    @BindView(R.id.square0)
    ImageView mSquare0;
    @BindView(R.id.square1)
    ImageView mSquare1;
    @BindView(R.id.square2)
    ImageView mSquare2;
    @BindView(R.id.square3)
    ImageView mSquare3;
    @BindView(R.id.square4)
    ImageView mSquare4;
    @BindView(R.id.square5)
    ImageView mSquare5;
    @BindView(R.id.square6)
    ImageView mSquare6;
    @BindView(R.id.square7)
    ImageView mSquare7;
    @BindView(R.id.square8)
    ImageView mSquare8;
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

    private SparseIntArray mSquareIndexMap;

    private TicTacToe mTicTacToe;

    private TicTacToe.SquareState mTurn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setLayoutParams();
        mTicTacToe = new TicTacToe();
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
        params = mSquare0.getLayoutParams();
        params.width = size;
        params.height = size;
        mSquare0.setLayoutParams(params);

        params = mSquare1.getLayoutParams();
        params.width = size;
        params.height = size;
        mSquare1.setLayoutParams(params);

        params = mSquare2.getLayoutParams();
        params.width = size;
        params.height = size;
        mSquare2.setLayoutParams(params);

        params = mSquare3.getLayoutParams();
        params.width = size;
        params.height = size;
        mSquare3.setLayoutParams(params);

        params = mSquare4.getLayoutParams();
        params.width = size;
        params.height = size;
        mSquare4.setLayoutParams(params);

        params = mSquare5.getLayoutParams();
        params.width = size;
        params.height = size;
        mSquare5.setLayoutParams(params);

        params = mSquare6.getLayoutParams();
        params.width = size;
        params.height = size;
        mSquare6.setLayoutParams(params);

        params = mSquare7.getLayoutParams();
        params.width = size;
        params.height = size;
        mSquare7.setLayoutParams(params);

        params = mSquare8.getLayoutParams();
        params.width = size;
        params.height = size;
        mSquare8.setLayoutParams(params);
    }

    @Override
    protected void onResume() {
        super.onResume();
        initBoard();
        initSquareIndexMap();
    }

    /**
     * 盤面の初期化を行う。
     */
    private void initBoard() {
        mTicTacToe.init();
        mTurn = TicTacToe.SquareState.Cross;
        mCrossLayout.setVisibility(View.VISIBLE);
        mCircleLayout.setVisibility(View.INVISIBLE);
        mRestart.setVisibility(View.INVISIBLE);
        mCrossIssueTextView.setVisibility(View.INVISIBLE);
        mCircleIssueTextView.setVisibility(View.INVISIBLE);
        mSquare0.setImageDrawable(null);
        mSquare1.setImageDrawable(null);
        mSquare2.setImageDrawable(null);
        mSquare3.setImageDrawable(null);
        mSquare4.setImageDrawable(null);
        mSquare5.setImageDrawable(null);
        mSquare6.setImageDrawable(null);
        mSquare7.setImageDrawable(null);
        mSquare8.setImageDrawable(null);
    }

    /**
     * {@link #onClickSquareView(ImageView)} で使用する {@link #mSquareIndexMap} を初期化します。
     */
    private void initSquareIndexMap() {
        mSquareIndexMap = new SparseIntArray();
        mSquareIndexMap.put(R.id.square0, 0);
        mSquareIndexMap.put(R.id.square1, 1);
        mSquareIndexMap.put(R.id.square2, 2);
        mSquareIndexMap.put(R.id.square3, 3);
        mSquareIndexMap.put(R.id.square4, 4);
        mSquareIndexMap.put(R.id.square5, 5);
        mSquareIndexMap.put(R.id.square6, 6);
        mSquareIndexMap.put(R.id.square7, 7);
        mSquareIndexMap.put(R.id.square8, 8);
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


    @OnClick({R.id.square0, R.id.square1, R.id.square2, R.id.square3, R.id.square4, R.id.square5, R.id.square6, R.id.square7, R.id.square8})
    void onClickSquareView(ImageView v) {
        if (mTicTacToe.setSquareState(mSquareIndexMap.get(v.getId()), mTurn)) {
            if (mTurn == TicTacToe.SquareState.Cross) {
                v.setImageResource(R.drawable.ic_clear_black_48px);
            } else {
                v.setImageResource(R.drawable.ic_panorama_fish_eye_white_48px);
            }
            updateBoard();
        }
    }

    @OnClick(R.id.restart)
    void onClickRestart() {
        initBoard();
    }
}
