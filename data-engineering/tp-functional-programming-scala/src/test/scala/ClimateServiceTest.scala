import com.github.polomarcus.utils.ClimateService
import com.github.polomarcus.model.CO2Record
import org.scalatest.funsuite.AnyFunSuite

//@See https://www.scalatest.org/scaladoc/3.1.2/org/scalatest/funsuite/AnyFunSuite.html
class ClimateServiceTest extends AnyFunSuite {
  test("containsWordGlobalWarming - non climate related words should return false") {
    assert( ClimateService.isClimateRelated("pizza") == false)
  }

  test("isClimateRelated - climate related words should return true") {
    assert(ClimateService.isClimateRelated("climate change") == true)
    assert(ClimateService.isClimateRelated("IPCC")== false)
  }

  test("isClimateRelated"){
    assert(ClimateService.isClimateRelated("temperature increase") == true)

  }
  //@TODO
  test("parseRawData") {
    // our inputs
    val firstRecord = (2003, 1, 355.2)     //help: to acces 2003 of this tuple, you can do firstRecord._1
    val secondRecord = (2004, 1, 375.2)
    val list1 = List(firstRecord, secondRecord)

    // our output of our method "parseRawData"
    val co2RecordWithType = CO2Record(firstRecord._1, firstRecord._2, firstRecord._3)
    val co2RecordWithType2 = CO2Record(secondRecord._1, secondRecord._2, secondRecord._3)
    val output = List(Some(co2RecordWithType), Some(co2RecordWithType2))

    // we call our function here to test our input and output
    assert(ClimateService.parseRawData(list1) == output)
  }

  test("getMinMax") {
    val firstRecord = CO2Record(2003, 1, 355.2)
    val secondRecord = CO2Record(2004, 1, 375.2)
    val list1 = List(firstRecord, secondRecord)
    assert(ClimateService.getMinMax(list1) == (355.2, 375.2))
  }

  test("getMinMaxByYear") {
    val firstRecord = CO2Record(2004, 1, 355.2)
    val secondRecord = CO2Record(2004, 1, 375.2)
    val list1 = List(firstRecord, secondRecord)
    assert(ClimateService.getMinMaxByYear(list1,firstRecord.year) == (355.2, 375.2))
  }

  //@TODO
  test("filterDecemberData") {
    val list1 = List(
      Some(CO2Record(1959, 7, 316.66)),
      Some(CO2Record(1959, 8, 314.8)),
      Some(CO2Record(1959, 9, 313.3)),
      Some(CO2Record(1959, 10, 313.32)),
      Some(CO2Record(1959, 11, 314.53)),
      Some(CO2Record(1959, 12, 315.72))
    )

    val filteredList = ClimateService.filterDecemberData(list1)

    assert(filteredList == List(
      CO2Record(1959, 7, 316.66),
      CO2Record(1959, 8, 314.8),
      CO2Record(1959, 9, 313.3),
      CO2Record(1959, 10, 313.32),
      CO2Record(1959, 11, 314.53)
    ))
  }

}
