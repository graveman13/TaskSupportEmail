import enumvalue.IndexLine
import enumvalue.LineSymbol
import enumvalue.ResponseType
import spock.lang.Specification

class EnumvalueTest extends Specification {

    def "is IndexLine return true values "() {
        given:
        Integer lineSymbol = IndexLine.LINE_SYMBOL.value
        Integer service_id = IndexLine.SERVICE_ID.value
        Integer questionType = enumvalue.IndexLine.QUESTION_TYPE_ID.value
        Integer responseType = IndexLine.RESPONSE_TYPE.value
        Integer dataFrom = IndexLine.DATE_FROM.value
        Integer datoTo = enumvalue.IndexLine.DATE_TO.value
        Integer localDate = enumvalue.IndexLine.LOCAL_DATE.value
        Integer waitingTime = IndexLine.WAITING_TIME.value

        expect:
        0 == lineSymbol
        1 == service_id
        2 == questionType
        3 == responseType
        4 == dataFrom
        5 == datoTo
        4 == localDate
        5 == waitingTime
    }

    def "is LineSymbol return tru values"() {
        given:
        String C = LineSymbol.C.lineType
        String D = LineSymbol.D.lineType

        expect:
        "WaitingLine".equals(C)
        "QueryLine".equals(D)
    }

    def "is ResponseType return true values"() {
        given:
        String P = ResponseType.P.lineType
        String N = ResponseType.N.lineType

        expect:
        "first answer".equals(P)
        "next answer".equals(N)
    }
}
