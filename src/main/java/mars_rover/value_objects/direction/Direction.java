package mars_rover.value_objects.direction;

public sealed interface Direction permits East, North, South, West {
    Direction turnLeft();

    Direction turnRight();
}
