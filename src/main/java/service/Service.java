package service;

import java.util.List;
import model.impl.QueryLine;
import model.impl.WaitingTimeLine;

public interface Service {

    List<String> parseInputLineToStringList(String inputLine);

    void createObjectsLines(List<String> pasreStringLines);

    public QueryLine getQueryLineType(String[] separationLine);

    public WaitingTimeLine getWaitingTimeLine(String[] separationLine);

    List<String> getAverageWaitingTimeLines();

    List<QueryLine> getQueryLineList();

    List<WaitingTimeLine> getWaitingTimeLineList();
}
