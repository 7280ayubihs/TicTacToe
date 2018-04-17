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

    /**
     * 盤面の状態を初期化します。
     */
    public void init() {
        mBoardState = new SquareState[] {
                SquareState.None, SquareState.None, SquareState.None,
                SquareState.None, SquareState.None, SquareState.None,
                SquareState.None, SquareState.None, SquareState.None,
        };
    }

    /**
     * 指定したマスに状態をセットします。
     * @param squareIndex マスのインデックス番号
     * @param state セットする状態。{@link SquareState#Circle} or {@link SquareState#Cross} を設定
     * @return セットに成功した場合は true を返します。セットに失敗した場合は false を返します。
     */
    public boolean setSquareState(int squareIndex, SquareState state) {
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

    /**
     * 勝敗チェック
     */

    /**
     * 盤面の状態をチェックし勝敗を返します。<br>
     * ゲームが終了したかをチェックする場合は、{@link #isFinished()} を使用してください。
     * @return 勝利側の {@link SquareState} を返します。ただし、{@link SquareState#None} が返された場合、
     * 勝敗はついていないか、引分けです。
     */
    public SquareState checkIssue() {
        if (mBoardState[0] != SquareState.None) {
            if (mBoardState[0] == mBoardState[1] && mBoardState[0] == mBoardState[2]){
                return mBoardState[0];
            }
            if (mBoardState[0] == mBoardState[3] && mBoardState[0] == mBoardState[6]) {
                return mBoardState[0];
            }
            if (mBoardState[0] == mBoardState[4] && mBoardState[0] == mBoardState[8]) {
                return mBoardState[0];
            }
        }
        if (mBoardState[1] != SquareState.None && mBoardState[1] == mBoardState[4] && mBoardState[1] == mBoardState[7]) {
            return mBoardState[1];
        }
        if (mBoardState[2] != SquareState.None) {
            if (mBoardState[2] == mBoardState[5] && mBoardState[2] == mBoardState[8]) {
                return mBoardState[2];
            }
            if (mBoardState[2] == mBoardState[4] && mBoardState[2] == mBoardState[6]) {
                return mBoardState[2];
            }
        }
        if (mBoardState[3] != SquareState.None && mBoardState[3] == mBoardState[4] && mBoardState[3] == mBoardState[5]) {
            return mBoardState[3];
        }
        if (mBoardState[6] != SquareState.None && mBoardState[6] == mBoardState[7] && mBoardState[6] == mBoardState[8]) {
            return mBoardState[6];
        }
        return SquareState.None;
    }

    /**
     * ゲームが終了しているかを取得します。<br>
     * 勝敗をチェックする場合は {@link #checkIssue()} を使用してください。
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
