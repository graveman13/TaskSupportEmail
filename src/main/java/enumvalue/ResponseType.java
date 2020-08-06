package enumvalue;

public enum ResponseType {
    P("first answer"),
    N("next answer");
    private String line;

    private ResponseType(String line) {
        this.line = line;
    }

    public String getLineType() {
        return line;
    }
}
