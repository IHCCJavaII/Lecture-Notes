import java.util.ArrayList;
import java.util.Arrays;

public class Program {
    public static void main(String[] args) {
        DaysOfTheWeek day = DaysOfTheWeek.MONDAY;




        if(day == DaysOfTheWeek.MONDAY){
            System.out.println("I hate mondays");
        }

        ResidenceHall[] halls = ResidenceHall.values();


        System.out.println("Residence Halls: " + Arrays.toString(halls));

        Game g = new Game(2000, 100, "Factorio", Difficulty.EASY);

        System.out.println(g);

        if(g.getDifficulty() == Difficulty.EASY){
            System.out.println("Enjoy your game on " + Difficulty.EASY.displayName);
        }
    }
}
