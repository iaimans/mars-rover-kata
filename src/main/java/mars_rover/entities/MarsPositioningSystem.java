package mars_rover.entities;

import mars_rover.PositioningSystem;
import mars_rover.value_objects.Position;
import mars_rover.value_objects.direction.Direction;

public class MarsPositioningSystem implements PositioningSystem {

  private final Position position;

  private final Direction direction;

  public MarsPositioningSystem(Position position, Direction direction) {
    this.position = position;
    this.direction = direction;
  }

  @Override
  public Position getPosition() {
    return position;
  }

  @Override
  public Direction getDirection() {
    return this.direction;
  }
}
