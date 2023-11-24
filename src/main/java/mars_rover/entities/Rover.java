package mars_rover.entities;

import java.util.List;

import mars_rover.value_objects.CommandEnum;
import mars_rover.value_objects.Position;
import mars_rover.value_objects.direction.Direction;
import mars_rover.value_objects.direction.East;
import mars_rover.value_objects.direction.North;
import mars_rover.value_objects.direction.South;
import mars_rover.value_objects.direction.West;

public class Rover {
  private Position position;

  private Direction direction;

  public Rover(Position position, Direction direction) {
    this.position = position;
    this.direction = direction;
  }

  public Position getPosition() {
    return position;
  }

  public Direction getDirection() {
    return this.direction;
  }

  public void turnLeft() {
    direction = direction.turnLeft();
  }

  public void turnRight() {
    direction = direction.turnRight();
  }


  public void move(List<CommandEnum> commandEnums) {
    commandEnums.forEach(this::executeCommand);
  }

  public void moveForward() {
    if (direction instanceof North) {
      position = position.incrementY();
    } else if (direction instanceof South) {
      position = position.decrementY();
    } else if (direction instanceof West) {
      position = position.decrementX();
    } else if (direction instanceof East) {
      position = position.incrementX();
    }
  }

  public void moveBackward() {
    if (direction instanceof North) {
      position = position.decrementY();
    } else if (direction instanceof West) {
      position = position.incrementX();
    } else if (direction instanceof South) {
      position = position.incrementY();
    } else if (direction instanceof East) {
      position = position.decrementX();
    }
  }

  private void executeCommand(CommandEnum commandEnum) {
    switch (commandEnum) {
      case FORWARD -> this.moveForward();
      case BACKWARD -> this.moveBackward();
      case TURN_LEFT -> this.turnLeft();
      case TURN_RIGHT -> this.turnRight();
    }
  }
}
