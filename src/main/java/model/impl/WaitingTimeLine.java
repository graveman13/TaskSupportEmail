package model.impl;

import enumvalue.LineSymbol;
import enumvalue.ResponseType;
import java.time.LocalDate;
import model.AbstractLine;

public class WaitingTimeLine extends AbstractLine {
    private Integer waitingTime;
    private LocalDate localDate;

    public WaitingTimeLine(LineSymbol lineSymbol, String serviceType, String questionType,
                           ResponseType responseType, String localDate, String waitingTime) {
        super(lineSymbol, serviceType, questionType, responseType);
        setWaitingTime(waitingTime);
        setLocalDate(localDate);
    }

    public Integer getWaitingTime() {
        return waitingTime;
    }

    private void setLocalDate(String date) {
        localDate = getLocalDate(date);
    }

    private void setWaitingTime(String time) {
        waitingTime = Integer.valueOf(time);
    }

    public LocalDate getLocalDate() {
        return localDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        WaitingTimeLine waitingTimeLine = (WaitingTimeLine) o;
        return waitingTimeLine.getWaitingTime().equals(this.waitingTime)
                && waitingTimeLine.localDate.equals(this.localDate)
                && waitingTimeLine.getLineSymbol().equals(this.getLineSymbol())
                && waitingTimeLine.getResponseType().equals(this.getResponseType())
                && waitingTimeLine.getServiceType().equals(this.getServiceType())
                && waitingTimeLine.getQuestionType().equals(this.getQuestionType());
    }
}
