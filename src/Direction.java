/**
 * Направления.
 * @author invis
 */
public class Direction {
    protected static final byte UP=0, DOWN=1, LEFT=3, RIGHT=2;
    /**
     * текущее направление
     */
    private byte NOW;
    /**
     * Конструктор, первоначально змея смотрит вверх.
     */
    public Direction(){
        NOW=UP;
    }
    /**
     * Поворачивает змею.
     * @param WHERE куда повернуть
     */
    public void turn(byte WHERE){
        if((NOW==UP && WHERE!=DOWN) ||
                (NOW==LEFT && WHERE!=RIGHT) ||
                (NOW==DOWN && WHERE!=UP) ||
                (NOW==RIGHT && WHERE!=LEFT))
            NOW=WHERE;
    }

    public byte getNOW(){
        return NOW;
    }
}