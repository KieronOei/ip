public class Task {
    private String name;
    private boolean done;

    public Task(String name) {
        this.name = name;
        done = false;
    }

    public void mark() throws CortanaException {
        if (done) {
            throw new CortanaException("Action failed. Task is already marked as done");
        }
        done = true;
    }

    public void unmark() throws CortanaException {
        if (!done) {
            throw new CortanaException("Action failed. Task is already marked as undone");
        }
        done = false;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", done ? "X" : " ", name);
    }
}
