package mars_rover.value_objects.direction;

public record West() implements Direction{
    @Override
    public Direction turnLeft() {
        return new South();
    }

    @Override
    public Direction turnRight() {
        return new North();
    }
}
