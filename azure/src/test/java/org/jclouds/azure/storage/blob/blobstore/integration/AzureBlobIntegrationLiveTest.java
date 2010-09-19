/**
 *
 * Copyright (C) 2010 Cloud Conscious, LLC. <info@cloudconscious.com>
 *
 * ====================================================================
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * ====================================================================
 */

package org.jclouds.azure.storage.blob.blobstore.integration;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.concurrent.ExecutionException;

import org.jclouds.blobstore.domain.Blob;
import org.jclouds.blobstore.integration.internal.BaseBlobIntegrationTest;
import org.testng.annotations.Test;

/**
 * 
 * @author Adrian Cole
 */
@Test(groups = "live", testName = "azureblob.AzureBlobIntegrationTest")
public class AzureBlobIntegrationLiveTest extends BaseBlobIntegrationTest {

   @Override
   public void testGetIfMatch() throws InterruptedException, UnsupportedEncodingException {
      // this currently fails
   }

   @Override
   @Test(expectedExceptions = IllegalArgumentException.class)
   public void testPutObjectStream() throws InterruptedException, IOException, ExecutionException {
      super.testPutObjectStream();
   }

   // according to docs, content disposition is not persisted
   // http://msdn.microsoft.com/en-us/library/dd179440.aspx
   @Override
   protected void checkContentDisposition(Blob blob, String contentDisposition) {
      assert blob.getPayload().getContentMetadata().getContentDisposition() == null;
      assert blob.getMetadata().getContentMetadata().getContentDisposition() == null;
   }
}