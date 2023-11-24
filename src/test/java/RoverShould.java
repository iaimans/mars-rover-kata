import static mars_rover.value_objects.CommandEnum.BACKWARD;
import static mars_rover.value_objects.CommandEnum.FORWARD;
import static mars_rover.value_objects.CommandEnum.TURN_LEFT;
import static mars_rover.value_objects.CommandEnum.TURN_RIGHT;
import static roverAssertions.RoverAssert.assertThat;

import java.util.List;
import java.util.stream.Stream;

import mars_rover.value_objects.CommandEnum;
import mars_rover.value_objects.direction.Direction;
import mars_rover.value_objects.direction.East;
import mars_rover.value_objects.direction.North;
import mars_rover.value_objects.Position;
import mars_rover.entities.Rover;
import mars_rover.value_objects.direction.South;
import mars_rover.value_objects.direction.West;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;


class RoverShould {

  Position initialPosition;

  Direction initialDirection;

  Rover rover;

  @BeforeEach
  void setup() {
    initialPosition = new Position(0, 0);
    initialDirection = new North();
    rover = new Rover(initialPosition, initialDirection);
  }

  @Test
  void start_with_and_initial_position_facing_initial_direction() {
    assertThat(rover).hasPosition(initialPosition);
    assertThat(rover).hasDirection(initialDirection);
  }

  private static Stream<Arguments> parametersDirectionsToLeftTest() {
    return Stream.of(
        Arguments.of(new North(), new West()),
        Arguments.of(new South(), new East()),
        Arguments.of(new East(), new North()),
        Arguments.of(new West(), new South())
    );
  }

  @ParameterizedTest(name = "facing in {0}")
  @MethodSource("parametersDirectionsToLeftTest")
  void turn_left(Direction initialDirection, Direction expectedDirection) {
    Rover rover = new Rover(initialPosition, initialDirection);
    rover.turnLeft();
    assertThat(rover).hasDirection(expectedDirection);
  }

  private static Stream<Arguments> parametersDirectionsToRightTest() {
    return Stream.of(
        Arguments.of(new North(), new East()),
        Arguments.of(new South(), new West()),
        Arguments.of(new East(), new South()),
        Arguments.of(new West(), new North())
    );
  }

  @ParameterizedTest(name = "facing in {0}")
  @MethodSource("parametersDirectionsToRightTest")
  void turn_right(Direction initialDirection, Direction expectedDirection) {
    Rover rover = new Rover(initialPosition, initialDirection);
    rover.turnRight();
    assertThat(rover).hasDirection(expectedDirection);
  }

  private static Stream<Arguments> parametersPositionsToMoveForwardTest() {
    return Stream.of(
        Arguments.of(new North(), new Position(0, 1)),
        Arguments.of(new South(), new Position(0, -1)),
        Arguments.of(new East(), new Position(1, 0)),
        Arguments.of(new West(), new Position(-1, 0))
    );
  }

  @ParameterizedTest(name = "in {0}")
  @MethodSource("parametersPositionsToMoveForwardTest")
  void move_forward(Direction direction, Position expected) {
    Rover rover = new Rover(initialPosition, direction);
    rover.moveForward();

    assertThat(rover).hasPosition(expected);
  }


  private static Stream<Arguments> parametersCommands() {
    return Stream.of(
        Arguments.of(
            List.of(FORWARD, TURN_LEFT, BACKWARD, TURN_RIGHT, BACKWARD, TURN_RIGHT, TURN_RIGHT),
            new South(),
            new Position(1, 0)
        ),
        Arguments.of(
            List.of(FORWARD, TURN_LEFT, TURN_RIGHT, BACKWARD, TURN_RIGHT),
            new East(),
            new Position(0, 0)
        )
    );
  }

  @ParameterizedTest(name = "when executing commands: {0} the rover should have direction {1} and position {2}")
  @MethodSource("parametersCommands")
  void follow_a_series_of_commands(List<CommandEnum> commandEnums, Direction expectedDirection, Position expectedPosition) {
   rover.move(commandEnums);

    assertThat(rover)
        .hasDirection(expectedDirection)
        .hasPosition(expectedPosition);
  }
}
