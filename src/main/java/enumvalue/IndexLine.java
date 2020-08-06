package enumvalue;

public enum IndexLine {
    LINE_SYMBOL(0),
    SERVICE_ID(1),
    QUESTION_TYPE_ID(2),
    RESPONSE_TYPE(3),
    DATE_FROM(4),
    DATE_TO(5),
    LOCAL_DATE(4),
    WAITING_TIME(5);

    private Integer idx;

    private IndexLine(Integer idx) {
        this.idx = idx;
    }

    public Integer getValue() {
        return idx;
    }
}
