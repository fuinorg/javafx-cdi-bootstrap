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

import static org.testfx.assertions.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit5.ApplicationExtension;

import javafx.scene.control.Button;
import javafx.scene.control.Label;

/**
 * Tests if the application can be started and if CDI beans are injected
 * (implicitly).
 */
@ExtendWith(ApplicationExtension.class)
public class FxCdiExampleAppTest {

	@BeforeEach
	public void setup() throws Exception {
		FxToolkit.registerPrimaryStage();
		FxToolkit.setupApplication(FxCdiApplicationLoader.class);
	}

	@Test
	public void testClickOnButton(final FxRobot robot) {

		// GIVEN
		assertThat(robot.lookup("#labelOne").queryAs(Label.class)).isVisible();
		assertThat(robot.lookup("#labelOne").queryAs(Label.class)).hasText("Label");
		assertThat(robot.lookup("#buttonOne").queryAs(Button.class)).isVisible();
		assertThat(robot.lookup("#buttonOne").queryAs(Button.class)).hasText("Click me!");

		// WHEN
		robot.clickOn("#buttonOne");

		// THEN
		assertThat(robot.lookup("#buttonOne").queryAs(Button.class)).hasText("Clicked");

	}

}
