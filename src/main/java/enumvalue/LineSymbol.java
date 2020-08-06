package enumvalue;

public enum LineSymbol {
    C("WaitingLine"), D("QueryLine");

    private String line;

    private LineSymbol(String line) {
        this.line = line;
    }

    public String getLineType() {
        return line;
    }
}
