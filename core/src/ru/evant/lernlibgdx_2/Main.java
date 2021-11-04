package ru.evant.lernlibgdx_2;

//	Самообразование #2
//	Java Game Development with LibGDX, 2nd Edition

/*
	Вы помогаете персонажу игрока, черепахе, плавать по океанскому дну в поисках морской звезды.
 */

/*
•	Игрок будет управлять черепахой, цель которой - собрать одну морскую звезду.
•	Движение контролируется клавишами со стрелками. Клавиша со стрелкой вверх перемещает черепаху к вверху экрана.
		Клавиша со стрелкой вправо перемещает черепаху к правой стороне экрана, и так далее.
		Одновременно можно нажать несколько клавиш со стрелками, чтобы переместить черепаха по диагонали.
		Скорость передвижения постоянная.
•	Черепаха собирает морские звезды, входя в контакт с ними (когда их картинки перекрываются).
		Когда это происходит, морская звезда исчезает, и появляется сообщение «Вы выиграли».
•	Графика, необходимая для этой игры, включает изображения черепахи, морской звезды, воды и сообщение,
		содержащее текст «Вы выиграли».
 */

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class Main extends Game {
    private SpriteBatch batch;
    private Texture turtleTexture;
    private float turtleX;
    private float turtleY;
    private Rectangle turtleRectangle;
    private Texture starfishTexture;
    private float starfishX;
    private float starfishY;
    private Rectangle starfishRectangle;
    private Texture oceanTexture;
    private Texture winMessageTexture;
    private boolean win;

    public void create() {
        batch = new SpriteBatch();
        turtleTexture = new Texture(Gdx.files.internal("turtle-1.png"));
        turtleX = 20;
        turtleY = 20;
        turtleRectangle = new Rectangle(turtleX, turtleY, turtleTexture.getWidth(), turtleTexture.getHeight());
        starfishTexture = new Texture(Gdx.files.internal("starfish.png"));
        starfishX = 380;
        starfishY = 380;
        starfishRectangle = new Rectangle(starfishX, starfishY, starfishTexture.getWidth(), starfishTexture.getHeight());
        oceanTexture = new Texture(Gdx.files.internal("water.jpg"));
        winMessageTexture = new Texture(Gdx.files.internal("you-win.jpg"));
        win = false;
    }

    public void render() {
        // проверить ввод данных пользователем
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) turtleX--;
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) turtleX++;
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) turtleY++;
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) turtleY--;

        // обновить местоположение прямоугольника черепахи
        turtleRectangle.setPosition(turtleX, turtleY);

        // проверить условие выигрыша: черепаха должна перекрывать морскую звезду
        if (turtleRectangle.overlaps(starfishRectangle)) win = true;

        // очистить экран
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // нарисовать графику
        batch.begin();
        batch.draw(oceanTexture, 0, 0);
        if (!win) batch.draw(starfishTexture, starfishX, starfishY);
        batch.draw(turtleTexture, turtleX, turtleY);
        if (win) batch.draw(winMessageTexture, 220, 180);
        batch.end();
    }

}
