package lesson1;

public class Teams {

    String name;
    Contributors[] contributors;

    public Teams(String name, Contributors... contributors) {
        this.name = name;
        this.contributors = contributors;
    }

    public String getName() {
        return name;
    }

    public Contributors[] getContributors() {
        return contributors;
    }

    public void showWinners() {
        for (Contributors c: contributors) {
            if (c.isDistance()){
                c.info();
            }
        }
    }

    public void showContributors() {
       for (Contributors c: contributors) {
            c.info();
        }

    }
}
