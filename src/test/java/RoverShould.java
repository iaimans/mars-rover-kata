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

public class RoverShould {

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

  private static Stream<Arguments> parametersPositionsToMoveForwardTwiceTest() {
    return Stream.of(
        Arguments.of(new North(), new Position(0, 2)),
        Arguments.of(new South(), new Position(0, -2)),
        Arguments.of(new East(), new Position(2, 0)),
        Arguments.of(new West(), new Position(-2, 0))
    );
  }

  @ParameterizedTest(name = "in {0}")
  @MethodSource("parametersPositionsToMoveForwardTwiceTest")
  void move_forward_twice(Direction direction, Position expected) {
    Rover rover = new Rover(initialPosition, direction);
    rover.moveForward();
    rover.moveForward();

    assertThat(rover).hasPosition(expected);
  }

  private static Stream<Arguments> parametersPositionsToMoveBackwardTwiceTest() {
    return Stream.of(
        Arguments.of(new North(), new Position(0, -2)),
        Arguments.of(new South(), new Position(0, 2)),
        Arguments.of(new East(), new Position(-2, 0)),
        Arguments.of(new West(), new Position(2, 0))
    );
  }

  @ParameterizedTest(name = "in {0}")
  @MethodSource("parametersPositionsToMoveBackwardTwiceTest")
  void move_backward_twice(Direction direction, Position expected) {
    Rover rover = new Rover(initialPosition, direction);
    rover.moveBackward();
    rover.moveBackward();

    assertThat(rover).hasPosition(expected);
  }

  @Test
  void follow_a_series_of_commands() {
    List<CommandEnum> commandEnums = List.of(FORWARD, TURN_LEFT, BACKWARD, TURN_RIGHT, BACKWARD, TURN_RIGHT, TURN_RIGHT);

    Rover roverAfterCommands = rover.followThis(commandEnums);

    assertThat(roverAfterCommands).hasDirection(new South());
    assertThat(roverAfterCommands).hasPosition(new Position(1, 0));
  }
}
