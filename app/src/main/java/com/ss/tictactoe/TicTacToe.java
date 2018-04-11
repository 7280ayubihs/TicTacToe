package com.ss.tictactoe;

public class TicTacToe {

    /**
     * 盤面の状態
     */
    private SquareState[] mBoardState;

    /**
     * コンストラクタ
     */
    public TicTacToe() {
        init();
    }

    public void init() {
        mBoardState = new SquareState[] {
                SquareState.None, SquareState.None, SquareState.None,   // A1, B1, C1,
                SquareState.None, SquareState.None, SquareState.None,   // A2, B2, C2,
                SquareState.None, SquareState.None, SquareState.None,   // A3, B3, C3,
        };
    }

    public boolean setSquareState(String square, SquareState state) {
        switch (square) {
            case "A1":
                return setSquareState(0, state);
            case "B1":
                return setSquareState(1, state);
            case "C1":
                return setSquareState(2, state);
            case "A2":
                return setSquareState(3, state);
            case "B2":
                return setSquareState(4, state);
            case "C2":
                return setSquareState(5, state);
            case "A3":
                return setSquareState(6, state);
            case "B3":
                return setSquareState(7, state);
            case "C3":
                return setSquareState(8, state);
            default:
                throw new IllegalArgumentException();
        }
    }

    private boolean setSquareState(int squareIndex, SquareState state) {
        if (state == SquareState.None) {
            throw new IllegalArgumentException();
        }
        if (checkIssue() != SquareState.None) {
            return false;
        }
        if (mBoardState[squareIndex] != SquareState.None) {
            return false;
        }
        mBoardState[squareIndex] = state;
        return true;
    }

    public SquareState getSquareState(String square) {
        switch (square) {
            case "A1":
                return getSquareState(0);
            case "B1":
                return getSquareState(1);
            case "C1":
                return getSquareState(2);
            case "A2":
                return getSquareState(3);
            case "B2":
                return getSquareState(4);
            case "C2":
                return getSquareState(5);
            case "A3":
                return getSquareState(6);
            case "B3":
                return getSquareState(7);
            case "C3":
                return getSquareState(8);
            default:
                throw new IllegalArgumentException();
        }
    }

    private SquareState getSquareState(int squareIndex) {
        if (squareIndex < 0 || mBoardState.length < squareIndex) {
            throw new IllegalArgumentException();
        }
        return mBoardState[squareIndex];
    }

    /**
     * 勝敗チェック
     */
    public SquareState checkIssue() {
        if (mBoardState[0] == mBoardState[1] && mBoardState[0] == mBoardState[2]) {
            return mBoardState[0];
        }
        if (mBoardState[3] == mBoardState[4] && mBoardState[3] == mBoardState[5]) {
            return mBoardState[3];
        }
        if (mBoardState[6] == mBoardState[7] && mBoardState[6] == mBoardState[8]) {
            return mBoardState[6];
        }
        if (mBoardState[0] == mBoardState[3] && mBoardState[0] == mBoardState[6]) {
            return mBoardState[0];
        }
        if (mBoardState[1] == mBoardState[4] && mBoardState[1] == mBoardState[7]) {
            return mBoardState[1];
        }
        if (mBoardState[2] == mBoardState[5] && mBoardState[2] == mBoardState[8]) {
            return mBoardState[2];
        }
        if (mBoardState[0] == mBoardState[4] && mBoardState[0] == mBoardState[8]) {
            return mBoardState[0];
        }
        if (mBoardState[2] == mBoardState[4] && mBoardState[2] == mBoardState[6]) {
            return mBoardState[2];
        }
        return SquareState.None;
    }

    /**
     * ゲームが終了しているか
     * @return 終了している場合は true を返します。
     */
    public boolean isFinished() {
        if (checkIssue() != SquareState.None) {
            return true;
        }
        for (SquareState state : mBoardState) {
            if (state == SquareState.None) {
                return false;
            }
        }
        return true;
    }

    public enum SquareState {
        None,
        Circle,
        Cross
    }
}
