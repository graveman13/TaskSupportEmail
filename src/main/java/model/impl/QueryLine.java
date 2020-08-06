package model.impl;

import enumvalue.LineSymbol;
import enumvalue.ResponseType;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import model.AbstractLine;

public class QueryLine extends AbstractLine {

    private LocalDate dateFrom;
    private LocalDate dateTo;
    private List<WaitingTimeLine> waitingLines;

    public QueryLine(LineSymbol lineSymbol, String serviceType, String questionType,
                     ResponseType responseType, String dateFrom) {
        super(lineSymbol, serviceType, questionType, responseType);
        waitingLines = new ArrayList<>();
        setDateFrom(dateFrom);
    }

    public QueryLine(LineSymbol lineSymbol, String serviceType, String questionType,
                     ResponseType responseType, String dateFrom, String dateTo) {
        this(lineSymbol, serviceType, questionType, responseType, dateFrom);
        setDateTo(dateTo);
    }

    public void addAll(List<WaitingTimeLine> waitingTimeLines) {
        waitingLines.addAll(waitingTimeLines.stream()
                .filter(wtl -> isSimular(wtl))
                .collect(Collectors.toList()));
    }

    public Double getAverageWaitingTime() {
        return waitingLines.stream()
                .mapToInt(line -> line.getWaitingTime())
                .average().stream().map(el -> Math.round(el)).findFirst().orElse(-1);
    }

    public boolean isSimular(WaitingTimeLine waitingTimeLine) {
        return isServiceTypeSimilar(this, waitingTimeLine)
                && isQuestionTypeSimilar(this, waitingTimeLine)
                && this.getResponseType().equals(waitingTimeLine.getResponseType())
                && isDateSimilar(waitingTimeLine);
    }

    private void setDateFrom(String date) {
        dateFrom = getLocalDate(date);
    }

    private void setDateTo(String date) {
        dateTo = getLocalDate(date);
    }

    public LocalDate getDateFrom() {
        return dateFrom;
    }

    public LocalDate getDateTo() {
        return dateTo;
    }

    private boolean isServiceTypeSimilar(QueryLine queryLine, WaitingTimeLine waitingTimeLine) {
        ServiceTypeImpl serviceTypeInQueryLine = queryLine.getServiceType();
        ServiceTypeImpl serviceTypeInWaitLine = waitingTimeLine.getServiceType();
        if (serviceTypeInQueryLine.getSpecialType()) {
            return true;
        }

        String serviceIdQueryLine = serviceTypeInQueryLine.getServiceId();
        String variationIdQueryLine = serviceTypeInQueryLine.getVariationId();

        String seviceIdWaitingTime = serviceTypeInWaitLine.getServiceId();
        String variationIdWaitingTime = serviceTypeInWaitLine.getVariationId();

        return serviceIdQueryLine.equals(seviceIdWaitingTime)
                && (variationIdQueryLine == null
                || variationIdQueryLine.equals(variationIdWaitingTime));
    }

    private boolean isQuestionTypeSimilar(QueryLine queryLine, WaitingTimeLine waitingTimeLine) {
        QuestionTypeImpl questionTypeInQueryLine = queryLine.getQuestionType();
        QuestionTypeImpl questionTypeInWaitLine = waitingTimeLine.getQuestionType();

        if (questionTypeInQueryLine.getSpecialType()) {
            return true;
        }

        String questionIdQueryLine = questionTypeInQueryLine.getQuestionId();
        String categoryQueryLine = questionTypeInQueryLine.getCategoryId();
        String subCategoryQueryLine = questionTypeInQueryLine.getSubCategoryId();

        String questionIdWaitingTime = questionTypeInWaitLine.getQuestionId();
        String categoryWaitingTime = questionTypeInWaitLine.getCategoryId();
        String subCategoryWaitingTime = questionTypeInWaitLine.getSubCategoryId();

        return questionIdQueryLine.equals(questionIdWaitingTime)
                && (categoryQueryLine == null
                || categoryQueryLine.equals(categoryWaitingTime)
                && (subCategoryQueryLine == null
                || subCategoryQueryLine.equals(subCategoryWaitingTime)));
    }

    private boolean isDateSimilar(WaitingTimeLine waitingTimeLine) {
        LocalDate waitingDate = waitingTimeLine.getLocalDate();
        return dateFrom.isBefore(waitingDate)
                && (dateTo == null || dateTo.isAfter(waitingDate));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        QueryLine queryLine = (QueryLine) o;
        return queryLine.dateFrom.equals(this.dateFrom)
                && (queryLine.dateTo == null && this.dateTo == null
                || queryLine.dateTo.equals(this.dateTo))
                && queryLine.waitingLines.equals(this.waitingLines)
                && queryLine.getLineSymbol().equals(this.getLineSymbol())
                && queryLine.getResponseType().equals(this.getResponseType())
                && queryLine.getServiceType().equals(this.getServiceType())
                && queryLine.getQuestionType().equals(this.getQuestionType());
    }

}
