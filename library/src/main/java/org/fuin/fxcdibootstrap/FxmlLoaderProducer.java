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
package org.fuin.fxcdibootstrap;

import java.nio.charset.StandardCharsets;
import java.util.ResourceBundle;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.Dependent;
import jakarta.enterprise.inject.Produces;
import jakarta.enterprise.inject.spi.CDI;
import jakarta.enterprise.inject.spi.InjectionPoint;
import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;

/**
 * Creates CDI instances of FXML loaders. This allows injecting the FXML loader via CDI.<br>
 * <br>
 * Here is an example on how to use it in the start method:
 * <pre>
 * public class Main extends FxCdiApplication {
 * 
 *   @Inject
 *   @Bundle("bundles/Application")
 *   private final FXMLLoader fxmlLoader;
 *   
 *   public void start(final Stage stage, final Application.Parameters parameters) throws Exception {
 *      final Parent mainWindow = fxmlLoader.load(getClass().getResourceAsStream("application.fxml"));
 *      final Scene scene = new Scene(mainWindow);
 *      stage.setScene(scene);
 *      stage.show();
 *   }
 *   
 *}
 * </pre>
 * 
 * @author Christoph Giesche, Michael Schnell
 */
@ApplicationScoped
public final class FxmlLoaderProducer {

	@Produces
	@Dependent
	public FXMLLoader produce(final InjectionPoint injectionPoint) {
		final Bundle annotation = injectionPoint.getAnnotated().getAnnotation(Bundle.class);

		final ResourceBundle resourceBundle;
		if (annotation != null) {
			resourceBundle = ResourceBundle.getBundle(annotation.value());
		} else {
			resourceBundle = null;
		}

		return new FXMLLoader(null, resourceBundle, new JavaFXBuilderFactory(), controllerClass -> {
			final Object controller = CDI.current().select(controllerClass).get();
			if (controller == null) {
				throw new RuntimeException("Failed to look up controller of type " + controllerClass.getName() + ".");
			}
			return controller;
		}, StandardCharsets.UTF_8);
	}

}
