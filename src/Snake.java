import java.awt.*;
import java.util.Random;
import java.util.ArrayList;
/**
 *
 * @author invis
 */
public class Snake {
    /**
     * Класс описывает поведение каждого отдельного квадрата
     * хвоста змеи.
     */
    private class Square{
        private int NOW_X , NOW_Y;

        private Square(){}

        private void paint(int x, int y, int part, Graphics g){
            g.setColor(Color.green); // хвост зелёный
            g.fillRect(x, y, part, part); //квадратный
            NOW_X = x;
            NOW_Y = y;
        }

        private void erase(int part, Graphics g){
            g.setColor(Color.white);
            g.fillRect(NOW_X, NOW_Y, part, part);
        }
    }
    /**
     * Динамический массив содержит все отдельные части хвоста змеи.
     */
    ArrayList<Square> al = new ArrayList(4);
    /**
     * Ширина и высота одной части змеи
     */
    private final int part = 6;
    /**
     * Объект для рисования "еды" в случайном месте.
     */
    Random random = new Random();
    /**
     * Текущие координаты.
     */
    private int x=216,y=216;
    /**
     * Координата Х "еды".
     */
    private int eatX;
    /**
     * Координата Y "еды".
     */
    private int eatY;
    /**
     * С помощью этого флага удлиняем змейку.
     */
    private boolean delete = true;
    /**
     * Создана ли уже "еда" для змейки.
     */
    private boolean alreadyCreate = false;
    /**
     * Жива ли змея.
     */
    protected boolean alive = true;
    /**
     * Конструктор создаёт один экземпляр квадратика.
     */
    Snake(){ al.add(new Square());}

    protected void paint(Graphics g){
        if(delete){
            al.get(0).erase(part, g);
            al.remove(0);
        }else{delete = true;}
        al.add(new Square());
        al.get(al.size()-1).paint(x, y, part, g);
    }

    public int gdeY(){
        return al.get(al.size()-1).NOW_Y;
    }

    public int gdeX(){
        return al.get(al.size()-1).NOW_X;
    }

    protected void step(Direction direction){
        switch(direction.getNOW()){
            case Direction.UP:
                y -= 6;break;
            case Direction.DOWN:
                y += 6;break;
            case Direction.LEFT:
                x -= 6;break;
            case Direction.RIGHT:
                x += 6;break;
        }
        //не врезалась ли змейка в стену
        if(x > 471 || x < 4 || y > 471|| y < 4){
            alive=false;
        }
        //не съела ли змейка себя
        for (int i = 0; i < al.size()-1; i++) {
            if (x == al.get(i).NOW_X &&
                    y == al.get(i).NOW_Y){
                alive=false;
            }
        }
    }
    /**
     * Рисует "еду" для змейки в случайном месте.
     * @param g
     */
    protected void food(Graphics g){
        if(eatX == x && eatY == y){
            alreadyCreate = false;
        }
        if(!alreadyCreate){
            eatX = (random.nextInt(77)+1)*6;
            eatY = (random.nextInt(77)+1)*6;
            g.setColor(Color.BLUE);
            g.fillRect(eatX, eatY, part, part);
            //добавить квадратик к змее.
            delete = false;
            alreadyCreate = true;
        }
    }
}