import enumvalue.LineSymbol
import enumvalue.ResponseType
import model.impl.QueryLine
import model.impl.WaitingTimeLine
import service.impl.ServiceImpl
import spock.lang.Specification

class ServiceTest extends Specification {

    private final String QUERY_1 = "D 1.2 3.2 P 10.12.2012-01.02.2013";
    private final String QUERY_2 = "D * 3.2 P 10.12.2012-01.02.2013";
    private final String QUERY_3 = "D 1.2 * P 10.01.2011";
    private final String QUERY_4 = "D * * P 10.12.2012";
    private final String WAITING_QUERY_1 = "C 1.2 3.2.4 P 11.12.2012 100"
    private final String WAITING_QUERY2 = "C 1.3 3.2 P 11.12.2012 120"
    private final String WAITING_QUERY_3 = "C 1.3 3.2 N 11.12.2012 170"
    private final String WAITING_QUERY_4 = "C 1.3 3.2.4 P 11.12.2015 13"
    private final String WAITING_QUERY_5 = "C 1.2 3.2.4 P 01.12.2007 82"

    private final ServiceImpl SERVICE_IMPL_1 =
            new ServiceImpl("D 1.2 3.2 P 10.12.2012-01.02.2013"
                    + "C 1.2 3.2.4 P 11.12.2012 100")
    private final ServiceImpl SERVICE_IMPL_2 =
            new ServiceImpl("C 1.2 3.2.4 P 11.12.2012 100"
                    + "C 1.3 3.2 P 11.12.2012 120"
                    + "D * 3.2 P 10.12.2012-01.02.2013")
    private final ServiceImpl SERVICE_IMPL_3 =
            new ServiceImpl("D 1.2 3.2 P 10.12.2012-01.02.2013"
                    + "C 1.2 3.2.4 P 11.12.2012 100"
                    + "C 1.3 3.2 P 11.12.2012 120"
                    + "C 1.3 3.2 N 11.12.2012 170"
                    + "D * 3.2 P 10.12.2012-01.02.2013"
                    + "D * * P 10.12.2012")
    private final ServiceImpl SERVICE_IMPL_4 =
            new ServiceImpl("D 1.2 3.2 P 10.12.2012-01.02.2013"
                    + "C 1.2 3.2.4 P 11.12.2012 100"
                    + "C 1.3 3.2 P 11.12.2012 120"
                    + "C 1.3 3.2 N 11.12.2012 170"
                    + "C 1.3 3.2.4 P 11.12.2015 13"
                    + "D * 3.2 P 10.12.2012-01.02.2013"
                    + "D * * P 10.12.2012")
    private final ServiceImpl SERVICE_IMPL_5 =
            new ServiceImpl("C 1.2 3.2.4 P 11.12.2012 100"
                    + "C 1.3 3.2 P 11.12.2012 120"
                    + "C 1.2 3.2.4 P 01.12.2007 82"
                    + "C 1.3 3.2 N 11.12.2012 170"
                    + "D 1.2 * P 10.01.2011"
                    + "C 1.3 3.2.4 P 11.12.2012 13"
                    + "D * 3.2 P 10.12.2012-01.02.2013")

    def "size list after parse"() {
        given:
        List<String> stringList =
                SERVICE_IMPL_1.parseInputLineToStringList(QUERY_1 + "" + WAITING_QUERY_1);

        expect:
        2 == stringList.size()
    }

    def "lists  Query line and Waiting line type is not empty"() {
        expect:
        !SERVICE_IMPL_1.getWaitingTimeLineList().isEmpty()
        !SERVICE_IMPL_1.getQueryLineList().isEmpty()
    }

    def "lists  Query line type have correct values"() {
        given:
        QueryLine queryLine1 = new QueryLine(LineSymbol.D, "1.2",
                "3.2", ResponseType.P, "10.12.2012", "01.02.2013")

        QueryLine queryLine2 = new QueryLine(LineSymbol.C, "1.1",
                "3.2", ResponseType.N, "10.12.2012", "01.02.2013")
        QueryLine queryLineFromList = SERVICE_IMPL_1.getQueryLineList().get(0);
        expect:
        queryLineFromList.equals(queryLine1)
        !queryLineFromList.equals(queryLine2)
    }

    def "lists  Waiting  line type have correct values"() {
        given:
        WaitingTimeLine waitingTimeLine1 = new WaitingTimeLine(LineSymbol.C, "1.2",
                "3.2.4", ResponseType.P, "11.12.2012", "100")
        WaitingTimeLine waitingTimeLine2 = new WaitingTimeLine(LineSymbol.C, "2.2",
                "3.2.4", ResponseType.P, "11.12.2012", "100")
        WaitingTimeLine waitLineLineFromList = SERVICE_IMPL_1.getWaitingTimeLineList().get(0);
        expect:
        waitingTimeLine1.equals(waitLineLineFromList)
        !waitingTimeLine2.equals(waitLineLineFromList)
    }

    def "getWaitingTimeLine method"() {
        given:
        String[] arrayString = ["C", "1.2", "3.2.4", "P", "11.12.2012", "100"]
        WaitingTimeLine line = SERVICE_IMPL_1.getWaitingTimeLine(arrayString)
        expect:
        new WaitingTimeLine(LineSymbol.C, "1.2",
                "3.2.4", ResponseType.P, "11.12.2012", "100").equals(line)
    }

    def "getQueryLineType method"() {
        given:
        String[] arrayString = ["D", "1.2", "3.2.4", "P", "11.12.2012"]
        QueryLine line = SERVICE_IMPL_1.getQueryLineType(arrayString)
        expect:
        new QueryLine(LineSymbol.D, "1.2",
                "3.2.4", ResponseType.P, "11.12.2012").equals(line)
    }

    def "getAverageWaitingTimeLines method"() {
        expect:
        "110.0".equals(SERVICE_IMPL_2.getAverageWaitingTimeLines().get(0))

        "-".equals(SERVICE_IMPL_3.getAverageWaitingTimeLines().get(0))
        "110.0".equals(SERVICE_IMPL_3.getAverageWaitingTimeLines().get(1))
        "110.0".equals(SERVICE_IMPL_3.getAverageWaitingTimeLines().get(2))

        "-".equals(SERVICE_IMPL_4.getAverageWaitingTimeLines().get(0))
        "110.0".equals(SERVICE_IMPL_4.getAverageWaitingTimeLines().get(1))
        "78.0".equals(SERVICE_IMPL_4.getAverageWaitingTimeLines().get(2))

        "100.0".equals(SERVICE_IMPL_5.getAverageWaitingTimeLines().get(0))
        "78.0".equals(SERVICE_IMPL_5.getAverageWaitingTimeLines().get(1))
    }
}
