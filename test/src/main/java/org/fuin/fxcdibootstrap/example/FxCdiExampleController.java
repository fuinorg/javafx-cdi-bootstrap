/**
 * Copyright 2023 by fuin.org.
 * <p>
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.fuin.fxcdibootstrap.example;

import java.util.ResourceBundle;

import jakarta.enterprise.event.Event;
import jakarta.enterprise.event.Observes;
import org.fuin.fxcdibootstrap.Bundle;

import jakarta.inject.Inject;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

/**
 * Example controller with CDI injected bundle.
 */
public class FxCdiExampleController {

    @FXML
    private Label labelOne;

    @FXML
    private Button buttonOne;

    @Inject
    @Bundle("fxml/application")
    private ResourceBundle bundle; // CDI injected source bundle

    @Inject
    private Event<FooEvent> fooEvent;

    /**
     * Example handling a CDI event.
     *
     * @param event Event to handle.
     */
    public void onBarEvent(@Observes Event<BarEvent> event) {
        // Do something useful with event received
    }

    @FXML
    public void onButtonOneClick(ActionEvent event) {
        final String text = bundle.getString("button-one-clicked");
        buttonOne.setText(text);
        // Emit CDI event
        fooEvent.fire(new FooEvent("Hello!"));
    }

}
