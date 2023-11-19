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

import java.util.ResourceBundle;

import org.fuin.fxcdibootstrap.Bundle;

import jakarta.inject.Inject;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

/**
 * Example controller with CDI injected bundle.
 */
public class FxCdiExampleContoller {

    @FXML
    private Label labelOne;

    @FXML
    private Button buttonOne;
    
    @Inject
	@Bundle("fxml/application")
    private ResourceBundle bundle;
    
    @FXML
    public void onButtonOneClick(ActionEvent event) {
    	final String text = bundle.getString("button-one-clicked");
    	buttonOne.setText(text);
    }
    
}
