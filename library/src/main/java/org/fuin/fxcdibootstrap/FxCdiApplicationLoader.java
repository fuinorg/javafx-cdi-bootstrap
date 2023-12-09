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

import java.util.logging.Logger;

import jakarta.enterprise.inject.se.SeContainer;
import jakarta.enterprise.inject.se.SeContainerInitializer;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Initializes the CDI container and creates the application bean. Shuts down
 * the CDI container when the application is stopped. From the perspective of
 * JavaFX this is the FX application.
 * 
 * @author Christoph Giesche, Michael Schnell
 */
public final class FxCdiApplicationLoader extends Application {

	private Logger log = Logger.getLogger(getClass().getName());

	private FxCdiApplication fxWeldApplication;

	private SeContainer seContainer;

	@Override
	public void init() throws Exception {
		seContainer = SeContainerInitializer.newInstance().initialize();
		fxWeldApplication = seContainer.select(FxCdiApplication.class).get();
		log.info("Initializing application.");
		fxWeldApplication.init();
	}

	@Override
	public void start(final Stage stage) throws Exception {
		log.info("Starting application.");
		fxWeldApplication.start(stage, getParameters());
	}

	@Override
	public void stop() throws Exception {
		log.info("Stopping application.");
		fxWeldApplication.stop();
		seContainer.close();
	}

}
