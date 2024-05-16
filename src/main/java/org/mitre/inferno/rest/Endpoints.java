package org.mitre.inferno.rest;

import static spark.Spark.before;
import static spark.Spark.get;
import static spark.Spark.options;

import com.google.gson.Gson;
import java.util.HashMap;
import java.util.Map;
import org.mitre.inferno.FHIRPathEvaluator;
import spark.ResponseTransformer;

public class Endpoints {
  public static final ResponseTransformer TO_JSON = new Gson()::toJson;

  private static Endpoints endpoints = null;

  private final FHIRPathEvaluator pathEvaluator;

  private Endpoints(FHIRPathEvaluator evaluator) {
    this.pathEvaluator = evaluator;
    setHeaders();
    
    get("/version", (req, res) -> buildVersionResponse(), TO_JSON);

    if (pathEvaluator != null) {
      FHIRPathEndpoint.getInstance(pathEvaluator);
    }
  }

  /**
   * This adds permissive CORS headers to all requests.
   */
  private void setHeaders() {
    before((req, res) -> {
      res.header("Access-Control-Allow-Origin", "*");
      res.header("Access-Control-Allow-Methods", "GET, POST, PUT, OPTIONS");
      res.header("Access-Control-Allow-Headers", "*");
    });

    // This responds to OPTIONS requests, used by browsers to "preflight" check CORS
    // requests,
    // with a 200 OK response with no content and the CORS headers above
    options("*", (req, res) -> "");
  }

  /**
   * Get the existing Endpoints or create one if it does not already exist.
   *
   * @param evaluator the FHIRPathEvaluator that should be used at the /fhirpath
   *                  endpoint.
   *                  Passing null will skip setting up the /fhirpath endpoint.
   * @return the singleton Endpoints
   */
  public static Endpoints getInstance(FHIRPathEvaluator evaluator) {
    if (endpoints == null) {
      endpoints = new Endpoints(evaluator);
    }
    return endpoints;
  }
  
  /**
   * Build a Map of the library versions used by this validator.
   *
   * @return a Map of library identifier -> version string
   */
  private static Map<String,String> buildVersionResponse() {
    // full package names used here only to make it more obvious what's going on
    // since the class names aren't distinct enough
    String hl7CoreVersion = org.hl7.fhir.utilities.VersionUtil.getVersion();
    String serviceVersion = org.mitre.inferno.Version.getVersion();

    Map<String, String> versions = new HashMap<>();
    versions.put("org.hl7.fhir.core", hl7CoreVersion);
    versions.put("inferno-framework/fhirpath-service", serviceVersion);
    return versions;
  }
}
