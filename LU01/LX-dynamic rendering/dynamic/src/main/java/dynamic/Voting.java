package dynamic;

public class Voting {

    public boolean isOldEnoughToVote;

    public Voting(boolean isOldEnoughToVote) {
        this.isOldEnoughToVote = isOldEnoughToVote;
    }

    public boolean getIsOldEnough() {
        return isOldEnoughToVote;
    }

    public void setIsOldEnough(boolean isOldEnoughToVote) {
        this.isOldEnoughToVote = isOldEnoughToVote;
    }

    // @Override
    // public String toString() {
    //     return super.toString() + "Screen"
    // }

}
