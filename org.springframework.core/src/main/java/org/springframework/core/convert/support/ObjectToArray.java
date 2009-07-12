/*
 * Copyright 2004-2009 the original author or authors.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.core.convert.support;

import java.lang.reflect.Array;

import org.springframework.core.convert.ConversionFailedException;
import org.springframework.core.convert.TypeDescriptor;

/**
 * Converts an object to a single-element array.
 * @author Keith Donald
 * @since 3.0
 */
@SuppressWarnings("unchecked")
class ObjectToArray implements ConversionExecutor {

	private TypeDescriptor targetArrayType;

	private ConversionExecutor elementConverter;
	
	public ObjectToArray(TypeDescriptor sourceObjectType, TypeDescriptor targetArrayType,
			GenericTypeConverter conversionService) {
		this.targetArrayType = targetArrayType;
		this.elementConverter = conversionService.getConversionExecutor(sourceObjectType.getType(), TypeDescriptor.valueOf(targetArrayType.getElementType()));
	}

	public Object execute(Object source) throws ConversionFailedException {
		Object array = Array.newInstance(targetArrayType.getType(), 1);
		Array.set(array, 0, elementConverter.execute(source));		
		return array;
	}

}