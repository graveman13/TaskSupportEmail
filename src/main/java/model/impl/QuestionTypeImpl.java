package model.impl;

import model.AbstractLineType;

public class QuestionTypeImpl extends AbstractLineType {
    public QuestionTypeImpl(String questionType) {
        super(questionType);
    }

    public String getQuestionId() {
        return getBaseId();
    }

    public String getCategoryId() {
        return getVariationId();
    }

    public String getSubCategoryId() {
        return getSubVariationId();
    }

    public boolean isSpecial() {
        return getSpecialType();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        QuestionTypeImpl questionType = (QuestionTypeImpl) o;
        return questionType.getQuestionId().equals(this.getQuestionId())
                && (questionType.getCategoryId() == null && this.getCategoryId() == null
                || questionType.getCategoryId().equals(this.getCategoryId()))
                && (questionType.getSubCategoryId() == null && this.getSubCategoryId() == null
                || questionType.getSubCategoryId().equals(this.getSubCategoryId()))
                && questionType.isSpecial() == this.isSpecial();
    }
}
