package com.example.bridge

import spock.lang.Specification

class BridgeTest extends Specification {

    def "calling process(), side with more cars will be decremented"() {
        given:
        Bridge bridge = new Bridge(north, south)

        when:
        bridge.process()

        then:
        bridge.northboundCars == expectedNorth
        bridge.southboundCars == expectedSouth

        where:
        north | south | expectedNorth | expectedSouth
        5     | 4     | 4             | 4
        4     | 5     | 4             | 4
        6     | 4     | 5             | 4
        4     | 6     | 4             | 5
    }

    def "calling process(), when both side are same then 1 cars will be decremented"() {
        given:
        Bridge bridge = new Bridge(north, south)

        when:
        bridge.process()

        then:
        bridge.northboundCars + bridge.southboundCars == expectedTotal

        where:
        north | south | expectedTotal
        5     | 5     | 9
        4     | 4     | 7
    }

    def "calling process(), priority switches when both sides are equal"() {
        given:
        Bridge bridge = new Bridge(north, south)

        when:
        bridge.process()
        bridge.process() // second call to check priority switch

        then:
        bridge.northboundCars == expectedNorth
        bridge.southboundCars == expectedSouth

        where:
        north | south | expectedNorth | expectedSouth
        5     | 4     | 4             | 3
        4     | 5     | 3             | 4
        6     | 4     | 4             | 4
        4     | 6     | 4             | 4
    }

    def "calling process(), exception is thrown when number of cars on either side becomes zero"() {
        given:
        Bridge bridge = new Bridge(north, south)

        when:
        bridge.process()

        then:
        bridge.northboundCars + bridge.southboundCars == expectedTotal
        thrown SolutionComplete

        where:
        north | south | expectedTotal
        1     | 1     | 1
    }

    def "calling addCars(), adds 1 to north and south totals"() {
        given:
        Bridge bridge = new Bridge(north, south)

        when:
        bridge.addCars()

        then:
        bridge.northboundCars == expectedNorth
        bridge.southboundCars == expectedSouth

        where:
        north | south | expectedNorth | expectedSouth
        5     | 4     | 6             | 5
        4     | 5     | 5             | 6
        6     | 4     | 7             | 5
        4     | 6     | 5             | 7
    }

}
