package mars_rover.value_objects.direction;

public record East() implements Direction {
    @Override
    public Direction turnLeft() {
        return new North();
    }

    @Override
    public Direction turnRight() {
        return new South();
    }
}
