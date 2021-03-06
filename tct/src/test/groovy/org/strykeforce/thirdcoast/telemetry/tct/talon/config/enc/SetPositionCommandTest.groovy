package org.strykeforce.thirdcoast.telemetry.tct.talon.config.enc

import org.strykeforce.thirdcoast.telemetry.tct.Command
import org.strykeforce.thirdcoast.telemetry.tct.talon.config.AbstractTalonConfigCommandTest

class SetPositionCommandTest extends AbstractTalonConfigCommandTest {

    Command command

    @Override
    void setup() {
        command = new SetPositionCommand(reader, talonSet)
    }


    def "nothing input"() {
        when:
        command.perform()

        then:
        1 * reader.readLine(_) >> ""
        0 * talon._
    }

    def "handles input"() {
        when:
        command.perform()

        then:
        2 * reader.readLine(_) >>> ["ABC", "27.67"]

        1 * writer.println("please enter a number") // ABC

        1 * talon.setPosition(27.67) // 27.67
        1 * talon.getDescription()
        0 * talon._
    }
}
