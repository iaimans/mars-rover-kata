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
// GPS gps;
// Controller controller;
  //TODO En vez de posicion crear un objecto GPS que es quien se encarga
  //TODO El Rover deberia tener un motor/controlador que se encargue de moverlo
  private Position position;

  private Direction direction;

  public Rover(Position position, Direction direction) {
    this.position = position;
    this.direction = direction;
  }

  public Position getPosition() {
    return position;
  } //TODO Deberia llamar a GPS y que me devuelva la posicion

  public Direction getDirection() {
    return this.direction;
  } //TODO Deberia llamar a GPS y que me devuelva la direccion

  public void turnLeft() {
    direction = direction.turnLeft();
  } //TODO Quien realiza la accion?

  public void turnRight() {
    direction = direction.turnRight();
  } //TODO Quien realiza la accion?

  // TODO: Use State Pattern with Move
  public void moveForward() { //TODO Quien realiza la accion? Un "fake" de motor/controlador que se encarga de mover el rover??
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

  public void moveBackward() { //TODO Quien realiza la accion?
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

  public Rover followThis(List<CommandEnum> commandEnums) { //TODO Rename?
    for (CommandEnum commandEnum : commandEnums) {
      executeCommand(commandEnum);
    }
    return new Rover(this.position, this.direction);
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
