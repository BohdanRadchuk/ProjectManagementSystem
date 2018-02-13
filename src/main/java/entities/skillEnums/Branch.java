package entities;

public enum Branch {
   Java("Java"), CPLUSPLUS("C++"), CSHARP ("C#"), JAVASCRIPT ("JS");

    private String branch;

    Branch(String branch) {
        this.branch = branch;
    }

    public String branch() {
        return branch;
    }
}
