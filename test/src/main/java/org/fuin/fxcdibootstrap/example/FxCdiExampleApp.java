/**
 * Copyright 2023 by fuin.org.
 * 
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.fuin.fxcdibootstrap.example;

import org.fuin.fxcdibootstrap.Bundle;
import org.fuin.fxcdibootstrap.FxCdiApplication;
import org.fuin.fxcdibootstrap.FxCdiApplicationLoader;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

@ApplicationScoped
public class FxCdiExampleApp extends FxCdiApplication {

	@Inject
	@Bundle("fxml/application")
	private FXMLLoader fxmlLoader;

	@Override
	public void start(final Stage stage, final Application.Parameters parameters) throws Exception {
		final Parent mainWindow = fxmlLoader.load(getClass().getResourceAsStream("/fxml/application.fxml"));
		final Scene scene = new Scene(mainWindow);
		stage.setScene(scene);
		stage.show();
	}

	public static void main(String[] args) {
		FxCdiApplicationLoader.launch(FxCdiApplicationLoader.class, args);
	}
	
}
