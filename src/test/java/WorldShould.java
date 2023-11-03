import static org.assertj.core.api.Assertions.assertThat;

import java.util.stream.Stream;

import mars_rover.value_objects.Position;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class WorldShould {

  private static Stream<Arguments> limitLengthPositions() {
    return Stream.of(
        Arguments.of(new Position(10, 5), new Position(0,5)),
        Arguments.of(new Position(10, 8), new Position(0,8)),
        Arguments.of(new Position(10, 0), new Position(0,0))
    );
  }

  @ParameterizedTest(name = "given a limit position {0} next length position should be {1}")
  @MethodSource("limitLengthPositions")
  void calculate_next_length_given_a_limit_position(Position actualPosition, Position expectedPosition) {
    World world = new World(10, 20);

    Position nextLengthPosition = world.nextLengthPosition(actualPosition);

    assertThat(nextLengthPosition).isEqualTo(expectedPosition);
  }


  private static Stream<Arguments> notLimitLengthPositions() {
    return Stream.of(
        Arguments.of(new Position(4, 5), new Position(5,5)),
        Arguments.of(new Position(6, 8), new Position(7,8)),
        Arguments.of(new Position(0, 0), new Position(1,0))
    );
  }

  @ParameterizedTest(name = "given a not limit position {0} next length position should be {1}")
  @MethodSource("notLimitLengthPositions")
  void calculate_next_length_given_a_not_limit_position(Position actualPosition, Position expectedPosition) {
    World world = new World(10, 20);

    Position nextLengthPosition = world.nextLengthPosition(actualPosition);

    assertThat(nextLengthPosition).isEqualTo(expectedPosition);
  }

}
