public class Task {
    private String name;
    private boolean done;

    public Task(String name) {
        this.name = name;
        done = false;
    }

    public void mark() {
        done = true;
    }

    public void unmark() {
        done = false;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", done ? "X" : " ", name);
    }
}
