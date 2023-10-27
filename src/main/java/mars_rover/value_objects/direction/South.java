package mars_rover.value_objects.direction;

public record South() implements Direction{
    @Override
    public Direction turnLeft() {
        return new East();
    }

    @Override
    public Direction turnRight() {
        return new West();
    }
}
