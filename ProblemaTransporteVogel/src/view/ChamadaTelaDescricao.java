/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 *
 * @author Gabriel
 */
public class ChamadaTelaDescricao extends Application {

    @Override
    public void start(Stage primaryStage) {
        TelaDescricaoProblema panel = new TelaDescricaoProblema();
        panel.setVisible(true);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
