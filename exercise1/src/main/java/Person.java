/*
 * Copyright 2006-2007 the original author or authors.
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


import com.gigaspaces.annotation.pojo.SpaceId;
import com.gigaspaces.annotation.pojo.SpaceIndex;
import com.gigaspaces.annotation.pojo.SpaceRouting;

/**
 * A simple object used to work with the Space.
 */
public class Person {

    //FirstName (as a routing key), LastName (as a SpaceIndex), age and PhoneNumber
    private String FirstName;
    private String LastName;
    private Integer age;
    private String PhoneNumber;


    /**
     * Necessary Default constructor
     */
    public Person() {
    }



    /**
     * Constructs a new Person with the given id and info and info.
     */
     Person(String FirstName, String LastName, Integer age, String PhoneNumber) {
        this.FirstName = FirstName;
        this.LastName = LastName;
        this.age = age;
        this.PhoneNumber = PhoneNumber;
    }

    /**
     * The id of this Person. We will use this attribute to route the Person objects when they are
     * written to the space, defined in the Person.gs.xml file.
     */
    @SpaceId
    @SpaceRouting
    public String getFirstName() {
        return FirstName;
    }

    @SpaceIndex
    public String getLastName() {
        return LastName;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }


    public Integer getAge() {
        return age;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }


    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void setPhoneNumber(String phoneNumber) {
        PhoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "Person{" +
                "FirstName='" + FirstName + '\'' +
                ", LastName='" + LastName + '\'' +
                ", age=" + age +
                ", PhoneNumber='" + PhoneNumber + '\'' +
                '}';
    }
}
