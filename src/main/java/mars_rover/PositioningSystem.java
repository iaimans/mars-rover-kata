package mars_rover;

import mars_rover.value_objects.Position;
import mars_rover.value_objects.direction.Direction;

public interface PositioningSystem {

  Position getPosition();

  Direction getDirection();
}
