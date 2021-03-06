<?php
/**
 *  Copyright 2011 Wordnik, Inc.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

/**
 *
 * NOTE: This class is auto generated by the swagger code generator program. Do not edit the class manually.
 */
class SchemaApi {

  function __construct($apiClient) {
    $this->apiClient = $apiClient;
  }

  /**
   * find
   * Get model schemata for data objects returned by this API.
   * 
   * @param string $model Optional model filter. If omitted, will return all models. (optional)
   * @return object
   */

   public function find($model=null) {

      //parse inputs
      $resourcePath = "/schema";
      $resourcePath = str_replace("{format}", "json", $resourcePath);
      $method = "GET";
      $queryParams = array();
      $headerParams = array();
      $headerParams['Accept'] = 'application/json';
      $headerParams['Content-Type'] = 'application/json';

      if($model != null) {
        $queryParams['model'] = $this->apiClient->toQueryValue($model);
      }
      // Generate form params
      if (! isset($body)) {
        $body = array();
      }
      if (empty($body)) {
        $body = null;
      }

      // Make the API Call
      $response = $this->apiClient->callAPI($resourcePath, $method,
                                            $queryParams, $body,
                                            $headerParams);


      if(! $response){
          return null;
      }

      $responseObject = $this->apiClient->deserialize($response,
                                                      'object');
      return $responseObject;

      }
  /**
   * websocketHelp
   * Returns help text &amp; subject list for websocket usage.
   * 
   * @return object
   */

   public function websocketHelp() {

      //parse inputs
      $resourcePath = "/schema/websocketHelp";
      $resourcePath = str_replace("{format}", "json", $resourcePath);
      $method = "GET";
      $queryParams = array();
      $headerParams = array();
      $headerParams['Accept'] = 'application/json';
      $headerParams['Content-Type'] = 'application/json';

      // Generate form params
      if (! isset($body)) {
        $body = array();
      }
      if (empty($body)) {
        $body = null;
      }

      // Make the API Call
      $response = $this->apiClient->callAPI($resourcePath, $method,
                                            $queryParams, $body,
                                            $headerParams);


      if(! $response){
          return null;
      }

      $responseObject = $this->apiClient->deserialize($response,
                                                      'object');
      return $responseObject;

      }
  

}

