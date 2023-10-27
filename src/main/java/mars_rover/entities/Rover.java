package mars_rover.entities;

import java.util.List;

import mars_rover.MovingSystem;
import mars_rover.PositioningSystem;
import mars_rover.value_objects.CommandEnum;
import mars_rover.value_objects.Position;
import mars_rover.value_objects.direction.Direction;

public class Rover {

  PositioningSystem positioningSystem;

  MovingSystem movingSystem;

  public Rover(PositioningSystem positioningSystem, MovingSystem movingSystem) {
    this.positioningSystem = positioningSystem;
    this.movingSystem = movingSystem;
  }

  public Position getPosition() {
    return positioningSystem.getPosition();
  }

  public Direction getDirection() {
    return positioningSystem.getDirection();
  }

  public void turnLeft() {
    movingSystem.turnLeft();
  }

  public void turnRight() {
    movingSystem.turnRight();
  }

  public void moveForward() {
    movingSystem.moveForward();
  }

  public void moveBackward() {
    movingSystem.moveBackward();
  }

  public Rover executeCommands(List<CommandEnum> commandEnums) {
    for (CommandEnum commandEnum : commandEnums) {
      executeCommand(commandEnum);
    }
    return null;
    //    return new Rover(this.position, this.direction);
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
