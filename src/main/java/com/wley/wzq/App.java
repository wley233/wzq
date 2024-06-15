package com.wley.wzq;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.entity.Entity;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;

import java.util.Map;

import static com.almasb.fxgl.dsl.FXGL.*;
import static com.wley.wzq.Constant.BOARD_SIZE;
import static com.wley.wzq.Constant.CELL_SIZE;

public class App extends GameApplication {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    protected void initSettings(GameSettings settings) {
        System.out.println("initSettings");
        settings.setTitle("wzq");
        settings.setVersion("0.1");
        settings.setWidth(BOARD_SIZE * CELL_SIZE + 100);
        settings.setHeight(BOARD_SIZE * CELL_SIZE);
        settings.setAppIcon("icon.png");
    }

    @Override
    protected void initGame() {
        System.out.println("initGame");
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                Rectangle rectangle = new Rectangle(CELL_SIZE, CELL_SIZE, Color.WHITE);
                Entity entity = entityBuilder()
                        .at(i * CELL_SIZE, j * CELL_SIZE)
                        .view(rectangle)
                        .with(new CellComponent(i,j))
                        .buildAndAttach();
                rectangle.setOnMouseClicked(e -> {
                    Circle circle = new Circle();
                    circle.setRadius((double) CELL_SIZE / 2 - 5);
                    circle.setCenterX(entity.getCenter().getX() + (double) CELL_SIZE / 2);
                    circle.setCenterY(entity.getCenter().getY() + (double) CELL_SIZE / 2);

                    CellComponent cellComponent = entity.getComponent(CellComponent.class);
                    int[][] arr = getWorldProperties().getValue("arr");

                    Integer playColor = getWorldProperties().getInt("currentPlayer");
                    if (playColor == 1) {
                        circle.setFill(Color.BLACK);
                        getWorldProperties().setValue("currentPlayer", 2);
                        arr[cellComponent.getX()][cellComponent.getY()] = 1;
                    } else if (playColor == 2) {
                        circle.setFill(Color.WHITE);
                        //圆形加上边框
                        circle.setStroke(Color.BLACK);
                        getWorldProperties().setValue("currentPlayer", 1);
                        arr[cellComponent.getX()][cellComponent.getY()] = 2;
                    }
                    getGameScene().addUINode(circle);

                    if (WinChecker.isLastMoveWinning(arr, cellComponent.getX(), cellComponent.getY(), playColor)) {
                        System.out.println("winner is " + playColor);
                        String color = playColor.equals(1) ? "黑" : "白";
                        Alert alert = new Alert(Alert.AlertType.INFORMATION, color + "子获胜！");
                        alert.showAndWait();
                        //退出游戏
                        Platform.exit();
                    }
                });
            }
        }


    }

    @Override
    protected void initUI() {
        System.out.println("initUI");
        for (int i = 0; i < BOARD_SIZE; i++) {
            Line line = new Line((double) CELL_SIZE / 2, (double) CELL_SIZE / 2 + i * CELL_SIZE,
                    BOARD_SIZE * CELL_SIZE - (double) CELL_SIZE / 2, (double) CELL_SIZE / 2 + i * CELL_SIZE);
            getGameScene().addUINode(line);
        }
        for (int i = 0; i < BOARD_SIZE; i++) {
            Line line = new Line((double) CELL_SIZE / 2 + i * CELL_SIZE, (double) CELL_SIZE / 2,
                    (double) CELL_SIZE / 2 + i * CELL_SIZE, BOARD_SIZE * CELL_SIZE - (double) CELL_SIZE / 2);
            getGameScene().addUINode(line);
        }

    }

    @Override
    protected void initInput() {
        System.out.println("initInput");
        //鼠标点击

    }

    @Override
    protected void initGameVars(Map<String, Object> vars) {
        vars.put("currentPlayer", 1);
        vars.put("arr", new int[BOARD_SIZE][BOARD_SIZE]);
    }
}