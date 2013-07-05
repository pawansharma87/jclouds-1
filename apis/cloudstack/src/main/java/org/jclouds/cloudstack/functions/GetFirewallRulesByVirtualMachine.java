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
package org.jclouds.cloudstack.functions;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.Set;

import javax.inject.Inject;
import javax.inject.Singleton;

import org.jclouds.cloudstack.CloudStackClient;
import org.jclouds.cloudstack.domain.FirewallRule;
import org.jclouds.cloudstack.options.ListFirewallRulesOptions;

import com.google.common.cache.CacheLoader;
import com.google.common.collect.ImmutableSet;

@Singleton
public class GetFirewallRulesByVirtualMachine extends CacheLoader<String, Set<FirewallRule>> {
   private final CloudStackClient client;

   @Inject
   public GetFirewallRulesByVirtualMachine(CloudStackClient client) {
      this.client = checkNotNull(client, "client");
   }

   /**
    * @throws org.jclouds.rest.ResourceNotFoundException
    *          when there is no ip forwarding rule available for the VM
    */
   @Override
   public Set<FirewallRule> load(String input) {
      String publicIPId = client.getVirtualMachineClient().getVirtualMachine(input).getPublicIPId();
      Set<FirewallRule> rules = client.getFirewallClient()
         .listFirewallRules(ListFirewallRulesOptions.Builder.ipAddressId(publicIPId));
      return rules != null ? rules : ImmutableSet.<FirewallRule>of();
   }
}
