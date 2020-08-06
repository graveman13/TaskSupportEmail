import enumvalue.LineSymbol
import spock.lang.Specification
import util.ParseInputStringLinesUtil

class ParseInputStringLinesUtilTest extends Specification {
    private static final String lines = "7\n" +
            "C 1.1 8.15.1 P 15.10.2012 83\n" +
            "C 3 10.2 N 02.10.2012 100\n" +
            "D 1 * P 8.10.2012-20.11.2012\n" +
            "D 3 10 P 01.12.2012"

    def "Parse string , correct value"() {
        given:
        List<String> stringList = ParseInputStringLinesUtil.getStringQueryList(lines)
         def a =  ParseInputStringLinesUtil
        expect:
        "C 1.1 8.15.1 P 15.10.2012 83".equals(stringList.get(0))
        "C 3 10.2 N 02.10.2012 100".equals(stringList.get(1))
        "D 1 * P 8.10.2012-20.11.2012".equals(stringList.get(2))
        "D 3 10 P 01.12.2012".equals(stringList.get(3))
    }

    def "Parse string, correct size"() {
        given:
        List<String> stringList = ParseInputStringLinesUtil.getStringQueryList(lines)
        expect:
        4 == stringList.size()
    }
}
