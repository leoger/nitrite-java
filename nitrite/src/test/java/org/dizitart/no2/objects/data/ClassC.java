/*
 *
 * Copyright 2018 Nitrite author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package org.dizitart.no2.objects.data;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@EqualsAndHashCode
@ToString
public class ClassC {
    @Getter @Setter private long id;
    @Getter @Setter private double digit;
    @Getter @Setter private ClassA parent;

    public static ClassC create(int seed) {
        ClassC classC = new ClassC();
        classC.id = seed * 5000;
        classC.digit = seed * 69.65;
        classC.parent = ClassA.create(seed);
        return classC;
    }
}
