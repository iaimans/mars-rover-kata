import mars_rover.value_objects.Position;

public record World(int length, int width) {

  public Position nextLengthPosition(Position position) {
    if (hasReachedWorldsLength(position)) {
      return new Position(0, position.y());
    }

    return new Position(position.x() + 1, position.y());
  }

  private boolean hasReachedWorldsLength(Position position) {
    return length == position.x();
  }

  public Position nextPreviousPosition(Position position) {
    return new Position(10, 5);
  }
}
