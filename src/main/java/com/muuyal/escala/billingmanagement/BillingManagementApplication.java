package com.muuyal.escala.billingmanagement;

import com.muuyal.escala.billingmanagement.dao.interfaces.ProjectDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.application.Application;

@SpringBootApplication
public class BillingManagementApplication extends Application {

	private ConfigurableApplicationContext springContext;
	private Parent root;

	@Autowired
	private ProjectDao projectDao;

	Stage window;

	@Override
	public void init() throws Exception {
		springContext = SpringApplication.run(BillingManagementApplication.class);
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/views/login.fxml"));
		fxmlLoader.setControllerFactory(springContext::getBean);
		root = fxmlLoader.load();
	}

	@Override
	public void start(Stage primaryStage) throws Exception {

		window = primaryStage;
		window.setTitle("Escala");
		Scene scene = new Scene(root, 800, 600);
		window.setScene(scene);
		window.show();
	}

	@Override
	public void stop() throws Exception {
		springContext.stop();
	}


	public static void main(String[] args) {

		launch(BillingManagementApplication.class, args);
	}

}
