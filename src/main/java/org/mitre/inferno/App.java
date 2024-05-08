package org.mitre.inferno;

import java.io.IOException;
import org.mitre.inferno.rest.Endpoints;
import org.mitre.inferno.utils.SparkUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import spark.Spark;

public class App {

  /**
   * Starting point for the FHIRPath Service.
   * @param args the application initialization arguments (unused)
   */
  public static void main(String[] args) {
    Logger logger = LoggerFactory.getLogger(App.class);
    logger.info("Starting Server...");
    SparkUtils.createServerWithRequestLog(logger);
    Spark.port(getPortNumber());
    try {
      Endpoints.getInstance(new FHIRPathEvaluator());
    } catch (IOException e) {
      logger.error("There was an error initializing the FHIRPath evaluator:", e);
      System.exit(1);
    }
  }

  private static int getPortNumber() {
    String port = System.getenv("FHIRPATH_PORT");
    if (port != null) {
      return Integer.parseInt(port);
    } else {
      return 6789;
    }
  }
}
