/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jclouds.net.domain;

import static com.google.common.base.Preconditions.checkNotNull;

import com.google.common.annotations.Beta;

/**
 * @author Adrian Cole
 * 
 */
@Beta
public enum IpProtocol {

   TCP, UDP, ICMP, ALL, UNRECOGNIZED;

   public String value() {
      return this == ALL ? "-1" : name().toLowerCase();
   }

   @Override
   public String toString() {
      return value();
   }

   public static IpProtocol fromValue(String protocol) {
      try {
         if (protocol.equalsIgnoreCase("-1"))
            return ALL;
         return valueOf(checkNotNull(protocol, "protocol").toUpperCase());
      } catch (IllegalArgumentException e) {
         return UNRECOGNIZED;
      }
   }
}
