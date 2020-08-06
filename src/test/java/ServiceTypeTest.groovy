import model.impl.QuestionTypeImpl
import model.impl.ServiceTypeImpl
import spock.lang.Specification

class ServiceTypeTest extends Specification {
    def "ServiceType return correct data"() {
        given:
        ServiceTypeImpl serviceTypeImpl1 = new ServiceTypeImpl("1.1")
        ServiceTypeImpl serviceTypeImpl2 = new ServiceTypeImpl("*");

        expect:
        "1" == serviceTypeImpl1.getServiceId()
        "1" == serviceTypeImpl1.getVariation()
        false == serviceTypeImpl1.getSpecialType()

        "*" == serviceTypeImpl2.getServiceId()
        null == serviceTypeImpl2.getVariation()
        true == serviceTypeImpl2.getSpecialType()
    }
}
