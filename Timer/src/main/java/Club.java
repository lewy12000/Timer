public class Club {
    String name;
    int matches;
    int points;

    public Club(){
        this.name = "Southampton";
        this.matches = 0;
        this.points = 0;
    }

   @Scheduled
   public void showMatches(){
       System.out.println(name + " played " + matches + " matches");
       matches++;
   }

   @Scheduled(period = 7300)
    public void showPoints(){
       System.out.println(name + " has " + points + " points after " + matches + " matches");
       points+=3;
   }

}