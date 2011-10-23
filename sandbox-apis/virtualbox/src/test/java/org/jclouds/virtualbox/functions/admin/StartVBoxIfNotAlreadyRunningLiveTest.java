/**
 * Licensed to jclouds, Inc. (jclouds) under one or more
 * contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  jclouds licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.jclouds.virtualbox.functions.admin;

import org.jclouds.compute.ComputeServiceContext;
import org.jclouds.domain.Credentials;
import org.jclouds.virtualbox.functions.admin.StartVBoxIfNotAlreadyRunning;
import org.testng.annotations.Test;
import org.virtualbox_4_1.SessionState;
import org.virtualbox_4_1.VirtualBoxManager;

import java.net.URI;

import static org.jclouds.virtualbox.experiment.TestUtils.computeServiceForLocalhostAndGuest;
import static org.testng.Assert.assertEquals;

@Test(groups = "live", singleThreaded = true, testName = "startVBoxIfNotAlreadyRunningLiveTest")
public class StartVBoxIfNotAlreadyRunningLiveTest {

   @Test
   public void testStartVbox() throws Exception {
      Credentials localhostCredentials = new Credentials("toor", "password");
      ComputeServiceContext localHostContext = computeServiceForLocalhostAndGuest("hostId", "localhost", "guestId", "localhost", localhostCredentials);

      URI endpointUri = URI.create("http://localhost:18083/");

      VirtualBoxManager manager = new StartVBoxIfNotAlreadyRunning(localHostContext, "hostId", localhostCredentials).apply(endpointUri);

      assertEquals(manager.getSessionObject().getState(), SessionState.Unlocked);
   }

}
