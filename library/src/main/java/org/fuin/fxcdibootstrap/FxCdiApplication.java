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

import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Defines the basic application lifecycle.
 * 
 * @author Christoph Giesche, Michael Schnell
 */
public abstract class FxCdiApplication {

	/**
	 * This method gets called, before application is started. The CDI container is
	 * already running when this method is called.
	 */
	public void init() {
		// May be overwritten by implementor
	}

	/**
	 * This method gets called, when application is started (after the
	 * {@link #init()} method).
	 *
	 * @param primaryStage the primary stage for this application, onto which the
	 *                     application scene can be set. Applications may create
	 *                     other stages, if needed, but they will not be
	 * @param parameters   Set of parameters for the application. This includes
	 *                     arguments passed on the command line.
	 */
	public abstract void start(final Stage primaryStage, final Application.Parameters parameters) throws Exception;

	/**
	 * This method gets called, when the application is about being stopped. The CDI
	 * container is still running when this method is called (it will be shutdown
	 * right after).
	 */
	public void stop() {
		// May be overwritten by implementor
	}

}
