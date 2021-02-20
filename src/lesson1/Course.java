package lesson1;

public class Course {

    Obstacle[] obstacles;

    public Course(Obstacle[] obstacles) {
        this.obstacles = obstacles;
    }
    public void doIt(Teams teams){
       
        for (Contributors contributors:teams.getContributors()) {
            for (Obstacle o:obstacles) {
                o.doit(contributors);
                if (!contributors.isDistance()){
                    break;
                }
            }
        }
    }
}
