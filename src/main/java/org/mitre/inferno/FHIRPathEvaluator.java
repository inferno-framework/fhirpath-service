package org.mitre.inferno;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import org.hl7.fhir.exceptions.FHIRException;
import org.hl7.fhir.r4.context.SimpleWorkerContext;
import org.hl7.fhir.r4.fhirpath.FHIRPathEngine;
import org.hl7.fhir.r4.model.Base;
import org.hl7.fhir.utilities.npm.FilesystemPackageCacheManager;

public class FHIRPathEvaluator extends FHIRPathEngine {
  /**
   * Initialize with FHIR R4 Core definitions.
   */
  public FHIRPathEvaluator() throws IOException {
    super(SimpleWorkerContext.fromPackage(
          new FilesystemPackageCacheManager.Builder().build()
              .loadPackage("hl7.fhir.r4.core#4.0.1", null)
    )); 
  }

  @Override
  public String evaluateToString(Base base, String path) {
    List<Base> result = this.evaluate(base, path);
    return '['
        + result.stream().map(this::baseToJson).collect(Collectors.joining(","))
        + ']';
  }

  private String baseToJson(Base item) {
    try {
      String repr = new JsonParser().composeBase(item);
      return String.format("{\"type\":\"%s\",\"element\":%s}", item.fhirType(), repr);
    } catch (IOException e) {
      throw new FHIRException("Failed to convert base to JSON", e);
    }
  }
}
