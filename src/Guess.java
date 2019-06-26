public class Guess {
    private int strike;
    private int ball;
    private int theGuess;
    public Guess(int input){
        strike = 0;
        ball = 0;
        theGuess = input;
    }

    public int getStrike() {
        return strike;
    }

    public int getBall() {
        return ball;
    }

    public int getTheGuess() {
        return theGuess;
    }

    public void incrBall(){
        this.ball++;
    }

    public void incrStrike(){
        this.strike++;
    }
}
