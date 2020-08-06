import model.impl.QuestionTypeImpl
import spock.lang.Specification

class QuestionTypeTest extends Specification {
    def "QueryLineType return correct data"() {
        given:
        QuestionTypeImpl questionType1 = new QuestionTypeImpl("1.1.3")
        QuestionTypeImpl questionType2 = new QuestionTypeImpl("*");

        expect:
        "1" == questionType1.getQuestionId()
        "1" == questionType1.getCategoryId()
        "3" == questionType1.getSubCategoryId()
        false == questionType1.getSpecialType()

        "*" == questionType2.getQuestionId()
        null == questionType2.getCategoryId()
        null == questionType2.getSubCategoryId()
        true == questionType2.getSpecialType()
    }
}
