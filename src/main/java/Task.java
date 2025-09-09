public class Task {
    private String name;
    private boolean isDone;

    public Task(String name) {
        this.name = name;
        isDone = false;
    }

    public void mark() throws CortanaException {
        if (isDone) {
            throw new CortanaException("Action failed. Task is already marked as isDone");
        }
        isDone = true;
    }

    public void unmark() throws CortanaException {
        if (!isDone) {
            throw new CortanaException("Action failed. Task is already marked as undone");
        }
        isDone = false;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", isDone ? "X" : " ", name);
    }
}
