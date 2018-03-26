package entities;

public class ProjectInfo {
    private String projectName;
    private int cost;
    private int developersCount;

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public int getDevelopersCount() {
        return developersCount;
    }

    public void setDevelopersCount(int developersCount) {
        this.developersCount = developersCount;
    }
}
