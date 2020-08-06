package service.impl;

import enumvalue.IndexLine;
import enumvalue.LineSymbol;
import enumvalue.ResponseType;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import model.impl.QueryLine;
import model.impl.WaitingTimeLine;
import service.Service;
import util.ParseInputStringLinesUtil;

public class ServiceImpl implements Service {
    private static final String SEPARATOR = " |\\-";
    private static final Integer LENGTH = 5;
    private List<QueryLine> queryLineList;
    private List<WaitingTimeLine> waitingTimeLineList;

    public ServiceImpl(String inputLines) {
        queryLineList = new ArrayList<>();
        waitingTimeLineList = new ArrayList<>();
        createObjectsLines(parseInputLineToStringList(inputLines));
    }

    @Override
    public List<String> parseInputLineToStringList(String inputLine) {
        List<String> listLines = null;
        try {
            listLines = ParseInputStringLinesUtil.getStringQueryList(inputLine);
        } catch (RuntimeException ex) {
            ex.printStackTrace();
        }
        return listLines;
    }

    @Override
    public void createObjectsLines(List<String> pasreStringLines) {
        for (int i = 0; i < pasreStringLines.size(); i++) {
            String[] separationLine = pasreStringLines.get(i).split(SEPARATOR);
            String lineSymbol = separationLine[IndexLine.LINE_SYMBOL.getValue()];

            if (LineSymbol.valueOf(lineSymbol).equals(LineSymbol.C)) {
                waitingTimeLineList.add(getWaitingTimeLine(separationLine));

            } else if (LineSymbol.valueOf(lineSymbol).equals(LineSymbol.D)) {
                QueryLine queryLine = getQueryLineType(separationLine);

                if (!waitingTimeLineList.isEmpty()) {
                    queryLine.addAll(waitingTimeLineList);
                }
                queryLineList.add(queryLine);
            }
        }
    }

    @Override
    public QueryLine getQueryLineType(String[] separationLine) {
        LineSymbol lineSymbol = LineSymbol.valueOf(
                separationLine[IndexLine.LINE_SYMBOL.getValue()]);
        String serviceType = separationLine[IndexLine.SERVICE_ID.getValue()];
        String questionType = separationLine[IndexLine.QUESTION_TYPE_ID.getValue()];
        ResponseType responseType = ResponseType.valueOf(
                separationLine[IndexLine.RESPONSE_TYPE.getValue()]);
        String dateFrom = separationLine[IndexLine.DATE_FROM.getValue()];

        return separationLine.length == LENGTH
                ? new QueryLine(lineSymbol, serviceType, questionType, responseType, dateFrom)
                : new QueryLine(lineSymbol, serviceType, questionType, responseType, dateFrom,
                separationLine[IndexLine.DATE_TO.getValue()]);
    }

    @Override
    public WaitingTimeLine getWaitingTimeLine(String[] separationLine) {
        WaitingTimeLine waitingTimeLine = new WaitingTimeLine(
                LineSymbol.valueOf(separationLine[IndexLine.LINE_SYMBOL.getValue()]),
                separationLine[IndexLine.SERVICE_ID.getValue()],
                separationLine[IndexLine.QUESTION_TYPE_ID.getValue()],
                ResponseType.valueOf(separationLine[IndexLine.RESPONSE_TYPE.getValue()]),
                separationLine[IndexLine.LOCAL_DATE.getValue()],
                separationLine[IndexLine.WAITING_TIME.getValue()]);
        return waitingTimeLine;
    }

    @Override
    public List<String> getAverageWaitingTimeLines() {
        return queryLineList.stream().map(ql ->
                String.valueOf(ql.getAverageWaitingTime() < 0 ? "-"
                        : ql.getAverageWaitingTime())).collect(Collectors.toList());
    }

    @Override
    public List<QueryLine> getQueryLineList() {
        return queryLineList;
    }

    @Override
    public List<WaitingTimeLine> getWaitingTimeLineList() {
        return waitingTimeLineList;
    }
}
