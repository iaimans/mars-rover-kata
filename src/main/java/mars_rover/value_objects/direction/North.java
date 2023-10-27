package mars_rover.value_objects.direction;

public record North() implements Direction{
    @Override
    public Direction turnLeft() {
        return new West();
    }

    @Override
    public Direction turnRight() {
        return new East();
    }
}
