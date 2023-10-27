package mars_rover.entities;

import mars_rover.MovingSystem;
import mars_rover.value_objects.direction.East;
import mars_rover.value_objects.direction.North;
import mars_rover.value_objects.direction.South;
import mars_rover.value_objects.direction.West;

public class MarsMovingSystem implements MovingSystem {

  MarsPositioningSystem marsPositioningSystem;

  public MarsMovingSystem(MarsPositioningSystem marsPositioningSystem) {
    this.marsPositioningSystem = marsPositioningSystem;
  }

  @Override
  public void turnLeft() {
    direction = direction.turnLeft();
  }

  @Override
  public void turnRight() {
    direction = direction.turnRight();
  }

  @Override
  public void moveForward() {
    if (direction instanceof North) {
      position = position.incrementY();
    } else if (direction instanceof West) {
      position = position.decrementX();
    } else if (direction instanceof South) {
      position = position.decrementY();
    } else if (direction instanceof East) {
      position = position.incrementX();
    }
  }

  @Override
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

  //
  //  public MovingControllerImpl followThis(List<CommandEnum> commandEnums) { //TODO Rename?
  //    for (CommandEnum commandEnum : commandEnums) {
  //      executeCommand(commandEnum);
  //    }
  //    return new MovingControllerImpl(this.position, this.direction);
  //  }

}
