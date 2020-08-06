package model;

import enumvalue.LineSymbol;
import enumvalue.ResponseType;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import model.impl.QuestionTypeImpl;
import model.impl.ServiceTypeImpl;

public abstract class AbstractLine {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("d.MM.yyyy");

    private LineSymbol lineSymbol;
    private ServiceTypeImpl serviceType;
    private QuestionTypeImpl questionType;
    private ResponseType responseType;

    public AbstractLine(LineSymbol lineSymbol, String serviceType,
                        String questionType, ResponseType responseType) {
        this.lineSymbol = lineSymbol;
        this.serviceType = new ServiceTypeImpl(serviceType);
        this.questionType = new QuestionTypeImpl(questionType);
        this.responseType = responseType;
    }

    public LineSymbol getLineSymbol() {
        return lineSymbol;
    }

    public ServiceTypeImpl getServiceType() {
        return serviceType;
    }

    public QuestionTypeImpl getQuestionType() {
        return questionType;
    }

    public ResponseType getResponseType() {
        return responseType;
    }

    public LocalDate getLocalDate(String lineDate) {
        return LocalDate.parse(lineDate, FORMATTER);
    }

}
