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

import java.util.ResourceBundle;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.Dependent;
import jakarta.enterprise.inject.Produces;
import jakarta.enterprise.inject.spi.InjectionPoint;

/**
 * Creates Resource bundles that can be injected via CDI.
 * 
 * Here is an example on how to use it in the controller:
 * <pre>
 * public class Controller {
 *     
 *   @Inject
 *   @Bundle("resourceBundles/LoginDialog")
 *   private ResourceBundle rb;
 *   
 *}   
 * </pre>
 * 
 * @author Christoph Giesche, Michael Schnell
 */
@ApplicationScoped
public class ResourceBundleProducer {

	@Produces
	@Dependent
	@Bundle("")
	public ResourceBundle produce(final InjectionPoint injectionPoint) {
		final String bundleName = injectionPoint.getAnnotated().getAnnotation(Bundle.class).value();
		return ResourceBundle.getBundle(bundleName);
	}

}
